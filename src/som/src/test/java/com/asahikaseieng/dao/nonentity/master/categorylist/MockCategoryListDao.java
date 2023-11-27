/*
 * Created on 2009/01/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.categorylist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockCategoryListDaoクラス
 * @author t0011036
 */
public class MockCategoryListDao implements CategoryListDao {

	/**
	 * コンストラクタ.
	 */
	public MockCategoryListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<CategoryList> getList(final CategoryListPagerCondition condition) {
		List<CategoryList> list = new ArrayList<CategoryList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getCategoryDivision())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getCategoryDivision())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* CategoryListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * CategoryListを生成する
	 * @param i インデックス
	 * @return CategoryList
	 */
	private CategoryList createBean(final int i) {
		CategoryList bean = new CategoryList();
		bean.setCategoryDivision("DIVISION" + i);
		bean.setCategoryCd("CD" + i);
		bean.setCategoryName("NAME" + i);
		return bean;
	}
}
