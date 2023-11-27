/*
 * Created on 2009/07/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repSlipShippingDeliveryHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * RepSlipShippingDeliveryHeaderDaoクラス
 * @author kanri-user
 */
public interface RepSlipShippingDeliveryHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repSlipShippingDeliveryHeader.RepSlipShippingDeliveryHeader.class;

	/** ARGSアノテーション getSlipShippingList */
	String getSlipShippingList_ARGS = "shippingNo";

	/** ARGSアノテーション getSlipShippingList */
	String getCstOrderNo_ARGS = "shippingNo,orderNo";
	
	/**
	 * Listメソッド
	 * 
	 * @param shippingNo shippingNo
	 * @return List
	 */
	List<RepSlipShippingDeliveryHeader> getSlipShippingList(
			final ArrayList<String> shippingNo);
	
	/**
	 * Listメソッド
	 * 
	 * @param shippingNo shippingNo
	 * @return List
	 */
	RepSlipShippingDeliveryHeader getCstOrderNo(final ArrayList<String> shippingNo,final String orderNo);

}
