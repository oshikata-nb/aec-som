/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgrdirectionforreport;

import java.util.List;

/**
 * PkgRdirectionDetailListForReportDaoクラス
 * @author kanri-user
 */
public interface PkgRdirectionDetailListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionDetailListForReport.class;

	/** ARGSアノテーション getDetailReportList */
	String getDetailReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<PkgRdirectionDetailListForReport>
	 */
	List<PkgRdirectionDetailListForReport> getDetailReportList(
			PkgRdirectionListConditionForReport condition);
}
