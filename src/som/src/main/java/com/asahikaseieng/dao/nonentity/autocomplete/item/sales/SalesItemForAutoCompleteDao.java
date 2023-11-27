/*
 * Created on 2009/03/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.item.sales;

import java.util.List;

/**
 * SalesItemForAutoCompleteDaoクラス
 * @author tosco
 */
public interface SalesItemForAutoCompleteDao {

	/** BEANアノテーション */
	Class<SalesItemForAutoComplete> BEAN = SalesItemForAutoComplete.class;

	// 品目コード・名称で検索-----------------------------------------------------
	/** ARGSアノテーション getDetailDigitList */
	String getSearchList_ARGS = "itemCd,deliveryCd,rowlimit";

	/**
	 * 品目マスタ詳細画面用
	 * @param itemCd 品目コードまたは品目名
	 * @param deliveryCd 納入先コード
	 * @param rowlimit 行上限
	 * @return List<SalesItemForAutoComplete>
	 */
	List<SalesItemForAutoComplete> getSearchList(final String itemCd,
			final String deliveryCd, final String rowlimit);

	// 他社コード１で検索------------------------------------------------------------------------
	/** ARGSアノテーション getDetailDigitOtherCompany1List */
	String getOtherCompany1SearchList_ARGS = "otherCompany1,deliveryCd,rowlimit";

	/**
	 * 品目マスタ詳細画面用（他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @param deliveryCd 納入先コード
	 * @param rowlimit 行上限
	 * @return List<SalesItemForAutoComplete>
	 */
	List<SalesItemForAutoComplete> getOtherCompany1SearchList(
			final String otherCompany1, final String deliveryCd,
			final String rowlimit);

}
