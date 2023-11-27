/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item.shipping;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.item.shipping.ShippingItemForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.item.shipping.ShippingItemForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 出荷指図用品目マスタのAuto Complete用ロジック
 * @author tosco
 */
public class ShippingItemForAutoCompleteLogicImpl implements
		ShippingItemForAutoCompleteLogic {
	/** 品目マスタ操作DAO */
	private ShippingItemForAutoCompleteDao shippingItemForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public ShippingItemForAutoCompleteLogicImpl() {
	}

	// 品目コードまたは、品目名称で検索--------------------------------
	/**
	 * 品目マスタ詳細画面用-小数点桁数・端数区分付き
	 * @param itemCd 品目コードまたは品目名称
	 * @return List<ShippingItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 * 
	 */
	public List<ShippingItemForAutoComplete> getDetailDigitList(
			final String itemCd) throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCd);
		List<ShippingItemForAutoComplete> list = shippingItemForAutoCompleteDao
				.getDetailDigitList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// 他社コードで検索----------------------------------------------------------
	/**
	 * 品目マスタ詳細画面用-小数点桁数・端数区分付き(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * 
	 * @return List<ShippingItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 * 
	 */
	public List<ShippingItemForAutoComplete> getDetailDigitOtherCompany1List(
			final String otherCompany1) throws NoDataException {
		String val = AecTextUtils.likeFilter(otherCompany1);
		List<ShippingItemForAutoComplete> list = shippingItemForAutoCompleteDao
				.getDetailDigitOtherCompany1List(val,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 品目マスタ操作DAOを設定します。
	 * 
	 * @param shippingItemForAutoCompleteDao 品目マスタ操作DAO
	 */
	public void setShippingItemForAutoCompleteDao(
			final ShippingItemForAutoCompleteDao shippingItemForAutoCompleteDao) {
		this.shippingItemForAutoCompleteDao = shippingItemForAutoCompleteDao;
	}

}
