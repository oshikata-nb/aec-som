/*
 * Created on 2009/01/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.login;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockLoginListForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockLoginForAutoCompleteDao implements LoginForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockLoginForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LoginForAutoComplete> getSearchList(final String tantoCd,
			final String rowlimit) {
		List<LoginForAutoComplete> list = new ArrayList<LoginForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(tantoCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(tantoCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* LoginListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * LoginListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return LoginListForAutoComplete
	 */
	private LoginForAutoComplete createBean(final int i) {
		LoginForAutoComplete bean = new LoginForAutoComplete();
		bean.setTantoCd("TANTO_CD" + i);
		bean.setTantoNm("NAME" + i);
		return bean;
	}
}
