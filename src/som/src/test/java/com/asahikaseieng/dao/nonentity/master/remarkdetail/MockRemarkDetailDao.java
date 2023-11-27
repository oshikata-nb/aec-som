/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarkdetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRemarkDetailDaoクラス
 * @author kanri-user
 */
public class MockRemarkDetailDao implements RemarkDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockRemarkDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public RemarkDetail getEntity(final java.math.BigDecimal remarkCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(remarkCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(remarkCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		RemarkDetail bean = new RemarkDetail();

		/* RemarkDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * RemarkDetailを生成する
	 * @param bean RemarkDetail
	 * @return RemarkDetail
	 */
	private void createBean(final RemarkDetail bean) {
		bean.setVenderDivision("TS");
		bean.setVenderCd("VENDER001");
		bean.setVenderName1("SOM");
	}
}
