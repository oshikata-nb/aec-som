/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.slipsales;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.location.slipsales.SlipSalesLocationForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 売上伝票出力画面用ロケーションマスタのAuto Complete用ロジック
 * @author tosco
 */
public interface SlipSalesLocationForAutoCompleteLogic {
	// ロケーションコードまたは、ロケーション名称で検索--------------------------------
	/**
	 * 売上伝票出力画面用ロケーションマスタのオートコンプリート用データの取得
	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @return List<SlipSalesLocationForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<SlipSalesLocationForAutoComplete> getSearchList(String locationCd)
			throws NoDataException;
}
