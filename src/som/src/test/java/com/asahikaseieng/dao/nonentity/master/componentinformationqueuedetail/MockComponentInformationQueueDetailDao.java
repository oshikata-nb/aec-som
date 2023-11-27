/*
 * Created on 2009/01/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentinformationqueuedetail;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockComponentInformationQueueDetailDaoクラス
 * @author t0011036
 */
public class MockComponentInformationQueueDetailDao implements
		ComponentInformationQueueDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockComponentInformationQueueDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ComponentInformationQueueDetail getEntity(final String itemCd,
			final BigDecimal version, final BigDecimal indicateOrder) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ComponentInformationQueueDetail bean = new ComponentInformationQueueDetail();

		/* ComponentInformationQueueDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ComponentInformationQueueDetailを生成する
	 * @param bean bean
	 * @return ComponentInformationQueueDetail
	 */
	private void createBean(final ComponentInformationQueueDetail bean) {
		bean.setItemCd("ITEM001");
		bean.setVersion(new BigDecimal("1"));
	}
}
