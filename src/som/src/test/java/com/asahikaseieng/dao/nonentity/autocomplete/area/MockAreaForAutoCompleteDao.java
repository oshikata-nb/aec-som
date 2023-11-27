/*
 * Created on 2009/02/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.area;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockAreaListForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockAreaForAutoCompleteDao implements AreaForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockAreaForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<AreaForAutoComplete> getSearchList(final String areaCd,
			final String rowlimit) {
		List<AreaForAutoComplete> list = new ArrayList<AreaForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(areaCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(areaCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* AreaListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * AreaListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return aREAListForAutoComplete
	 */
	private AreaForAutoComplete createBean(final int i) {
		AreaForAutoComplete bean = new AreaForAutoComplete();
		bean.setAreaCd("AREA_CD" + i);
		bean.setAreaName("NAME" + i);
		return bean;
	}
}
