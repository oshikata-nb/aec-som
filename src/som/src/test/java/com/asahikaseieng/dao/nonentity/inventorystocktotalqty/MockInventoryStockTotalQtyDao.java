/*
 * Created on 2009/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorystocktotalqty;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInventoryStockTotalQtyDaoクラス
 * @author t0011036
 */
public class MockInventoryStockTotalQtyDao implements InventoryStockTotalQtyDao {

	/**
	 * コンストラクタ.
	 */
	public MockInventoryStockTotalQtyDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public InventoryStockTotalQty getTotalQty(final String itemCd,
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

		InventoryStockTotalQty bean = new InventoryStockTotalQty();

		/* InventoryStockTotalQtyを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * InventoryStockTotalQtyを生成する
	 * @param bean bean
	 * @return InventoryStockTotalQty
	 */
	private void createBean(final InventoryStockTotalQty bean) {
		bean.setInventoryQty(new BigDecimal("1"));
	}
}
