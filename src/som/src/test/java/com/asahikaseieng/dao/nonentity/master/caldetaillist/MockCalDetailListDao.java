/*
 * Created on 2009/04/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.caldetaillist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCalDetailListDaoクラス
 * @author t0011036
 */
public class MockCalDetailListDao implements CalDetailListDao {

	/**
	 * コンストラクタ.
	 */
	public MockCalDetailListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CalDetailList> getList(final String calCd,
			final BigDecimal calYear) {
		List<CalDetailList> list = new ArrayList<CalDetailList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(calCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(calCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* CalDetailListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * CalDetailListを生成する
	 * @param i インデックス
	 * @return CalDetailList
	 */
	private CalDetailList createBean(final int i) {
		CalDetailList bean = new CalDetailList();
		bean.setCalCd("CAL_CD" + i);
		bean.setCalName("NAME" + i);
		return bean;
	}
}
