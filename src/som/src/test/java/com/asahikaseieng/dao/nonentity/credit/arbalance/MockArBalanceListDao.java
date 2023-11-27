/*
 * Created on 2009/06/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arbalance;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockArBalanceListDaoクラス
 * @author t0011036
 */
public class MockArBalanceListDao implements ArBalanceListDao {

	/**
	 * コンストラクタ.
	 */
	public MockArBalanceListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ArBalanceList> getSearchList(
			final ArBalancePagerCondition condition) {
		List<ArBalanceList> list = new ArrayList<ArBalanceList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhAccruedDebitDivi())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhCreditAmountDivi())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhSectionCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhTantoCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhTargetMonth())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhVenderCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhAccruedDebitDivi())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhCreditAmountDivi())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhSectionCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD
				.equals(condition.getSrhTantoCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhTargetMonth())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhVenderCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ArBalanceListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ArBalanceListを生成する
	 * @param i インデックス
	 * @return ArBalanceList
	 */
	private ArBalanceList createBean(final int i) {
		ArBalanceList bean = new ArBalanceList();
		bean.setVenderCd("VENDER" + i);
		bean.setVenderName("NAME" + i);
		return bean;
	}

	/**
	 * {@inheritDoc}
	 */
	public ArBalanceList getSearchDate(final String sectionCd) {
		if (Constants.TEST_PARAMETER_NODATA.equals(sectionCd)) {
			return null;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(sectionCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		ArBalanceList bean = new ArBalanceList();

		/* ArBalanceListを生成する */
		createBean(bean);

		return bean;
	}

	/**
	 * ArBalanceListを生成する
	 * @param bean ArBalanceList
	 */
	private void createBean(final ArBalanceList bean) {
		bean.setVenderCd("VENDER01");
		bean.setVenderName("NAME01");
	}
}
