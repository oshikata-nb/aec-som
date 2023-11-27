/*
 * Created on 2009/05/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.reasonlist;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockReasonListDaoクラス
 * @author t0011036
 */
public class MockReasonListDao implements ReasonListDao {

	/**
	 * コンストラクタ.
	 */
	public MockReasonListDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ReasonList> getList(final ReasonListPagerCondition condition) {
		List<ReasonList> list = new ArrayList<ReasonList>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhRyCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhRyCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* ReasonListを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * ReasonListを生成する
	 * @param i インデックス
	 * @return ReasonList
	 */
	private ReasonList createBean(final int i) {
		ReasonList bean = new ReasonList();
		bean.setRyCd("RY" + i);
		bean.setRyDescription("DESCRIPTION" + i);
		bean.setDefaultFlg(new BigDecimal("0"));
		return bean;
	}
}
