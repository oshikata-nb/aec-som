/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.organization;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOrganizationListForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockOrganizationForAutoCompleteDao implements
		OrganizationForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockOrganizationForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<OrganizationForAutoComplete> getSearchList(
			final String organizationCd, final String rowlimit) {
		List<OrganizationForAutoComplete> list = new ArrayList<OrganizationForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(organizationCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(organizationCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* OrganizationListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * OrganizationListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return OrganizationListForAutoComplete
	 */
	private OrganizationForAutoComplete createBean(final int i) {
		OrganizationForAutoComplete bean = new OrganizationForAutoComplete();
		bean.setOrganizationCd("ORGANIZATION" + i);
		bean.setOrganizationName("NAME" + i);
		return bean;
	}
}
