/*
 * Created on 2009/05/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueueheader;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemQueueHeaderDaoクラス
 * @author t0011036
 */
public class MockItemQueueHeaderDao implements ItemQueueHeaderDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemQueueHeaderDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ItemQueueHeader getEntity(final String itemCd,
			final BigDecimal version) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ItemQueueHeader bean = new ItemQueueHeader();

		/* ItemQueueHeaderを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ItemQueueHeaderを生成する
	 * @param bean bean
	 * @return ItemQueueHeader
	 */
	private void createBean(final ItemQueueHeader bean) {
		bean.setHeadItemCd("ITEM001");
		bean.setHeadItemName("NAME001");
	}
}
