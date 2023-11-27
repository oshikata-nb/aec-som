/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.changehistorylist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockChangeHistoryListDaoクラス
 * @author t0011036
 */
public class MockChangeHistoryListDao implements ChangeHistoryListDao {

	/**
	 * コンストラクタ.
	 */
	public MockChangeHistoryListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ChangeHistoryList> getList(final BigDecimal menuId,
			final String itemCd) {
		List<ChangeHistoryList> list = new ArrayList<ChangeHistoryList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(itemCd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(itemCd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ChangeHistoryListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ChangeHistoryListを生成する
	 * @param i インデックス
	 * @return ChangeHistoryList
	 */
	private ChangeHistoryList createBean(final int i) {
		ChangeHistoryList bean = new ChangeHistoryList();
		bean.setItemCd("ITEM" + i);
		bean.setReason("REASON" + i);
		return bean;
	}
}
