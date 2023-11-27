/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe.MgrecipeLineForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe.MgrecipeLineForComboboxesDao;
import com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe.RecipeProcedureSetpNoForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe.RecipeProcedureSetpNoForComboboxesDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeBumonDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeBumonDetailDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeItemQueueDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeItemQueueDetailDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeLabelList;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeLabelListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeNameList;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeNameListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeAsprovaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeAsprovaListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeInspectionList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeInspectionListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeRemarkList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeRemarkListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 基本処方検索 共通ロジック実装クラス
 * @author tosco
 */
public class MgrecipeCommonsLogicImpl implements MgrecipeCommonsLogic {
	/** 名称マスタDAO */
	private MgrecipeNameListDao mgrecipeNameListDao;

	/** 品目マスタキューDAO */
	private MgrecipeItemQueueDetailDao mgrecipeItemQueueDetailDao;

	/** 会計部門マスタDAO */
	private MgrecipeBumonDetailDao mgrecipeBumonDetailDao;

	/** 処方フォーミュラ操作DAO */
	private RecipeFormulaListDao recipeFormulaListDao;

	/** 処方プロシージャ操作DAO */
	private RecipeProcedureListDao recipeProcedureListDao;

	/** 処方検査操作DAO */
	private RecipeInspectionListDao recipeInspectionListDao;

	/** 処方ASPROVA操作DAO */
	private RecipeAsprovaListDao recipeAsprovaListDao;

	/** 処方その他操作DAO */
	private RecipeRemarkListDao recipeRemarkListDao;

	/** 処方ヘッダー操作DAO */
	private RecipeHeaderListDao recipeHeaderListDao;

	/** 帳票・ラベルマスタ操作DAO */
	private MgrecipeLabelListDao mgrecipeLabelListDao;

	/** 生産ラインコンボボックス用DAO */
	private MgrecipeLineForComboboxesDao mgrecipeLineForComboboxesDao;

	/** 工程順序コンボボックス用DAO */
	private RecipeProcedureSetpNoForComboboxesDao recipeProcedureSetpNoForComboboxesDao;

	/** ステータス 試作用 */
	private static final String RSTS_TRIAL_CD = "2";

	/** ステータス 一般使用 */
	private static final String RSTS_GENERAL_CD = "3";

	/**
	 * コンストラクタ
	 */
	public MgrecipeCommonsLogicImpl() {
	}

	/**
	 * 生産ラインを全件取得する
	 * @return List<MgrecipeLine>
	 */
	public List<MgrecipeLineForComboboxes> getAllLines() {
		return mgrecipeLineForComboboxesDao.findAll();
	}

	/**
	 * レシピステータスを全件取得する
	 * @return List<MgrecipeNameList>
	 */
	public List<MgrecipeNameList> getAllStatus() {
		return mgrecipeNameListDao
				.getSearchList(MgrecipeNameListDao.RECIPE_STATUS_KEY);
	}

	/**
	 * レシピステータスコンボボックス配列を取得する
	 * @param isNewSearch true:原処方から新規作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> getStatusComboList(final Boolean isNewSearch) {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();
		// 名称マスタ検索
		List<MgrecipeNameList> statusList = getAllStatus();
		for (MgrecipeNameList bean : statusList) {
			if (isNewSearch) {
				if (bean.getNameCd().equals(RSTS_TRIAL_CD)
						|| bean.getNameCd().equals(RSTS_GENERAL_CD)) {
					ComboBoxItems item = new ComboBoxItems();
					item.setValues(bean.getNameCd());
					item.setLabales(bean.getName01());
					res.add(item);
				}
			} else {
				ComboBoxItems item = new ComboBoxItems();
				item.setValues(bean.getNameCd());
				item.setLabales(bean.getName01());
				res.add(item);
			}
		}
		return res;
	}

	/**
	 * 生産ラインコンボボックス作成
	 * @param newFlg true:新規・編集入力
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createLineCombobox(final Boolean newFlg) {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 生産ライン検索
		List<MgrecipeLineForComboboxes> lineList = getAllLines();
		for (MgrecipeLineForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getProductionLine());
			item.setLabales(bean.getProductionLineName());
			res.add(item);
		}

		if (!newFlg) {
			// 全てを追加
			ComboBoxItems allItem = new ComboBoxItems();
			allItem.setValues(MgrecipeConst.COMBO_ALL_VALUE);
			allItem.setLabales(MgrecipeConst.COMBO_ALL_LABEL);
			res.add(0, allItem);
		}

		return res;
	}

	/**
	 * ステータスコンボボックス(すべて有)作成
	 * @param isNewSearch true:原処方から新規作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createStatusAllCombobox(final Boolean isNewSearch) {
		// 名称マスタからステータスコンボボックス用配列を取得
		List<ComboBoxItems> res = getStatusComboList(isNewSearch);
		// 全てを追加
		ComboBoxItems allItem = new ComboBoxItems();
		allItem.setValues(MgrecipeConst.COMBO_ALL_VALUE);
		allItem.setLabales(MgrecipeConst.COMBO_ALL_LABEL);
		res.add(0, allItem);

		return res;
	}

	/**
	 * 品目マスタキューから指定した品目コードの最新バージョンのデータを取得する。
	 * @param itemCd 品目コード
	 * @return MgrecipeItemQueueDetail
	 * @throws NoDataException データが存在しない場合
	 */
	public MgrecipeItemQueueDetail getMaxVersionItemQueue(final String itemCd)
			throws NoDataException {
		MgrecipeItemQueueDetail item = mgrecipeItemQueueDetailDao
				.getEntity(itemCd);
		if (item == null) {
			throw new NoDataException();
		}
		return item;
	}

