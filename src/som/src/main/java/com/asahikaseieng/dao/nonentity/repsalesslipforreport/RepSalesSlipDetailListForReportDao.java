/*
 * Created on 2009/07/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repsalesslipforreport;

import java.util.ArrayList;
import java.util.List;

/**
 * RepSalesSlipDetailListForReportDaoクラス
 * @author kanri-user
 */
public interface RepSalesSlipDetailListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repsalesslipforreport.RepSalesSlipDetailListForReport.class;

	/** ARGSアノテーション getDetailList */
	String getDetailList_ARGS = "slipSalesNo, salesNo";

	/**
	 * Listメソッド
	 * 
	 * @param slipSalesNo slipSalesNo
	 * @param salesNo salesNo
	 * @return List
	 */
	List<RepSalesSlipDetailListForReport> getDetailList(
			final ArrayList<String> slipSalesNo, final ArrayList<String> salesNo);
}
