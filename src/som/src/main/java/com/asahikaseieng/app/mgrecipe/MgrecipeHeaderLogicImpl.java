/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.sql.Timestamp;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe.RecipeHeaderForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.mgrecipe.RecipeHeaderForComboboxesDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeLabelListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeAsprovaListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeInspectionListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeRemarkListDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * ヘッダー情報 ロジック実装クラス
 * @author tosco
 */
public class MgrecipeHeaderLogicImpl implements MgrecipeHeaderLogic {
	/** 処方ヘッダー操作DAO */
	private RecipeHeaderListDao recipeHeaderListDao;
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
	/** ヘッダー操作-工程パターンコンボボックス用DAO */
	private RecipeHeaderForComboboxesDao recipeHeaderForComboboxesDao;
	/** 処方詳細操作DAO */
	private MgrecipeLabelListDao mgrecipeLabelListDao;

	/**
	 * コンストラクタ.
	 */
	public MgrecipeHeaderLogicImpl() {
	}
	/**
	 * 工程パターン一覧取得
	 * @param use 用途
	 * @return List<RecipeHeaderList>
	 */
	public List<RecipeHeaderForComboboxes> getOperationPatternList(final String use) {
		return recipeHeaderForComboboxesDao.getOperationPatternList(use);
	}

	/**
	 * 処方ヘッダにコード・バージョン・タイプが一致するデータが存在する場合取得する
	 * @param recipeCd レシピコード
	 * @param recipeVersion レシピバージョン
	 * @param type タイプ
	 * @return RecipeHeaderList
	 * @throws NoDataException データが存在しない例外
	 */
	public RecipeHeaderList getEntity(final String recipeCd, final String recipeVersion, final String type)
			throws NoDataException {

		RecipeHeaderList bean = recipeHeaderListDao.getEntity(recipeCd, recipeVersion, type);

		if (bean == null) {
			throw new NoDataException();
		}
		return bean;
	}
	/**
	 * 登録処理を行う
	 * @param bean 登録対象ビーン
	 */
	public void insert(final RecipeHeaderList bean) {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			//処方ヘッダーに挿入
			int updateNum = recipeHeaderListDao.insert(bean);

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
	public void update(final RecipeHeaderList bean) {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			//処方ヘッダーに更新
			int updateNum = recipeHeaderListDao.update(bean);

			if (updateNum != 1) {
				//排他エラー
				throw new OptimisticLockRuntimeException();
			}
		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}
	}
	/**
	 * 処方ヘッダにコード・バージョン・タイプが一致するデータが存在する場合取得する
	 * @param recipeId レシピインデックス
	 * @param recipeType レシピタイプ
	 * @return RecipeHeaderList
	 * @throws NoDataException データが存在しない例外
	 */
	public RecipeHeaderList findByPrimaryKey(final String recipeId, final String recipeType)
	throws NoDataException {

		RecipeHeaderList bean = recipeHeaderListDao.findByPrimaryKey(recipeId, recipeType);

		if (bean == null) {
			throw new NoDataException();
		}
		return bean;
	}
	/**
	 * 処方プロシージャ削除処理を行う
	 * @param recipeId レシピインデックス
	 * @param updateDate 更新日
	 * @throws NoDataException データ無し例外
	 */
	public void delete(final String recipeId, final Timestamp updateDate) throws NoDataException {
		try {
			//処方ヘッダーを削除
			RecipeHeaderList bean = recipeHeaderListDao.findByPrimaryKey
						(recipeId, RecipeHeaderListDao.RECIPE_TYPE_MASTER);
			int deleteNum = recipeHeaderListDao.delete(bean);

			if (deleteNum != 1) {
				throw new NoDataException();
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			//削除エラー
			throw new NoDataException();
		}
		//処方ヘッダが削除できたので、残りのテーブルを削除
		//procedure削除
		recipeProcedureListDao.deleteByRecipeId(recipeId);
		//formula削除
		recipeFormulaListDao.deleteByRecipeId(recipeId);
		//inspection削除
		recipeInspectionListDao.deleteByRecipeId(recipeId);
		//asprova削除
		recipeAsprovaListDao.deleteByRecipeId(recipeId);
		//remark削除
		recipeRemarkListDao.deleteByRecipeId(recipeId);
		//詳細削除（帳票・ラベルマスタ)削除
		mgrecipeLabelListDao.deleteByLabelId(recipeId);
	}

	/* -------------------- setter -------------------- */

	/**
	 * 処方ヘッダー操作DAOを設定します。
	 * @param recipeHeaderListDao 処方ヘッダー操作DAO
	 */
	public void setRecipeHeaderListDao(final RecipeHeaderListDao recipeHeaderListDao) {
		this.recipeHeaderListDao = recipeHeaderListDao;
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
	public void setRecipeRemarkListDao(final RecipeRemarkListDao recipeRemarkListDao) {
		this.recipeRemarkListDao = recipeRemarkListDao;
	}
	/**
	 * ヘッダー操作-工程パターンコンボボックス用DAOを設定します。
	 * @param recipeHeaderForComboboxesDao ヘッダー操作-工程パターンコンボボックス用DAO
	 */
	public void setRecipeHeaderForComboboxesDao(
			final RecipeHeaderForComboboxesDao recipeHeaderForComboboxesDao) {
		this.recipeHeaderForComboboxesDao = recipeHeaderForComboboxesDao;
	}
	/**
	 * 処方詳細操作DAOを設定します。
	 * @param mgrecipeLabelListDao 処方詳細操作DAO
	 */
	public void setMgrecipeLabelListDao(final MgrecipeLabelListDao mgrecipeLabelListDao) {
		this.mgrecipeLabelListDao = mgrecipeLabelListDao;
	}

}
