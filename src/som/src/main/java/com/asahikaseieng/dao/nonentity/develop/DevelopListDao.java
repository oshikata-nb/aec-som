/*
 * Created on 2007/11/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.develop;

import java.util.List;

/**
 * DevelopListDaoクラス
 * @author FPC
 */
public interface DevelopListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.develop.DevelopList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List
	 */
	List<DevelopList> getList(final DevelopListPagerCondition condition);

	/** ARGSアノテーション getList */
	String getListExcelReport_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List
	 */
	List<DevelopList> getListExcelReport(final DevelopListPagerCondition condition);
}
