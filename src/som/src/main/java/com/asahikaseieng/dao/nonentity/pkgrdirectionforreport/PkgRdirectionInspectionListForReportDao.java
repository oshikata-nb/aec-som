/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgrdirectionforreport;

import java.util.List;

/**
 * PkgRdirectionInspectionListForReportDaoクラス
 * @author kanri-user
 */
public interface PkgRdirectionInspectionListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionInspectionListForReport.class;

	/** ARGSアノテーション getInspectionReportList */
	String getInspectionReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<PkgRdirectionInspectionListForReport>
	 */
	List<PkgRdirectionInspectionListForReport> getInspectionReportList(
			PkgRdirectionListConditionForReport condition);
}
