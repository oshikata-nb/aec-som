/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemrelatedmgrecipelist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemRelatedMgrecipeListDaoクラス
 * @author t0011036
 */
public class MockItemRelatedMgrecipeListDao implements
		ItemRelatedMgrecipeListDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemRelatedMgrecipeListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemRelatedMgrecipeList> getList(final String itemCd) {
		List<ItemRelatedMgrecipeList> list = new ArrayList<ItemRelatedMgrecipeList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemRelatedMgrecipeListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ItemRelatedMgrecipeListを生成する
	 * @param i インデックス
	 * @return ItemRelatedMgrecipeList
	 */
	private ItemRelatedMgrecipeList createBean(final int i) {
		ItemRelatedMgrecipeList bean = new ItemRelatedMgrecipeList();
		bean.setMgrecipeItemCd("ITEM" + i);
		bean.setMgrecipeItemName("NAME" + i);
		return bean;
	}
}
