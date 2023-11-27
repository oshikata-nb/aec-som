/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.operation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOperationListForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockOperationForAutoCompleteDao implements
		OperationForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockOperationForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<OperationForAutoComplete> getSearchList(
			final String operationCd, final BigDecimal recipeUse,
			final String rowlimit) {
		List<OperationForAutoComplete> list = new ArrayList<OperationForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(operationCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(operationCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* OperationListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * OperationListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return OperationListForAutoComplete
	 */
	private OperationForAutoComplete createBean(final int i) {
		OperationForAutoComplete bean = new OperationForAutoComplete();
		bean.setOperationCd("OPERATION_CD" + i);
		bean.setOperationName("NAME" + i);
		return bean;
	}
}
