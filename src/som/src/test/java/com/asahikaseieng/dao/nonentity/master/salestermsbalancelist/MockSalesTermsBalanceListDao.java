/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsbalancelist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockSalesTermsBalanceListDaoクラス
 * @author t0011036
 */
public class MockSalesTermsBalanceListDao implements SalesTermsBalanceListDao {

	/**
	 * コンストラクタ.
	 */
	public MockSalesTermsBalanceListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<SalesTermsBalanceList> getList(final String balanceCd) {
		List<SalesTermsBalanceList> list = new ArrayList<SalesTermsBalanceList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(balanceCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(balanceCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* SalesTermsBalanceListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * SalesTermsBalanceListを生成する
	 * @param i インデックス
	 * @return SalesTermsBalanceList
	 */
	private SalesTermsBalanceList createBean(final int i) {
		SalesTermsBalanceList bean = new SalesTermsBalanceList();
		bean.setVenderCd("VENDER_CD" + i);
		bean.setVenderName1("VENDER_NAME" + i);
		bean.setShopLevel(new BigDecimal(i));
		bean.setShopLevelName("SHOP_LEVEL_NAME" + i);
		return bean;
	}
}
