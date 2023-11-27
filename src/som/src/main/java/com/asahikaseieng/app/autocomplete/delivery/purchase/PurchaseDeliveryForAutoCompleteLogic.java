/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.delivery.purchase;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.delivery.purchase.PurchaseDeliveryForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 納入先マスタのAuto Complete用ロジック
 * @author tosco
 */
public interface PurchaseDeliveryForAutoCompleteLogic {
	//納入先コードまたは、納入先名称で検索--------------------------------
	/**
	 * 検索画面用納入先マスタのオートコンプリート用データの取得
	 * @param deliveryCd 納入先コードまたは納入先名称
	 * @return List<PurchaseDeliveryForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<PurchaseDeliveryForAutoComplete> getSearchList(String deliveryCd) throws NoDataException;

	//納入先コードまたは、納入先名称で検索--------------------------------
	/**
	 * 検索画面用納入先マスタのオートコンプリート用データの取得
	 * @param deliveryCd 納入先コードまたは納入先名称
	 * @return List<PurchaseDeliveryForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<PurchaseDeliveryForAutoComplete> getSearchListDetail(String deliveryCd) throws NoDataException;

}
