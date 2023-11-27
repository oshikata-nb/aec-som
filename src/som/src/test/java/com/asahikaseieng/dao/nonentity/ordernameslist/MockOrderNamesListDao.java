/*
 * Created on 2009/08/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.ordernameslist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOrderNamesListDaoクラス
 * @author t0011036
 */
public class MockOrderNamesListDao implements OrderNamesListDao {

	/**
	 * コンストラクタ.
	 */
	public MockOrderNamesListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<OrderNamesList> getList(final String nameDivision) {
		List<OrderNamesList> list = new ArrayList<OrderNamesList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(nameDivision)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(nameDivision)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* OrderNamesListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * OrderNamesListを生成する
	 * @param i インデックス
	 * @return OrderNamesList
	 */
	private OrderNamesList createBean(final int i) {
		OrderNamesList bean = new OrderNamesList();
		bean.setNameDivision("NAME_DIVISION" + i);
		bean.setNameCd("NAME_CD" + i);
		bean.setName01("NAME01" + i);
		return bean;
	}
}
