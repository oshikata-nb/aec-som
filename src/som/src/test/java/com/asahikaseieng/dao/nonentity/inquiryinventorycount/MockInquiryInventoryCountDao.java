/*
 * Created on 2009/04/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryinventorycount;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInquiryInventoryCountDaoクラス
 * @author t0011036
 */
public class MockInquiryInventoryCountDao implements InquiryInventoryCountDao {

	/**
	 * コンストラクタ.
	 */
	public MockInquiryInventoryCountDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public InquiryInventoryCount getInventoryCount(final Timestamp countDate,
			final String countDivision, final String countLocation,
			final String itemCd, final String lotNo,
			final Timestamp countUpdateDate) {
		if (Constants.TEST_PARAMETER_NODATA.equals(countDivision)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(countDivision)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(countLocation)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(countLocation)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(lotNo)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(lotNo)) {
			throw new LargeAmountDataRuntimeException();
		}

		InquiryInventoryCount bean = new InquiryInventoryCount();

		/* InquiryInventoryCountを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * InquiryInventoryCountを生成する
	 * @param bean bean
	 * @return InquiryInventoryCount
	 */
	private void createBean(final InquiryInventoryCount bean) {
		bean.setInventoryCount(new BigDecimal("1"));
	}
}
