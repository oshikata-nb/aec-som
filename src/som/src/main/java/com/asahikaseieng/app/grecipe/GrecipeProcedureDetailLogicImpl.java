/*
 * Created on 2009/01/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.entity.master.operation.Operation;
import com.asahikaseieng.dao.entity.master.operation.OperationDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeProcedureList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeProcedureListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 原処方-工程詳細 ロジック実装クラス
 * @author tosco
 */
public class GrecipeProcedureDetailLogicImpl implements GrecipeProcedureDetailLogic {

	/** 処方ヘッダー操作DAO */
	private GrecipeRecipeHeaderListDao grecipeRecipeHeaderListDao;
	/** 処方プロシージャDao */
	private GrecipeRecipeProcedureListDao grecipeRecipeProcedureListDao;
	/** 工程マスタDao */
	private OperationDao operationDao;

	/**
	 * コンストラクタ
	 */
	public GrecipeProcedureDetailLogicImpl() {
	}

	/**
	 * ヘッダー部のデータを処方ヘッダーから取得する。
	 * @param recipeId レシピインデックス
	 * @param stepNo   STEP_NO
	 * @return GrecipeRecipeHeaderList 検索結果データ
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	public GrecipeRecipeHeaderList getHeader
		(final BigDecimal recipeId, final BigDecimal stepNo) throws NoDataException {
		checkParams(recipeId);
		checkParams(stepNo);

		GrecipeRecipeHeaderList header = grecipeRecipeHeaderListDao.getProcedureHeader(
			recipeId, new BigDecimal(GrecipeRecipeHeaderListDao.RECIPE_TYPE_GENERAL), stepNo);
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
	 * @return GrecipeRecipeProcedureList 検索結果Bean
	 * @throws NoDataException データが存在しない例外
	 */
	public GrecipeRecipeProcedureList getEntity(final BigDecimal recipeId, final BigDecimal stepNo)
	throws NoDataException {
		checkParams(recipeId);
		checkParams(stepNo);

		GrecipeRecipeProcedureList bean = grecipeRecipeProcedureListDao.getEntity(recipeId, stepNo);
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
	public ActionMessages checkForUpdate(final GrecipeRecipeProcedureList bean) {
		ActionMessages errors = new ActionMessages();

		if (bean == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForUpdate");
		}

		// 工程マスタを検索
		Operation opeBean = operationDao.getEntity(bean.getOperationCd());

		if (opeBean == null) {
			// データが存在しない場合
			errors = GrecipeUtil.addError(errors, "errors.grecipe.no.operation");
		}

		return errors;
	}

	/**
	 * 処方プロシージャ更新処理を行う.
	 * @param bean 処方プロシージャBean
	 * @throws Exception データが存在しない例外
	 */
	public void update(final GrecipeRecipeProcedureList bean) throws Exception {
		try {
			// 更新処理
			grecipeRecipeProcedureListDao.update(bean);
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
	 * @param grecipeRecipeHeaderListDao 処方ヘッダー操作DAO
	 */
	public void setGrecipeRecipeHeaderListDao(final GrecipeRecipeHeaderListDao grecipeRecipeHeaderListDao) {
		this.grecipeRecipeHeaderListDao = grecipeRecipeHeaderListDao;
	}
	/**
	 * 処方プロシージャDaoを設定します。
	 * @param grecipeRecipeProcedureListDao 処方プロシージャDao
	 */
	public void setGrecipeRecipeProcedureListDao
		(final GrecipeRecipeProcedureListDao grecipeRecipeProcedureListDao) {
		this.grecipeRecipeProcedureListDao = grecipeRecipeProcedureListDao;
	}
	/**
	 * 工程マスタDaoを設定します。
	 * @param operationDao 工程マスタDao
	 */
	public void setOperationDao(final OperationDao operationDao) {
		this.operationDao = operationDao;
	}

}
