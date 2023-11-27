/*
 * Created on 2009/04/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorydrawingtotalqty;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;


/**
 * MockInventoryDrawingTotalQtyDaoクラス
 * @author t0011036
 */
public class MockInventoryDrawingTotalQtyDao implements InventoryDrawingTotalQtyDao {

	/**
	 * コンストラクタ.
	 */
	public MockInventoryDrawingTotalQtyDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public InventoryDrawingTotalQty getTotalQty(final String itemCd,
			final String otherCompanyCd1) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(otherCompanyCd1)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(otherCompanyCd1)) {
			throw new LargeAmountDataRuntimeException();
		}

		InventoryDrawingTotalQty bean = new InventoryDrawingTotalQty();

		/* InventoryDrawingTotalQtyを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * InventoryDrawingTotalQtyを生成する
	 * @param bean bean
	 * @return InventoryDrawingTotalQty
	 */
	private void createBean(final InventoryDrawingTotalQty bean) {
		bean.setInventoryQty(new BigDecimal("1"));
	}
}
