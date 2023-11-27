/*
 * Created on 2009/02/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemcategorylistforreport;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemCategoryListForReportDaoクラス
 * @author t0011036
 */
public class MockItemCategoryListForReportDao implements
		ItemCategoryListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemCategoryListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemCategoryListForReport> getListForReport(
			final ItemCategoryListConditionForReport condition) {
		List<ItemCategoryListForReport> list = new ArrayList<ItemCategoryListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhItemCategory())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhItemCategory())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ItemCategoryListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ItemCategoryListForReportを生成する
	 * @param i インデックス
	 * @return ItemCategoryListForReport
	 */
	private ItemCategoryListForReport createBean(final int i) {
		ItemCategoryListForReport bean = new ItemCategoryListForReport();
		bean.setItemCategory("ITEM_CATEGORY" + i);
		bean.setItemCategoryName("NAME" + i);
		return bean;
	}
}
