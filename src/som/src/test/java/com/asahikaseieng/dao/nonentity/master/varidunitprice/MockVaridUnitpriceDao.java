/*
 * Created on 2009/03/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.varidunitprice;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockVaridUnitpriceDaoクラス
 * @author kanri-user
 */
public class MockVaridUnitpriceDao implements VaridUnitpriceDao {

	/**
	 * コンストラクタ.
	 */
	public MockVaridUnitpriceDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public VaridUnitprice getUnitPrice(final String itemCd,
			final String balanceCd, final String date, final BigDecimal status) {
		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		VaridUnitprice bean = new VaridUnitprice();

		/* UnitpriceDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * VaridUnitpriceを生成する
	 * @param bean VaridUnitprice
	 * @return VaridUnitprice
	 */
	private void createBean(final VaridUnitprice bean) {
		bean.setVenderCd("VENDER001");
	}
}
