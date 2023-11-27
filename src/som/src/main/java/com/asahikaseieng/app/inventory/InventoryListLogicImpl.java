/*
 * Created on 2007/11/22
 *
 * $copyright$
 * tanaka:Unitprice
 */
package com.asahikaseieng.app.inventory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.asahikaseieng.dao.nonentity.inventoryitemqueuedetail.InventoryItemQueueDetail;
import com.asahikaseieng.dao.nonentity.inventoryitemqueuedetail.InventoryItemQueueDetailDao;
import com.asahikaseieng.dao.nonentity.inventorylist.InventoryList;
import com.asahikaseieng.dao.nonentity.inventorylist.InventoryListDao;
import com.asahikaseieng.dao.nonentity.inventorylist.InventoryListPagerCondition;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryListForReportCondition;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLocationListForReport;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLocationListForReportDao;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLotListForReport;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLotListForReportDao;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLowerLocationListForReport;
import com.asahikaseieng.dao.nonentity.inventorylistforreport.InventoryLowerLocationListForReportDao;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetail;
import com.asahikaseieng.dao.nonentity.master.locationdetail.LocationDetailDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 在庫照会一覧ロジック
 * @author tanaka
 */
public class InventoryListLogicImpl implements InventoryListLogic {

	private InventoryListDao inventoryListDao;

	// private InventoryListForReportDao inventoryListForReportDao;

	private LocationDetailDao locationDetailDao;

	private InventoryItemQueueDetailDao inventoryItemQueueDetailDao;

	private InventoryLocationListForReportDao inventoryLocationListForReportDao;

	private InventoryLowerLocationListForReportDao inventoryLowerLocationListForReportDao;

	private InventoryLotListForReportDao inventoryLotListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public InventoryListLogicImpl() {
		super();
	}

	/**
	 * {@inheritDoc}
	 * @throws NoDataException NoDataException
	 */
	public List<InventoryList> getList(
			final InventoryListPagerCondition condition) throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<InventoryList> list = new ArrayList<InventoryList>();

		/* ロケーション＆品目＆ロット */
		if (!StringUtils.isEmpty(condition.getSrhLocationCd())
				&& (!StringUtils.isEmpty(condition.getSrhItemCd()) || !StringUtils
						.isEmpty(condition.getSrhOtherCompanyCd1()))
				&& !StringUtils.isEmpty(condition.getSrhLotNo())) {
			list = inventoryListDao.getLocItemLotList(condition);
		}

		/* ロケーション＆品目 */
		if (!StringUtils.isEmpty(condition.getSrhLocationCd())
				&& (!StringUtils.isEmpty(condition.getSrhItemCd()) || !StringUtils
						.isEmpty(condition.getSrhOtherCompanyCd1()))
				&& StringUtils.isEmpty(condition.getSrhLotNo())) {
			list = inventoryListDao.getLocItemList(condition);
		}

		/* ロケーション */
		if (!StringUtils.isEmpty(condition.getSrhLocationCd())
				&& StringUtils.isEmpty(condition.getSrhItemCd())
				&& StringUtils.isEmpty(condition.getSrhOtherCompanyCd1())
				&& StringUtils.isEmpty(condition.getSrhLotNo())) {
			list = inventoryListDao.getLocList(condition);
		}

		/* なし */
		if (StringUtils.isEmpty(condition.getSrhLocationCd())
				&& StringUtils.isEmpty(condition.getSrhItemCd())
				&& StringUtils.isEmpty(condition.getSrhOtherCompanyCd1())
				&& StringUtils.isEmpty(condition.getSrhLotNo())) {
			list = inventoryListDao.getList(condition);
		}

		/* 品目＆ロット */
		if (StringUtils.isEmpty(condition.getSrhLocationCd())
				&& (!StringUtils.isEmpty(condition.getSrhItemCd()) || !StringUtils
						.isEmpty(condition.getSrhOtherCompanyCd1()))
				&& !StringUtils.isEmpty(condition.getSrhLotNo())) {
			list = inventoryListDao.getItemLotList(condition);
		}

