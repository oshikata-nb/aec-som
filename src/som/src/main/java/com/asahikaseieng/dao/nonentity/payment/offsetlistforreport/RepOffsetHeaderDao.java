/*
 * Created on 2009/07/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offsetlistforreport;

import java.util.ArrayList;
import java.util.List;

/**
 * RepOffsetHeaderDaoクラス
 * @author kanri-user
 */
public interface RepOffsetHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.offsetlistforreport.RepOffsetHeader.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "offsetNo";

	/**
	 * RepOffsetHeaderメソッド
	 * 
	 * @param offsetno offsetno
	 * @return RepOffsetHeader
	 */
	List<RepOffsetHeader> getListForReport(final ArrayList<String> offsetno);
}
