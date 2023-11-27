/*
 * Created on 2009/02/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.mgrecipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeItemQueueDetail;
import com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeItemQueueDetailDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderList;
import com.asahikaseieng.dao.nonentity.mgrecipe.RecipeHeaderListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 基本処方検索 ロジック実装クラス
 * @author tosco
 */
public class MgrecipeFormulaDetailLogicImpl implements
		MgrecipeFormulaDetailLogic {

	/** 処方ヘッダー操作DAO */
	private RecipeHeaderListDao recipeHeaderListDao;

	/** 処方フォーミュラDao */
	private RecipeFormulaListDao recipeFormulaListDao;

	/** 品目マスタ検索Dao */
	private MgrecipeItemQueueDetailDao mgrecipeItemQueueDetailDao;

	/**
	 * コンストラクタ.基本処方検索
	 */
	public MgrecipeFormulaDetailLogicImpl() {
	}

	/**
	 * ヘッダー部のデータを処方ヘッダーから取得する。
	 * @param recipeId レシピインデックス
	 * @param stepNo STEP_NO
	 * @return RecipeHeaderList 検索結果データ
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	public RecipeHeaderList getHeader(final BigDecimal recipeId,
			final BigDecimal stepNo) throws NoDataException {
		checkParams(recipeId);
		checkParams(stepNo);

		RecipeHeaderList header = recipeHeaderListDao.getFormulaHeader(
			recipeId, new BigDecimal(RecipeHeaderListDao.RECIPE_TYPE_MASTER),
			stepNo);
		if (header == null) {
			// データが存在しなかった場合は例外発生
			throw new NoDataException();
		}
		return header;
	}

	/**
	 * 処方フォーミュラ検索処理を行う.
	 * @param recipeId レシピインデックス
	 * @param stepNo STEP_NO
	 * @param lineNo LINE_NO
	 * @return RecipeProcedureList 検索結果Bean
	 * @throws NoDataException データが存在しない例外
	 */
	public RecipeFormulaList getEntity(final BigDecimal recipeId,
			final BigDecimal stepNo, final BigDecimal lineNo)
			throws NoDataException {
		checkParams(recipeId);
		checkParams(stepNo);
		checkParams(lineNo);

		RecipeFormulaList bean = recipeFormulaListDao.getEntity(recipeId,
			stepNo, lineNo);
		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 更新時のマスタチェックを行う.<br>
	 * 品目マスタにデータがない場合はエラーとする。
	 * @param bean 処方フォーミュラBean
	 * @return ActionMessages エラー内容
	 */
	public ActionMessages checkForUpdate(final RecipeFormulaList bean) {
		ActionMessages errors = new ActionMessages();

		if (bean == null) {
			throw new IllegalArgumentException(
				"IllegalArgumentException : Paramater is empty.パラメータチェック.checkForUpdate");
		}

		// 品目マスタを検索
		MgrecipeItemQueueDetail opeBean = mgrecipeItemQueueDetailDao
				.getEntity(bean.getItemCd());

		if (opeBean == null) {
			// データが存在しない場合
			errors = MgrecipeUtil.addError(errors,
				"errors.mgrecipe.no.item.queue");
		}

		return errors;
	}

	/**
	 * 処方フォーミュラ更新処理を行う.
	 * @param bean 処方フォーミュラBean
	 * @param form 配合詳細情報
	 * @throws Exception データが存在しない例外
	 */
	public void update(final RecipeFormulaList bean,
			final MgrecipeFormulaDetailForm form) throws Exception {
		try {
			// 更新処理
			recipeFormulaListDao.update(bean);

			// 処方ヘッダを更新
			updateHeader(form);

		} catch (NotSingleRowUpdatedRuntimeException e) {
			// 排他エラー
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 処方ヘッダを更新
	 * @param frm 配合詳細情報
	 * @throws NoDataException
	 */
	private void updateHeader(final MgrecipeFormulaDetailForm frm)
			throws NoDataException {
		// 配合リスト取得
		List<RecipeFormulaList> searchList = recipeFormulaListDao
				.getSearchList(new BigDecimal(frm.getRecipeId()),
					BigDecimal.ZERO);
		// 表示時ヘッダ情報取得
		RecipeHeaderList headBean = frm.getHeaderBean();
		// ヘッダ更新用の収率を再計算
		BigDecimal processLoss = MgrecipeUtil.calcProcessLoss(searchList,
			headBean.getStdQty(), 4, RoundingMode.HALF_UP);
		// 収率を再計算
		headBean.setProcessLoss(processLoss);

		if (headBean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		// 処方ヘッダーに更新
		int updateNum = recipeHeaderListDao.update(headBean);
		if (updateNum != 1) {
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
	public void setRecipeHeaderListDao(
			final RecipeHeaderListDao recipeHeaderListDao) {
		this.recipeHeaderListDao = recipeHeaderListDao;
	}

	/**
	 * 処方フォーミュラDaoを設定します。
	 * @param recipeFormulaListDao 処方フォーミュラDao
	 */
	public void setRecipeFormulaListDao(
			final RecipeFormulaListDao recipeFormulaListDao) {
		this.recipeFormulaListDao = recipeFormulaListDao;
	}

	/**
	 * mgrecipeItemQueueDetailDaoを設定します。
	 * @param mgrecipeItemQueueDetailDao mgrecipeItemQueueDetailDao
	 */
	public void setMgrecipeItemQueueDetailDao(
			final MgrecipeItemQueueDetailDao mgrecipeItemQueueDetailDao) {
		this.mgrecipeItemQueueDetailDao = mgrecipeItemQueueDetailDao;
	}
}
