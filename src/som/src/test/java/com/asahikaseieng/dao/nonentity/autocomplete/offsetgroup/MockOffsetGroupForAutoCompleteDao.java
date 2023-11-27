/*
 * Created on 2009/07/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.offsetgroup;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockOffsetGroupForAutoCompleteDaoクラス
 * @author t0011036
 */
public class MockOffsetGroupForAutoCompleteDao implements
		OffsetGroupForAutoCompleteDao {

	/**
	 * コンストラクタ.
	 */
	public MockOffsetGroupForAutoCompleteDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<OffsetGroupForAutoComplete> getSearchList(
			final String offsetGroupCd, final String rowlimit) {
		List<OffsetGroupForAutoComplete> list = new ArrayList<OffsetGroupForAutoComplete>();

		if (Constants.TEST_PARAMETER_NODATA.equals(offsetGroupCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(offsetGroupCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* OffsetGroupListForAutoCompleteを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * OffsetGroupListForAutoCompleteを生成する
	 * @param i インデックス
	 * @return OffsetGroupListForAutoComplete
	 */
	private OffsetGroupForAutoComplete createBean(final int i) {
		OffsetGroupForAutoComplete bean = new OffsetGroupForAutoComplete();
		bean.setOffsetGroupCd("OFFSET_GROUP_CD" + i);
		bean.setOffsetGroupName("NAME" + i);
		return bean;
	}
}
