/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.carry;

import java.util.ArrayList;
import java.util.List;

/**
 * MockCarryListForComboboxesDaoクラス
 * @author t0011036
 */
public class MockCarryListForComboboxesDao implements CarryListForComboboxesDao {

	/**
	 * コンストラクタ.
	 */
	public MockCarryListForComboboxesDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CarryListForComboboxes> getListForComboboxes() {
		List<CarryListForComboboxes> list = new ArrayList<CarryListForComboboxes>();

		for (int i = 1; i < 10; i++) {
			/* CarryListForComboboxesを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * CarryListForComboboxesを生成する
	 * @param i インデックス
	 * @return CarryListForComboboxes
	 */
	private CarryListForComboboxes createBean(final int i) {
		CarryListForComboboxes bean = new CarryListForComboboxes();
		bean.setCarryCd("CARRY" + i);
		bean.setCarryName1("NAME" + i);
		return bean;
	}
}
