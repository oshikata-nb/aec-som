/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.item.shipping;

import java.util.List;

/**
 * ShippingItemForAutoCompleteDaoクラス
 * @author tosco
 */
public interface ShippingItemForAutoCompleteDao {

	/** BEANアノテーション */
	Class<ShippingItemForAutoComplete> BEAN = ShippingItemForAutoComplete.class;

	// 品目コード・名称で検索-----------------------------------------------------
	/** ARGSアノテーション getDetailDigitList */
	String getDetailDigitList_ARGS = "itemCd,rowlimit";

	/**
	 * 品目マスタ詳細画面用-小数点桁数・端数区分付き
	 * @param itemCd 品目コードまたは品目名
	 * @param rowlimit 行上限
	 * @return List<ShippingItemForAutoComplete>
	 */
	List<ShippingItemForAutoComplete> getDetailDigitList(final String itemCd,
			final String rowlimit);

	// 他社コード１で検索------------------------------------------------------------------------
	/** ARGSアノテーション getDetailDigitOtherCompany1List */
	String getDetailDigitOtherCompany1List_ARGS = "otherCompany1,rowlimit";

	/**
	 * 品目マスタ詳細画面用-小数点桁数・端数区分付き（他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @param rowlimit 行上限
	 * @return List<ShippingItemForAutoComplete>
	 */
	List<ShippingItemForAutoComplete> getDetailDigitOtherCompany1List(
			final String otherCompany1, final String rowlimit);

}
