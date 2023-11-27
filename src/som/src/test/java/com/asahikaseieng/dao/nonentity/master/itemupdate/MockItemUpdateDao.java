/*
 * Created on 2009/07/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemupdate;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * MockItemUpdateDaoクラス
 * @author t0011036
 */
public class MockItemUpdateDao implements ItemUpdateDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemUpdateDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int update(final String itemCd, final BigDecimal version) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ItemUpdate bean = new ItemUpdate();

		/* ItemUpdateを生成する */
		createBean(bean);

		return 1;
	}

	/**
	 * ItemUpdateを生成する
	 * @param bean bean
	 * @return ItemUpdate
	 */
	private void createBean(final ItemUpdate bean) {
		bean.setItemCd("ITEM01");
		bean.setVersion(new BigDecimal("1"));
		bean.setUpdateDate(AecDateUtils.getCurrentTimestamp());
	}
}
