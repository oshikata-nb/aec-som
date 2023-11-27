/*
 * Created on 2009/05/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorycountdetail;

import java.sql.Timestamp;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * MockInventoryCountDetailDaoクラス
 * @author t0011036
 */
public class MockInventoryCountDetailDao implements InventoryCountDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockInventoryCountDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public InventoryCountDetail getEntity(final Timestamp countDate,
			final String countDivision, final Timestamp countUpdateDate) {
		if (Constants.TEST_PARAMETER_NODATA.equals(countDivision)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(countDivision)) {
			throw new LargeAmountDataRuntimeException();
		}

		InventoryCountDetail bean = new InventoryCountDetail();

		/* InventoryCountDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * InventoryCountDetailを生成する
	 * @param bean bean
	 * @return InventoryCountDetail
	 */
	private void createBean(final InventoryCountDetail bean) {
		bean.setCountDate(AecDateUtils.getCurrentTimestamp());
	}
}
