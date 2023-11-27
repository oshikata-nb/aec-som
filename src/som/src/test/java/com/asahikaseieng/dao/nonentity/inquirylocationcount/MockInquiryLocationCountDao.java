/*
 * Created on 2009/04/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquirylocationcount;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInquiryLocationCountDaoクラス
 * @author t0011036
 */
public class MockInquiryLocationCountDao implements InquiryLocationCountDao {

	/**
	 * コンストラクタ.
	 */
	public MockInquiryLocationCountDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public InquiryLocationCount getLocationCount(final String countDivision) {
		if (Constants.TEST_PARAMETER_NODATA.equals(countDivision)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(countDivision)) {
			throw new LargeAmountDataRuntimeException();
		}

		InquiryLocationCount bean = new InquiryLocationCount();

		/* InquiryLocationCountを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * InquiryLocationCountを生成する
	 * @param bean bean
	 * @return InquiryLocationCount
	 */
	private void createBean(final InquiryLocationCount bean) {
		bean.setLocationCount(new BigDecimal("1"));
	}
}
