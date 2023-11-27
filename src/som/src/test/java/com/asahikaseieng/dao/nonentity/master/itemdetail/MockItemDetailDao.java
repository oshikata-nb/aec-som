/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemdetail;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemDetailDaoクラス
 * @author t0011036
 */
public class MockItemDetailDao implements ItemDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ItemDetail getEntity(final String itemCd, final BigDecimal version) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ItemDetail bean = new ItemDetail();

		/* ItemDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ItemDetailを生成する
	 * @param bean bean
	 * @return ItemDetail
	 */
	private void createBean(final ItemDetail bean) {
		bean.setItemCd("ITEM_CD001");
		bean.setItemName("NAME001");
	}
}
