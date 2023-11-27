/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.delivery;

import java.util.List;

/**
 * DeliveryForAutoCompleteDaoクラス
 * @author tosco
 */
public interface DeliveryForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.delivery.DeliveryForAutoComplete.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "deliveryCd,rowlimit";

	// 納入先コード・名称で検索-----------------------------------------------------
	/**
	 * DeliveryForAutoCompleteメソッド
	 * 
	 * @param deliveryCd 納入先コード
	 * @param rowlimit rowlimit
	 * @return DeliveryForAutoComplete
	 */
	List<DeliveryForAutoComplete> getSearchList(final String deliveryCd,
			final String rowlimit);
	
	// 納入先コード・名称・区分で検索-----------------------------------------------------
	/** ARGSアノテーション getDeliverySearchList */
	String getDeliverySearchList_ARGS = "deliveryCd,deliveryDivision,rowlimit";

	/**
	 * getDeliverySearchListメソッド
	 * 
	 * @param deliveryCd deliveryCd
	 * @param deliveryDivision deliveryDivision
	 * @param rowlimit 行上限
	 * @return DeliveryForAutoComplete
	 */
	List<DeliveryForAutoComplete> getDeliverySearchList(final String deliveryCd,
			final String deliveryDivision, final String rowlimit);

}
