/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.delivery.shipping;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.delivery.shipping.ShippingDeliveryForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.delivery.shipping.ShippingDeliveryForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 納入先マスタのAuto Complete用ロジック
 * @author tosco
 */
public class ShippingDeliveryForAutoCompleteLogicImpl implements
		ShippingDeliveryForAutoCompleteLogic {
	/** 納入先マスタ操作DAO */
	private ShippingDeliveryForAutoCompleteDao shippingDeliveryForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public ShippingDeliveryForAutoCompleteLogicImpl() {
	}

	// 納入先コードまたは、納入先名称で検索--------------------------------
	/**
	 * 検索画面用納入先マスタのオートコンプリート用データの取得
	 * @param itemCd 納入先コードまたは納入先名称
	 * @return List<ShippingDeliveryForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ShippingDeliveryForAutoComplete> getSearchList(
			final String itemCd) throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCd);
		List<ShippingDeliveryForAutoComplete> list = shippingDeliveryForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 納入先マスタ操作DAOを設定します。
	 * @param shippingDeliveryForAutoCompleteDao 納入先マスタ操作DAO
	 */
	public void setShippingDeliveryForAutoCompleteDao(
			final ShippingDeliveryForAutoCompleteDao shippingDeliveryForAutoCompleteDao) {
		this.shippingDeliveryForAutoCompleteDao = shippingDeliveryForAutoCompleteDao;
	}

}
