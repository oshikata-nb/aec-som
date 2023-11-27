/*
 * Created on 2009/05/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.viewauthoritylist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockViewAuthorityListDaoクラス
 * @author t0011036
 */
public class MockViewAuthorityListDao implements ViewAuthorityListDao {

	/**
	 * コンストラクタ.
	 */
	public MockViewAuthorityListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ViewAuthorityList> getList(final String tantoCd,
			final BigDecimal menuId, final BigDecimal tabId) {
		List<ViewAuthorityList> list = new ArrayList<ViewAuthorityList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(tantoCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(tantoCd)) {
			throw new LargeAmountDataRuntimeException();
		}
		for (int i = 1; i < 10; i++) {
			/* ViewAuthorityListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ViewAuthorityListを生成する
	 * @param i インデックス
	 * @return ViewAuthorityList
	 */
	private ViewAuthorityList createBean(final int i) {
		ViewAuthorityList bean = new ViewAuthorityList();
		bean.setMenuId(new BigDecimal(i));
		bean.setRoleId(new BigDecimal(i));
		bean.setTabId(new BigDecimal(i));
		return bean;
	}
}
