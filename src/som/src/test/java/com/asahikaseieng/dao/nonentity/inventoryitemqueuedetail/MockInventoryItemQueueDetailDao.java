/*
 * Created on 2009/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventoryitemqueuedetail;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInventoryItemQueueDetailDaoクラス
 * @author t0011036
 */
public class MockInventoryItemQueueDetailDao implements
		InventoryItemQueueDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockInventoryItemQueueDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public InventoryItemQueueDetail getEntity(final String itemCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		InventoryItemQueueDetail bean = new InventoryItemQueueDetail();

		/* InventoryItemQueueDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * InventoryItemQueueDetailを生成する
	 * @param bean bean
	 * @return InventoryItemQueueDetail
	 */
	private void createBean(final InventoryItemQueueDetail bean) {
		bean.setItemCd("ITEM_CD001");
		bean.setItemName("ITEM_NAME001");
		bean.setVersion(new BigDecimal("1"));
	}
}
