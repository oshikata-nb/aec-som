/*
 * Created on 2009/07/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.artrans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockArTransJournalListDaoクラス
 * @author t0011036
 */
public class MockArTransJournalListDao implements ArTransJournalListDao {

	/**
	 * コンストラクタ.
	 */
	public MockArTransJournalListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ArTransJournalList> getList(final String denYmd) {
		List<ArTransJournalList> list = new ArrayList<ArTransJournalList>();

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
	private ArTransJournalList createBean(final int i) {
		ArTransJournalList bean = new ArTransJournalList();
		bean.setDenKbn("KBN" + i);
		bean.setDenNo("NO" + i);
		bean.setDenYmd("20090701");
		bean.setGyoNo(new BigDecimal(i));
		return bean;
	}
}
