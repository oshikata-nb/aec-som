/*
 * Created on 2009/02/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.delivery.buyingapproval;

import java.util.List;

/**
 * BuyingApprovalDeliveryForAutoCompleteDaoクラス
 * @author tosco
 */
public interface BuyingApprovalDeliveryForAutoCompleteDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.autocomplete.delivery.buyingapproval.BuyingApprovalDeliveryForAutoComplete.class;

	/** ARGSアノテーション getListForAutoComplete */
	String getListForAutoComplete_ARGS = "deliveryCd,rowlimit";

	// 納入先コード・名称で検索-----------------------------------------------------
	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "deliveryCd,rowlimit";

	/**
	 * 検索画面用納入先一覧取得用
	 * @param deliveryCd 納入先コードまたは納入先名
	 * @param rowlimit rowlimit
	 * @return List<BuyingApprovalDeliveryForAutoComplete>
	 */
	List<BuyingApprovalDeliveryForAutoComplete> getSearchList(
			final String deliveryCd, final String rowlimit);

}
