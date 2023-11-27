/*
 * Created on 2009/04/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.convinventory;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockConvInventoryItemDetailDaoクラス
 * @author t0011036
 */
public class MockConvInventoryItemDetailDao implements
		ConvInventoryItemDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockConvInventoryItemDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ConvInventoryItemDetail getEntity(final String itemCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ConvInventoryItemDetail bean = new ConvInventoryItemDetail();

		/* ConvInventoryItemDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ConvInventoryItemDetailを生成する
	 * @param bean bean
	 * @return ConvInventoryItemDetail
	 */
	private void createBean(final ConvInventoryItemDetail bean) {
		bean.setItemCd("ITEM_CD001");
		bean.setTypeDivision(new BigDecimal("1"));
	}
}
