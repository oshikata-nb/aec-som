/*
 * Created on 2009/04/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.inventorydrawinglist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockInventoryDrawingListDaoクラス
 * @author t0011036
 */
public class MockInventoryDrawingListDao implements InventoryDrawingListDao {

	/**
	 * コンストラクタ.
	 */
	public MockInventoryDrawingListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<InventoryDrawingList> getList(final InventoryDrawingListPagerCondition condition
	) {
		List<InventoryDrawingList> list = new ArrayList<InventoryDrawingList>();

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

		for (int i = 1; i < 10; i++) {
			/* InventoryDrawingListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * InventoryDrawingListを生成する
	 * @param i インデックス
	 * @return InventoryDrawingList
	 */
	private InventoryDrawingList createBean(final int i) {
		InventoryDrawingList bean = new InventoryDrawingList();
		bean.setInoutDivision(new BigDecimal(i));
		bean.setInoutDivisionName("NAME" + i);
		bean.setInoutQty(new BigDecimal(i));
		bean.setInoutScheduleQty(new BigDecimal(i));
		bean.setInoutSourceNo("NO" + i);
		return bean;
	}
}
