/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.delivery;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.deliverylist.DeliveryList;
import com.asahikaseieng.dao.nonentity.master.deliverylist.DeliveryListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.deliverylistforreport.DeliveryListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.deliverylistforreport.DeliveryListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 納入先一覧ロジック interface.
 * @author t0011036
 */
public interface DeliveryListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<DeliveryList>
	 */
	List<DeliveryList> getList(final DeliveryListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<DeliveryListForReport>
	 */
	List<DeliveryListForReport> getListForReport(
			final DeliveryListConditionForReport condition);
}
