/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgrdirectionforreport;

import java.util.List;

/**
 * PkgRdirectionHeaderListForReportDaoクラス
 * @author kanri-user
 */
public interface PkgRdirectionHeaderListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionHeaderListForReport.class;

	/** ARGSアノテーション getHeaderReportList */
	String getHeaderReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<PkgRdirectionHeaderListForReport>
	 */
	List<PkgRdirectionHeaderListForReport> getHeaderReportList(
			PkgRdirectionListConditionForReport condition);
}
