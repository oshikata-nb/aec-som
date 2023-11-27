/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.delivery.purchase;

import java.util.List;

/**
 * PurchaseDeliveryForAutoCompleteDaoクラス
 * @author tosco
 */
public interface PurchaseDeliveryForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.delivery.purchase.PurchaseDeliveryForAutoComplete.class;

	// 納入先コード・名称で検索-----------------------------------------------------
	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "deliveryCd,rowlimit";

	/**
	 * 検索画面用納入先一覧取得用
	 * @param deliveryCd 納入先コードまたは納入先名
	 * @param rowlimit rowlimit
	 * @return List<PurchaseDeliveryForAutoComplete>
	 */
	List<PurchaseDeliveryForAutoComplete> getSearchList(
			final String deliveryCd, final String rowlimit);

	// 納入先コード・名称で検索 詳細画面--------------------------------------------
	/** ARGSアノテーション getSearchListDetail */
	String getSearchListDetail_ARGS = "deliveryCd,rowlimit";

	/**
	 * 詳細画面用納入先一覧取得用
	 * @param deliveryCd 納入先コードまたは納入先名
	 * @param rowlimit rowlimit
	 * @return List<PurchaseDeliveryForAutoComplete>
	 */
	List<PurchaseDeliveryForAutoComplete> getSearchListDetail(
			final String deliveryCd, final String rowlimit);

}
