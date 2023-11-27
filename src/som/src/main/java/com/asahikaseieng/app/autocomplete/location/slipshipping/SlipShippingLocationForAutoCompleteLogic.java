/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.slipshipping;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.location.slipshipping.SlipShippingLocationForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 出荷関連帳票出力画面用ロケーションマスタのAuto Complete用ロジック
 * @author tosco
 */
public interface SlipShippingLocationForAutoCompleteLogic {
	// ロケーションコードまたは、ロケーション名称で検索--------------------------------
	/**
	 * 出荷関連帳票出力画面用ロケーションマスタのオートコンプリート用データの取得
	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @return List<SlipShippingLocationForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<SlipShippingLocationForAutoComplete> getSearchList(String locationCd)
			throws NoDataException;
}
