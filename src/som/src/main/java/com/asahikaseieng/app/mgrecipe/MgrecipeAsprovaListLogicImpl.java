/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe.RecipeResouceGroupForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe.RecipeResouceGroupForComboboxesDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeOperationGroupDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeOperationGroupDetailDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeResouceDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeResouceDetailDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeResouceGroupDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeResouceGroupDetailDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeAsprovaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeAsprovaListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.IgnoreCaseBeanUtils;

/**
 * 基本処方－Asprovaタブ ロジック実装クラス
 * @author tosco
 */
public class MgrecipeAsprovaListLogicImpl implements MgrecipeAsprovaListLogic {

	/** 設備グループ情報取得Dao */
	private RecipeResouceGroupForComboboxesDao recipeResouceGroupForComboboxesDao;

	/** 処方ASPROVA_Dao */
	private RecipeAsprovaListDao recipeAsprovaListDao;

	/** 設備グループDao */
	private MgrecipeResouceGroupDetailDao mgrecipeResouceGroupDetailDao;

	/** 設備Dao */
	private MgrecipeResouceDetailDao mgrecipeResouceDetailDao;

	/** 工程グループDao */
	private MgrecipeOperationGroupDetailDao mgrecipeOperationGroupDetailDao;

	/** 処方プロシージャDao */
	private RecipeProcedureListDao recipeProcedureListDao;

	/** 工程グループコード‐調合 */
	public static final String OPERATION_GROUP_CD_COMPOUND = "10";

	/** 工程グループコード‐移送 */
	public static final String OPERATION_GROUP_CD_TRANSFER = "20";

	/** 工程グループコード‐充填 */
	public static final String OPERATION_GROUP_CD_FILLING = "30";

	/** 工程グループコード‐包装 */
	public static final String OPERATION_GROUP_CD_PACKING = "40";

	/** 工程グループ名‐充填 */
	public static final String OPERATION_GROUP_NM_FILLING = "充填工程";

	/** 工程グループ名‐包装 */
	public static final String OPERATION_GROUP_NM_PACKING = "包装工程";

	/** 下段存在フラグ－存在しない */
	public static final String LOWER_LIST_NOT_EXIST = "0";

	/** 下段存在フラグ－存在する */
	public static final String LOWER_LIST_EXIST = "1";

	/**
	 * コンストラクタ.基本処方検索
	 */
	public MgrecipeAsprovaListLogicImpl() {
	}

	/**
	 * 用途が製造の場合の初期表示画面項目設定
	 * @param frm AsprovaタブForm
	 */
	public void makeInitDispProduction(final MgrecipeAsprovaListForm frm) {

		// 下段なし
		frm.setDispLowerListFlg(LOWER_LIST_NOT_EXIST);

		// 工程グループ名称取得
		setUpperOpeGrpNm(frm, OPERATION_GROUP_CD_COMPOUND);

		// 設備グループリスト取得
		List<RecipeResouceGroupForComboboxes> list = recipeResouceGroupForComboboxesDao
				.findByOperationGroupCd(OPERATION_GROUP_CD_COMPOUND);
		list.addAll(recipeResouceGroupForComboboxesDao
				.findByOperationGroupCd(OPERATION_GROUP_CD_TRANSFER));

		// コンボボックス設定
		if (list.isEmpty()) {
			// 空の設備グループコンボボックスの作成
			frm.setUpperResGrpCombo(makeEmptyComb());
		} else {
			// 設備グループコンボボックスの作成
			List<ComboBoxItems> comb = createRecipeResouceGroupCombobox(list);
			frm.setUpperResGrpCombo(comb);
		}

	}

