/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemremarkdetail;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemRemarkDetailDaoクラス
 * @author t0011036
 */
public class MockItemRemarkDetailDao implements ItemRemarkDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemRemarkDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ItemRemarkDetail getEntity(final String itemCd,
			final BigDecimal version) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ItemRemarkDetail bean = new ItemRemarkDetail();

		/* ItemRemarkDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ItemRemarkDetailを生成する
	 * @param bean bean
	 * @return ItemRemarkDetail
	 */
	private void createBean(final ItemRemarkDetail bean) {
		bean.setItemCd("ITEM01");
		bean.setVersion(new BigDecimal("1"));
	}
}
