/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.banklist;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBankListDaoクラス
 * @author t0011036
 */
public class MockBankListDao implements BankListDao {

	/**
	 * コンストラクタ.
	 */
	public MockBankListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BankList> getList(final BankListPagerCondition condition) {
		List<BankList> list = new ArrayList<BankList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhBankCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhBankCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhBranchCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhBranchCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhBankMasterCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhBankMasterCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* BankListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * BankListを生成する
	 * @param i インデックス
	 * @return BankList
	 */
	private BankList createBean(final int i) {
		BankList bean = new BankList();
		bean.setBankCd("BANK" + i);
		bean.setBankName("NAME" + i);
		return bean;
	}
}
