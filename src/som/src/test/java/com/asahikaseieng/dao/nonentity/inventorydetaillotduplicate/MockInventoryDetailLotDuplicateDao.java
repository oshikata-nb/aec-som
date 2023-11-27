/*
 * Created on 2009/05/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorydetaillotduplicate;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInventoryDetailLotDuplicateDaoクラス
 * @author t0011036
 */
public class MockInventoryDetailLotDuplicateDao implements
		InventoryDetailLotDuplicateDao {

	/**
	 * コンストラクタ.
	 */
	public MockInventoryDetailLotDuplicateDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public InventoryDetailLotDuplicate getEntity(final String lotNo) {
		if (Constants.TEST_PARAMETER_NODATA.equals(lotNo)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(lotNo)) {
			throw new LargeAmountDataRuntimeException();
		}

		InventoryDetailLotDuplicate bean = new InventoryDetailLotDuplicate();

		/* InventoryDetailLotDuplicateを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * InventoryDetailLotDuplicateを生成する
	 * @param bean bean
	 * @return InventoryDetailLotDuplicate
	 */
	private void createBean(final InventoryDetailLotDuplicate bean) {
		bean.setItemCd("ITEM001");
		bean.setLocationCd("LOCATION001");
		bean.setLotNo("LOT001");
	}
}
