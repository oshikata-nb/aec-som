/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuedetail;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemQueueDetailDaoクラス
 * @author t0011036
 */
public class MockItemQueueDetailDao implements ItemQueueDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemQueueDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ItemQueueDetail getEntity(final String itemCd,
			final BigDecimal version) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ItemQueueDetail bean = new ItemQueueDetail();

		/* ItemQueueDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ItemQueueDetailを生成する
	 * @param bean bean
	 * @return ItemQueueDetail
	 */
	private void createBean(final ItemQueueDetail bean) {
		bean.setItemCd("ITEM001");
		bean.setItemName("NAME001");
		bean.setVersion(new BigDecimal("1"));
	}
}
