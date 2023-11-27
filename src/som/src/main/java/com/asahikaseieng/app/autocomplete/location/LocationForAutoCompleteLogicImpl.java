/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.location.LocationForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.location.LocationForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * ロケーションのAuto Complete用ロジック
 * @author t0011036
 */
public class LocationForAutoCompleteLogicImpl implements
		LocationForAutoCompleteLogic {
	/* ロケーション操作DAO */
	private LocationForAutoCompleteDao locationForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public LocationForAutoCompleteLogicImpl() {
	}

	/**
	 * 検索画面用ロケーションのオートコンプリート用データの取得
	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @return List<LocationForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<LocationForAutoComplete> getSearchList(final String locationCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(locationCd);
		List<LocationForAutoComplete> list = locationForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 利用可能ロケーションのオートコンプリート用データの取得
	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @return List<LocationForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<LocationForAutoComplete> getSearchAvailableList(
			final String locationCd) throws NoDataException {
		String val = AecTextUtils.likeFilter(locationCd);
		List<LocationForAutoComplete> list = locationForAutoCompleteDao
				.getSearchAvailableList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * locationForAutoCompleteDaoを設定します。
	 * @param locationForAutoCompleteDao locationForAutoCompleteDao
	 */
	public void setLocationForAutoCompleteDao(
			final LocationForAutoCompleteDao locationForAutoCompleteDao) {
		this.locationForAutoCompleteDao = locationForAutoCompleteDao;
	}
}
