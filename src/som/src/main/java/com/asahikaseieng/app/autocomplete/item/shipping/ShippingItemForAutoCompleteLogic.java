/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item.shipping;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.item.shipping.ShippingItemForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 出荷指図用品目マスタのAuto Complete用ロジック
 * @author tosco
 */
public interface ShippingItemForAutoCompleteLogic {
	//品目コードまたは、品目名称で検索--------------------------------
	/**
	 * 品目マスタ詳細画面用-小数点桁数・端数区分付き
	 * @param itemCd 品目コードまたは品目名称
	 * @return List<ShippingItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ShippingItemForAutoComplete> getDetailDigitList(String itemCd) throws NoDataException;

	//他社コードで検索----------------------------------------------------------
	/**
	 * 品目マスタ詳細画面用-小数点桁数・端数区分付き(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<ShippingItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ShippingItemForAutoComplete> getDetailDigitOtherCompany1List(String otherCompany1)
		throws NoDataException;

}
