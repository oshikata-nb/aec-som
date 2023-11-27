/*
 * Created on 2008/10/10
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.claim.eraser;

import java.util.List;

import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserCsmList;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserCsmListDao;
import com.asahikaseieng.dao.nonentity.claim.eraser.ClaimEraserCsmListPagerCondition;
import com.asahikaseieng.dao.nonentity.claim.eraserlistforreport.ClaimEraserCsmListConditionForReport;
import com.asahikaseieng.dao.nonentity.claim.eraserlistforreport.ClaimEraserCsmListForReport;
import com.asahikaseieng.dao.nonentity.claim.eraserlistforreport.ClaimEraserCsmListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 消込入力一覧 ロジック実装クラス(カスタマイズ)
 * @author tosco
 */
public class EraserCsmListLogicImpl implements EraserCsmListLogic {

	private ClaimEraserCsmListDao eraserCsmListDao;

	private ClaimEraserCsmListForReportDao eraserCsmListForReportDao;

	/**
	 * コンストラクタ.消込入力一覧
	 */
	public EraserCsmListLogicImpl() {
	}

	/**
	 * 消込入力一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<ClaimEraserCsmList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<ClaimEraserCsmList> getSearchList(
			final ClaimEraserCsmListPagerCondition condition)
			throws NoDataException {
		checkParams(condition);
		List<ClaimEraserCsmList> list = eraserCsmListDao
				.getSearchList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * パラメータチェック.getSearchList
	 * @param condition 検索条件
	 */
	private void checkParams(final ClaimEraserCsmListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * 入金消込一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<ClaimEraserCsmListForReport>
	 */
	public List<ClaimEraserCsmListForReport> getListForReport(
			final ClaimEraserCsmListConditionForReport condition) {
		List<ClaimEraserCsmListForReport> list = eraserCsmListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * eraserCsmListDaoを設定します。
	 * @param eraserCsmListDao EraserCsmListDao
	 */
	public void setEraserCsmListDao(final ClaimEraserCsmListDao eraserCsmListDao) {
		this.eraserCsmListDao = eraserCsmListDao;
	}

	/**
	 * eraserCsmListForReportDaoを設定します。
	 * @param eraserCsmListForReportDao eraserCsmListForReportDao
	 */
	public void setEraserCsmListForReportDao(
			final ClaimEraserCsmListForReportDao eraserCsmListForReportDao) {
		this.eraserCsmListForReportDao = eraserCsmListForReportDao;
	}
}
