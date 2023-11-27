/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.shippingresult;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.location.shippingresult.ShippingResultLocationForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.location.shippingresult.ShippingResultLocationForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 出荷実績画面用ロケーションマスタのAuto Complete用ロジック
 * @author tosco
 */
public class ShippingResultLocationForAutoCompleteLogicImpl implements
		ShippingResultLocationForAutoCompleteLogic {
	/** 出荷実績画面用ロケーションマスタ操作DAO */
	private ShippingResultLocationForAutoCompleteDao shippingResultLocationForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public ShippingResultLocationForAutoCompleteLogicImpl() {
	}

	// ロケーションコードまたは、ロケーション名称で検索--------------------------------
	/**
	 * 出荷実績画面用ロケーションマスタのオートコンプリート用データの取得
	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @param itemCd 品目コード
	 * @return List<ShippingResultLocationListForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ShippingResultLocationForAutoComplete> getSearchList(
			final String locationCd, final String itemCd)
			throws NoDataException {

		String val = AecTextUtils.likeFilter(locationCd);
		List<ShippingResultLocationForAutoComplete> list = shippingResultLocationForAutoCompleteDao
				.getSearchList(val, itemCd, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 出荷実績画面用ロケーションマスタ操作DAOを設定します。
	 * 
	 * @param shippingResultLocationForAutoCompleteDao 出荷実績画面用ロケーションマスタ操作DAO
	 */
	public void setShippingResultLocationListForAutoCompleteDao(
			final ShippingResultLocationForAutoCompleteDao shippingResultLocationForAutoCompleteDao) {
		this.shippingResultLocationForAutoCompleteDao = shippingResultLocationForAutoCompleteDao;
	}

}
