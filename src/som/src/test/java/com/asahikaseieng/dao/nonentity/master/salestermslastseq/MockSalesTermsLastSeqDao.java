/*
 * Created on 2009/05/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermslastseq;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockSalesTermsLastSeqDaoクラス
 * @author t0011036
 */
public class MockSalesTermsLastSeqDao implements SalesTermsLastSeqDao {

	/**
	 * コンストラクタ.
	 */
	public MockSalesTermsLastSeqDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public SalesTermsLastSeq getLastSeq(final String deliveryCd,
			final String balanceCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(deliveryCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(deliveryCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(balanceCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(balanceCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		SalesTermsLastSeq bean = new SalesTermsLastSeq();

		/* SalesTermsLastSeqを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * SalesTermsLastSeqを生成する
	 * @param bean SalesTermsLastSeq
	 * @return SalesTermsLastSeq
	 */
	private void createBean(final SalesTermsLastSeq bean) {
		bean.setLastSeq(new BigDecimal("1"));
	}
}
