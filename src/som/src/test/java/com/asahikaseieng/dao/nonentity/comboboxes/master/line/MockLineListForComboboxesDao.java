/*
 * Created on 2009/05/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.line;

import java.util.ArrayList;
import java.util.List;

/**
 * MockLineListForComboboxesDaoクラス
 * @author t0011036
 */
public class MockLineListForComboboxesDao implements LineListForComboboxesDao {

	/**
	 * コンストラクタ.
	 */
	public MockLineListForComboboxesDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<LineListForComboboxes> getListForComboboxes() {
		List<LineListForComboboxes> list = new ArrayList<LineListForComboboxes>();

		for (int i = 1; i < 10; i++) {
			/* LineListForComboboxesを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * LineListForComboboxesを生成する
	 * @param i インデックス
	 * @return LineListForComboboxes
	 */
	private LineListForComboboxes createBean(final int i) {
		LineListForComboboxes bean = new LineListForComboboxes();
		bean.setProductionLine("PRODUCTION_LINE" + i);
		bean.setProductionLineName("NAME" + i);
		return bean;
	}
}
