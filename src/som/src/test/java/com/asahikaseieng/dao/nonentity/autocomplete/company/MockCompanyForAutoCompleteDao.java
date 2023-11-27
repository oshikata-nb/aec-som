/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.company;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCompanyListForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockCompanyForAutoCompleteDao implements CompanyForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockCompanyForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CompanyForAutoComplete> getSearchList(final String companyCd,
			final String rowlimit) {
		List<CompanyForAutoComplete> list = new ArrayList<CompanyForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(companyCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(companyCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* CompanyListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * CompanyListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return CompanyListForAutoComplete
	 */
	private CompanyForAutoComplete createBean(final int i) {
		CompanyForAutoComplete bean = new CompanyForAutoComplete();
		bean.setCompanyCd("COMPANY" + i);
		bean.setHomeName1("NAME" + i);
		return bean;
	}
}
