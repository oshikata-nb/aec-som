/*
 * Created on 2009/09/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.classificationdetail;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockClassificationDetailDaoクラス
 * @author t0011036
 */
public class MockClassificationDetailDao implements ClassificationDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockClassificationDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public ClassificationDetail getEntity(final BigDecimal dataType,
			final String categoryDivision, final BigDecimal dataTotalDivision) {
		if (Constants.TEST_PARAMETER_NODATA.equals(categoryDivision)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(categoryDivision)) {
			throw new LargeAmountDataRuntimeException();
		}

		ClassificationDetail bean = new ClassificationDetail();

		/* ClassificationDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ClassificationDetailを生成する
	 * @param bean bean
	 * @return ClassificationDetail
	 */
	private void createBean(final ClassificationDetail bean) {
		bean.setDataType(BigDecimal.ONE);
		bean.setCategoryDivision("1");
		bean.setDataTotalDivision(BigDecimal.ONE);
	}
}
