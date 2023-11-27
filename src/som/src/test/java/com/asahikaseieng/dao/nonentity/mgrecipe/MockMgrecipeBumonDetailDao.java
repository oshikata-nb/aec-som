/*
 * Created on 2009/06/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mgrecipe;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockMgrecipeBumonDetailDaoクラス
 * @author t0011036
 */
public class MockMgrecipeBumonDetailDao implements MgrecipeBumonDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockMgrecipeBumonDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public MgrecipeBumonDetail getEntity(final String sectionCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(sectionCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(sectionCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		MgrecipeBumonDetail bean = new MgrecipeBumonDetail();

		/* MgrecipeBumonDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * MgrecipeBumonDetailを生成する
	 * @param bean bean
	 * @return MgrecipeBumonDetail
	 */
	private void createBean(final MgrecipeBumonDetail bean) {
		bean.setSectionCd("SECTION001");
		bean.setSectionName("NAME001");
	}
}
