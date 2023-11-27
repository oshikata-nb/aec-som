/*
 * Created on 2009/04/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item.order;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.item.order.OrderItemForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.item.order.OrderItemForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 受注用品目マスタのAuto Complete用ロジック
 * @author tosco
 */
public class OrderItemForAutoCompleteLogicImpl implements
		OrderItemForAutoCompleteLogic {
	/** 品目マスタ操作DAO */
	private OrderItemForAutoCompleteDao orderItemForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public OrderItemForAutoCompleteLogicImpl() {
	}

	// 品目コードまたは、品目名称で検索--------------------------------
	/**
	 * 品目マスタ詳細画面用
	 * @param itemCd 品目コードまたは品目名称
	 * @param deliveryCd 納入先コード
	 * @param orderDivision 受注区分
	 * @param balanceCd 帳合コード
	 * @return List<OrderItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<OrderItemForAutoComplete> getDetailList(final String itemCd,
			final String deliveryCd, final String orderDivision,
			final String balanceCd) throws NoDataException {

		String val = AecTextUtils.likeFilter(itemCd);

		List<OrderItemForAutoComplete> list = orderItemForAutoCompleteDao
				.getSearchList(val, deliveryCd, orderDivision, balanceCd,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// 他社コードで検索----------------------------------------------------------
	/**
	 * 品目マスタ詳細画面用(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @param deliveryCd 納入先コード
	 * @param orderDivision 受注区分
	 * @param balanceCd 帳合コード
	 * @return List<OrderItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<OrderItemForAutoComplete> getDetailOtherCompany1List(
			final String otherCompany1, final String deliveryCd,
			final String orderDivision, final String balanceCd)
			throws NoDataException {

		String val = AecTextUtils.likeFilter(otherCompany1);

		List<OrderItemForAutoComplete> list = orderItemForAutoCompleteDao
				.getOtherCompany1SearchList(val, deliveryCd, orderDivision,
					balanceCd, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 品目マスタ操作DAOを設定します。
	 * 
	 * @param orderItemForAutoCompleteDao 品目マスタ操作DAO
	 */
	public void setOrderItemForAutoCompleteDao(
			final OrderItemForAutoCompleteDao orderItemForAutoCompleteDao) {
		this.orderItemForAutoCompleteDao = orderItemForAutoCompleteDao;
	}

}
