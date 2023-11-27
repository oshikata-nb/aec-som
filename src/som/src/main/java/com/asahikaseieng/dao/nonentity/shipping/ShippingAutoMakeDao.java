/*
 * Created on 2009/07/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shipping;

import java.util.List;

/**
 * ShippingAutoMakeDaoクラス
 * @author kanri-user
 */
public interface ShippingAutoMakeDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shipping.ShippingAutoMake.class;

	/** ARGSアノテーション getShippingAutoMakeList */
	String getShippingAutoMakeList_ARGS = "shippingDateFrom, shippingDateTo";

	/**
	 * Listメソッド
	 * 
	 * @param shippingDateFrom shippingDateFrom
	 * @param shippingDateTo shippingDateTo
	 * @return List
	 */
	List<ShippingAutoMake> getShippingAutoMakeList(
			final String shippingDateFrom, final String shippingDateTo);
}
