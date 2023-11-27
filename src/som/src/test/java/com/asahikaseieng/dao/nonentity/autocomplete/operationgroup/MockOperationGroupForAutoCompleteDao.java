/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.operationgroup;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOperationGroupListForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockOperationGroupForAutoCompleteDao implements
		OperationGroupForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockOperationGroupForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<OperationGroupForAutoComplete> getSearchList(
			final String operationGroup, final String rowlimit) {
		List<OperationGroupForAutoComplete> list = new ArrayList<OperationGroupForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(operationGroup)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(operationGroup)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* OperationGroupListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * OperationGroupListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return OperationGroupListForAutoComplete
	 */
	private OperationGroupForAutoComplete createBean(final int i) {
		OperationGroupForAutoComplete bean = new OperationGroupForAutoComplete();
		bean.setOperationGroupCd("OPERATION_GROUP_CD" + i);
		bean.setOperationGroupName("NAME" + i);
		return bean;
	}
}
