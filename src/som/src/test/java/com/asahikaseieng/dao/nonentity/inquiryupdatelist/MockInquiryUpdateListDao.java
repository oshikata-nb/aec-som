/*
 * Created on 2009/04/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inquiryupdatelist;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * MockInquiryUpdateListDaoクラス
 * @author t0011036
 */
public class MockInquiryUpdateListDao implements InquiryUpdateListDao {

	/**
	 * コンストラクタ.
	 */
	public MockInquiryUpdateListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InquiryUpdateList> getList(final Timestamp countDate,
			final String countDivision, final String countLocation,
			final String itemCd, final String lotNo) {
		List<InquiryUpdateList> list = new ArrayList<InquiryUpdateList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(countDivision)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(countDivision)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(countLocation)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(countLocation)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(lotNo)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(lotNo)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InquiryUpdateListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * InquiryUpdateListを生成する
	 * @param i インデックス
	 * @return InquiryUpdateList
	 */
	private InquiryUpdateList createBean(final int i) {
		InquiryUpdateList bean = new InquiryUpdateList();
		bean.setCountDate(AecDateUtils.getCurrentTimestamp());
		bean.setCountDivision("COUNT_DIVISION" + i);
		bean.setCountLocation("COUNT_LOCATION" + i);
		bean.setItemCd("ITEM_CD" + i);
		bean.setLotNo("LOT_NO" + i);
		return bean;
	}
}
