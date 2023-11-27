/*
 * Created on 2009/01/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.vender;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockVenderListForAutoCompleteDaoクラス
 * @author kanri-user
 */
public class MockVenderForAutoCompleteDao implements VenderForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockVenderForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<VenderForAutoComplete> getSearchList(
			final String venderDivison, final String venderCd,
			final String rowlimit) {
		List<VenderForAutoComplete> list = new ArrayList<VenderForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(venderCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(venderCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* VenderListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * VenderListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return VenderListForAutoComplete
	 */
	private VenderForAutoComplete createBean(final int i) {
		VenderForAutoComplete bean = new VenderForAutoComplete();
		bean.setVenderCd("VENDER" + i);
		bean.setVenderShortedName("NAME" + i);
		return bean;
	}
}
