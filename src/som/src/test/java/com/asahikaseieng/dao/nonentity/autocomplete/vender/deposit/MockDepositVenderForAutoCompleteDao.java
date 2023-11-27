/*
 * Created on 2009/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.vender.deposit;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockDepositVenderForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockDepositVenderForAutoCompleteDao implements
		DepositVenderForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockDepositVenderForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<DepositVenderForAutoComplete> getSearchList(
			final String venderDivision, final String venderCd,
			final String rowlimit) {
		List<DepositVenderForAutoComplete> list = new ArrayList<DepositVenderForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(venderCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(venderCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* DepositVenderForForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * DepositVenderForForAutoCompleteを生成する
	 * @param i インデックス
	 * @return DepositVenderForForAutoComplete
	 */
	private DepositVenderForAutoComplete createBean(final int i) {
		DepositVenderForAutoComplete bean = new DepositVenderForAutoComplete();
		bean.setVenderCd("VENDER" + i);
		bean.setVenderShortedName("NAME" + i);
		return bean;
	}
}
