/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.sales;

import java.util.List;

import com.asahikaseieng.dao.nonentity.sales.SalesDeliverySearchList;
import com.asahikaseieng.dao.nonentity.sales.SalesDeliverySearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 納入先検索(ポップアップ)ロジック interface.
 * @author tosco
 */
public interface SalesDeliverySearchLogic {

	/**
	 * 納入先検索処理を行う.
	 * @param condition 検索条件
	 * @return List<SalesDeliverySearchList> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	List<SalesDeliverySearchList> getList(final SalesDeliverySearchListPagerCondition condition)
		throws NoDataException;
}
