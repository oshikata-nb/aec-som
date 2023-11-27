/*
 * Created on 2009/07/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.aptrans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockApTransJournalListDaoクラス
 * @author t0011036
 */
public class MockApTransJournalListDao implements ApTransJournalListDao {

	/**
	 * コンストラクタ.
	 */
	public MockApTransJournalListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ApTransJournalList> getList(final String denYmd) {
		List<ApTransJournalList> list = new ArrayList<ApTransJournalList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(denYmd)) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(denYmd)) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ApTransJournalListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ApTransJournalListを生成する
	 * @param i インデックス
	 * @return ApTransJournalList
	 */
	private ApTransJournalList createBean(final int i) {
		ApTransJournalList bean = new ApTransJournalList();
		bean.setDenKbn("KBN" + i);
		bean.setDenNo("NO" + i);
		bean.setDenYmd("20090701");
		bean.setGyoNo(new BigDecimal(i));
		return bean;
	}
}
