/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.buying;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.location.buying.BuyingLocationForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 仕入画面用ロケーションマスタのAuto Complete用ロジック
 * @author tosco
 */
public interface BuyingLocationForAutoCompleteLogic {
	//ロケーションコードまたは、ロケーション名称で検索--------------------------------
	/**
	 * 仕入画面用ロケーションマスタのオートコンプリート用データの取得	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @return List<BuyingLocationListForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合	 */
	List<BuyingLocationForAutoComplete> getSearchList(String locationCd)
		throws NoDataException;
}
