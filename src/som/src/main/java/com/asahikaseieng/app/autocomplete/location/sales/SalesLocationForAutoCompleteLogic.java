/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.sales;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.location.sales.SalesLocationForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 売上画面用ロケーションマスタのAuto Complete用ロジック
 * @author tosco
 */
public interface SalesLocationForAutoCompleteLogic {
	//ロケーションコードまたは、ロケーション名称で検索--------------------------------
	/**
	 * 売上画面用ロケーションマスタのオートコンプリート用データの取得	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @param itemCd 品目コード	 * @return List<SalesLocationForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合	 */
	List<SalesLocationForAutoComplete> getSearchList(String locationCd, String itemCd)
		throws NoDataException;
}
