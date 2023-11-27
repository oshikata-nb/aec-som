/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.companylist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCompanyListDaoクラス
 * @author t0011036
 */
public class MockCompanyListDao implements CompanyListDao {

	/**
	 * コンストラクタ.
	 */
	public MockCompanyListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CompanyList> getList(final CompanyListPagerCondition condition) {
		List<CompanyList> list = new ArrayList<CompanyList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhHomeName1())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhHomeName1())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* CompanyListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * CompanyListを生成する
	 * @param i インデックス
	 * @return CompanyList
	 */
	private CompanyList createBean(final int i) {
		CompanyList bean = new CompanyList();
		bean.setCompanyCd("COMPANY" + i);
		bean.setHomeName1("NAME" + i);
		return bean;
	}
}