	/**
	 * 用途が包装の場合の初期表示画面項目設定
	 * @param frm AsprovaタブForm
	 */
	public void makeInitDispPacking(final MgrecipeAsprovaListForm frm) {

		List<RecipeProcedureList> procList = recipeProcedureListDao
				.getSearchList(new BigDecimal(frm.getRecipeId()));

		boolean existFillingFlg = false;
		boolean existPackingFlg = false;

		if (!procList.isEmpty()) {
			for (RecipeProcedureList bean : procList) {
				// 工程グループが40
				if (bean.getOperationGroupCd().equals(
					OPERATION_GROUP_CD_FILLING)) {
					existFillingFlg = true;
				} else if (bean.getOperationGroupCd().equals(
					OPERATION_GROUP_CD_PACKING)) {
					existPackingFlg = true;
				}
			}
		}
		if (existFillingFlg && existPackingFlg) {
			// 下段あり
			frm.setDispLowerListFlg(LOWER_LIST_EXIST);

			// 工程グループ設定
			frm.setUpperOpeGrpCd(OPERATION_GROUP_CD_FILLING);
			frm.setUpperOpeGrpNm(OPERATION_GROUP_NM_FILLING);
			frm.setLowerOpeGrpCd(OPERATION_GROUP_CD_PACKING);
			frm.setLowerOpeGrpNm(OPERATION_GROUP_NM_PACKING);

			// 上段のコンボボックス設定
			setUpperCombo(frm);
			// 下段のコンボボックス設定
			setLowerCombo(frm);

		} else if (existFillingFlg) {
			// 下段なし
			frm.setDispLowerListFlg(LOWER_LIST_NOT_EXIST);
			// 工程グループ名称取得
			this.setUpperOpeGrpNm(frm, OPERATION_GROUP_CD_FILLING);
			// 上段のコンボボックス設定
			setUpperCombo(frm);

		} else if (existPackingFlg) {
			// 下段あり
			frm.setDispLowerListFlg(LOWER_LIST_EXIST);

			// 工程グループ設定
			frm.setUpperOpeGrpCd(OPERATION_GROUP_CD_FILLING);
			frm.setUpperOpeGrpNm(OPERATION_GROUP_NM_FILLING);
			frm.setLowerOpeGrpCd(OPERATION_GROUP_CD_PACKING);
			frm.setLowerOpeGrpNm(OPERATION_GROUP_NM_PACKING);

			// 空の設備グループコンボボックスの作成
			frm.setUpperResGrpCombo(makeEmptyComb());
			// 下段のコンボボックス設定
			setLowerCombo(frm);

		} else {
			// 下段なし
			frm.setDispLowerListFlg(LOWER_LIST_NOT_EXIST);
			// 工程グループ名称設定
			frm.setUpperOpeGrpCd(null);
			frm.setUpperOpeGrpNm(null);
			// 空の設備グループコンボボックスの作成
			frm.setUpperResGrpCombo(makeEmptyComb());
		}
	}

	/**
	 * 工程グループ名称取得
	 * @param frm
	 */
	private void setUpperOpeGrpNm(final MgrecipeAsprovaListForm frm,
			final String key) {
		MgrecipeOperationGroupDetail bean = mgrecipeOperationGroupDetailDao
				.findByPrimaryKey(key);
		if (bean == null) {
			frm.setUpperOpeGrpCd(null);
			frm.setUpperOpeGrpNm(null);
		} else {
			frm.setUpperOpeGrpCd(bean.getOperationGroupCd());
			frm.setUpperOpeGrpNm(bean.getOperationGroupName());
		}
	}

	/**
	 * 上段のコンボボックス設定
	 * @param frm
	 * @param list
	 */
	private void setUpperCombo(final MgrecipeAsprovaListForm frm) {
		// 設備グループリスト取得
		List<RecipeResouceGroupForComboboxes> list = recipeResouceGroupForComboboxesDao
				.findByOperationGroupCd(OPERATION_GROUP_CD_FILLING);

		if (list.isEmpty()) {
			// 空の設備グループコンボボックスの作成
			frm.setUpperResGrpCombo(makeEmptyComb());
		} else {
			// 設備グループコンボボックスの作成
			List<ComboBoxItems> comb = createRecipeResouceGroupCombobox(list);
			frm.setUpperResGrpCombo(comb);
		}
	}

	/**
	 * 下段のコンボボックス設定
	 * @param frm
	 * @param list
	 */
	private void setLowerCombo(final MgrecipeAsprovaListForm frm) {
		// 設備グループリスト取得
		List<RecipeResouceGroupForComboboxes> list = recipeResouceGroupForComboboxesDao
				.findByOperationGroupCd(OPERATION_GROUP_CD_PACKING);
		if (list.isEmpty()) {
			// 空の設備グループコンボボックスの作成
			frm.setLowerResGrpCombo(makeEmptyComb());
		} else {
			// 設備グループコンボボックスの作成
			List<ComboBoxItems> comb = createRecipeResouceGroupCombobox(list);
			frm.setLowerResGrpCombo(comb);
		}
	}

