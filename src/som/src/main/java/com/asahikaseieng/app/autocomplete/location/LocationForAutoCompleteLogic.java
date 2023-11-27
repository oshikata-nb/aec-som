/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.location.LocationForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * ロケーションのAuto Complete用ロジック
 * @author t0011036
 */
public interface LocationForAutoCompleteLogic {
	/**
	 * 検索画面用ロケーションのオートコンプリート用データの取得
	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @return List<LocationForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<LocationForAutoComplete> getSearchList(String locationCd)
			throws NoDataException;

	/**
	 * 利用可能ロケーションのオートコンプリート用データの取得
	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @return List<LocationForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<LocationForAutoComplete> getSearchAvailableList(String locationCd)
			throws NoDataException;
}