		/* ロット */
		if (StringUtils.isEmpty(condition.getSrhLocationCd())
				&& StringUtils.isEmpty(condition.getSrhItemCd())
				& StringUtils.isEmpty(condition.getSrhOtherCompanyCd1())
				&& !StringUtils.isEmpty(condition.getSrhLotNo())) {
			list = inventoryListDao.getLotList(condition);
		}

		/* 品目 */
		if (StringUtils.isEmpty(condition.getSrhLocationCd())
				&& (!StringUtils.isEmpty(condition.getSrhItemCd()) || !StringUtils
						.isEmpty(condition.getSrhOtherCompanyCd1()))
				&& StringUtils.isEmpty(condition.getSrhLotNo())) {
			list = inventoryListDao.getItemList(condition);
		}

		/* ロケーション＆ロット */
		if (!StringUtils.isEmpty(condition.getSrhLocationCd())
				&& StringUtils.isEmpty(condition.getSrhItemCd())
				&& StringUtils.isEmpty(condition.getSrhOtherCompanyCd1())
				&& !StringUtils.isEmpty(condition.getSrhLotNo())) {
			list = inventoryListDao.getLocLotList(condition);
		}

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 * @throws NoDataException NoDataException
	 */
	public List<InventoryList> getRelocationList(
			final InventoryListPagerCondition condition) throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<InventoryList> list = new ArrayList<InventoryList>();

		/* ロケーション＆品目＆ロット */
		if (!StringUtils.isEmpty(condition.getSrhLocationCd())
				&& (!StringUtils.isEmpty(condition.getSrhItemCd()) || !StringUtils
						.isEmpty(condition.getSrhOtherCompanyCd1()))
				&& !StringUtils.isEmpty(condition.getSrhLotNo())) {
			list = inventoryListDao.getReLocItemLotList(condition);
		}

		/* ロケーション＆品目 */
		if (!StringUtils.isEmpty(condition.getSrhLocationCd())
				&& (!StringUtils.isEmpty(condition.getSrhItemCd()) || !StringUtils
						.isEmpty(condition.getSrhOtherCompanyCd1()))
				&& StringUtils.isEmpty(condition.getSrhLotNo())) {
			list = inventoryListDao.getReLocItemList(condition);
		}

		/* ロケーション */
		if (!StringUtils.isEmpty(condition.getSrhLocationCd())
				&& StringUtils.isEmpty(condition.getSrhItemCd())
				&& StringUtils.isEmpty(condition.getSrhOtherCompanyCd1())
				&& StringUtils.isEmpty(condition.getSrhLotNo())) {
			list = inventoryListDao.getReLocList(condition);
		}

		/* ロケーション＆ロット */
		if (!StringUtils.isEmpty(condition.getSrhLocationCd())
				&& StringUtils.isEmpty(condition.getSrhItemCd())
				&& StringUtils.isEmpty(condition.getSrhOtherCompanyCd1())
				&& !StringUtils.isEmpty(condition.getSrhLotNo())) {
			list = inventoryListDao.getReLocLotList(condition);
		}

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final InventoryListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 在庫照会一覧検索（ロケーション在庫帳票用）
	 * @param condition condition
	 * @return List<InventoryLocationListForReport>
	 * @throws NoDataException NoDataException
	 */
	public List<InventoryLocationListForReport> getLocationListForReport(
			final InventoryListForReportCondition condition)
			throws NoDataException {
		List<InventoryLocationListForReport> list = new ArrayList<InventoryLocationListForReport>();

		list = inventoryLocationListForReportDao
				.getLocationListForReport(condition);
		return list;

	}

	/**
	 * 在庫照会一覧検索（下位ロケのロケーション在庫帳票用）
	 * @param condition condition
	 * @return List<InventoryLowerLocationListForReport>
	 * @throws NoDataException NoDataException
	 */
	public List<InventoryLowerLocationListForReport> getLowerLocationListForReport(
			final InventoryListForReportCondition condition)
			throws NoDataException {
		List<InventoryLowerLocationListForReport> list = new ArrayList<InventoryLowerLocationListForReport>();

		list = inventoryLowerLocationListForReportDao
				.getLowerLocationListForReport(condition);
		return list;

	}

