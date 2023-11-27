/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.bumon;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBumonListForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockBumonForAutoCompleteDao implements BumonForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockBumonForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BumonForAutoComplete> getSearchList(final String sectionCd,
			final String rowlimit) {
		List<BumonForAutoComplete> list = new ArrayList<BumonForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(sectionCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(sectionCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* BumonListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * BumonListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return BumonListForAutoComplete
	 */
	private BumonForAutoComplete createBean(final int i) {
		BumonForAutoComplete bean = new BumonForAutoComplete();
		bean.setSectionCd("BUMON" + i);
		bean.setSectionName("NAME" + i);
		return bean;
	}
}
