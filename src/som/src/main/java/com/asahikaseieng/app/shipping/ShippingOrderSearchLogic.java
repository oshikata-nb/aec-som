/*
 * Created on 2009/02/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shipping;

import java.util.List;

import com.asahikaseieng.dao.nonentity.shipping.ShippingOrderSearchList;
import com.asahikaseieng.dao.nonentity.shipping.ShippingOrderSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受注検索(ポップアップ)ロジック interface.
 * @author tosco
 */
public interface ShippingOrderSearchLogic {

	/**
	 * 受注検索処理を行う.
	 * @param condition 検索条件
	 * @return List<ShippingOrderSearch> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	List<ShippingOrderSearchList> getList(final ShippingOrderSearchListPagerCondition condition)
		throws NoDataException;
}
