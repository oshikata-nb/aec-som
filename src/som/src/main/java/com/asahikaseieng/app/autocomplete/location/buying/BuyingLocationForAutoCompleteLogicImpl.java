/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.buying;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.location.buying.BuyingLocationForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.location.buying.BuyingLocationForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 仕入画面用ロケーションマスタのAuto Complete用ロジック
 * @author tosco
 */
public class BuyingLocationForAutoCompleteLogicImpl implements
		BuyingLocationForAutoCompleteLogic {
	/** 仕入画面用ロケーションマスタ操作DAO */
	private BuyingLocationForAutoCompleteDao buyingLocationForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public BuyingLocationForAutoCompleteLogicImpl() {
	}

	// ロケーションコードまたは、ロケーション名称で検索--------------------------------
	/**
	 * 仕入画面用ロケーションマスタのオートコンプリート用データの取得
	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @return List<BuyingLocationListForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 * 
	 */
	public List<BuyingLocationForAutoComplete> getSearchList(
			final String locationCd) throws NoDataException {

		String val = AecTextUtils.likeFilter(locationCd);
		List<BuyingLocationForAutoComplete> list = buyingLocationForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 仕入画面用ロケーションマスタ操作DAOを設定します。
	 * @param buyingLocationForAutoCompleteDao 仕入画面用ロケーションマスタ操作DAO
	 */
	public void setBuyingLocationListForAutoCompleteDao(
			final BuyingLocationForAutoCompleteDao buyingLocationForAutoCompleteDao) {
		this.buyingLocationForAutoCompleteDao = buyingLocationForAutoCompleteDao;
	}

}
