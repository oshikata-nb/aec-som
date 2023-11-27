/*
 * Created on 2009/04/28
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inoutrecordlist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInoutRecordListDaoクラス
 * @author t0011036
 */
public class MockInoutRecordListDao implements InoutRecordListDao {

	/**
	 * コンストラクタ.
	 */
	public MockInoutRecordListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InoutRecordList> getList(
			final InoutRecordListPagerCondition condition) {
		List<InoutRecordList> list = new ArrayList<InoutRecordList>();

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
				.equals(condition.getSrhLocationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InoutRecordListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * InoutRecordListを生成する
	 * @param i インデックス
	 * @return InoutRecordList
	 */
	private InoutRecordList createBean(final int i) {
		InoutRecordList bean = new InoutRecordList();
		bean.setDirectionDivision(new BigDecimal(i));
		bean.setDirectionStatus(new BigDecimal(i));
		bean.setInoutDivision(new BigDecimal(i));
		bean.setItemCd("ITEM_CD" + i);
		bean.setItemName("ITEM_NAME" + i);
		return bean;
	}
}
