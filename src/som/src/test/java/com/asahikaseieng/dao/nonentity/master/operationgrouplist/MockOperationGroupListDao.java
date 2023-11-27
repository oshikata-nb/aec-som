/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationgrouplist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOperationGroupListDaoクラス
 * @author t0011036
 */
public class MockOperationGroupListDao implements OperationGroupListDao {

	/**
	 * コンストラクタ.
	 */
	public MockOperationGroupListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<OperationGroupList> getList(
			final OperationGroupListPagerCondition condition) {
		List<OperationGroupList> list = new ArrayList<OperationGroupList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getOperationGroupCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getOperationGroupCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* OperationGroupListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * OperationGroupListを生成する
	 * @param i インデックス
	 * @return OperationGroupList
	 */
	private OperationGroupList createBean(final int i) {
		OperationGroupList bean = new OperationGroupList();
		bean.setOperationGroupCd("OPERATION_GROUP_CD" + i);
		bean.setOperationGroupName("NAME" + i);
		return bean;
	}
}
