/*
 * Created on 2009/03/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.postlistforreport;

import java.util.List;

/**
 * PostListForReportDaoクラス
 * @author t0011036
 */
public interface PostListForReportDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.postlistforreport.PostListForReport.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "condition";

	/**
	 * PostListForReportメソッド
	 * 
	 * @param condition 検索条件
	 * @return List<PostListForReport>
	 */
	List<PostListForReport> getListForReport(
			final PostListConditionForReport condition);
}