	/**
	 * Asprovaタブ－一覧検索処理
	 * 
	 * @param recipeId レシピインデックス
	 * @param resouceGroupCd 設備グループコード
	 * @param operationGroupCd 工程グループコード
	 * @param productionLine 生産ラインコード
	 * @return List<RecipeFormulaList> データ
	 * @throws NoDataException データが存在しない例外
	 */
	public List<RecipeAsprovaList> getSearchList(final BigDecimal recipeId,
			final String resouceGroupCd, final String operationGroupCd,
			final String productionLine) throws NoDataException {

		checkParams(recipeId);
		checkParams(new BigDecimal(operationGroupCd));

		/* 設備検索 */
		List<RecipeAsprovaList> list = recipeAsprovaListDao.getResouceList(
			resouceGroupCd, productionLine);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// List<RecipeAsprovaList> list = recipeAsprovaListDao
		// .getSearchListByPrimaryKey(recipeId, resouceGroupCd,
		// operationGroupCd, null);
		//
		// if (list.isEmpty()) {
		// list = recipeAsprovaListDao.getResouceList(resouceGroupCd,
		// productionLine);
		// if (list.isEmpty()) {
		// throw new NoDataException();
		// }
		// }

		/* 基本処方Asprova検索 */
		List<RecipeAsprovaList> list2 = recipeAsprovaListDao
				.getSearchListByPrimaryKey(recipeId, resouceGroupCd,
					operationGroupCd, null);

		if (!list2.isEmpty()) {
			/* 設備と基本処方Asprovaとを比較 */
			for (int i = 0; i < list.size(); i++) {
				for (int j = 0; j < list2.size(); j++) {
					if (list.get(i).getResouceCd().equals(
						list2.get(j).getResouceCd())
							&& list.get(i).getResouceGroupCd().equals(
								list2.get(j).getResouceGroupCd())) {
						list.get(i).setRecipeId(list2.get(j).getRecipeId());
						list.get(i).setOperationGroupCd(
							list2.get(j).getOperationGroupCd());
						list.get(i).setYouinsu(list2.get(j).getYouinsu());
						list.get(i).setMaejikan(list2.get(j).getMaejikan());
						list.get(i).setAtojikan(list2.get(j).getAtojikan());
						list.get(i).setProcessWorkTime1(
							list2.get(j).getProcessWorkTime1());
						list.get(i).setStrProcessWorkTime1(
							list2.get(j).getStrProcessWorkTime1());
						list.get(i).setProcessWorkTime2(
							list2.get(j).getProcessWorkTime2());
						list.get(i).setStrProcessWorkTime2(
							list2.get(j).getStrProcessWorkTime2());
						list.get(i).setMachineWorkTime1(
							list2.get(j).getMachineWorkTime1());
						list.get(i).setMachineWorkTime2(
							list2.get(j).getMachineWorkTime2());
						list.get(i).setManWorkTime1(
							list2.get(j).getManWorkTime1());
						list.get(i).setManWorkTime2(
							list2.get(j).getManWorkTime2());
						list.get(i).setRecipePriority(
							list2.get(j).getRecipePriority());
						list.get(i).setStrRecipePriority(
							list2.get(j).getStrRecipePriority());
						list.get(i).setInputorCd(list2.get(j).getInputorCd());
						list.get(i).setInputDate(list2.get(j).getInputDate());
						list.get(i).setUpdatorCd(list2.get(j).getUpdatorCd());
						list.get(i).setUpdateDate(list2.get(j).getUpdateDate());
						list.get(i).setStrCheckFlg(
							list2.get(j).getStrCheckFlg());
						list.get(i).setCheckFlg(true);
					}
				}
			}
		}

		return list;
	}

