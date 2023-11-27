/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemtechlabeldetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemTechLabelDetailDaoクラス
 * @author t0011036
 */
public class MockItemTechLabelDetailDao implements ItemTechLabelDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemTechLabelDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ItemTechLabelDetail getEntity(final String labelCd,
			final String commonCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(labelCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(labelCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(commonCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(commonCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ItemTechLabelDetail bean = new ItemTechLabelDetail();

		/* ItemTechLabelDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ItemTechLabelDetailを生成する
	 * @param bean bean
	 * @return ItemTechLabelDetail
	 */
	private void createBean(final ItemTechLabelDetail bean) {
		bean.setCommonCd("COMMON001");
		bean.setLabelCd("LABEL001");
	}
}