	/**
	 * 会計部門検索
	 * @param sectionCd 会計部門コード
	 * @return MgrecipeBumonDetail
	 * @throws NoDataException データが存在しない場合
	 */
	public MgrecipeBumonDetail getBumonEntity(final String sectionCd)
			throws NoDataException {
		MgrecipeBumonDetail section = mgrecipeBumonDetailDao
				.getEntity(sectionCd);
		if (section == null) {
			throw new NoDataException();
		}
		return section;
	}

	/**
	 * 処方フォーミュラに登録処理を行う
	 * @param bean RecipeFormulaList
	 */
	public void insertFormula(final RecipeFormulaList bean) {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			// 処方フォーミュラに挿入
			int updateNum = recipeFormulaListDao.insert(bean);

			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 処方フォーミュラに更新処理を行う
	 * @param bean RecipeFormulaList
	 */
	public void updateFormula(final RecipeFormulaList bean) {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			// 処方フォーミュラに更新
			int updateNum = recipeFormulaListDao.update(bean);

			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 処方プロシージャからレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<RecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	public List<RecipeProcedureList> getProcedure(final String recipeId)
			throws NoDataException {
		List<RecipeProcedureList> result = recipeProcedureListDao
				.findByRecipeId(recipeId, null);
		if (result.isEmpty()) {
			throw new NoDataException();
		}
		return result;
	}

	/**
	 * 処方プロシージャに登録処理を行う
	 * @param bean RecipeFormulaList
	 */
	public void insertProcedure(final RecipeProcedureList bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			// 処方プロシージャに挿入
			int updateNum = recipeProcedureListDao.insert(bean);

			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 処方処方検査からレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<RecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	public List<RecipeInspectionList> getInspection(final String recipeId)
			throws NoDataException {
		List<RecipeInspectionList> result = recipeInspectionListDao
				.findByRecipeId(recipeId, null, null);
		if (result.isEmpty()) {
			throw new NoDataException();
		}
		return result;
	}

	/**
	 * 処方検査に登録処理を行う
	 * @param bean RecipeInspectionList
	 */
	public void insertInspection(final RecipeInspectionList bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			// 処方プロシージャに挿入
			int updateNum = recipeInspectionListDao.insert(bean);

			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 処方フォーミュラからレシピIDに紐づくデータをすべて取得
	 * @param recipeId レシピインデックス
	 * @return List<RecipeFormulaList>
	 */
	public List<RecipeFormulaList> findFormulaByRecipeId(final String recipeId) {
		// 必ずLINE_NO=1001が1件あるからデータが存在しないことはない
		List<RecipeFormulaList> result = recipeFormulaListDao
				.findByRecipeId(recipeId);
		return result;
	}

	/**
	 * 処方フォーミュラに一括登録処理を行う
	 * @param list List<RecipeFormulaList>
	 */
	public void insertFormulaList(final List<RecipeFormulaList> list) {
		for (RecipeFormulaList bean : list) {
			// 処方フォーミュラに挿入
			insertFormula(bean);
		}
	}

	/**
	 * 処方フォーミュラに一括更新処理を行う
	 * @param list List<RecipeFormulaList>
	 */
	public void updateFormulaList(final List<RecipeFormulaList> list) {
		for (RecipeFormulaList bean : list) {
			// 処方フォーミュラに挿入
			updateFormula(bean);
		}
	}

	/**
	 * 処方ASPROVAからレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<RecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	public List<RecipeAsprovaList> findAsprovaByRecipeId(final String recipeId)
			throws NoDataException {
		List<RecipeAsprovaList> result = recipeAsprovaListDao.findByRecipeId(
			recipeId, null, null);
		if (result.isEmpty()) {
			throw new NoDataException();
		}
		return result;
	}

	/**
	 * 処方ASPROVAに登録処理を行う
	 * @param bean RecipeAsprovaList
	 */
	public void insertAsprova(final RecipeAsprovaList bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			// 処方ASPROVAに挿入
			int updateNum = recipeAsprovaListDao.insert(bean);

			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 処方その他からレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<RecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	public List<RecipeRemarkList> findRemarkByRecipeId(final String recipeId)
			throws NoDataException {
		List<RecipeRemarkList> result = recipeRemarkListDao
				.findByRecipeId(recipeId);
		if (result.isEmpty()) {
			throw new NoDataException();
		}
		return result;
	}

	/**
	 * 処方その他に登録処理を行う
	 * @param bean RecipeRemarkList
	 */
	public void insertRemark(final RecipeRemarkList bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			// 処方その他に挿入
			int updateNum = recipeRemarkListDao.insert(bean);

			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 共通ヘッダー部のデータを処方ヘッダーから取得する。
	 * @param recipeId レシピインデックス
	 * @return RecipeHeaderList
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	public RecipeHeaderList getCommonHeader(final String recipeId)
			throws NoDataException {
		RecipeHeaderList header = recipeHeaderListDao.getCommonHeader(recipeId,
			RecipeHeaderListDao.RECIPE_TYPE_MASTER);
		if (header == null) {
			// データが存在しなかった場合は例外発生
			throw new NoDataException();
		}
		return header;
	}

	/**
	 * 指定したラベルコードに一致するデータを帳票・ラベルマスタから取得する
	 * @param labelCd ラベルコード（レシピインデックス）
	 * @return List<MgrecipeLabelList>
	 * @throws NoDataException データが存在しない場合発生
	 */
	public List<MgrecipeLabelList> getLabelList(final String labelCd)
			throws NoDataException {
		List<MgrecipeLabelList> result = mgrecipeLabelListDao
				.getSearchList(labelCd);
		if (result.isEmpty()) {
			throw new NoDataException();
		}
		return result;
	}

	/**
	 * 帳票・ラベルマスタに登録処理を行う
	 * @param bean MgrecipeLabelList
	 */
	public void insertLabel(final MgrecipeLabelList bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			// 処方その他に挿入
			int updateNum = mgrecipeLabelListDao.insert(bean);

			if (updateNum != 1) {
				// 排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 工程順序リストを取得する
	 * @param recipeId レシピインデックス
	 * @return List<RecipeProcedureSetpNoForComboboxes>
	 */
	public List<RecipeProcedureSetpNoForComboboxes> getProcedureSetpNoList(
			final BigDecimal recipeId) {
		return recipeProcedureSetpNoForComboboxesDao.findByRecipeId(recipeId);
	}

	/**
	 * 工程順序コンボボックス作成
	 * @param recipeId レシピインデックス
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createProcedureSetpNoCombobox(
			final String recipeId, final boolean zero) {
		BigDecimal decRecipeId = null;
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 0:すべてを先頭に設定
		if (zero) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues("0");
			item.setLabales("0");
			res.add(item);
		}
		if (recipeId != null) {
			try {
				decRecipeId = new BigDecimal(recipeId);

				// 工程順序検索
				List<RecipeProcedureSetpNoForComboboxes> list = getProcedureSetpNoList(decRecipeId);
				if (list != null) {
					for (RecipeProcedureSetpNoForComboboxes bean : list) {
						ComboBoxItems item = new ComboBoxItems();
						item.setValues(bean.getStepNo().toString());
						item.setLabales(bean.getSeq().toString());
						res.add(item);
					}
				}
			} catch (NumberFormatException e) {
				// レシピインデックスが数値として無効な場合検索結果なしとする
				decRecipeId = null;
			}
		}
		return res;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 名称マスタDAO を設定します。
	 * @param mgrecipeNameListDao 名称マスタDAO
	 */
	public void setMgrecipeNameListDao(
			final MgrecipeNameListDao mgrecipeNameListDao) {
		this.mgrecipeNameListDao = mgrecipeNameListDao;
	}

	/**
	 * 品目マスタキューDAOを取得します。
	 * @return 品目マスタキューDAO
	 */
	public MgrecipeItemQueueDetailDao getMgrecipeItemQueueDao() {
		return mgrecipeItemQueueDetailDao;
	}

	/**
	 * 品目マスタキューDAOを設定します。
	 * @param mgrecipeItemQueueDetailDao 品目マスタキューDAO
	 */
	public void setMgrecipeItemQueueDetailDao(
			final MgrecipeItemQueueDetailDao mgrecipeItemQueueDetailDao) {
		this.mgrecipeItemQueueDetailDao = mgrecipeItemQueueDetailDao;
	}

	/**
	 * 処方フォーミュラ操作DAOを設定します。
	 * @param recipeFormulaListDao 処方フォーミュラ操作DAO
	 */
	public void setRecipeFormulaListDao(
			final RecipeFormulaListDao recipeFormulaListDao) {
		this.recipeFormulaListDao = recipeFormulaListDao;
	}

	/**
	 * 処方プロシージャ操作DAOを取得します。
	 * @return 処方プロシージャ操作DAO
	 */
	public RecipeProcedureListDao getRecipeProcedureDetailDao() {
		return recipeProcedureListDao;
	}

	/**
	 * 処方プロシージャ操作DAOを設定します。
	 * @param recipeProcedureListDao 処方プロシージャ操作DAO
	 */
	public void setRecipeProcedureListDao(
			final RecipeProcedureListDao recipeProcedureListDao) {
		this.recipeProcedureListDao = recipeProcedureListDao;
	}

	/**
	 * 処方処方検査操作DAOを設定します。
	 * @param recipeInspectionListDao 処方処方検査操作DAO
	 */
	public void setRecipeInspectionListDao(
			final RecipeInspectionListDao recipeInspectionListDao) {
		this.recipeInspectionListDao = recipeInspectionListDao;
	}

	/**
	 * 処方ASPROVA操作DAOを設定します。
	 * @param recipeAsprovaListDao 処方ASPROVA操作DAO
	 */
	public void setRecipeAsprovaListDao(
			final RecipeAsprovaListDao recipeAsprovaListDao) {
		this.recipeAsprovaListDao = recipeAsprovaListDao;
	}

	/**
	 * 処方その他操作DAOを設定します。
	 * @param recipeRemarkListDao 処方その他操作DAO
	 */
	public void setRecipeRemarkListDao(
			final RecipeRemarkListDao recipeRemarkListDao) {
		this.recipeRemarkListDao = recipeRemarkListDao;
	}

	/**
	 * 処方ヘッダー操作DAOを設定します。
	 * @param recipeHeaderListDao 処方ヘッダー操作DAO
	 */
	public void setRecipeHeaderListDao(
			final RecipeHeaderListDao recipeHeaderListDao) {
		this.recipeHeaderListDao = recipeHeaderListDao;
	}

	/**
	 * 帳票・ラベルマスタ操作DAOを設定します。
	 * @param mgrecipeLabelListDao 帳票・ラベルマスタ操作DAO
	 */
	public void setMgrecipeLabelListDao(
			final MgrecipeLabelListDao mgrecipeLabelListDao) {
		this.mgrecipeLabelListDao = mgrecipeLabelListDao;
	}

	/**
	 * 生産ラインコンボボックス用DAOを設定します。
	 * @param mgrecipeLineForComboboxesDao 生産ラインコンボボックス用DAO
	 */
	public void setMgrecipeLineForComboboxesDao(
			final MgrecipeLineForComboboxesDao mgrecipeLineForComboboxesDao) {
		this.mgrecipeLineForComboboxesDao = mgrecipeLineForComboboxesDao;
	}

	/**
	 * 工程順序コンボボックス用DAOを設定します。
	 * @param recipeProcedureSetpNoForComboboxesDao 工程順序コンボボックス用DAO
	 */
	public void setRecipeProcedureSetpNoForComboboxesDao(
			final RecipeProcedureSetpNoForComboboxesDao recipeProcedureSetpNoForComboboxesDao) {
		this.recipeProcedureSetpNoForComboboxesDao = recipeProcedureSetpNoForComboboxesDao;
	}

	/**
	 * mgrecipeBumonDetailDaoを設定します。
	 * @param mgrecipeBumonDetailDao mgrecipeBumonDetailDao
	 */
	public void setMgrecipeBumonDetailDao(
			final MgrecipeBumonDetailDao mgrecipeBumonDetailDao) {
		this.mgrecipeBumonDetailDao = mgrecipeBumonDetailDao;
	}
}
