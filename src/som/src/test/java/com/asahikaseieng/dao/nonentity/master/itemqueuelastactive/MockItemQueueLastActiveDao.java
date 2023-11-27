/*
 * Created on 2009/05/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuelastactive;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemQueueLastActiveDaoクラス
 * @author t0011036
 */
public class MockItemQueueLastActiveDao implements ItemQueueLastActiveDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemQueueLastActiveDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ItemQueueLastActive getEntity(final String itemCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ItemQueueLastActive bean = new ItemQueueLastActive();

		/* ItemQueueLastActiveを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ItemQueueLastActiveを生成する
	 * @param bean bean
	 * @return ItemQueueLastActive
	 */
	private void createBean(final ItemQueueLastActive bean) {
		bean.setItemCd("ITEM001");
		bean.setItemName("NAME001");
		bean.setVersion(BigDecimal.ONE);
	}
}
