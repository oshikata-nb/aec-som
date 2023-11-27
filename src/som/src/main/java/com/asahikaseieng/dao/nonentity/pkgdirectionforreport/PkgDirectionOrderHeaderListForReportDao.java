/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgdirectionforreport;

import java.util.List;

/**
 * PkgDirectionOrderHeaderListForReportDaoクラス
 * @author kanri-user
 */
public interface PkgDirectionOrderHeaderListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderHeaderListForReport.class;

	/** ARGSアノテーション getHeaderList */
	String getHeaderList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<PkgDirectionOrderHeaderListForReport>
	 */
	List<PkgDirectionOrderHeaderListForReport> getHeaderReportList(PkgDirectionOrderListConditionForReport condition
	);
}
