/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.grecipe.GrecipeRecipeProcedureSetpNoForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.grecipe.GrecipeRecipeProcedureSetpNoForComboboxesDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeItemQueueDetail;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeItemQueueDetailDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeLabelList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeLabelListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeNameList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeNameListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeInspectionList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeInspectionListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeProcedureList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeProcedureListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeRemarkList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeRemarkListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 原処方 共通ロジック実装クラス
 * @author tosco
 */
public class GrecipeCommonsLogicImpl implements GrecipeCommonsLogic {
	/** 名称マスタDAO */
	private GrecipeNameListDao grecipeNameListDao;

	/** 品目マスタキューDAO */
	private GrecipeItemQueueDetailDao grecipeItemQueueDetailDao;

	/** 処方フォーミュラ操作DAO */
	private GrecipeRecipeFormulaListDao grecipeRecipeFormulaListDao;

	/** 処方プロシージャ操作DAO */
	private GrecipeRecipeProcedureListDao grecipeRecipeProcedureListDao;

	/** 処方検査操作DAO */
	private GrecipeRecipeInspectionListDao grecipeRecipeInspectionListDao;

	/** 処方その他操作DAO */
	private GrecipeRecipeRemarkListDao grecipeRecipeRemarkListDao;

	/** 処方ヘッダー操作DAO */
	private GrecipeRecipeHeaderListDao grecipeRecipeHeaderListDao;

	/** 帳票・ラベルマスタ操作DAO */
	private GrecipeLabelListDao grecipeLabelListDao;

	/** 工程順序コンボボックス用DAO */
	private GrecipeRecipeProcedureSetpNoForComboboxesDao grecipeRecipeProcedureSetpNoForComboboxesDao;

	/**
	 * コンストラクタ
	 */
	public GrecipeCommonsLogicImpl() {
	}

	/**
	 * レシピステータスを全件取得する
	 * @return List<MgrecipeNameList>
	 */
	public List<GrecipeNameList> getAllStatus() {
		return grecipeNameListDao
				.getSearchList(GrecipeNameListDao.RECIPE_STATUS_KEY);
	}

	/**
	 * レシピステータスコンボボックス配列を取得する
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> getStatusComboList() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();
		// 名称マスタ検索
		List<GrecipeNameList> statusList = getAllStatus();
		for (GrecipeNameList bean : statusList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getNameCd());
			item.setLabales(bean.getName01());
			res.add(item);
		}
		return res;
	}

	/**
	 * ステータスコンボボックス(すべて有)作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createStatusAllCombobox() {
		// 名称マスタからステータスコンボボックス用配列を取得
		List<ComboBoxItems> res = getStatusComboList();
		// 全てを追加
		ComboBoxItems allItem = new ComboBoxItems();
		allItem.setValues(GrecipeConst.COMBO_ALL_VALUE);
		allItem.setLabales(GrecipeConst.COMBO_ALL_LABEL);
		res.add(0, allItem);

		return res;
	}

	/**
	 * 品目マスタキューから指定した品目コードの最新バージョンのデータを取得する。
	 * @param itemCd 品目コード
	 * @return GrecipeItemQueueDetail
	 * @throws NoDataException データが存在しない場合
	 */
	public GrecipeItemQueueDetail getMaxVersionItemQueue(final String itemCd)
			throws NoDataException {
		GrecipeItemQueueDetail item = grecipeItemQueueDetailDao
				.getEntity(itemCd);
		if (item == null) {
			throw new NoDataException();
		}
		return item;
	}

