/*
 * Created on 2009/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorystocklist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInventoryStockListDaoクラス
 * @author t0011036
 */
public class MockInventoryStockListDao implements InventoryStockListDao {

	/**
	 * コンストラクタ.
	 */
	public MockInventoryStockListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryStockList> getList(
			final InventoryStockListPagerCondition condition) {
		List<InventoryStockList> list = new ArrayList<InventoryStockList>();

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
			/* InventoryStockListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * InventoryStockListを生成する
	 * @param i インデックス
	 * @return InventoryStockList
	 */
	private InventoryStockList createBean(final int i) {
		InventoryStockList bean = new InventoryStockList();
		bean.setInoutDivision(new BigDecimal(i));
		bean.setInoutDivisionName("NAME" + i);
		bean.setInoutQty(new BigDecimal(i));
		bean.setInoutScheduleQty(new BigDecimal(i));
		bean.setInoutSourceNo("NO" + i);
		return bean;
	}
}
