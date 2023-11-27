/*
 * Created on 2009/05/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.bank;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBankForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockBankForAutoCompleteDao implements BankForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockBankForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BankForAutoComplete> getSearchList(final String bankMasterCd,
			final String rowlimit) {
		List<BankForAutoComplete> list = new ArrayList<BankForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(bankMasterCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(bankMasterCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* BankListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * BankListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return aREAListForAutoComplete
	 */
	private BankForAutoComplete createBean(final int i) {
		BankForAutoComplete bean = new BankForAutoComplete();
		bean.setBankCd("BANK_CD" + i);
		bean.setBankName("BANK_NAME" + i);
		bean.setBranchCd("BRANCH_CD" + i);
		bean.setBranchName("BRANCH_NAME" + i);
		bean.setBankMasterCd("BANK_MASTER_CD" + i);
		bean.setBankMasterName("BANK_MASTER_NAME" + i);
		return bean;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BankForAutoComplete> getBankSearchList(final String bankCd,
			final String rowlimit) {
		List<BankForAutoComplete> list = new ArrayList<BankForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(bankCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(bankCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* BankListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BankForAutoComplete> getBranchSearchList(final String bankCd,
			final String branchCd, final String rowlimit) {
		List<BankForAutoComplete> list = new ArrayList<BankForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(bankCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(bankCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(branchCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(branchCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* BankListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}
}
