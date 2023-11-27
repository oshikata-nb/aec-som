/*
 * Created on 2009/09/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuenewitemcd;

import java.math.BigDecimal;

/**
 * MockItemQueueNewItemCdDaoクラス
 * @author t0011036
 */
public class MockItemQueueNewItemCdDao implements ItemQueueNewItemCdDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemQueueNewItemCdDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ItemQueueNewItemCd getNewItemCd() {
		ItemQueueNewItemCd bean = new ItemQueueNewItemCd();

		/* ItemQueueNewItemCdを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ItemQueueNewItemCdを生成する
	 * @param bean bean
	 * @return ItemQueueNewItemCd
	 */
	private void createBean(final ItemQueueNewItemCd bean) {
		bean.setNextval(BigDecimal.ONE);
	}
}
