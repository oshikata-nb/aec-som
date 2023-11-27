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
public class DailyReportHeaderList extends DailyReportHeaderListBase
									 implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/* 時間内 */
	private String strInTotal;
	/* 時間外 */
	private String strOutTotal;
	/* 就業時間 */
	private String strEmpTime;
	/* 間接時間 */
	private String strIndTime;

	/**
	 * コンストラクタ.
	 */
	public DailyReportHeaderList() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		setStrInTotal(AecNumberUtils.decimalFormat(getInTotal(), "##0.00"));
		setStrOutTotal(AecNumberUtils.decimalFormat(getOutTotal(), "##0.00"));
		setStrEmpTime(AecNumberUtils.decimalFormat(getEmpTime(), "##0.00"));
		setStrIndTime(AecNumberUtils.decimalFormat(getIndTime(), "##0.00"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
		setInTotal(AecNumberUtils.convertBigDecimal(getStrInTotal()));
		setOutTotal(AecNumberUtils.convertBigDecimal(getStrOutTotal()));
		setEmpTime(AecNumberUtils.convertBigDecimal(getStrEmpTime()));
		setIndTime(AecNumberUtils.convertBigDecimal(getStrIndTime()));
	}

	/**
	 * 時間内を取得します。
	 * @return strInTotal
	 */
	public String getStrInTotal() {
		return strInTotal;
	}
	/**
	 * 時間内を設定します。
	 * @param strInTotal strInTotal
	 */
	public void setStrInTotal(final String strInTotal) {
		this.strInTotal = strInTotal;
	}

	/**
	 * 時間外を取得します。
	 * @return strOutTotal
	 */
	public String getStrOutTotal() {
		return strOutTotal;
	}
	/**
	 * 時間外を設定します。
	 * @param strOutTotal strOutTotal
	 */
	public void setStrOutTotal(final String strOutTotal) {
		this.strOutTotal = strOutTotal;
	}

	/**
	 * 就業時間を取得します。
	 * @return strEmpTime
	 */
	public String getStrEmpTime() {
		return strEmpTime;
	}
	/**
	 * 就業時間を設定します。
	 * @param strEmpTime strEmpTime
	 */
	public void setStrEmpTime(final String strEmpTime) {
		this.strEmpTime = strEmpTime;
	}

	/**
	 * 就業時間を取得します。
	 * @return strIndTime
	 */
	public String getStrIndTime() {
		return strIndTime;
	}
	/**
	 * 就業時間を設定します。
	 * @param strIndTime strIndTime
	 */
	public void setStrIndTime(final String strIndTime) {
		this.strIndTime = strIndTime;
	}

}

