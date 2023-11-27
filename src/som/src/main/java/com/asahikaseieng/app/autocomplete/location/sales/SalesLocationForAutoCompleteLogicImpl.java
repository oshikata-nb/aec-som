/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.sales;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.location.sales.SalesLocationForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.location.sales.SalesLocationForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 売上画面用ロケーションマスタのAuto Complete用ロジック
 * @author tosco
 */
public class SalesLocationForAutoCompleteLogicImpl implements
		SalesLocationForAutoCompleteLogic {
	/** 売上画面用ロケーションマスタ操作DAO */
	private SalesLocationForAutoCompleteDao salesLocationForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public SalesLocationForAutoCompleteLogicImpl() {
	}

	// ロケーションコードまたは、ロケーション名称で検索--------------------------------
	/**
	 * 売上画面用ロケーションマスタのオートコンプリート用データの取得
	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @param itemCd 品目コード
	 * @return List<SalesLocationListForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 * 
	 */
	public List<SalesLocationForAutoComplete> getSearchList(
			final String locationCd, final String itemCd)
			throws NoDataException {

		String val = AecTextUtils.likeFilter(locationCd);
		List<SalesLocationForAutoComplete> list = salesLocationForAutoCompleteDao
				.getSearchList(val, itemCd, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 売上画面用ロケーションマスタ操作DAOを設定します。
	 * @param salesLocationForAutoCompleteDao 売上画面用ロケーションマスタ操作DAO
	 */
	public void setSalesLocationListForAutoCompleteDao(
			final SalesLocationForAutoCompleteDao salesLocationForAutoCompleteDao) {
		this.salesLocationForAutoCompleteDao = salesLocationForAutoCompleteDao;
	}

}
