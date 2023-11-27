/*
 * Created on 2009/04/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryinputlistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * MockInquiryInputListForReportDaoクラス
 * @author t0011036
 */
public class MockInquiryInputListForReportDao implements
		InquiryInputListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockInquiryInputListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InquiryInputListForReport> getListForReport(
			final InquiryInputListConditionForReport condition) {
		List<InquiryInputListForReport> list = new ArrayList<InquiryInputListForReport>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhLocationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhCountDivision())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhCountDivision())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOtherCompanyCd1())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhAliasLotNo())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhCountDivision())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOtherCompanyCd1())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhAliasLotNo())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InquiryInputListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * InquiryInputListForReportを生成する
	 * @param i インデックス
	 * @return InquiryInputListForReport
	 */
	private InquiryInputListForReport createBean(final int i) {
		InquiryInputListForReport bean = new InquiryInputListForReport();
		bean.setAliasLotNo("ALIAS_LOT_NO" + i);
		bean.setCountDate(AecDateUtils.getCurrentTimestamp());
		bean.setCountDivision("COUNT_DIVISION" + i);
		bean.setItemCd("ITEM_CD" + i);
		bean.setItemName("ITEM_NAME" + i);
		return bean;
	}
}
