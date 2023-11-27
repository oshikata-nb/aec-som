/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.shipping;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.location.shipping.ShippingLocationForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.location.shipping.ShippingLocationForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 出荷指図画面用ロケーションマスタのAuto Complete用ロジック
 * @author tosco
 */
public class ShippingLocationForAutoCompleteLogicImpl implements
		ShippingLocationForAutoCompleteLogic {
	/** 出荷指図画面用ロケーションマスタ操作DAO */
	private ShippingLocationForAutoCompleteDao shippingLocationForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public ShippingLocationForAutoCompleteLogicImpl() {
	}

	// ロケーションコードまたは、ロケーション名称で検索--------------------------------
	/**
	 * 出荷指図画面用ロケーションマスタのオートコンプリート用データの取得
	 * 
	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @param itemCd 品目コード
	 * 
	 * @return List<ShippingLocationListForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 * 
	 */
	public List<ShippingLocationForAutoComplete> getSearchList(
			final String locationCd, final String itemCd)
			throws NoDataException {

		String val = AecTextUtils.likeFilter(locationCd);
		List<ShippingLocationForAutoComplete> list = shippingLocationForAutoCompleteDao
				.getSearchList(val, itemCd, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 出荷指図画面用ロケーションマスタ操作DAOを設定します。
	 * 
	 * @param shippingLocationForAutoCompleteDao 出荷指図画面用ロケーションマスタ操作DAO
	 */
	public void setShippingLocationListForAutoCompleteDao(
			final ShippingLocationForAutoCompleteDao shippingLocationForAutoCompleteDao) {
		this.shippingLocationForAutoCompleteDao = shippingLocationForAutoCompleteDao;
	}

}
