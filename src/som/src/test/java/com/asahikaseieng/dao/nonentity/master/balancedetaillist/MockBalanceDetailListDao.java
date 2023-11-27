/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.balancedetaillist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBalanceDetailListDaoクラス
 * @author t0011036
 */
public class MockBalanceDetailListDao implements BalanceDetailListDao {

	/**
	 * コンストラクタ.
	 */
	public MockBalanceDetailListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BalanceDetailList> getList(final String balanceCd) {
		List<BalanceDetailList> list = new ArrayList<BalanceDetailList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(balanceCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(balanceCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* BalanceDetailListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * BalanceDetailListを生成する
	 * @param i インデックス
	 * @return BalanceDetailList
	 */
	private BalanceDetailList createBean(final int i) {
		BalanceDetailList bean = new BalanceDetailList();
		bean.setShopLevel(new BigDecimal(i));
		bean.setShopLevelName("得意先");
		bean.setVenderCd("VENDER_CD" + i);
		bean.setVenderName1("NAME" + i);
		return bean;
	}
}
