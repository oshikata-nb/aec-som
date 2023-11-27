/*
 * Created on 2009/01/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.venderdetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockVenderDetailDaoクラス
 * @author kanri-user
 */
public class MockVenderDetailDao implements VenderDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockVenderDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public VenderDetail getEntity(final String venderDivison,
			final String venderCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(venderCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(venderCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		VenderDetail bean = new VenderDetail();

		/* VenderDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * VenderDetailを生成する
	 * @param bean VenderDetail
	 * @return VenderDetail
	 */
	private void createBean(final VenderDetail bean) {
		bean.setVenderDivision("TS");
		bean.setVenderCd("VENDER001");
		bean.setVenderName1("SOM");
		bean.setVenderName2("AEC");
	}
}
