/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.dailyreport;

import com.asahikaseieng.utils.AecNumberUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * DailyReportHeaderListクラス.
 * @author fml
 */
public class DailyReportDetailList extends DailyReportDetailListBase
									 implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/* 作業時間 */
	private String strJobTime;

	/**
	 * コンストラクタ.
	 */
	public DailyReportDetailList() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		setStrJobTime(AecNumberUtils.decimalFormat(getJobTime(), "##0.00"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
		setJobTime(AecNumberUtils.convertBigDecimal(getStrJobTime()));
	}

	/**
	 * 作業時間を取得します。
	 * @return strJobTime
	 */
	public String getStrJobTime() {
		return strJobTime;
	}
	/**
	 * 作業時間を設定します。
	 * @param strJobTime strJobTime
	 */
	public void setStrJobTime(final String strJobTime) {
		this.strJobTime = strJobTime;
	}
}