	/**
	 * 処方フォーミュラに登録処理を行う
	 * @param bean GrecipeRecipeFormulaList
	 */
	public void insertFormula(final GrecipeRecipeFormulaList bean) {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			// 処方フォーミュラに挿入
			int updateNum = grecipeRecipeFormulaListDao.insert(bean);

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
	 * @param bean GrecipeRecipeFormulaList
	 */
	public void updateFormula(final GrecipeRecipeFormulaList bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}
		try {
			// 処方フォーミュラに更新
			int updateNum = grecipeRecipeFormulaListDao.update(bean);

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
	 * @return List<GrecipeRecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	public List<GrecipeRecipeProcedureList> getProcedure(final String recipeId)
			throws NoDataException {
		List<GrecipeRecipeProcedureList> result = grecipeRecipeProcedureListDao
				.findByRecipeId(recipeId, null);
		if (result.isEmpty()) {
			throw new NoDataException();
		}
		return result;
	}

	/**
	 * 処方プロシージャに登録処理を行う
	 * @param bean GrecipeRecipeFormulaList
	 */
	public void insertProcedure(final GrecipeRecipeProcedureList bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			// 処方プロシージャに挿入
			int updateNum = grecipeRecipeProcedureListDao.insert(bean);

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
	 * @return List<GrecipeRecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	public List<GrecipeRecipeInspectionList> getInspection(final String recipeId)
			throws NoDataException {
		List<GrecipeRecipeInspectionList> result = grecipeRecipeInspectionListDao
				.findByRecipeId(recipeId, null, null);
		if (result.isEmpty()) {
			throw new NoDataException();
		}
		return result;
	}

	/**
	 * 処方検査に登録処理を行う
	 * @param bean GrecipeRecipeInspectionList
	 */
	public void insertInspection(final GrecipeRecipeInspectionList bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			// 処方プロシージャに挿入
			int updateNum = grecipeRecipeInspectionListDao.insert(bean);

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
	 * @return List<GrecipeRecipeFormulaList>
	 */
	public List<GrecipeRecipeFormulaList> findFormulaByRecipeId(
			final String recipeId) {
		// 必ずLINE_NO=1001が1件あるからデータが存在しないことはない
		List<GrecipeRecipeFormulaList> result = grecipeRecipeFormulaListDao
				.findByRecipeId(recipeId);
		return result;
	}

	/**
	 * 処方フォーミュラに一括登録処理を行う
	 * @param list List<GrecipeRecipeFormulaList>
	 */
	public void insertFormulaList(final List<GrecipeRecipeFormulaList> list) {
		for (GrecipeRecipeFormulaList bean : list) {
			// 処方フォーミュラに挿入
			insertFormula(bean);
		}
	}

	/**
	 * 処方フォーミュラに一括更新処理を行う
	 * @param list List<GrecipeRecipeFormulaList>
	 */
	public void updateFormulaList(final List<GrecipeRecipeFormulaList> list) {
		for (GrecipeRecipeFormulaList bean : list) {
			// 処方フォーミュラに挿入
			updateFormula(bean);
		}
	}

	/**
	 * 処方その他からレシピインデックスに紐づいているデータを全て取得する
	 * @param recipeId レシピインデックス
	 * @return List<GrecipeRecipeProcedureList>
	 * @throws NoDataException データが存在しない場合
	 */
	public List<GrecipeRecipeRemarkList> findRemarkByRecipeId(
			final String recipeId) throws NoDataException {
		List<GrecipeRecipeRemarkList> result = grecipeRecipeRemarkListDao
				.findByRecipeId(recipeId);
		if (result.isEmpty()) {
			throw new NoDataException();
		}
		return result;
	}

	/**
	 * 処方その他に登録処理を行う
	 * @param bean GrecipeRecipeRemarkList
	 */
	public void insertRemark(final GrecipeRecipeRemarkList bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			// 処方その他に挿入
			int updateNum = grecipeRecipeRemarkListDao.insert(bean);

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
	 * @return GrecipeRecipeHeaderList
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	public GrecipeRecipeHeaderList getCommonHeader(final String recipeId)
			throws NoDataException {
		GrecipeRecipeHeaderList header = grecipeRecipeHeaderListDao
				.getCommonHeader(recipeId);
		if (header == null) {
			// データが存在しなかった場合は例外発生
			throw new NoDataException();
		}
		return header;
	}

	/**
	 * 指定したラベルコードに一致するデータを帳票・ラベルマスタから取得する
	 * @param labelCd ラベルコード（レシピインデックス）
	 * @return List<GrecipeLabelList>
	 * @throws NoDataException データが存在しない場合発生
	 */
	public List<GrecipeLabelList> getLabelList(final String labelCd)
			throws NoDataException {
		List<GrecipeLabelList> result = grecipeLabelListDao
				.getSearchList(labelCd);
		if (result.isEmpty()) {
			throw new NoDataException();
		}
		return result;
	}

	/**
	 * 帳票・ラベルマスタに登録処理を行う
	 * @param bean GrecipeLabelList
	 */
	public void insertLabel(final GrecipeLabelList bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			// 処方その他に挿入
			int updateNum = grecipeLabelListDao.insert(bean);

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
	 * @return List<GrecipeRecipeProcedureSetpNoForComboboxes>
	 */
	public List<GrecipeRecipeProcedureSetpNoForComboboxes> getProcedureSetpNoList(
			final BigDecimal recipeId) {
		return grecipeRecipeProcedureSetpNoForComboboxesDao
				.findByRecipeId(recipeId);
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
				List<GrecipeRecipeProcedureSetpNoForComboboxes> list = getProcedureSetpNoList(decRecipeId);
				if (list != null) {
					for (GrecipeRecipeProcedureSetpNoForComboboxes bean : list) {
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
	 * @param grecipeNameListDao 名称マスタDAO
	 */
	public void setGrecipeNameListDao(
			final GrecipeNameListDao grecipeNameListDao) {
		this.grecipeNameListDao = grecipeNameListDao;
	}

	/**
	 * 品目マスタキューDAOを設定します。
	 * @param grecipeItemQueueDetailDao 品目マスタキューDAO
	 */
	public void setGrecipeItemQueueDetailDao(
			final GrecipeItemQueueDetailDao grecipeItemQueueDetailDao) {
		this.grecipeItemQueueDetailDao = grecipeItemQueueDetailDao;
	}

	/**
	 * 処方フォーミュラ操作DAOを設定します。
	 * @param grecipeRecipeFormulaListDao 処方フォーミュラ操作DAO
	 */
	public void setGrecipeRecipeFormulaListDao(
			final GrecipeRecipeFormulaListDao grecipeRecipeFormulaListDao) {
		this.grecipeRecipeFormulaListDao = grecipeRecipeFormulaListDao;
	}

	/**
	 * 処方プロシージャ操作DAOを設定します。
	 * @param grecipeRecipeProcedureListDao 処方プロシージャ操作DAO
	 */
	public void setGrecipeRecipeProcedureListDao(
			final GrecipeRecipeProcedureListDao grecipeRecipeProcedureListDao) {
		this.grecipeRecipeProcedureListDao = grecipeRecipeProcedureListDao;
	}

	/**
	 * 処方処方検査操作DAOを設定します。
	 * @param grecipeRecipeInspectionListDao 処方処方検査操作DAO
	 */
	public void setGrecipeRecipeInspectionListDao(
			final GrecipeRecipeInspectionListDao grecipeRecipeInspectionListDao) {
		this.grecipeRecipeInspectionListDao = grecipeRecipeInspectionListDao;
	}

	/**
	 * 処方その他操作DAOを設定します。
	 * @param grecipeRecipeRemarkListDao 処方その他操作DAO
	 */
	public void setGrecipeRecipeRemarkListDao(
			final GrecipeRecipeRemarkListDao grecipeRecipeRemarkListDao) {
		this.grecipeRecipeRemarkListDao = grecipeRecipeRemarkListDao;
	}

	/**
	 * 処方ヘッダー操作DAOを設定します。
	 * @param grecipeRecipeHeaderListDao 処方ヘッダー操作DAO
	 */
	public void setGrecipeRecipeHeaderListDao(
			final GrecipeRecipeHeaderListDao grecipeRecipeHeaderListDao) {
		this.grecipeRecipeHeaderListDao = grecipeRecipeHeaderListDao;
	}

	/**
	 * 帳票・ラベルマスタ操作DAOを設定します。
	 * @param grecipeLabelListDao 帳票・ラベルマスタ操作DAO
	 */
	public void setGrecipeLabelListDao(
			final GrecipeLabelListDao grecipeLabelListDao) {
		this.grecipeLabelListDao = grecipeLabelListDao;
	}

	/**
	 * 工程順序コンボボックス用DAOを設定します。
	 * @param grecipeRecipeProcedureSetpNoForComboboxesDao 工程順序コンボボックス用DAO
	 */
	public void setGrecipeRecipeProcedureSetpNoForComboboxesDao(
			final GrecipeRecipeProcedureSetpNoForComboboxesDao grecipeRecipeProcedureSetpNoForComboboxesDao) {
		this.grecipeRecipeProcedureSetpNoForComboboxesDao = grecipeRecipeProcedureSetpNoForComboboxesDao;
	}
}
