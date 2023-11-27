/*
 * Created on 2009/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorystocklistforreport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInventoryStockListForReportDaoクラス
 * @author t0011036
 */
public class MockInventoryStockListForReportDao implements
		InventoryStockListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockInventoryStockListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryStockListForReport> getListForReport(
			final String itemCd, final String otherCompanyCd1) {
		List<InventoryStockListForReport> list = new ArrayList<InventoryStockListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(otherCompanyCd1)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(otherCompanyCd1)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InventoryStockListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * InventoryStockListForReportを生成する
	 * @param i インデックス
	 * @return InventoryStockListForReport
	 */
	private InventoryStockListForReport createBean(final int i) {
		InventoryStockListForReport bean = new InventoryStockListForReport();
		bean.setInoutDivision(new BigDecimal(i));
		bean.setInoutDivisionName("NAME" + i);
		bean.setInoutQty(new BigDecimal(i));
		bean.setInoutScheduleQty(new BigDecimal(i));
		bean.setInoutSourceNo("NO" + i);
		return bean;
	}
}
