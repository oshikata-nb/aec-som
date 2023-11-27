/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.component;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockComponentListForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockComponentForAutoCompleteDao implements
		ComponentForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockComponentForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ComponentForAutoComplete> getSearchList(
			final String componentCd, final String rowlimit) {
		List<ComponentForAutoComplete> list = new ArrayList<ComponentForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(componentCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(componentCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ComponentListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ComponentListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return ComponentListForAutoComplete
	 */
	private ComponentForAutoComplete createBean(final int i) {
		ComponentForAutoComplete bean = new ComponentForAutoComplete();
		bean.setComponentCd("COMPONENT" + i);
		bean.setComponentName("NAME" + i);
		return bean;
	}
}
