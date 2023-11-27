/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancelist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBalanceListDaoクラス
 * @author t0011036
 */
public class MockBalanceListDao implements BalanceListDao {

	/**
	 * コンストラクタ.
	 */
	public MockBalanceListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BalanceList> getList(final BalanceListPagerCondition condition) {
		List<BalanceList> list = new ArrayList<BalanceList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhVenderCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhVenderCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* BalanceListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * BalanceListを生成する
	 * @param i インデックス
	 * @return BalanceList
	 */
	private BalanceList createBean(final int i) {
		BalanceList bean = new BalanceList();
		bean.setBalanceCd("BALANCE" + i);
		bean.setBalanceTypeName("BALANCE_NAME" + i);
		bean.setVenderName1("VENDER_NAME1" + i);
		bean.setVenderName2("VENDER_NAME2" + i);
		bean.setVenderName3("VENDER_NAME3" + i);
		bean.setVenderName4("VENDER_NAME4" + i);
		bean.setVenderName5("VENDER_NAME5" + i);
		return bean;
	}
}
