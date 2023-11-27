/*
 * Created on 2009/04/14
 * 
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item.order;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.item.order.OrderItemForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受注用品目マスタのAuto Complete用ロジック
 * @author tosco
 */
public interface OrderItemForAutoCompleteLogic {
	//品目コードまたは、品目名称で検索--------------------------------
	/**
	 * 品目マスタ詳細画面用
	 * @param itemCd 品目コードまたは品目名称
	 * @param deliveryCd 納入先コード
	 * @param orderDivision 受注区分
	 * @param balanceCd 帳合コード
	 * @return List<OrderItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<OrderItemForAutoComplete> getDetailList(
		final String itemCd, final String deliveryCd
		, final String orderDivision, final String balanceCd)
		throws NoDataException;

	//他社コードで検索----------------------------------------------------------
	/**
	 * 品目マスタ詳細画面用(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @param deliveryCd 納入先コード
	 * @param orderDivision 受注区分
	 * @param balanceCd 帳合コード
	 * @return List<OrderItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<OrderItemForAutoComplete> getDetailOtherCompany1List(
			final String otherCompany1, final String deliveryCd
			, final String orderDivision, final String balanceCd)
			throws NoDataException;

}
