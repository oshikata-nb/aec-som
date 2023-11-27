/*
 * Created on 2009/03/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.sql.Timestamp;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.nonentity.comboboxes.grecipe.GrecipeRecipeHeaderForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.grecipe.GrecipeRecipeHeaderForComboboxesDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeLabelListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeInspectionListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeProcedureListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeRemarkListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * ヘッダー情報 ロジック実装クラス
 * @author tosco
 */
public class GrecipeHeaderLogicImpl implements GrecipeHeaderLogic {
	/** 処方ヘッダー操作DAO */
	private GrecipeRecipeHeaderListDao grecipeRecipeHeaderListDao;
	/** 処方フォーミュラ操作DAO */
	private GrecipeRecipeFormulaListDao grecipeRecipeFormulaListDao;
	/** 処方プロシージャ操作DAO */
	private GrecipeRecipeProcedureListDao grecipeRecipeProcedureListDao;
	/** 処方検査操作DAO */
	private GrecipeRecipeInspectionListDao grecipeRecipeInspectionListDao;
	/** 処方その他操作DAO */
	private GrecipeRecipeRemarkListDao grecipeRecipeRemarkListDao;
	/** ヘッダー操作-工程パターンコンボボックス用DAO */
	private GrecipeRecipeHeaderForComboboxesDao grecipeRecipeHeaderForComboboxesDao;
	/** 処方詳細操作DAO */
	private GrecipeLabelListDao grecipeLabelListDao;

	/**
	 * コンストラクタ.
	 */
	public GrecipeHeaderLogicImpl() {
	}
	/**
	 * 工程パターン一覧取得
	 * @param use 用途
	 * @return List<GrecipeRecipeHeaderList>
	 */
	public List<GrecipeRecipeHeaderForComboboxes> getOperationPatternList(final String use) {
		return grecipeRecipeHeaderForComboboxesDao.getOperationPatternList(use);
	}

	/**
	 * 処方ヘッダにコード・バージョン・タイプが一致するデータが存在する場合取得する
	 * @param recipeCd レシピコード
	 * @param recipeVersion レシピバージョン
	 * @param type タイプ
	 * @return GrecipeRecipeHeaderList
	 * @throws NoDataException データが存在しない例外
	 */
	public GrecipeRecipeHeaderList getEntity(final String recipeCd, final String recipeVersion, final String type)
			throws NoDataException {

		GrecipeRecipeHeaderList bean = grecipeRecipeHeaderListDao.getEntity(recipeCd, recipeVersion, type);

		if (bean == null) {
			throw new NoDataException();
		}
		return bean;
	}
	/**
	 * 登録処理を行う
	 * @param bean 登録対象ビーン
	 */
	public void insert(final GrecipeRecipeHeaderList bean) {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			//処方ヘッダーに挿入
			int updateNum = grecipeRecipeHeaderListDao.insert(bean);

			if (updateNum != 1) {
				//排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}
	/**
	 * 更新処理を行う
	 * @param bean 登録対象ビーン
	 */
	public void update(final GrecipeRecipeHeaderList bean) {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			//処方ヘッダーに更新
			int updateNum = grecipeRecipeHeaderListDao.update(bean);

			if (updateNum != 1) {
				//排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}
	/**
	 * 処方ヘッダにレシピインデックスが一致するデータが存在する場合取得する
	 * @param recipeId レシピインデックス
	 * @return GrecipeRecipeHeaderList
	 * @throws NoDataException データが存在しない例外
	 */
	public GrecipeRecipeHeaderList findByPrimaryKey(final String recipeId) throws NoDataException {

		GrecipeRecipeHeaderList bean = grecipeRecipeHeaderListDao.getRecipeId(recipeId);

		if (bean == null) {
			throw new NoDataException();
		}
		return bean;
	}
	/**
	 * 処方ヘッダと紐づいている一連のテーブルの削除処理を行う
	 * @param recipeId レシピインデックス
	 * @param updateDate 更新日
	 * @throws NoDataException データ無し例外
	 */
	public void delete(final String recipeId, final Timestamp updateDate) throws NoDataException {
		try {
			//処方ヘッダーを削除
			GrecipeRecipeHeaderList bean = grecipeRecipeHeaderListDao.getRecipeId(recipeId);
			bean.setUpdateDate(updateDate);
			int deleteNum = grecipeRecipeHeaderListDao.delete(bean);

			if (deleteNum != 1) {
				throw new NoDataException();
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			//削除エラー
			throw new NoDataException();
		}
		//処方ヘッダが削除できたので、残りのテーブルを削除
		//procedure削除
		grecipeRecipeProcedureListDao.deleteByRecipeId(recipeId);
		//formula削除
		grecipeRecipeFormulaListDao.deleteByRecipeId(recipeId);
		//inspection削除
		grecipeRecipeInspectionListDao.deleteByRecipeId(recipeId);
		//remark削除
		grecipeRecipeRemarkListDao.deleteByRecipeId(recipeId);
		//詳細削除（帳票・ラベルマスタ)削除
		grecipeLabelListDao.deleteByLabelId(recipeId);
	}
	/**
	 * 基本処方のORIGINAL_RECIPE_IDに設定されている件数を取得する
	 * @param recipeId レシピインデックス
	 * @return 件数
	 */
	public int getOriginalRecipeIdCount(final String recipeId) {
		GrecipeRecipeHeaderList bean = grecipeRecipeHeaderListDao.getOriginalRecipeCount(recipeId);
		return bean.getCount().intValue();
	}
	/* -------------------- setter -------------------- */

	/**
	 * 処方ヘッダー操作DAOを設定します。
	 * @param grecipeRecipeHeaderListDao 処方ヘッダー操作DAO
	 */
	public void setGrecipeRecipeHeaderListDao(final GrecipeRecipeHeaderListDao grecipeRecipeHeaderListDao) {
		this.grecipeRecipeHeaderListDao = grecipeRecipeHeaderListDao;
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
	public void setGrecipeRecipeRemarkListDao(final GrecipeRecipeRemarkListDao grecipeRecipeRemarkListDao) {
		this.grecipeRecipeRemarkListDao = grecipeRecipeRemarkListDao;
	}
	/**
	 * ヘッダー操作-工程パターンコンボボックス用DAOを設定します。
	 * @param grecipeRecipeHeaderForComboboxesDao ヘッダー操作-工程パターンコンボボックス用DAO
	 */
	public void setGrecipeRecipeHeaderForComboboxesDao(
			final GrecipeRecipeHeaderForComboboxesDao grecipeRecipeHeaderForComboboxesDao) {
		this.grecipeRecipeHeaderForComboboxesDao = grecipeRecipeHeaderForComboboxesDao;
	}
	/**
	 * 処方詳細操作DAOを設定します。
	 * @param grecipeLabelListDao 処方詳細操作DAO
	 */
	public void setGrecipeLabelListDao(final GrecipeLabelListDao grecipeLabelListDao) {
		this.grecipeLabelListDao = grecipeLabelListDao;
	}

}
