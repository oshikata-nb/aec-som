/*
 * Created on 2009/03/31
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reasondetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;


/**
 * MockReasonDetailDaoクラス
 * @author t0011036
 */
public class MockReasonDetailDao implements ReasonDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockReasonDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ReasonDetail getEntity(
		final String ryCd
	) {
		if (Constants.TEST_PARAMETER_NODATA.equals(ryCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(ryCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ReasonDetail bean = new ReasonDetail();

		/* ReasonDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ReasonDetailを生成する
	 * @param bean bean
	 * @return ReasonDetail
	 */
	private void createBean(final ReasonDetail bean) {
		bean.setRyCd("RY_CD001");
		bean.setRyDescription("RY_DESCRIPTION001");
	}
}
