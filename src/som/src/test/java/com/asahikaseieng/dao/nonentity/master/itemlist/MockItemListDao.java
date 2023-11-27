/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemlist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemListDaoクラス
 * @author t0011036
 */
public class MockItemListDao implements ItemListDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemList> getList(final ItemListPagerCondition condition) {
		List<ItemList> list = new ArrayList<ItemList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ItemListを生成する
	 * @param i インデックス
	 * @return ItemList
	 */
	private ItemList createBean(final int i) {
		ItemList bean = new ItemList();
		bean.setItemCd("ITEM" + i);
		bean.setItemName("NAME" + i);
		return bean;
	}
}
