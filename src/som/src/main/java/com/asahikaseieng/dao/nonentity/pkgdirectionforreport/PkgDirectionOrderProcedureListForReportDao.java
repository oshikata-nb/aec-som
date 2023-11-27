/*
 * Created on 2009/09/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.pkgdirectionforreport;

import java.util.List;

/**
 * PkgDirectionOrderProcedureListForReportDaoクラス
 * @author kanri-user
 */
public interface PkgDirectionOrderProcedureListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderProcedureListForReport.class;

	/** ARGSアノテーション getProcedureList */
	String getProcedureList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<PkgDirectionOrderProcedureListForReport>
	 */
	List<PkgDirectionOrderProcedureListForReport> getProcedureReportList(
			PkgDirectionOrderListConditionForReport condition);
}
