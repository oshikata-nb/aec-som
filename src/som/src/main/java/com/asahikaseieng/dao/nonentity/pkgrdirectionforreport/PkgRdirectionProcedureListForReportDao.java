/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgrdirectionforreport;

import java.util.List;

/**
 * PkgRdirectionProcedureListForReportDaoクラス
 * @author kanri-user
 */
public interface PkgRdirectionProcedureListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionProcedureListForReport.class;

	/** ARGSアノテーション getProcedureReportList */
	String getProcedureReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<PkgRdirectionProcedureListForReport>
	 */
	List<PkgRdirectionProcedureListForReport> getProcedureReportList(
			PkgRdirectionListConditionForReport condition);
}
