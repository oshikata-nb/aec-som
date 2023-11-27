/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.purchase;

import java.util.List;

import com.asahikaseieng.dao.nonentity.purchase.PurchaseList;
import com.asahikaseieng.dao.nonentity.purchase.PurchasePagerCondition;
import com.asahikaseieng.dao.nonentity.purchaseforreport.PurchaseListConditionForReport;
import com.asahikaseieng.dao.nonentity.purchaseforreport.PurchaseListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 発注一覧 ロジッククラス interface.
 * @author tosco
 */
public interface PurchaseListLogic {

	/**
	 * 検索処理を行う.全検索
	 * @param condition 検索条件
	 * @return List<PurchaseList> 発注一覧 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<PurchaseList> getSearchList(final PurchasePagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票Excel
	 * 
	 * @param condition 検索条件
	 * @return List<PurchaseListForReport>帳票Excel 検索結果リスト
	 */
	List<PurchaseListForReport> getReportList(
			final PurchaseListConditionForReport condition);
}
