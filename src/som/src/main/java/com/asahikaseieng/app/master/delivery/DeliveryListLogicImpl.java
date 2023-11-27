/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.delivery;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.deliverylist.DeliveryList;
import com.asahikaseieng.dao.nonentity.master.deliverylist.DeliveryListDao;
import com.asahikaseieng.dao.nonentity.master.deliverylist.DeliveryListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.deliverylistforreport.DeliveryListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.deliverylistforreport.DeliveryListForReport;
import com.asahikaseieng.dao.nonentity.master.deliverylistforreport.DeliveryListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 納入先一覧ロジック 実装クラス.
 * @author t0011036
 */
public class DeliveryListLogicImpl implements DeliveryListLogic {

	private DeliveryListDao deliveryListDao;

	private DeliveryListForReportDao deliveryListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public DeliveryListLogicImpl() {
	}

	/**
	 * 納入先一覧検索
	 * @param condition 検索条件
	 * @return List<DeliveryList>
	 * @throws NoDataException NoDataException
	 */
	public List<DeliveryList> getList(final DeliveryListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<DeliveryList> list = deliveryListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final DeliveryListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 納入先一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<DeliveryListForReport>
	 */
	public List<DeliveryListForReport> getListForReport(
			final DeliveryListConditionForReport condition) {
		List<DeliveryListForReport> list = deliveryListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * deliveryListDaoを設定します。
	 * @param deliveryListDao deliveryListDao
	 */
	public void setDeliveryListDao(final DeliveryListDao deliveryListDao) {
		this.deliveryListDao = deliveryListDao;
	}

	/**
	 * deliveryListForReportDaoを設定します。
	 * @param deliveryListForReportDao deliveryListForReportDao
	 */
	public void setDeliveryListForReportDao(
			final DeliveryListForReportDao deliveryListForReportDao) {
		this.deliveryListForReportDao = deliveryListForReportDao;
	}
}
