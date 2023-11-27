/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuelastversion;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemQueueLastVersionDaoクラス
 * @author t0011036
 */
public class MockItemQueueLastVersionDao implements ItemQueueLastVersionDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemQueueLastVersionDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ItemQueueLastVersion getLastVersion(final String itemCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ItemQueueLastVersion bean = new ItemQueueLastVersion();

		/* ItemQueueLastVersionを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ItemQueueLastVersionを生成する
	 * @param bean bean
	 * @return ItemQueueLastVersion
	 */
	private void createBean(final ItemQueueLastVersion bean) {
		bean.setVersion(new BigDecimal("1"));
	}
}
