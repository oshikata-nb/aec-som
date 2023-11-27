/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarkdetailcheck;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRemarkDetailCheckDaoクラス
 * @author kanri-user
 */
public class MockRemarkDetailCheckDao implements RemarkDetailCheckDao {

	/**
	 * コンストラクタ.
	 */
	public MockRemarkDetailCheckDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public RemarkDetailCheck getRemarkDetailCheck(final String venderDivision,
			final String venderCd, final String deliveryCd, final String itemCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(venderCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(venderCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		RemarkDetailCheck bean = new RemarkDetailCheck();

		/* RemarkDetailCheckを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * RemarkDetailCheckを生成する
	 * @param bean RemarkDetailCheck
	 * @return RemarkDetailCheck
	 */
	private void createBean(final RemarkDetailCheck bean) {
		bean.setVenderDivision("TS");
		bean.setVenderCd("VENDER001");
	}
}
