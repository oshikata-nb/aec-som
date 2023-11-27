/*
 * Created on 2009/07/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repSlipShippingDeliveryDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * RepSlipShippingDeliveryDetailDaoクラス
 * @author kanri-user
 */
public interface RepSlipShippingDeliveryDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repSlipShippingDeliveryDetail.RepSlipShippingDeliveryDetail.class;

	/** ARGSアノテーション getSlipShippingList */
	String getSlipShippingList_ARGS = "shippingNo";

	/**
	 * Listメソッド
	 * 
	 * @param shippingNo shippingNo
	 * @return List
	 */
	List<RepSlipShippingDeliveryDetail> getSlipShippingList(
			final ArrayList<String> shippingNo);
}
