/*
 * Created on 2017/06/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repSlipShippingFareDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * RepSlipShippingDeliveryDetailDaoクラス
 * @author kanri-user
 */
public interface RepSlipShippingFareDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repSlipShippingFareDetail.RepSlipShippingFareDetail.class;

	/** ARGSアノテーション getSlipShippingList */
	String getSlipShippingList_ARGS = "shippingNo";

	/**
	 * Listメソッド
	 * 
	 * @param shippingNo shippingNo
	 * @return List
	 */
	List<RepSlipShippingFareDetail> getSlipShippingList(
			final ArrayList<String> shippingNo);
}
