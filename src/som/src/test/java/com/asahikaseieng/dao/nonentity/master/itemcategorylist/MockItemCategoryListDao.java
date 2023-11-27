/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemcategorylist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemCategoryListDaoクラス
 * @author t0011036
 */
public class MockItemCategoryListDao implements ItemCategoryListDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemCategoryListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemCategoryList> getList(
			final ItemCategoryListPagerCondition condition) {
		List<ItemCategoryList> list = new ArrayList<ItemCategoryList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhItemCategory())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhItemCategory())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemCategoryListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ItemCategoryListを生成する
	 * @param i インデックス
	 * @return ItemCategoryList
	 */
	private ItemCategoryList createBean(final int i) {
		ItemCategoryList bean = new ItemCategoryList();
		bean.setItemCategory("ITEM_CATEGORY" + i);
		bean.setItemCategoryName("NAME" + i);
		return bean;
	}
}
