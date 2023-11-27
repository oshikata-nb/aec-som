/*
 * Created on 2009/02/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.role;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRoleListForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockRoleForAutoCompleteDao implements RoleForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockRoleForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RoleForAutoComplete> getSearchList(final String roleId,
			final String rowlimit) {
		List<RoleForAutoComplete> list = new ArrayList<RoleForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(roleId)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(roleId)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* RoleListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * RoleListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return RoleListForAutoComplete
	 */
	private RoleForAutoComplete createBean(final int i) {
		RoleForAutoComplete bean = new RoleForAutoComplete();
		bean.setRoleId(new BigDecimal(i));
		bean.setRoleName("NAME" + i);
		return bean;
	}
}
