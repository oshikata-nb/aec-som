/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.shippingresult;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.location.shippingresult.ShippingResultLocationForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 出荷実績画面用ロケーションマスタのAuto Complete用ロジック
 * @author tosco
 */
public interface ShippingResultLocationForAutoCompleteLogic {
	//ロケーションコードまたは、ロケーション名称で検索--------------------------------
	/**
	 * 出荷実績画面用ロケーションマスタのオートコンプリート用データの取得
	 * @param itemCd 品目コード
	 * @throws NoDataException 検索結果が存在しない場合
	List<ShippingResultLocationForAutoComplete> getSearchList(String locationCd, String itemCd)
		throws NoDataException;
}