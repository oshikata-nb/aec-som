/*
 * Created on 2009/02/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.loginlist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockLoginListDaoクラス
 * @author t0011036
 */
public class MockLoginListDao implements LoginListDao {

	/**
	 * コンストラクタ.
	 */
	public MockLoginListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LoginList> getList(final LoginListPagerCondition condition) {
		List<LoginList> list = new ArrayList<LoginList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhTantoCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD
				.equals(condition.getSrhTantoCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* LoginListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * LoginListを生成する
	 * @param i インデックス
	 * @return LoginList
	 */
	private LoginList createBean(final int i) {
		LoginList bean = new LoginList();
		bean.setTantoCd("TANTO_CD" + i);
		bean.setTantoNm("NAME" + i);
		return bean;
	}
}
