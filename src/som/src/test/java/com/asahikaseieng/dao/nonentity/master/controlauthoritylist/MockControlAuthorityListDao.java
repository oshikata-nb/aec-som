/*
 * Created on 2009/06/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.controlauthoritylist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockControlAuthorityListDaoクラス
 * @author t0011036
 */
public class MockControlAuthorityListDao implements ControlAuthorityListDao {

	/**
	 * コンストラクタ.
	 */
	public MockControlAuthorityListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ControlAuthorityList> getList(final String tantoCd,
			final String organizationCd, final BigDecimal postId,
			final BigDecimal menuId, final BigDecimal tabId,
			final BigDecimal controlId) {
		List<ControlAuthorityList> list = new ArrayList<ControlAuthorityList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(tantoCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(tantoCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ControlAuthorityListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ControlAuthorityListを生成する
	 * @param i インデックス
	 * @return ControlAuthorityList
	 */
	private ControlAuthorityList createBean(final int i) {
		ControlAuthorityList bean = new ControlAuthorityList();
		bean.setControlId(new BigDecimal(i));
		bean.setMenuId(new BigDecimal(i));
		bean.setRoleId(new BigDecimal(i));
		bean.setTabId(new BigDecimal(i));
		return bean;
	}
}
