/*
 * Created on 2009/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorymovedetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInventoryMoveDetailDaoクラス
 * @author t0011036
 */
public class MockInventoryMoveDetailDao implements InventoryMoveDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockInventoryMoveDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public InventoryMoveDetail getEntity(final String locationCd,
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

		InventoryMoveDetail bean = new InventoryMoveDetail();

		/* InventoryMoveDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * InventoryMoveDetailを生成する
	 * @param bean bean
	 * @return InventoryMoveDetail
	 */
	private void createBean(final InventoryMoveDetail bean) {
		bean.setOutLocationCd("LOCATION_CD001");
		bean.setOutLocationName("LOCATION_NAME001");
		bean.setItemCd("ITEM_CD001");
		bean.setItemName("ITEM_NAME001");
		bean.setLotNo("LOT_NO001");
	}
}
