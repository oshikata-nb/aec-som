/*
 * Created on 2009/04/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.cal;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCalForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockCalForAutoCompleteDao implements CalForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockCalForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CalForAutoComplete> getSearchList(final String calCd,
			final String rowlimit) {
		List<CalForAutoComplete> list = new ArrayList<CalForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(calCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(calCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* CalListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * CalListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return aREAListForAutoComplete
	 */
	private CalForAutoComplete createBean(final int i) {
		CalForAutoComplete bean = new CalForAutoComplete();
		bean.setCalCd("CAL_CD" + i);
		bean.setCalName("NAME" + i);
		return bean;
	}
}
