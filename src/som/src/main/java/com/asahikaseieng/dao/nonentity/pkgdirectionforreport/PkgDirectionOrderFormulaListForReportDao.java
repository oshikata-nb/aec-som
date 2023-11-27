/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgdirectionforreport;

import java.util.List;

/**
 * PkgDirectionOrderFormulaListForReportDaoクラス
 * @author kanri-user
 */
public interface PkgDirectionOrderFormulaListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderFormulaListForReport.class;

	/** ARGSアノテーション getFormulaList */
	String getFormulaList_ARGS = "condition";

	/**
	 * Listメソッド
	 *@param condition condition
	 * @return List<PkgDirectionOrderFormulaListForRepor>
	 */
	List<PkgDirectionOrderFormulaListForReport> getFormulaReportList(PkgDirectionOrderListConditionForReport condition
	);
}
