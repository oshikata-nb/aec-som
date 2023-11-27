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
 * RepSalesSlipListForReportDaoクラス
 * @author kanri-user
 */
public interface RepSalesSlipListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repsalesslipforreport.RepSalesSlipListForReport.class;

	/** ARGSアノテーション getSalesList */
	String getSalesList_ARGS = "shippingNo";

	/**
	 * Listメソッド
	 * 
	 * @param salesNo salesNo
	 * @return List<RepSalesSlipListForReport>
	 */
	List<RepSalesSlipListForReport> getSalesList(final ArrayList<String> salesNo);
}