	/**
	 * 登録時のマスタチェックを行う.<br>
	 * 設備グループマスタと、設備マスタにデータがない場合はエラーとする。
	 * @param searchAsprList 一覧データ
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForRegist(
			final List<RecipeAsprovaList> searchAsprList) {
		ActionMessages errors = new ActionMessages();

		if (searchAsprList == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForRegist");
		}

		// 設備マスタ存在チェック
		for (int i = 0; i < searchAsprList.size(); i++) {
			RecipeAsprovaList bean = searchAsprList.get(i);

			if (!bean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}

			// 設備グループマスタを検索
			MgrecipeResouceGroupDetail resouceGroupBean = mgrecipeResouceGroupDetailDao
					.getEntity(bean.getResouceGroupCd());

			if (resouceGroupBean == null) {
				// データが存在しない場合
				errors = MgrecipeUtil.addError(errors,
					"errors.mgrecipe.no.resouce.group.row", i + 1);
			}

			// 設備マスタを検索
			MgrecipeResouceDetail resouceBean = mgrecipeResouceDetailDao
					.getEntity(bean.getResouceCd());

			if (resouceBean == null) {
				// データが存在しない場合
				errors = MgrecipeUtil.addError(errors,
					"errors.mgrecipe.no.resouce.row", i + 1);
			}

		}
		return errors;
	}

	/**
	 * 処方Asprova登録処理を行う.
	 * @param frm AsprovaタブForm
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	public void regist(final MgrecipeAsprovaListForm frm, final String tantoCd)
			throws Exception {

		try {
			// 上段登録
			if (frm.getUpperSearchDiv().equals(
				MgrecipeAsprovaListForm.SEARCH_DIVISION_RESOUCE)) {
				// 新規登録
				insertUpper(frm, tantoCd);
			} else if (frm.getUpperSearchDiv().equals(
				MgrecipeAsprovaListForm.SEARCH_DIVISION_ASPROVA)) {
				// 更新登録（用途が包装の場合のみ）
				// if (AecNumberUtils.convertBigDecimal(frm.getRecipeUse())
				// .compareTo(MgrecipeConst.RECIPE_USE_PACKING) == 0) {
				// updateUpper(frm, tantoCd);
				// }
				insertUpper(frm, tantoCd);
			}

			// 下段登録
			if (frm.getDispLowerListFlg().equals(LOWER_LIST_EXIST)) {
				if (frm.getLowerSearchDiv().equals(
					MgrecipeAsprovaListForm.SEARCH_DIVISION_RESOUCE)) {
					// 新規登録
					insertLower(frm, tantoCd);
				} else if (frm.getLowerSearchDiv().equals(
					MgrecipeAsprovaListForm.SEARCH_DIVISION_ASPROVA)) {
					// 更新登録
					// updateLower(frm, tantoCd);
					insertLower(frm, tantoCd);
				}
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 上段 新規登録処理
	 * @param frm Asprovaタブ一覧Form
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void insertUpper(final MgrecipeAsprovaListForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {

		/* 削除処理 */
		recipeAsprovaListDao.deleteByRecipeIdOperationGroupCd(
			frm.getRecipeId(), frm.getUpperOpeGrpCd());

