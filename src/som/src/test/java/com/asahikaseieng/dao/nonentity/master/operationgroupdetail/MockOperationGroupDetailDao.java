/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationgroupdetail;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOperationGroupDetailDaoクラス
 * @author t0011036
 */
public class MockOperationGroupDetailDao implements OperationGroupDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockOperationGroupDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public OperationGroupDetail getEntity(final String operationGroupCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(operationGroupCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(operationGroupCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		OperationGroupDetail bean = new OperationGroupDetail();

		/* OperationGroupDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * OperationGroupDetailを生成する
	 * @param bean bean
	 * @return OperationGroupDetail
	 */
	private void createBean(final OperationGroupDetail bean) {
		bean.setOperationGroupCd("OPERATION_GROUP_CD01");
		bean.setOperationGroupName("NAME01");
	}
}
