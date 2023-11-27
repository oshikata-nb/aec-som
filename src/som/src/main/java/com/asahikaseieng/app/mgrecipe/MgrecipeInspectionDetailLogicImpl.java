/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.entity.master.names.Names;
import com.asahikaseieng.dao.entity.master.names.NamesDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeInspectionList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeInspectionListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 基本処方検索 ロジック実装クラス
 * @author tosco
 */
public class MgrecipeInspectionDetailLogicImpl implements MgrecipeInspectionDetailLogic {

	/** 処方ヘッダー操作DAO */
	private RecipeHeaderListDao recipeHeaderListDao;
	/** 処方検査Dao */
	private RecipeInspectionListDao recipeInspectionListDao;
	/** NamesDao */
	private NamesDao namesDao;

	/**
	 * コンストラクタ.基本処方検索
	 */
	public MgrecipeInspectionDetailLogicImpl() {
	}

	/**
	 * ヘッダー部のデータを処方ヘッダーから取得する。
	 * @param recipeId レシピインデックス
	 * @param stepNo   STEP_NO
	 * @return RecipeHeaderList 検索結果データ
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	public RecipeHeaderList getHeader(final BigDecimal recipeId, final BigDecimal stepNo) throws NoDataException {
		checkParams(recipeId);
		checkParams(stepNo);

		RecipeHeaderList header = recipeHeaderListDao.getInspectionHeader(
			recipeId, new BigDecimal(RecipeHeaderListDao.RECIPE_TYPE_MASTER), stepNo);
		if (header == null) {
			//データが存在しなかった場合は例外発生
			throw new NoDataException();
		}
		return header;
	}

	/**
	 * 処方検査-検索処理を行う.
	 * @param recipeId レシピインデックス
	 * @param stepNo STEP_NO
	 * @param lineNo LINE_NO
	 * @return RecipeInspectionList 検索結果Bean
	 * @throws NoDataException データが存在しない例外
	 */
	public RecipeInspectionList getEntity(final BigDecimal recipeId,
			final BigDecimal stepNo, final BigDecimal lineNo) throws NoDataException {
		checkParams(recipeId);
		checkParams(stepNo);
		checkParams(lineNo);

		RecipeInspectionList bean = recipeInspectionListDao.getEntity(recipeId, stepNo, lineNo);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 更新時のマスタチェックを行う.<br>
	 * 　工程マスタにデータがない場合はエラーとする。
	 * @param bean 処方プロシージャBean
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForUpdate(final RecipeInspectionList bean) {
		ActionMessages errors = new ActionMessages();

		if (bean == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForUpdate");
		}

		// 各種名称マスタを検索
		Names nameBean = namesDao.getEntity(bean.getInspectionCd(), "STDV");

		if (nameBean == null) {
			// データが存在しない場合
			errors = MgrecipeUtil.addError(errors, "errors.mgrecipe.no.names");
		}

		return errors;
	}

	/**
	 * 処方検査更新処理を行う.
	 * @param bean 処方プロシージャBean
	 * @throws Exception データが存在しない例外
	 */
	public void update(final RecipeInspectionList bean) throws Exception {
		try {
			// 更新処理
			recipeInspectionListDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}


	/**
	 * パラメータチェック
	 * @param recipeId レシピインデックス
	 */
	private void checkParams(final BigDecimal recipeId) {

		if (recipeId == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getEntity");
		}
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
	 * 処方検査Daoを設定します。
	 * @param recipeInspectionListDao 処方検査Dao
	 */
	public void setRecipeInspectionListDao(final RecipeInspectionListDao recipeInspectionListDao) {
		this.recipeInspectionListDao = recipeInspectionListDao;
	}
	/**
	 * NamesDaoを設定します。
	 * @param namesDao NamesDao
	 */
	public void setNamesDao(final NamesDao namesDao) {
		this.namesDao = namesDao;
	}
}
