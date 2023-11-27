/*
 * Created on 2009/01/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.deliverylist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockDeliveryListDaoクラス
 * @author t0011036
 */
public class MockDeliveryListDao implements DeliveryListDao {

	/**
	 * コンストラクタ.
	 */
	public MockDeliveryListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<DeliveryList> getList(final DeliveryListPagerCondition condition) {
		List<DeliveryList> list = new ArrayList<DeliveryList>();

		if (Constants.TEST_PARAMETER_NODATA
				.equals(condition.getSrhDeliveryCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhDeliveryCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* DeliveryListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * DeliveryListを生成する
	 * @param i インデックス
	 * @return DeliveryList
	 */
	private DeliveryList createBean(final int i) {
		DeliveryList bean = new DeliveryList();
		bean.setDeliveryCd("DELIVERY" + i);
		bean.setDeliveryName1("NAME" + i);
		return bean;
	}
}
