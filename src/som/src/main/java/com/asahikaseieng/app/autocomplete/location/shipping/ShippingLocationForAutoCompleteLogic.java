/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.shipping;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.location.shipping.ShippingLocationForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 出荷指図画面用ロケーションマスタのAuto Complete用ロジック
 * @author tosco
 */
public interface ShippingLocationForAutoCompleteLogic {
	//ロケーションコードまたは、ロケーション名称で検索--------------------------------
	/**
	 * 出荷指図画面用ロケーションマスタのオートコンプリート用データの取得
	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @param itemCd 品目コード
	 * @return List<ShippingLocationListForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ShippingLocationForAutoComplete> getSearchList(String locationCd, String itemCd)
		throws NoDataException;
}
