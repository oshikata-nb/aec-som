/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.line;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockLineListForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockLineForAutoCompleteDao implements LineForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockLineForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LineForAutoComplete> getSearchList(final String productionLine,
			final String rowlimit) {
		List<LineForAutoComplete> list = new ArrayList<LineForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(productionLine)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(productionLine)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* LineListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * LineListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return LineListForAutoComplete
	 */
	private LineForAutoComplete createBean(final int i) {
		LineForAutoComplete bean = new LineForAutoComplete();
		bean.setProductionLine("PRODUCTION_LINE" + i);
		bean.setProductionLineName("NAME" + i);
		return bean;
	}
}
