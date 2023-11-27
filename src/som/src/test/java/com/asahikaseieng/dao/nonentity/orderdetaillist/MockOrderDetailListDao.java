/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetaillist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOrderDetailListDaoクラス
 * @author kanri-user
 */
public class MockOrderDetailListDao implements OrderDetailListDao {

	/**
	 * コンストラクタ.
	 */
	public MockOrderDetailListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<OrderDetailList> getDetailList(final String orderNo) {
		List<OrderDetailList> list = new ArrayList<OrderDetailList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(orderNo)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(orderNo)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* VenderListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * VenderListを生成する
	 * @param i インデックス
	 * @return VenderList
	 */
	private OrderDetailList createBean(final int i) {
		OrderDetailList bean = new OrderDetailList();
		bean.setItemCd("ITEM" + i);
		return bean;
	}

	/**
	 * {@inheritDoc}
	 */
	public int deleteByOrderNo(final String orderNo) {
		return 0;
	}

	/**
	 * {@inheritDoc}
	 */
	public BigDecimal getOrderDetailMaxRow(final String orderNo) {
		return null;
	}
	
}
