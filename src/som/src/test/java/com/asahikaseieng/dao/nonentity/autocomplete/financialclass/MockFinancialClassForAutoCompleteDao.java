/*
 * Created on 2009/05/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.financialclass;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockFinancialClassForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockFinancialClassForAutoCompleteDao implements
		FinancialClassForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockFinancialClassForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<FinancialClassForAutoComplete> getSearchList(
			final String financialClassCd, final String rowlimit) {
		List<FinancialClassForAutoComplete> list = new ArrayList<FinancialClassForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(financialClassCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(financialClassCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* FinancialClassListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * FinancialClassListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return aREAListForAutoComplete
	 */
	private FinancialClassForAutoComplete createBean(final int i) {
		FinancialClassForAutoComplete bean = new FinancialClassForAutoComplete();
		bean.setFinancialClassCd("CD" + i);
		bean.setFinancialClassName("NAME" + i);
		return bean;
	}
}
