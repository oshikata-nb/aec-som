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
public interface RepSalesSlipTaxRatioListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repsalesslipforreport.RepSalesSlipTaxRatioListForReport.class;

	/** ARGSアノテーション getDetailList */
	String getTaxRatioList_ARGS = "slipSalesNo";

	/**
	 * Listメソッド
	 *
	 * @param slipSalesNo slipSalesNo
	 * @return List
	 */
	List<RepSalesSlipTaxRatioListForReport> getTaxRatioList(
			final ArrayList<String> slipSalesNo);
}
