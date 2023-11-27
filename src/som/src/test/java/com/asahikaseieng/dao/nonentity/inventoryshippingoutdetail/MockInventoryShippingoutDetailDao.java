/*
 * Created on 2009/04/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventoryshippingoutdetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInventoryShippingoutDetailDaoクラス
 * @author t0011036
 */
public class MockInventoryShippingoutDetailDao implements
		InventoryShippingoutDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockInventoryShippingoutDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public InventoryShippingoutDetail getEntity(final String locationCd,
			final String itemCd, final String lotNo) {
		if (Constants.TEST_PARAMETER_NODATA.equals(locationCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(locationCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(lotNo)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(lotNo)) {
			throw new LargeAmountDataRuntimeException();
		}

		InventoryShippingoutDetail bean = new InventoryShippingoutDetail();

		/* InventoryShippingoutDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * InventoryShippingoutDetailを生成する
	 * @param bean bean
	 * @return InventoryShippingoutDetail
	 */
	private void createBean(final InventoryShippingoutDetail bean) {
		bean.setLocationCd("LOCATION_CD001");
		bean.setLocationName("LOCATION_NAME001");
		bean.setItemCd("ITEM_CD001");
		bean.setItemName("ITEM_NAME001");
		bean.setLotNo("LOT_NO001");
	}
}
