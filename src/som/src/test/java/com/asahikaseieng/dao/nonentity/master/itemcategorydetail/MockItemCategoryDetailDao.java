/*
 * Created on 2009/01/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemcategorydetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockItemCategoryDetailDaoクラス
 * @author t0011036
 */
public class MockItemCategoryDetailDao implements ItemCategoryDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockItemCategoryDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ItemCategoryDetail getEntity(final String itemCategory) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCategory)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCategory)) {
			throw new LargeAmountDataRuntimeException();
		}

		ItemCategoryDetail bean = new ItemCategoryDetail();

		/* ItemCategoryDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ItemCategoryDetailを生成する
	 * @param bean bean
	 * @return ItemCategoryDetail
	 */
	private void createBean(final ItemCategoryDetail bean) {
		bean.setItemCategory("ITEM_CATEGORY001");
		bean.setItemCategoryName("NAME001");
	}
}
