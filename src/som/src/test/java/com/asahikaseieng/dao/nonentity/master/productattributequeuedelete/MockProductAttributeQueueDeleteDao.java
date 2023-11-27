/*
 * Created on 2009/05/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.productattributequeuedelete;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockProductAttributeQueueDeleteDaoクラス
 * @author t0011036
 */
public class MockProductAttributeQueueDeleteDao implements
		ProductAttributeQueueDeleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockProductAttributeQueueDeleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public int delete(final String itemCd, final BigDecimal version) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return 0;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ProductAttributeQueueDelete bean = new ProductAttributeQueueDelete();

		/* ProductAttributeQueueDeleteを生成する */
		createBean(bean);

		return 1;
	}

	/**
	 * ProductAttributeQueueDeleteを生成する
	 * @param bean bean
	 * @return ProductAttributeQueueDelete
	 */
	private void createBean(final ProductAttributeQueueDelete bean) {
		bean.setItemCd("ITEM_CD001");
		bean.setVersion(new BigDecimal("1"));
	}
}
