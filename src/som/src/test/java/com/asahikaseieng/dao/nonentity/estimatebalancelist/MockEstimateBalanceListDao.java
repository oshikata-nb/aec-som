/*
 * Created on 2009/03/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.estimatebalancelist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockEstimateBalanceListDaoクラス
 * @author t0011036
 */
public class MockEstimateBalanceListDao implements EstimateBalanceListDao {

	/**
	 * コンストラクタ.
	 */
	public MockEstimateBalanceListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<EstimateBalanceList> getList(final String balanceCd) {
		List<EstimateBalanceList> list = new ArrayList<EstimateBalanceList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(balanceCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(balanceCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* EstimateBalanceListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * EstimateBalanceListを生成する
	 * @param i インデックス
	 * @return EstimateBalanceList
	 */
	private EstimateBalanceList createBean(final int i) {
		EstimateBalanceList bean = new EstimateBalanceList();
		bean.setVenderCd("VENDER_CD" + i);
		bean.setVenderName1("NAME" + i);
		return bean;
	}
}
