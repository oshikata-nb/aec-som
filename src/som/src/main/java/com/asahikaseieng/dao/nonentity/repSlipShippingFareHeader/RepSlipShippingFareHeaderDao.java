/*
 * Created on 2017/06/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.repSlipShippingFareHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * RepSlipShippingDeliveryHeaderDaoクラス
 * @author kanri-user
 */
public interface RepSlipShippingFareHeaderDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.repSlipShippingFareHeader.RepSlipShippingFareHeader.class;

	/** ARGSアノテーション getSlipShippingList */
	String getSlipShippingList_ARGS = "shippingNo";

	/**
	 * Listメソッド
	 * 
	 * @param shippingNo shippingNo
	 * @return List
	 */
	List<RepSlipShippingFareHeader> getSlipShippingList(
			final ArrayList<String> shippingNo);
}
