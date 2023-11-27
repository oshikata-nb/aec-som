/*
 * Created on 2009/03/09
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.purchasedelivery;

import java.util.List;

import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliveryList;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliveryListPagerCondition;
import com.asahikaseieng.dao.nonentity.purchasedeliveryforreport.PurchaseDeliveryListConditionForReport;
import com.asahikaseieng.dao.nonentity.purchasedeliveryforreport.PurchaseDeliveryListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 納期回答検索画面 ロジッククラス interface.
 * @author tosco
 */
public interface PurchaseDeliveryListLogic {

	/**
	 * 購買外注オーダーテーブルの検索処理を行う.全検索
	 * @param condition 検索条件
	 * @return List<PurchaseDeliveryList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<PurchaseDeliveryList> getSearchList(
			final PurchaseDeliveryListPagerCondition condition)
			throws NoDataException;

	/**
	 * 購買オーダーテーブル検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<PurchaseDeliveryListForReport> 検索結果リスト
	 */
	List<PurchaseDeliveryListForReport> getReportList(
			final PurchaseDeliveryListConditionForReport condition);
}
