/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.delivery.purchaseorder;

import java.util.List;

/**
 * PurchaseOrderDeliveryForAutoCompleteDaoクラス
 * @author tosco
 */
public interface PurchaseOrderDeliveryForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.delivery.purchaseorder.PurchaseOrderDeliveryForAutoComplete.class;

	// 納入先コード・名称で検索-----------------------------------------------------
	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "deliveryCd,rowlimit";

	/**
	 * 検索画面用納入先一覧取得用
	 * @param deliveryCd 納入先コードまたは納入先名
	 * @param rowlimit rowlimit
	 * @return List<PurchaseOrderDeliveryForAutoComplete>
	 */
	List<PurchaseOrderDeliveryForAutoComplete> getSearchList(
			final String deliveryCd, final String rowlimit);

}
