/*
 * Created on 2009/04/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.names;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockNamesListForComboboxesDaoクラス
 * @author t0011036
 */
public class MockNamesListForComboboxesDao implements NamesListForComboboxesDao {

	/**
	 * コンストラクタ.
	 */
	public MockNamesListForComboboxesDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<NamesListForComboboxes> getListForComboboxes(
			final String nameDivision) {
		List<NamesListForComboboxes> list = new ArrayList<NamesListForComboboxes>();

		if (Constants.TEST_PARAMETER_NODATA.equals(nameDivision)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(nameDivision)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* NamesListForComboboxesを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * NamesListForComboboxesを生成する
	 * @param i インデックス
	 * @return NamesListForComboboxes
	 */
	private NamesListForComboboxes createBean(final int i) {
		NamesListForComboboxes bean = new NamesListForComboboxes();
		bean.setNameCd("NAME_CD" + i);
		bean.setName01("NAME" + i);
		return bean;
	}
}
