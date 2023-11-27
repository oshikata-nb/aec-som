/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.carry;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCarryListForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockCarryForAutoCompleteDao implements CarryForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockCarryForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CarryForAutoComplete> getSearchList(final String carryCd,
			final String rowlimit) {
		List<CarryForAutoComplete> list = new ArrayList<CarryForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(carryCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(carryCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* CarryListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * CarryListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return CarryListForAutoComplete
	 */
	private CarryForAutoComplete createBean(final int i) {
		CarryForAutoComplete bean = new CarryForAutoComplete();
		bean.setCarryCd("CARRY" + i);
		bean.setCarryName1("NAME" + i);
		return bean;
	}
}
