/*
 * Created on 2009/05/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repSlipShippingOrderDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * RepSlipShippingOrderDetailDaoクラス
 * @author kanri-user
 */
public interface RepSlipShippingOrderDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repSlipShippingOrderDetail.RepSlipShippingOrderDetail.class;

	/** ARGSアノテーション getSlipShippingList */
	String getSlipShippingList_ARGS = "slipShippingNo, shippingNo";

	/**
	 * Listメソッド
	 * 
	 * @param slipShippingNo slipShippingNo
	 * @param shippingNo shippingNo
	 * @return List
	 */
	List<RepSlipShippingOrderDetail> getSlipShippingList(
			final ArrayList<String> slipShippingNo,
			final ArrayList<String> shippingNo);
}
