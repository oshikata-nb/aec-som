/*
 * Created on 2009/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.belonglistforreport;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.LargeAmountDataRuntimeException;

/**
 * MockBelongListForReportDaoクラス
 * @author t0011036
 */
public class MockBelongListForReportDao implements BelongListForReportDao {

	/**
	 * コンストラクタ.
	 */
	public MockBelongListForReportDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<BelongListForReport> getListForReport(
			final BelongListConditionForReport condition) {
		List<BelongListForReport> list = new ArrayList<BelongListForReport>();

		if (Constants.TEST_PARAMETER_NODATA.equals(condition
				.getSrhOrganizationCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhTantoCd())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_NODATA.equals(condition.getSrhPostId())) {
			return list;
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition
				.getSrhOrganizationCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD
				.equals(condition.getSrhTantoCd())) {
			throw new LargeAmountDataRuntimeException();
		}

		if (Constants.TEST_PARAMETER_THRESHOLD.equals(condition.getSrhPostId())) {
			throw new LargeAmountDataRuntimeException();
		}

		for (int i = 1; i < 10; i++) {
			/* BelongListForReportを生成する */
			list.add(createBean(i));
		}

		return list;
	}

	/**
	 * BelongListForReportを生成する
	 * @param i インデックス
	 * @return BelongListForReport
	 */
	private BelongListForReport createBean(final int i) {
		BelongListForReport bean = new BelongListForReport();
		bean.setOrganizationCd("ORGANIZATION_CD" + i);
		bean.setOrganizationName("部署名称" + i);
		bean.setTantoCd("TANTO_CD" + i);
		bean.setTantoNm("担当者名" + i);
		bean.setPostId(new BigDecimal("i"));
		bean.setPostName("役職名称" + i);
		return bean;
	}
}
