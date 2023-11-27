/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.bumondetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBumonDetailDaoクラス
 * @author t0011036
 */
public class MockBumonDetailDao implements BumonDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockBumonDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public BumonDetail getEntity(final String sectionCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(sectionCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(sectionCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		BumonDetail bean = new BumonDetail();

		/* BumonDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * BumonDetailを生成する
	 * @param bean bean
	 * @return BumonDetail
	 */
	private void createBean(final BumonDetail bean) {
		bean.setSectionCd("SECTION001");
		bean.setSectionName("NAME001");
	}
}
