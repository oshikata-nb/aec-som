/*
 * Created on 2009/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorylist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInventoryListDaoクラス
 * @author t0011036
 */
public class MockInventoryListDao implements InventoryListDao {

	/**
	 * コンストラクタ.
	 */
	public MockInventoryListDao() {
		super();
	}

	/**
	 * InventoryListを生成する
	 * @param i インデックス
	 * @return InventoryList
	 */
	private InventoryList createBean(final int i) {
		InventoryList bean = new InventoryList();
		bean.setLotNo("LOT_NO" + i);
		bean.setFraction(new BigDecimal(i));
		bean.setFractionUnit("FRACTION_UNIT" + i);
		bean.setItemCd("ITEM_CD" + i);
		bean.setItemName("ITEM_NAME" + i);
		return bean;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryList> getLocItemLotList(
			final InventoryListPagerCondition condition) {
		List<InventoryList> list = new ArrayList<InventoryList>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhLocationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOtherCompanyCd1())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOtherCompanyCd1())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhLotNo())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhLotNo())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InventoryListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryList> getList(
			final InventoryListPagerCondition condition) {
		List<InventoryList> list = new ArrayList<InventoryList>();

		for (int i = 1; i < 10; i++) {
			/* InventoryListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal getLocItemLotTotalQty(
			final InventoryListPagerCondition condition) {
		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhLocationCd())) {
			return new BigDecimal("0");
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return new BigDecimal("0");
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOtherCompanyCd1())) {
			return new BigDecimal("0");
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOtherCompanyCd1())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhLotNo())) {
			return new BigDecimal("0");
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhLotNo())) {
			throw new LargeAmountDataRuntimeException();
		}

		return new BigDecimal("0");
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal getTotalQty(final InventoryListPagerCondition condition) {
		return new BigDecimal("0");
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryList> getItemList(
			final InventoryListPagerCondition condition) {
		List<InventoryList> list = new ArrayList<InventoryList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOtherCompanyCd1())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOtherCompanyCd1())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InventoryListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryList> getItemLotList(
			final InventoryListPagerCondition condition) {
		List<InventoryList> list = new ArrayList<InventoryList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOtherCompanyCd1())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOtherCompanyCd1())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhLotNo())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhLotNo())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InventoryListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal getItemLotTotalQty(final String itemCd,
			final String otherCompanyCd1, final String lotNo) {
		return new BigDecimal("0");
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal getItemTotalQty(final String itemCd,
			final String otherCompanyCd1) {
		return new BigDecimal("0");
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryList> getLocItemList(
			final InventoryListPagerCondition condition) {
		List<InventoryList> list = new ArrayList<InventoryList>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhLocationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOtherCompanyCd1())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOtherCompanyCd1())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InventoryListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal getLocItemLotTotalQty(final String locationCd,
			final String itemCd, final String otherCompanyCd1,
			final String lotNo) {
		return new BigDecimal("0");
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal getLocItemTotalQty(final String locationCd,
			final String itemCd, final String otherCompanyCd1) {
		return new BigDecimal("0");
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryList> getLocList(
			final InventoryListPagerCondition condition) {
		List<InventoryList> list = new ArrayList<InventoryList>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhLocationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InventoryListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryList> getLocLotList(
			final InventoryListPagerCondition condition) {
		List<InventoryList> list = new ArrayList<InventoryList>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhLocationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhLotNo())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhLotNo())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InventoryListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal getLocLotTotalQty(final String locationCd,
			final String lotNo) {
		return new BigDecimal("0");
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal getLocTotalQty(final String locationCd) {
		return new BigDecimal("0");
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryList> getLotList(
			final InventoryListPagerCondition condition) {
		List<InventoryList> list = new ArrayList<InventoryList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhLotNo())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhLotNo())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InventoryListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal getLotTotalQty(final String lotNo) {
		return new BigDecimal("0");
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal getTotalQty(final BigDecimal availableFlg) {
		return new BigDecimal("0");
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryList> getReLocItemList(
			final InventoryListPagerCondition condition) {
		List<InventoryList> list = new ArrayList<InventoryList>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhLocationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InventoryListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryList> getReLocItemLotList(
			final InventoryListPagerCondition condition) {
		List<InventoryList> list = new ArrayList<InventoryList>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhLocationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhLotNo())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhLotNo())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InventoryListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryList> getReLocList(
			final InventoryListPagerCondition condition) {
		List<InventoryList> list = new ArrayList<InventoryList>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhLocationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InventoryListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryList> getReLocLotList(
			final InventoryListPagerCondition condition) {
		List<InventoryList> list = new ArrayList<InventoryList>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhLocationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhLotNo())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhLotNo())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InventoryListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal getReLocItemLotTotalQty(final String locationCd,
			final String itemCd, final String otherCompanyCd1,
			final String lotNo, final BigDecimal availableFlg) {
		return new BigDecimal("0");
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal getReLocItemTotalQty(final String locationCd,
			final String itemCd, final String otherCompanyCd1,
			final BigDecimal availableFlg) {
		return new BigDecimal("0");
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal getReLocLotTotalQty(final String locationCd,
			final String lotNo, final BigDecimal availableFlg) {
		return new BigDecimal("0");
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal getReLocTotalQty(final String locationCd,
			final BigDecimal availableFlg) {
		return new BigDecimal("0");
	}
}
