/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancelistdetail;

import java.math.BigDecimal;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBalanceListDetailDaoクラス
 * @author t0011036
 */
public class MockBalanceListDetailDao implements BalanceListDetailDao {

	/**
	 * コンストラクタ.
	 */
	public MockBalanceListDetailDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public BalanceListDetail getEntity(final String balanceCd,
			final BigDecimal balanceType) {
		if (Constants.TEST_PARAMETER_NODATA.equals(balanceCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(balanceCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		BalanceListDetail bean = new BalanceListDetail();

		/* BalanceListDetailを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * BalanceListDetailを生成する
	 * @param bean bean
	 * @return BalanceListDetail
	 */
	private void createBean(final BalanceListDetail bean) {
		bean.setParentBalanceCd("BALANCE001");
		bean.setVenderCd1("VENDER_CD001");
		bean.setVenderCd2("VENDER_CD002");
		bean.setVenderCd3("VENDER_CD003");
		bean.setVenderCd4("VENDER_CD004");
		bean.setVenderCd5("VENDER_CD005");
	}
}
