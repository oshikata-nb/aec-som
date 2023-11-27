/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.delivery.purchaseorder;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.delivery.purchaseorder.PurchaseOrderDeliveryForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 納入先マスタのAuto Complete用ロジック
 * @author tosco
 */
public interface PurchaseOrderDeliveryForAutoCompleteLogic {
	//納入先コードまたは、納入先名称で検索--------------------------------
	/**
	 * 検索画面用納入先マスタのオートコンプリート用データの取得
	 * @param deliveryCd 納入先コードまたは納入先名称
	 * @return List<PurchaseOrderDeliveryForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<PurchaseOrderDeliveryForAutoComplete> getSearchList(String deliveryCd) throws NoDataException;

}
