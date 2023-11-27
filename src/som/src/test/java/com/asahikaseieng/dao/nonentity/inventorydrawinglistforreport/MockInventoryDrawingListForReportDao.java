/*
 * Created on 2009/04/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorydrawinglistforreport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInventoryDrawingListForReportDaoクラス
 * @author t0011036
 */
public class MockInventoryDrawingListForReportDao implements
		InventoryDrawingListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockInventoryDrawingListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryDrawingListForReport> getListForReport(
			final String itemCd, final String otherCompanyCd1) {
		List<InventoryDrawingListForReport> list = new ArrayList<InventoryDrawingListForReport>();

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
			/* InventoryDrawingListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * InventoryDrawingListForReportを生成する
	 * @param i インデックス
	 * @return InventoryDrawingListForReport
	 */
	private InventoryDrawingListForReport createBean(final int i) {
		InventoryDrawingListForReport bean = new InventoryDrawingListForReport();
		bean.setInoutDivision(new BigDecimal(i));
		bean.setInoutDivisionName("NAME" + i);
		bean.setInoutQty(new BigDecimal(i));
		bean.setInoutScheduleQty(new BigDecimal(i));
		bean.setInoutSourceNo("NO" + i);
		return bean;
	}
}
