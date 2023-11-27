/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationdetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOperationDetailDaoクラス
 * @author t0011036
 */
public class MockOperationDetailDao implements OperationDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockOperationDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public OperationDetail getEntity(final String operationCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(operationCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(operationCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		OperationDetail bean = new OperationDetail();

		/* OperationDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * OperationDetailを生成する
	 * @param bean bean
	 * @return OperationDetail
	 */
	private void createBean(final OperationDetail bean) {
		bean.setOperationCd("OPERATION_CD01");
		bean.setOperationName("NAME01");
	}
}
