/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraserlistforreport;

import java.util.List;

/**
 * ClaimEraserCsmListForReportDaoクラス
 * @author t0011036
 */
public interface ClaimEraserCsmListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.claim.eraserlistforreport.ClaimEraserCsmListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * ClaimEraserCsmListForReportメソッド
	 * @param condition 検索条件
	 * @return List<ClaimEraserCsmListForReport>
	 */
	List<ClaimEraserCsmListForReport> getListForReport(
			final ClaimEraserCsmListConditionForReport condition);
}
