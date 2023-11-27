/*
 * Created on 2009/05/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repSlipShippingOrderHead;

import java.util.ArrayList;
import java.util.List;

/**
 * RepSlipShippingOrderHeadDaoクラス
 * @author kanri-user
 */
public interface RepSlipShippingOrderHeadDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repSlipShippingOrderHead.RepSlipShippingOrderHead.class;

	/** ARGSアノテーション getSlipShippingList */
	String getSlipShippingList_ARGS = "shippingOrderNo";

	/**
	 * Listメソッド
	 * 
	 * @param shippingOrderNo shippingOrderNo
	 * @return List
	 */
	List<RepSlipShippingOrderHead> getSlipShippingList(
			final ArrayList<String> shippingOrderNo);
}
