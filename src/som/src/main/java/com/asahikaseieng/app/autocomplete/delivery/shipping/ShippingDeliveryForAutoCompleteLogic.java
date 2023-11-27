/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.delivery.shipping;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.delivery.shipping.ShippingDeliveryForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 納入先マスタのAuto Complete用ロジック
 * @author tosco
 */
public interface ShippingDeliveryForAutoCompleteLogic {
	// 納入先コードまたは、納入先名称で検索--------------------------------
	/**
	 * 検索画面用納入先マスタのオートコンプリート用データの取得
	 * @param deliveryCd 納入先コードまたは納入先名称
	 * @return List<ShippingDeliveryForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ShippingDeliveryForAutoComplete> getSearchList(String deliveryCd)
			throws NoDataException;

}
