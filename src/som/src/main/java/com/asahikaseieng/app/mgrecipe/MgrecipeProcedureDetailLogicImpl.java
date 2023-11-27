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

import com.asahikaseieng.dao.entity.master.operation.Operation;
import com.asahikaseieng.dao.entity.master.operation.OperationDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeProcedureListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 基本処方検索 ロジック実装クラス
 * @author tosco
 */
public class MgrecipeProcedureDetailLogicImpl implements MgrecipeProcedureDetailLogic {

	/** 処方ヘッダー操作DAO */
	private RecipeHeaderListDao recipeHeaderListDao;
	/** 処方プロシージャDao */
	private RecipeProcedureListDao recipeProcedureListDao;
	/** 工程マスタDao */
	private OperationDao operationDao;

	/**
	 * コンストラクタ.基本処方検索
	 */
	public MgrecipeProcedureDetailLogicImpl() {
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

		RecipeHeaderList header = recipeHeaderListDao.getProcedureHeader(
			recipeId, new BigDecimal(RecipeHeaderListDao.RECIPE_TYPE_MASTER), stepNo);
		if (header == null) {
			//データが存在しなかった場合は例外発生
			throw new NoDataException();
		}
		return header;
	}

	/**
	 * 処方プロシージャ検索処理を行う.
	 * @param recipeId レシピインデックス
	 * @param stepNo   STEP_NO
	 * @return RecipeProcedureList 検索結果Bean
	 * @throws NoDataException データが存在しない例外
	 */
	public RecipeProcedureList getEntity(final BigDecimal recipeId, final BigDecimal stepNo)
	throws NoDataException {
		checkParams(recipeId);
		checkParams(stepNo);

		RecipeProcedureList bean = recipeProcedureListDao.getEntity(recipeId, stepNo);
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
	public ActionMessages checkForUpdate(final RecipeProcedureList bean) {
		ActionMessages errors = new ActionMessages();

		if (bean == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForUpdate");
		}

		// 工程マスタを検索
		Operation opeBean = operationDao.getEntity(bean.getOperationCd());

		if (opeBean == null) {
			// データが存在しない場合
			errors = MgrecipeUtil.addError(errors, "errors.mgrecipe.no.operation");
		}

		return errors;
	}

	/**
	 * 処方プロシージャ更新処理を行う.
	 * @param bean 処方プロシージャBean
	 * @throws Exception データが存在しない例外
	 */
	public void update(final RecipeProcedureList bean) throws Exception {
		try {
			// 更新処理
			recipeProcedureListDao.update(bean);
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
	 * 処方プロシージャDaoを設定します。
	 * @param recipeProcedureListDao 処方プロシージャDao
	 */
	public void setRecipeProcedureListDao(final RecipeProcedureListDao recipeProcedureListDao) {
		this.recipeProcedureListDao = recipeProcedureListDao;
	}
	/**
	 * 工程マスタDaoを設定します。
	 * @param operationDao 工程マスタDao
	 */
	public void setOperationDao(final OperationDao operationDao) {
		this.operationDao = operationDao;
	}

}
