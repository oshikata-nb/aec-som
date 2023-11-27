/*
 * Created on 2009/04/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryinputlist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * MockInquiryInputListDaoクラス
 * @author t0011036
 */
public class MockInquiryInputListDao implements InquiryInputListDao {

	/**
	 * コンストラクタ.
	 */
	public MockInquiryInputListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InquiryInputList> getList(
			final InquiryInputListPagerCondition condition) {
		List<InquiryInputList> list = new ArrayList<InquiryInputList>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhLocationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOtherCompanyCd1())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOtherCompanyCd1())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhAliasLotNo())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhAliasLotNo())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InquiryInputListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * InquiryInputListを生成する
	 * @param i インデックス
	 * @return InquiryInputList
	 */
	private InquiryInputList createBean(final int i) {
		InquiryInputList bean = new InquiryInputList();
		bean.setAliasLotNo("ALIAS_LOT_NO" + i);
		bean.setCountDate(AecDateUtils.getCurrentTimestamp());
		bean.setCountDivision("COUNT_DIVISION" + i);
		bean.setItemCd("ITEM_CD" + i);
		bean.setItemName("ITEM_NAME" + i);
		return bean;
	}
}
