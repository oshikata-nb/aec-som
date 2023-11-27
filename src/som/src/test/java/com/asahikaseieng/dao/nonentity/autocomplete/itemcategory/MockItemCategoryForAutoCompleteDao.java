/*
 * Created on 2009/02/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.itemcategory;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemCategoryListForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockItemCategoryForAutoCompleteDao implements
		ItemCategoryForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemCategoryForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemCategoryForAutoComplete> getSearchList(
			final String itemCategory, final String rowlimit) {
		List<ItemCategoryForAutoComplete> list = new ArrayList<ItemCategoryForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCategory)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCategory)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemCategoryListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ItemCategoryListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return ItemCategoryListForAutoComplete
	 */
	private ItemCategoryForAutoComplete createBean(final int i) {
		ItemCategoryForAutoComplete bean = new ItemCategoryForAutoComplete();
		bean.setItemCategory("ITEM_CATEGORY" + i);
		bean.setItemCategoryName("NAME" + i);
		return bean;
	}
}