	/**
	 * 在庫照会一覧検索（ロット在庫帳票用）
	 * @param condition condition
	 * @return List<InventoryLotListForReport>
	 * @throws NoDataException NoDataException
	 */
	public List<InventoryLotListForReport> getLotListForReport(
			final InventoryListForReportCondition condition)
			throws NoDataException {

		List<InventoryLotListForReport> list = new ArrayList<InventoryLotListForReport>();

		list = inventoryLotListForReportDao.getLotListForReport(condition);
		return list;

	}

	// /**
	// * 在庫照会一覧検索（帳票用）
	// * @param locationCd locationCd
	// * @param itemCd itemCd
	// * @param otherCompanyCd1 otherCompanyCd1
	// * @param lotNo lotNo
	// * @param availableFlg vailableFlg
	// * @return List<InventoryListForReport>
	// * @throws NoDataException NoDataException
	// */
	// public List<InventoryListForReport> getListForReport(
	// final String locationCd, final String itemCd,
	// final String otherCompanyCd1, final String lotNo,
	// final BigDecimal availableFlg) throws NoDataException {
	// List<InventoryListForReport> list = new
	// ArrayList<InventoryListForReport>();
	//
	// /* ロケーション＆品目＆ロット */
	// if (!StringUtils.isEmpty(locationCd)
	// && (!StringUtils.isEmpty(itemCd) || !StringUtils
	// .isEmpty(otherCompanyCd1))
	// && !StringUtils.isEmpty(lotNo)) {
	// list = inventoryListForReportDao.getLocItemLotListForReport(
	// locationCd, itemCd, otherCompanyCd1, lotNo);
	// }
	//
	// /* ロケーション＆品目 */
	// if (!StringUtils.isEmpty(locationCd)
	// && (!StringUtils.isEmpty(itemCd) || !StringUtils
	// .isEmpty(otherCompanyCd1))
	// && StringUtils.isEmpty(lotNo)) {
	// list = inventoryListForReportDao.getLocItemListForReport(
	// locationCd, itemCd, otherCompanyCd1, availableFlg);
	// }
	//
	// /* ロケーション */
	// if (!StringUtils.isEmpty(locationCd) && StringUtils.isEmpty(itemCd)
	// && StringUtils.isEmpty(otherCompanyCd1)
	// && StringUtils.isEmpty(lotNo)) {
	// list = inventoryListForReportDao.getLocListForReport(locationCd,
	// availableFlg);
	// }
	//
	// /* なし */
	// if (StringUtils.isEmpty(locationCd) && StringUtils.isEmpty(itemCd)
	// && StringUtils.isEmpty(otherCompanyCd1)
	// && StringUtils.isEmpty(lotNo)) {
	// list = inventoryListForReportDao.getListForReport(availableFlg);
	// }
	//
	// /* 品目＆ロット */
	// if (StringUtils.isEmpty(locationCd)
	// && (!StringUtils.isEmpty(itemCd) || !StringUtils
	// .isEmpty(otherCompanyCd1))
	// && !StringUtils.isEmpty(lotNo)) {
	// list = inventoryListForReportDao.getItemLotListForReport(itemCd,
	// otherCompanyCd1, lotNo);
	// }
	//
	// /* ロット */
	// if (StringUtils.isEmpty(locationCd) && StringUtils.isEmpty(itemCd)
	// & StringUtils.isEmpty(otherCompanyCd1)
	// && !StringUtils.isEmpty(lotNo)) {
	// list = inventoryListForReportDao.getLotListForReport(lotNo);
	// }
	//
	// /* 品目 */
	// if (StringUtils.isEmpty(locationCd)
	// && (!StringUtils.isEmpty(itemCd) || !StringUtils
	// .isEmpty(otherCompanyCd1))
	// && StringUtils.isEmpty(lotNo)) {
	// list = inventoryListForReportDao.getItemListForReport(itemCd,
	// otherCompanyCd1);
	// }
	//
	// /* ロケーション＆ロット */
	// if (!StringUtils.isEmpty(locationCd) && StringUtils.isEmpty(itemCd)
	// && StringUtils.isEmpty(otherCompanyCd1)
	// && !StringUtils.isEmpty(lotNo)) {
	// list = inventoryListForReportDao.getLocLotListForReport(locationCd,
	// lotNo);
	// }
	//
	// if (list.isEmpty()) {
	// throw new NoDataException();
	// }
	//
	// return list;
	// }

