/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgrdirectionforreport;

import java.util.List;

/**
 * PkgRdirectionFormulaListForReportDaoクラス
 * @author kanri-user
 */
public interface PkgRdirectionFormulaListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionFormulaListForReport.class;

	/** ARGSアノテーション getFormulaReportList */
	String getFormulaReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<PkgRdirectionFormulaListForReport>
	 */
	List<PkgRdirectionFormulaListForReport> getFormulaReportList(
			PkgRdirectionListConditionForReport condition);
}
