/*
 * Created on 2009/05/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemremarkdelete;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemRemarkDeleteDaoクラス
 * @author t0011036
 */
public class MockItemRemarkDeleteDao implements ItemRemarkDeleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemRemarkDeleteDao() {
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

		ItemRemarkDelete bean = new ItemRemarkDelete();

		/* ItemRemarkDeleteを生成する */
		createBean(bean);

		return 1;
	}

	/**
	 * ItemRemarkDeleteを生成する
	 * @param bean bean
	 * @return ItemRemarkDelete
	 */
	private void createBean(final ItemRemarkDelete bean) {
		bean.setItemCd("ITEM001");
		bean.setVersion(new BigDecimal("1"));
	}
}