	/**
	 * ロケーション検索
	 * @param locationCd ロケーションコード
	 * @return LocationDetail
	 */
	public LocationDetail getLocationEntity(final String locationCd) {
		LocationDetail bean = locationDetailDao.getEntity(locationCd);
		return bean;
	}

	/**
	 * 品目検索
	 * @param itemCd 品目コード
	 * @return InventoryItemQueueDetail
	 */
	public InventoryItemQueueDetail getItemQueueEntity(final String itemCd) {
		InventoryItemQueueDetail bean = inventoryItemQueueDetailDao
				.getEntity(itemCd);
		return bean;
	}

	/**
	 * 在庫数量合計取得
	 * @param locationCd locationCd
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @param lotNo lotNo
	 * @param availableFlg vailableFlg
	 * @return BigDecimal
	 */
	public BigDecimal getTotalQty(final String locationCd, final String itemCd,
			final String otherCompanyCd1, final String lotNo,
			final BigDecimal availableFlg) {
		BigDecimal totalQty = null;

		/* ロケーション＆品目＆ロット */
		if (!StringUtils.isEmpty(locationCd)
				&& (!StringUtils.isEmpty(itemCd) || !StringUtils
						.isEmpty(otherCompanyCd1))
				&& !StringUtils.isEmpty(lotNo)) {
			totalQty = inventoryListDao.getLocItemLotTotalQty(locationCd,
				itemCd, otherCompanyCd1, lotNo);
		}

		/* ロケーション＆品目 */
		if (!StringUtils.isEmpty(locationCd)
				&& (!StringUtils.isEmpty(itemCd) || !StringUtils
						.isEmpty(otherCompanyCd1))
				&& StringUtils.isEmpty(lotNo)) {
			totalQty = inventoryListDao.getLocItemTotalQty(locationCd, itemCd,
				otherCompanyCd1);
		}

		/* ロケーション */
		if (!StringUtils.isEmpty(locationCd) && StringUtils.isEmpty(itemCd)
				&& StringUtils.isEmpty(otherCompanyCd1)
				&& StringUtils.isEmpty(lotNo)) {
			totalQty = inventoryListDao.getLocTotalQty(locationCd);
		}

		/* なし */
		if (StringUtils.isEmpty(locationCd) && StringUtils.isEmpty(itemCd)
				&& StringUtils.isEmpty(otherCompanyCd1)
				&& StringUtils.isEmpty(lotNo)) {
			totalQty = inventoryListDao.getTotalQty(availableFlg);
		}

		/* 品目＆ロット */
		if (StringUtils.isEmpty(locationCd)
				&& (!StringUtils.isEmpty(itemCd) || !StringUtils
						.isEmpty(otherCompanyCd1))
				&& !StringUtils.isEmpty(lotNo)) {
			totalQty = inventoryListDao.getItemLotTotalQty(itemCd,
				otherCompanyCd1, lotNo);
		}

		/* ロット */
		if (StringUtils.isEmpty(locationCd) && StringUtils.isEmpty(itemCd)
				& StringUtils.isEmpty(otherCompanyCd1)
				&& !StringUtils.isEmpty(lotNo)) {
			totalQty = inventoryListDao.getLotTotalQty(lotNo);
		}

		/* 品目 */
		if (StringUtils.isEmpty(locationCd)
				&& (!StringUtils.isEmpty(itemCd) || !StringUtils
						.isEmpty(otherCompanyCd1))
				&& StringUtils.isEmpty(lotNo)) {
			totalQty = inventoryListDao
					.getItemTotalQty(itemCd, otherCompanyCd1);
		}

		/* ロケーション＆ロット */
		if (!StringUtils.isEmpty(locationCd) && StringUtils.isEmpty(itemCd)
				&& StringUtils.isEmpty(otherCompanyCd1)
				&& !StringUtils.isEmpty(lotNo)) {
			totalQty = inventoryListDao.getLocLotTotalQty(locationCd, lotNo);
		}

		return totalQty;
	}

