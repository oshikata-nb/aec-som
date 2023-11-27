/*
 * Created on 2007/12/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.nonentity.inventoryitemqueuedetail.InventoryItemQueueDetail;
import com.asahikaseieng.dao.nonentity.inventorylist.InventoryList;
import com.asahikaseieng.dao.nonentity.inventorylist.InventoryListPagerCondition;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryListForReportCondition;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLocationListForReport;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLotListForReport;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLowerLocationListForReport;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 在庫照会一覧ロジック interface.
 * @author FPC
 */
public interface InventoryListLogic {
	/**
	 * 検索処理を行う．
	 * @param condition condition
	 * @return List<InventoryList>
	 * @exception NoDataException NoDataException
	 */
	List<InventoryList> getList(final InventoryListPagerCondition condition)
			throws NoDataException;

	/**
	 * 検索処理を行う．
	 * @param condition condition
	 * @return List<InventoryList>
	 * @exception NoDataException NoDataException
	 */
	List<InventoryList> getRelocationList(
			final InventoryListPagerCondition condition) throws NoDataException;

	/**
	 * ロケーション検索処理を行う.
	 * @param locationCd ロケーションコード
	 * @return LocationDetail
	 */
	LocationDetail getLocationEntity(final String locationCd);

	/**
	 * 品目検索処理を行う.
	 * @param itemCd 品目コード
	 * @return InventoryItemQueueDetail
	 */
	InventoryItemQueueDetail getItemQueueEntity(final String itemCd);

	/**
	 * 在庫数量合計取得.
	 * @param locationCd locationCd
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @param lotNo lotNo
	 * @param availableFlg availableFlg
	 * @return BigDecimal
	 */
	BigDecimal getTotalQty(final String locationCd, final String itemCd,
			final String otherCompanyCd1, final String lotNo,
			final BigDecimal availableFlg);

	/**
	 * 在庫数量合計取得.
	 * @param locationCd locationCd
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @param lotNo lotNo
	 * @param availableFlg availableFlg
	 * @return BigDecimal
	 */
	BigDecimal getReTotalQty(final String locationCd, final String itemCd,
			final String otherCompanyCd1, final String lotNo,
			final BigDecimal availableFlg);

	/**
	 * 帳票用検索処理を行う.(ロケーション）
	 * @param condition condition
	 * @throws NoDataException NoDataException
	 * @return List<InventoryLocationListForReport>
	 */
	List<InventoryLocationListForReport> getLocationListForReport(
			final InventoryListForReportCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.（下位のロケーション）
	 * @param condition condition
	 * @throws NoDataException NoDataException
	 * @return List<InventoryLocationListForReport>
	 */
	List<InventoryLowerLocationListForReport> getLowerLocationListForReport(
			final InventoryListForReportCondition condition)
			throws NoDataException;

	/**
	 * 在庫照会一覧検索（ロット在庫帳票用）
	 * @param condition condition
	 * @return List<InventoryLotListForReport>
	 * @throws NoDataException NoDataException
	 */
	List<InventoryLotListForReport> getLotListForReport(
			final InventoryListForReportCondition condition)
			throws NoDataException;
}
