/*
 * Created on 2009/03/09
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.purchasedelivery;

import java.util.List;

import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliveryList;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliveryListDao;
import com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliveryListPagerCondition;
import com.asahikaseieng.dao.nonentity.purchasedeliveryforreport.PurchaseDeliveryListConditionForReport;
import com.asahikaseieng.dao.nonentity.purchasedeliveryforreport.PurchaseDeliveryListForReport;
import com.asahikaseieng.dao.nonentity.purchasedeliveryforreport.PurchaseDeliveryListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 納期回答検索画面 ロジック実装クラス
 * @author tosco
 */
public class PurchaseDeliveryListLogicImpl implements PurchaseDeliveryListLogic {

	/** 納期回答検索画面Dao */
	private PurchaseDeliveryListDao purchaseDeliveryListDao;

	private PurchaseDeliveryListForReportDao purchaseDeliveryListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public PurchaseDeliveryListLogicImpl() {
	}

	/**
	 * 購買オーダーテーブル検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<PurchaseDeliveryList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<PurchaseDeliveryList> getSearchList(
			final PurchaseDeliveryListPagerCondition condition)
			throws NoDataException {

		checkParams(condition);
		List<PurchaseDeliveryList> list = purchaseDeliveryListDao
				.getSearchList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 購買オーダーテーブル検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<PurchaseDeliveryListForReport> 検索結果リスト
	 */
	public List<PurchaseDeliveryListForReport> getReportList(
			final PurchaseDeliveryListConditionForReport condition) {

		List<PurchaseDeliveryListForReport> list = purchaseDeliveryListForReportDao
				.getReportList(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final PurchaseDeliveryListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 納期回答検索画面Daoを設定します。
	 * @param purchaseDeliveryListDao 納期回答検索画面Dao
	 */
	public void setPurchaseDeliveryListDao(
			final PurchaseDeliveryListDao purchaseDeliveryListDao) {
		this.purchaseDeliveryListDao = purchaseDeliveryListDao;

	}

	/**
	 * 納期回答検索画面帳票ExcelDaoを設定します。
	 * @param purchaseDeliveryListForReportDao 納期回答検索画面帳票Dao
	 */
	public void setPurchaseDeliveryListForReportDao(
			final PurchaseDeliveryListForReportDao purchaseDeliveryListForReportDao) {
		this.purchaseDeliveryListForReportDao = purchaseDeliveryListForReportDao;
	}

}
