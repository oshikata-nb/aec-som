/*
 * Created on 2009/03/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.grecipe;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.apache.struts.action.ActionMessages;
import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.comboboxes.SelectRecipeUse;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeItemQueueDetail;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeItemQueueDetailDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeFormulaListDao;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderList;
import com.asahikaseieng.dao.nonentity.grecipe.GrecipeRecipeHeaderListDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 原処方検索 ロジック実装クラス
 * @author tosco
 */
public class GrecipeFormulaDetailLogicImpl implements GrecipeFormulaDetailLogic {

	/** 処方ヘッダー操作DAO */
	private GrecipeRecipeHeaderListDao recipeRecipeHeaderListDao;

	/** 処方フォーミュラDao */
	private GrecipeRecipeFormulaListDao grecipeRecipeFormulaListDao;

	/** 品目マスタ検索Dao */
	private GrecipeItemQueueDetailDao grecipeItemQueueDetailDao;

	/**
	 * コンストラクタ.原処方検索
	 */
	public GrecipeFormulaDetailLogicImpl() {
	}

	/**
	 * ヘッダー部のデータを処方ヘッダーから取得する。
	 * @param recipeId レシピインデックス
	 * @param stepNo STEP_NO
	 * @return GrecipeRecipeHeaderList 検索結果データ
	 * @throws NoDataException データが存在しなかった場合は例外発生
	 */
	public GrecipeRecipeHeaderList getHeader(final BigDecimal recipeId,
			final BigDecimal stepNo) throws NoDataException {
		checkParams(recipeId);
		checkParams(stepNo);

		GrecipeRecipeHeaderList header = recipeRecipeHeaderListDao
				.getFormulaHeader(recipeId, stepNo);
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
	public GrecipeRecipeFormulaList getEntity(final BigDecimal recipeId,
			final BigDecimal stepNo, final BigDecimal lineNo)
			throws NoDataException {
		checkParams(recipeId);
		checkParams(stepNo);
		checkParams(lineNo);

		GrecipeRecipeFormulaList bean = grecipeRecipeFormulaListDao.getEntity(
			recipeId, stepNo, lineNo);
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
	public ActionMessages checkForUpdate(final GrecipeRecipeFormulaList bean) {
		ActionMessages errors = new ActionMessages();

		if (bean == null) {
			throw new IllegalArgumentException("IllegalArgumentException : "
					+ "Paramater is empty.パラメータチェック.checkForUpdate");
		}

		// 品目マスタを検索
		GrecipeItemQueueDetail opeBean = grecipeItemQueueDetailDao
				.getEntity(bean.getItemCd());

		if (opeBean == null) {
			// データが存在しない場合
			errors = GrecipeUtil.addError(errors,
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
	public void update(final GrecipeRecipeFormulaList bean,
			final GrecipeFormulaDetailForm form) throws Exception {
		try {
			// 更新処理
			grecipeRecipeFormulaListDao.update(bean);

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
	private void updateHeader(final GrecipeFormulaDetailForm frm)
			throws NoDataException {
		// 配合リスト取得
		List<GrecipeRecipeFormulaList> searchList = grecipeRecipeFormulaListDao
				.getSearchList(new BigDecimal(frm.getRecipeId()),
					BigDecimal.ZERO);
		// 表示時ヘッダ情報取得
		GrecipeRecipeHeaderList headBean = frm.getHeaderBean();
		if (headBean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		// 収率
		BigDecimal processLoss = null;
		if (SelectRecipeUse.RECIPE_USE_PRODUCTION.equals(frm.getRecipeUse())) {
			// 製造時
			// ヘッダ更新用の収率を再計算
			processLoss = GrecipeUtil.calcProcessLoss(searchList, headBean
					.getStdQty(), 4, RoundingMode.HALF_UP);
		} else {
			// 包装時
			processLoss = GrecipeConst.PACKAGE_PROCESS_LOSS;
		}
		// 収率
		headBean.setProcessLoss(processLoss);

		// 処方ヘッダーに更新
		int updateNum = recipeRecipeHeaderListDao.update(headBean);
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
	 * @param recipeRecipeHeaderListDao 処方ヘッダー操作DAO
	 */
	public void setRecipeRecipeHeaderListDao(
			final GrecipeRecipeHeaderListDao recipeRecipeHeaderListDao) {
		this.recipeRecipeHeaderListDao = recipeRecipeHeaderListDao;
	}

	/**
	 * 処方フォーミュラDaoを設定します。
	 * @param grecipeRecipeFormulaListDao 処方フォーミュラDao
	 */
	public void setGrecipeRecipeFormulaListDao(
			final GrecipeRecipeFormulaListDao grecipeRecipeFormulaListDao) {
		this.grecipeRecipeFormulaListDao = grecipeRecipeFormulaListDao;
	}

	/**
	 * grecipeItemQueueDetailDaoを設定します。
	 * @param grecipeItemQueueDetailDao grecipeItemQueueDetailDao
	 */
	public void setGrecipeItemQueueDetailDao(
			final GrecipeItemQueueDetailDao grecipeItemQueueDetailDao) {
		this.grecipeItemQueueDetailDao = grecipeItemQueueDetailDao;
	}
}
