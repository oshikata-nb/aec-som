/*
 * Created on 2009/06/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.delivery.shipping;

import java.util.List;

/**
 * ShippingDeliveryForAutoCompleteDaoクラス
 * @author kanri-user
 */
public interface ShippingDeliveryForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.delivery.shipping.ShippingDeliveryForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "deliveryCd,rowlimit";

	/**
	 * Listメソッド
	 * 
	 * @param deliveryCd deliveryCd
	 * @param rowlimit rowlimit
	 * @return List<ShippingDeliveryForAutoComplete>
	 */
	List<ShippingDeliveryForAutoComplete> getSearchList(
			final String deliveryCd, final String rowlimit);
}
