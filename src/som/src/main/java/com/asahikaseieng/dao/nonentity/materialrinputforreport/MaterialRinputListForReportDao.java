/*
 * Created on 2009/08/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.materialrinputforreport;

import java.util.List;

/**
 * MaterialRinputListForReportDaoクラス
 * @author kanri-user
 */
public interface MaterialRinputListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.materialrinputforreport.MaterialRinputListForReport.class;

	/** ARGSアノテーション getReportList */
	String getReportList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List<MaterialRinputListForReport>
	 */
	List<MaterialRinputListForReport> getReportList(
			final MaterialRinputListConditionForReport condition);
}
