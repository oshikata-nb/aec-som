/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationlist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOperationListDaoクラス
 * @author t0011036
 */
public class MockOperationListDao implements OperationListDao {

	/**
	 * コンストラクタ.
	 */
	public MockOperationListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<OperationList> getList(
			final OperationListPagerCondition condition) {
		List<OperationList> list = new ArrayList<OperationList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOperationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOperationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* OperationListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * OperationListを生成する
	 * @param i インデックス
	 * @return OperationList
	 */
	private OperationList createBean(final int i) {
		OperationList bean = new OperationList();
		bean.setOperationCd("OPERATION_CD" + i);
		bean.setOperationName("NAME" + i);
		return bean;
	}
}
