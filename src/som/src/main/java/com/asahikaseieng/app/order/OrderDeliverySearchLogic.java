/*
 * Created on 2009/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order;

import java.util.List;

import com.asahikaseieng.dao.nonentity.orderdelivery.OrderDeliverySearchList;
import com.asahikaseieng.dao.nonentity.orderdelivery.OrderDeliverySearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 納入先検索(ポップアップ)ロジック interface.
 * @author tosco
 */
public interface OrderDeliverySearchLogic {

	/**
	 * 納入先検索処理を行う.
	 * @param condition 検索条件
	 * @return List<OrderDeliverySearchList> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	List<OrderDeliverySearchList> getList(final OrderDeliverySearchListPagerCondition condition)
		throws NoDataException;
}
