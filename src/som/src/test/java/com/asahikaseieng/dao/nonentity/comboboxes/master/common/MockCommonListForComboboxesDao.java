/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.common;

import java.util.ArrayList;
import java.util.List;

/**
 * MockCommonListForComboboxesDaoクラス
 * @author t0011036
 */
public class MockCommonListForComboboxesDao implements
		CommonListForComboboxesDao {

	/**
	 * コンストラクタ.
	 */
	public MockCommonListForComboboxesDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CommonListForComboboxes> getListForComboboxes() {
		List<CommonListForComboboxes> list = new ArrayList<CommonListForComboboxes>();

		for (int i = 1; i < 10; i++) {
			/* CommonListForComboboxesを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * CommonListForComboboxesを生成する
	 * @param i インデックス
	 * @return CommonListForComboboxes
	 */
	private CommonListForComboboxes createBean(final int i) {
		CommonListForComboboxes bean = new CommonListForComboboxes();
		bean.setCommonCd("COMMON" + i);
		bean.setCommonName("NAME" + i);
		bean.setCommonValue("NAME" + i);
		return bean;
	}
}
