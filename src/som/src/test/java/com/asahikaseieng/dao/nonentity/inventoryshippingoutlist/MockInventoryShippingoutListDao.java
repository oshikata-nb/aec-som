/*
 * Created on 2009/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventoryshippingoutlist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInventoryShippingoutListDaoクラス
 * @author t0011036
 */
public class MockInventoryShippingoutListDao implements
		InventoryShippingoutListDao {

	/**
	 * コンストラクタ.
	 */
	public MockInventoryShippingoutListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryShippingoutList> getList(
			final InventoryShippingoutListPagerCondition condition) {
		List<InventoryShippingoutList> list = new ArrayList<InventoryShippingoutList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhItemCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhItemCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOtherCompanyCd1())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOtherCompanyCd1())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhLocationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhLocationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* InventoryShippingoutListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * InventoryShippingoutListを生成する
	 * @param i インデックス
	 * @return InventoryShippingoutList
	 */
	private InventoryShippingoutList createBean(final int i) {
		InventoryShippingoutList bean = new InventoryShippingoutList();
		bean.setItemCd("ITEM_CD" + i);
		bean.setLocationCd("LOCATION_CD" + i);
		bean.setAliasLotNo("ALIAS_LOT_NO" + i);
		bean.setFraction(new BigDecimal(i));
		bean.setInventoryQty(new BigDecimal(i));
		return bean;
	}
}
