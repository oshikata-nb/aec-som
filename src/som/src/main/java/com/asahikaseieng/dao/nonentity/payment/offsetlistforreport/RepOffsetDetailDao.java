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
 * RepOffsetDetailDaoクラス
 * @author kanri-user
 */
public interface RepOffsetDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.offsetlistforreport.RepOffsetDetail.class;

	/** ARGSアノテーション getListForReport */
	String getListForReport_ARGS = "offsetNo";

	/**
	 * RepOffsetDetailメソッド
	 * 
	 * @param offsetno offsetno
	 * @return RepOffsetDetail
	 */
	List<RepOffsetDetail> getListForReport(final ArrayList<String> offsetno);
}
