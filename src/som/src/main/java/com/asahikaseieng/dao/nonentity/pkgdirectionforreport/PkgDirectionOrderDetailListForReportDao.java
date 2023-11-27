/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgdirectionforreport;

import java.util.List;

/**
 * PkgDirectionOrderDetailListForReportDaoクラス
 * @author kanri-user
 */
public interface PkgDirectionOrderDetailListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderDetailListForReport.class;

	/** ARGSアノテーション getDetailList */
	String getDetailList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<PkgDirectionOrderDetailListForReport>
	 */
	List<PkgDirectionOrderDetailListForReport> getDetailReportList(
			PkgDirectionOrderListConditionForReport condition);
}
