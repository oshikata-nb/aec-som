/*
 * Created on 2009/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuedelete;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemQueueDeleteDaoクラス
 * @author t0011036
 */
public class MockItemQueueDeleteDao implements ItemQueueDeleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemQueueDeleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int delete(final String itemCd, final BigDecimal version) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ItemQueueDelete bean = new ItemQueueDelete();

		/* ItemQueueDeleteを生成する */
		createBean(bean);

		return 1;
	}

	/**
	 * ItemQueueDeleteを生成する
	 * @param bean bean
	 * @return ItemQueueDelete
	 */
	private void createBean(final ItemQueueDelete bean) {
		bean.setItemCd("ITEM001");
		bean.setItemName("NAME001");
	}
}
