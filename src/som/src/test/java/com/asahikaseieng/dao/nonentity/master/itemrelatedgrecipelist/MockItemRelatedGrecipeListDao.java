/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemrelatedgrecipelist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemRelatedGrecipeListDaoクラス
 * @author t0011036
 */
public class MockItemRelatedGrecipeListDao implements ItemRelatedGrecipeListDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemRelatedGrecipeListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemRelatedGrecipeList> getList(final String itemCd) {
		List<ItemRelatedGrecipeList> list = new ArrayList<ItemRelatedGrecipeList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemRelatedGrecipeListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ItemRelatedGrecipeListを生成する
	 * @param i インデックス
	 * @return ItemRelatedGrecipeList
	 */
	private ItemRelatedGrecipeList createBean(final int i) {
		ItemRelatedGrecipeList bean = new ItemRelatedGrecipeList();
		bean.setGrecipeItemCd("ITEM" + i);
		bean.setGrecipeItemName("NAME" + i);
		return bean;
	}
}
