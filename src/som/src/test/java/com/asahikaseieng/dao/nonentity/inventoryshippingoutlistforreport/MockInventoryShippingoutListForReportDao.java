/*
 * Created on 2009/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventoryshippingoutlistforreport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInventoryShippingoutListForReportDaoクラス
 * @author t0011036
 */
public class MockInventoryShippingoutListForReportDao implements
		InventoryShippingoutListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockInventoryShippingoutListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryShippingoutListForReport> getListForReport(
			final InventoryShippingoutListConditionForReport condition) {
		List<InventoryShippingoutListForReport> list = new ArrayList<InventoryShippingoutListForReport>();

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

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhLocationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InventoryShippingoutListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * InventoryShippingoutListForReportを生成する
	 * @param i インデックス
	 * @return InventoryShippingoutListForReport
	 */
	private InventoryShippingoutListForReport createBean(final int i) {
		InventoryShippingoutListForReport bean = new InventoryShippingoutListForReport();
		bean.setItemCd("ITEM_CD" + i);
		bean.setLocationCd("LOCATION_CD" + i);
		bean.setAliasLotNo("ALIAS_LOT_NO" + i);
		bean.setFraction(new BigDecimal(i));
		bean.setInventoryQty(new BigDecimal(i));
		return bean;
	}
}
