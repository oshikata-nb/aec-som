/*
 * Created on 2009/05/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.master.itemcategory;

import java.util.ArrayList;
import java.util.List;

/**
 * MockItemCategoryListForComboboxesDaoクラス
 * @author t0011036
 */
public class MockItemCategoryListForComboboxesDao implements
		ItemCategoryListForComboboxesDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemCategoryListForComboboxesDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemCategoryListForComboboxes> getListForComboboxes() {
		List<ItemCategoryListForComboboxes> list = new ArrayList<ItemCategoryListForComboboxes>();

		for (int i = 1; i < 10; i++) {
			/* ItemCategoryListForComboboxesを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ItemCategoryListForComboboxesを生成する
	 * @param i インデックス
	 * @return ItemCategoryListForComboboxes
	 */
	private ItemCategoryListForComboboxes createBean(final int i) {
		ItemCategoryListForComboboxes bean = new ItemCategoryListForComboboxes();
		bean.setItemCategory("ITEM_CATEGORY" + i);
		bean.setItemCategoryName("NAME" + i);
		return bean;
	}
}
