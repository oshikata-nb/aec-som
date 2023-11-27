/*
 * Created on 2009/06/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repLabelArrival;

import java.util.List;

/**
 * RepLabelArrivalDaoクラス
 * @author kanri-user
 */
public interface RepLabelArrivalDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repLabelArrival.RepLabelArrival.class;

	/** ARGSアノテーション getRepLabelArrivalList */
	String getRepLabelArrivalList_ARGS = "buySubcontractOrderNo";

	/**
	 * Listメソッド
	 * 
	 * @param lotno lotno
	 * @return List
	 */
	List<RepLabelArrival> getRepLabelArrivalList(final String[] lotno);
}
