/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.remark;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockRemarkListForAutoCompleteDaoクラス
 * @author kanri-user
 */
public class MockRemarkForAutoCompleteDao implements RemarkForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockRemarkForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<RemarkForAutoComplete> getSearchList(
			final String venderDivision, final String venderCd,
			final String deliveryCd, final String itemCd, final String rowlimit) {
		List<RemarkForAutoComplete> list = new ArrayList<RemarkForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(venderCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(venderCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* RemarkListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * RemarkListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return RemarkListForAutoComplete
	 */
	private RemarkForAutoComplete createBean(final int i) {
		RemarkForAutoComplete bean = new RemarkForAutoComplete();
		bean.setDeliveryCd("DELIVERY" + i);
		bean.setItemCd("ITEM" + i);
		return bean;
	}
}
