/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carrydetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCarryDetailDaoクラス
 * @author t0011036
 */
public class MockCarryDetailDao implements CarryDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockCarryDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public CarryDetail getEntity(final String carryCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(carryCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(carryCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		CarryDetail bean = new CarryDetail();

		/* CarryDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * CarryDetailを生成する
	 * @param bean bean
	 * @return CarryDetail
	 */
	private void createBean(final CarryDetail bean) {
		bean.setCarryCd("CARRY001");
		bean.setCarryName1("NAME001");
	}
}
