/*
 * Created on 2009/04/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.item.order;

import java.util.List;

/**
 * OrderItemForAutoCompleteDaoクラス
 * @author tosco
 */
public interface OrderItemForAutoCompleteDao {

	/** BEANアノテーション */
	Class<OrderItemForAutoComplete> BEAN = OrderItemForAutoComplete.class;

	// 品目コード・名称で検索-----------------------------------------------------
	/** ARGSアノテーション getDetailDigitList */
	String getSearchList_ARGS = "itemCd,deliveryCd,orderDivision,balanceCd,rowlimit";

	/**
	 * 品目マスタ詳細画面用
	 * @param itemCd 品目コードまたは品目名
	 * @param deliveryCd 納入先コード
	 * @param orderDivision 受注区分
	 * @param balanceCd 帳合コード
	 * @param rowlimit 行上限
	 * @return List<OrderItemForAutoComplete>
	 */
	List<OrderItemForAutoComplete> getSearchList(final String itemCd,
			final String deliveryCd, final String orderDivision,
			final String balanceCd, final String rowlimit);

	// 他社コード１で検索------------------------------------------------------------------------
	/** ARGSアノテーション getDetailDigitOtherCompany1List */
	String getOtherCompany1SearchList_ARGS = "otherCompany1,deliveryCd,orderDivision,balanceCd,rowlimit";

	/**
	 * 品目マスタ詳細画面用（他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @param deliveryCd 納入先コード
	 * @param orderDivision 受注区分
	 * @param balanceCd 帳合コード
	 * @param rowlimit 行上限
	 * @return List<OrderItemForAutoComplete>
	 */
	List<OrderItemForAutoComplete> getOtherCompany1SearchList(
			final String otherCompany1, final String deliveryCd,
			final String orderDivision, final String balanceCd,
			final String rowlimit);

}
