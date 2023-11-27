/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item.sales;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.item.sales.SalesItemForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 売上用品目マスタのAuto Complete用ロジック
 * @author tosco
 */
public interface SalesItemForAutoCompleteLogic {
	//品目コードまたは、品目名称で検索--------------------------------
	/**
	 * 品目マスタ詳細画面用
	 * @param itemCd 品目コードまたは品目名称
	 * @param deliveryCd 納入先コード
	 * @return List<ShippingItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合	 */
	List<SalesItemForAutoComplete> getDetailList(final String itemCd, final String deliveryCd)
		throws NoDataException;

	//他社コードで検索----------------------------------------------------------
	/**
	 * 品目マスタ詳細画面用
	 * @param otherCompany1 他社コード１	 * @param deliveryCd 納入先コード
	 * @return List<ShippingItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合	 */
	List<SalesItemForAutoComplete> getDetailOtherCompany1List(final String otherCompany1, final String deliveryCd)
		throws NoDataException;

}