	/**
	 * 在庫数量合計取得
	 * @param locationCd locationCd
	 * @param itemCd itemCd
	 * @param otherCompanyCd1 otherCompanyCd1
	 * @param lotNo lotNo
	 * @param availableFlg vailableFlg
	 * @return BigDecimal
	 */
	public BigDecimal getReTotalQty(final String locationCd,
			final String itemCd, final String otherCompanyCd1,
			final String lotNo, final BigDecimal availableFlg) {
		BigDecimal totalQty = null;

		/* ロケーション＆品目＆ロット */
		if (!StringUtils.isEmpty(locationCd)
				&& (!StringUtils.isEmpty(itemCd) || !StringUtils
						.isEmpty(otherCompanyCd1))
				&& !StringUtils.isEmpty(lotNo)) {
			totalQty = inventoryListDao.getReLocItemLotTotalQty(locationCd,
				itemCd, otherCompanyCd1, lotNo, availableFlg);
		}

		/* ロケーション＆品目 */
		if (!StringUtils.isEmpty(locationCd)
				&& (!StringUtils.isEmpty(itemCd) || !StringUtils
						.isEmpty(otherCompanyCd1))
				&& StringUtils.isEmpty(lotNo)) {
			totalQty = inventoryListDao.getReLocItemTotalQty(locationCd,
				itemCd, otherCompanyCd1, availableFlg);
		}

		/* ロケーション */
		if (!StringUtils.isEmpty(locationCd) && StringUtils.isEmpty(itemCd)
				&& StringUtils.isEmpty(otherCompanyCd1)
				&& StringUtils.isEmpty(lotNo)) {
			totalQty = inventoryListDao.getReLocTotalQty(locationCd,
				availableFlg);
		}

		/* ロケーション＆ロット */
		if (!StringUtils.isEmpty(locationCd) && StringUtils.isEmpty(itemCd)
				&& StringUtils.isEmpty(otherCompanyCd1)
				&& !StringUtils.isEmpty(lotNo)) {
			totalQty = inventoryListDao.getReLocLotTotalQty(locationCd, lotNo,
				availableFlg);
		}

		return totalQty;
	}

	/* -------------------- setter -------------------- */

	/**
	 * inventoryListDaoを設定します。
	 * @param inventoryListDao inventoryListDao
	 */
	public void setInventoryListDao(final InventoryListDao inventoryListDao) {
		this.inventoryListDao = inventoryListDao;
	}

	/**
	 * locationDetailDaoを設定します。
	 * @param locationDetailDao locationDetailDao
	 */
	public void setLocationDetailDao(final LocationDetailDao locationDetailDao) {
		this.locationDetailDao = locationDetailDao;
	}

	/**
	 * inventoryItemQueueDetailDaoを設定します。
	 * @param inventoryItemQueueDetailDao inventoryItemQueueDetailDao
	 */
	public void setInventoryItemQueueDetailDao(
			final InventoryItemQueueDetailDao inventoryItemQueueDetailDao) {
		this.inventoryItemQueueDetailDao = inventoryItemQueueDetailDao;
	}

	/**
	 * inventoryLocationListForReportDaoを設定します。
	 * @param inventoryLocationListForReportDao
	 *            inventoryLocationListForReportDao
	 */
	public void setInventoryLocationListForReportDao(
			final InventoryLocationListForReportDao inventoryLocationListForReportDao) {
		this.inventoryLocationListForReportDao = inventoryLocationListForReportDao;
	}

	/**
	 * inventoryLowerLocationListForReportDaoを設定します。
	 * @param inventoryLowerLocationListForReportDao
	 *            inventoryLowerLocationListForReportDao
	 */
	public void setInventoryLowerLocationListForReportDao(
			final InventoryLowerLocationListForReportDao inventoryLowerLocationListForReportDao) {
		this.inventoryLowerLocationListForReportDao = inventoryLowerLocationListForReportDao;
	}

	/**
	 * inventoryLotListForReportDaoを設定します。
	 * @param inventoryLotListForReportDao inventoryLotListForReportDao
	 */
	public void setInventoryLotListForReportDao(
			final InventoryLotListForReportDao inventoryLotListForReportDao) {
		this.inventoryLotListForReportDao = inventoryLotListForReportDao;
	}
}
