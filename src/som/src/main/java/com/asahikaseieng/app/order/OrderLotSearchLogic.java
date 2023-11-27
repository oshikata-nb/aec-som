/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order;

import java.util.List;

import com.asahikaseieng.dao.entity.master.item.Item;
import com.asahikaseieng.dao.nonentity.orderlotsearchlist.OrderLotSearchList;
import com.asahikaseieng.dao.nonentity.orderlotsearchlist.OrderLotSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * ロット検索(ポップアップ)ロジック interface.
 * @author tosco
 */
public interface OrderLotSearchLogic {

	/**
	 * 品目在庫の販売引当残を取得処理を行う.
	 * @param itemCd 検索条件
	 * @return String 販売引当残
	 */
	String getItemInventory(final String itemCd);

	/**
	 * 先付け受注数の取得
	 * @param itemCd
	 * @return
	 */
	String getPreOrderQty(final String itemCd);

	
	/**
	 * 品目情報検索処理を行う.
	 * @param itemCd 品目コード
	 * @return Item 検索結果
	 * @throws NoDataException NoDataException
	 */
	Item getItem(final String itemCd) throws NoDataException;

	/**
	 * ロット検索処理を行う.
	 * @param condition 検索条件
	 * @param unitDivision 区分
	 * @param venderDivision 取引先区分
	 * @param venderCd 取引先コード
	 * @return List<OrderLotSearch> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	List<OrderLotSearchList> getList(
			final OrderLotSearchListPagerCondition condition,
			final String unitDivision, final String venderDivision,
			final String venderCd) throws NoDataException;
}