		/* 登録処理 */
		for (RecipeAsprovaList bean : frm.getUpperSearchList()) {

			if (!bean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}

			RecipeAsprovaList newBean = new RecipeAsprovaList();

			// BeanをnewBeanにコピー
			IgnoreCaseBeanUtils.copyProperties(newBean, bean);

			if (bean.getRecipeId() == null) {
				// 登録情報
				newBean.setRecipeId(new BigDecimal(frm.getRecipeId()));
				newBean.setOperationGroupCd(frm.getUpperOpeGrpCd());
				newBean.setRecipePriority(new BigDecimal(0));
				if ((frm.getUpperYouinsu() != null)
						&& (!frm.getUpperYouinsu().equals(""))) {
					newBean.setYouinsu(AecNumberUtils.convertBigDecimal(frm
							.getUpperYouinsu()));
				}
				newBean.setInputorCd(tantoCd); // 登録者コード
				newBean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				newBean.setUpdatorCd(tantoCd); // 更新者コード
			} else {
				// 更新情報
				if ((frm.getUpperYouinsu() != null)
						&& (!frm.getUpperYouinsu().equals(""))) {
					newBean.setYouinsu(AecNumberUtils.convertBigDecimal(frm
							.getUpperYouinsu()));
				}
				newBean.setUpdatorCd(tantoCd); // 更新者コード
			}

			// 登録処理
			int insertNum = recipeAsprovaListDao.insert(newBean);
			if (insertNum != 1) {
				// 一意制約エラー
				throw new DuplicateRuntimeException(0);
			}
		}
	}

	/**
	 * 下段 新規登録処理
	 * @param frm Asprovaタブ一覧Form
	 * @param tantoCd 担当者コード
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private void insertLower(final MgrecipeAsprovaListForm frm,
			final String tantoCd) throws IllegalAccessException,
			InvocationTargetException {

		/* 削除処理 */
		recipeAsprovaListDao.deleteByRecipeIdOperationGroupCd(
			frm.getRecipeId(), frm.getLowerOpeGrpCd());

		/* 登録処理 */
		for (RecipeAsprovaList bean : frm.getLowerSearchList()) {

			if (!bean.isCheckFlg()) {
				// チェック無は読み飛ばし
				continue;
			}

			RecipeAsprovaList newBean = new RecipeAsprovaList();

			// BeanをnewBeanにコピー
			IgnoreCaseBeanUtils.copyProperties(newBean, bean);

			if (bean.getRecipeId() == null) {
				// 登録情報
				newBean.setRecipeId(new BigDecimal(frm.getRecipeId()));
				newBean.setOperationGroupCd(frm.getLowerOpeGrpCd());
				newBean.setRecipePriority(new BigDecimal(0));
				if ((frm.getLowerYouinsu() != null)
						&& (!frm.getLowerYouinsu().equals(""))) {
					newBean.setYouinsu(AecNumberUtils.convertBigDecimal(frm
							.getLowerYouinsu()));
				}
				newBean.setInputorCd(tantoCd); // 登録者コード
				newBean.setInputDate(AecDateUtils.getCurrentTimestamp()); // 登録日時
				newBean.setUpdatorCd(tantoCd); // 更新者コード
			} else {
				// 更新情報
				if ((frm.getLowerYouinsu() != null)
						&& (!frm.getLowerYouinsu().equals(""))) {
					newBean.setYouinsu(AecNumberUtils.convertBigDecimal(frm
							.getLowerYouinsu()));
				}
				newBean.setUpdatorCd(tantoCd); // 更新者コード
			}

			// 登録処理
			int insertNum = recipeAsprovaListDao.insert(newBean);
			if (insertNum != 1) {
				// 一意制約エラー
				throw new DuplicateRuntimeException(0);
			}
		}
	}

	/**
	 * 上段 追加登録処理
	 * @param frm Asprovaタブ一覧Form
	 * @param tantoCd 担当者コード
	 */
	// private void updateUpper(final MgrecipeAsprovaListForm frm,
	// final String tantoCd) {
	// /* 登録処理 */
	// for (RecipeAsprovaList bean : frm.getUpperSearchList()) {
	//
	// if (bean.getRecipeId() != null) {
	// // 更新情報
	// if ((frm.getUpperYouinsu() != null)
	// && (!frm.getUpperYouinsu().equals(""))) {
	// bean.setYouinsu(AecNumberUtils.convertBigDecimal(frm
	// .getUpperYouinsu()));
	// }
	// bean.setUpdatorCd(tantoCd); // 更新者コード
	//
	// // 登録処理
	// int updateNum = recipeAsprovaListDao.update(bean);
	// if (updateNum != 1) {
	// // 一意制約エラー
	// throw new DuplicateRuntimeException(0);
	// }
	// }
	// }
	//
	// }
	/**
	 * 下段 追加登録処理
	 * @param frm Asprovaタブ一覧Form
	 * @param tantoCd 担当者コード
	 */
	// private void updateLower(final MgrecipeAsprovaListForm frm,
	// final String tantoCd) {
	//
	// /* 登録処理 */
	// for (RecipeAsprovaList bean : frm.getLowerSearchList()) {
	//
	// if (bean.getRecipeId() != null) {
	// // 更新情報
	// if ((frm.getLowerYouinsu() != null)
	// && (!frm.getLowerYouinsu().equals(""))) {
	// bean.setYouinsu(AecNumberUtils.convertBigDecimal(frm
	// .getLowerYouinsu()));
	// }
	// bean.setUpdatorCd(tantoCd); // 更新者コード
	//
	// // 更新処理
	// int updateNum = recipeAsprovaListDao.update(bean);
	// if (updateNum != 1) {
	// // 一意制約エラー
	// throw new DuplicateRuntimeException(0);
	// }
	// }
	// }
	// }
	/**
	 * 設備グループコンボボックス作成
	 * @param list 設備グループリスト
	 * @return List<ComboBoxItems>
	 */
	private List<ComboBoxItems> createRecipeResouceGroupCombobox(
			final List<RecipeResouceGroupForComboboxes> list) {

		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// コンボボックスの作成
		for (RecipeResouceGroupForComboboxes bean : list) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getResouceGroupCd());
			item.setLabales(bean.getResouceGroupName());
			res.add(item);
		}
		return res;
	}

	/**
	 * 空の設備グループコンボボックスの作成
	 * @return List<ComboBoxItems>
	 */
	private List<ComboBoxItems> makeEmptyComb() {
		// 空のコンボボックスの作成
		List<ComboBoxItems> comb = new ArrayList<ComboBoxItems>();
		ComboBoxItems item = new ComboBoxItems();
		item.setValues("");
		item.setLabales("　　　　　　　　　　　　　　");
		comb.add(item);
		return comb;
	}

	/**
	 * パラメータチェック
	 * @param recipeId レシピインデックス
	 */
	private void checkParams(final BigDecimal recipeId) {

		if (recipeId == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * 設備グループコード取得
	 * @param recipeId レシピインデックス
	 * @param operationGroupCd 工程グループコード
	 * @return RecipeAsprovaList
	 */
	public RecipeAsprovaList getResouceGroupCd(final BigDecimal recipeId,
			final String operationGroupCd) {
		checkParams(recipeId);
		checkParams(new BigDecimal(operationGroupCd));

		/* 設備検索 */
		RecipeAsprovaList bean = recipeAsprovaListDao.getResouceGroupCd(
			recipeId, operationGroupCd);

		return bean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 設備グループ情報取得Daoを設定します。
	 * @param recipeResouceGroupForComboboxesDao 設備グループ情報取得Dao
	 */
	public void setRecipeResouceGroupForComboboxesDao(
			final RecipeResouceGroupForComboboxesDao recipeResouceGroupForComboboxesDao) {
		this.recipeResouceGroupForComboboxesDao = recipeResouceGroupForComboboxesDao;
	}

	/**
	 * 処方ASPROVA_Daoを設定します。
	 * @param recipeAsprovaListDao 処方ASPROVA_Dao
	 */
	public void setRecipeAsprovaListDao(
			final RecipeAsprovaListDao recipeAsprovaListDao) {
		this.recipeAsprovaListDao = recipeAsprovaListDao;
	}

	/**
	 * 設備グループDaoを設定します。
	 * @param mgrecipeResouceGroupDetailDao 設備グループDao
	 */
	public void setMgrecipeResouceGroupDetailDao(
			final MgrecipeResouceGroupDetailDao mgrecipeResouceGroupDetailDao) {
		this.mgrecipeResouceGroupDetailDao = mgrecipeResouceGroupDetailDao;
	}

	/**
	 * 設備Daoを設定します。
	 * @param mgrecipeResouceDetailDao 設備Dao
	 */
	public void setMgrecipeResouceDetailDao(
			final MgrecipeResouceDetailDao mgrecipeResouceDetailDao) {
		this.mgrecipeResouceDetailDao = mgrecipeResouceDetailDao;
	}

	/**
	 * 工程グループDaoを設定します。
	 * @param mgrecipeOperationGroupDetailDao 工程グループDao
	 */
	public void setMgrecipeOperationGroupDetailDao(
			final MgrecipeOperationGroupDetailDao mgrecipeOperationGroupDetailDao) {
		this.mgrecipeOperationGroupDetailDao = mgrecipeOperationGroupDetailDao;
	}

	/**
	 * 処方プロシージャDaoを設定します。
	 * @param recipeProcedureListDao 処方プロシージャDao
	 */
	public void setRecipeProcedureListDao(
			final RecipeProcedureListDao recipeProcedureListDao) {
		this.recipeProcedureListDao = recipeProcedureListDao;

	}

}
