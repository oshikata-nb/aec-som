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
 * RepSalesSlipHeadListForReportDaoクラス
 * @author kanri-user
 */
public interface RepSalesSlipHeadListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repsalesslipforreport.RepSalesSlipHeadListForReport.class;

	/** ARGSアノテーション getHeadList */
	String getHeadList_ARGS = "salesOrderNo";

	/**
	 * Listメソッド
	 * 
	 * @param salesOrderNo salesOrderNo
	 * @return List
	 */
	List<RepSalesSlipHeadListForReport> getHeadList(final ArrayList<String> salesOrderNo);
}
