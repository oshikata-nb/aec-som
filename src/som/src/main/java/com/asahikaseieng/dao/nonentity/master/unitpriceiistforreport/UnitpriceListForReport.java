/*
 * Created on 2009/05/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.unitpriceiistforreport;

/**
 * UnitpriceListForReportクラス.
 * @author t0011036
 */
public class UnitpriceListForReport extends UnitpriceListForReportBase {

	private static final long serialVersionUID = 1L;

	private String strVersion;

	private String strValidDate;

	/**
	 * コンストラクタ.
	 */
	public UnitpriceListForReport() {
		super();
	}

	/**
	 * strValidDateを取得します。
	 * @return strValidDate
	 */
	public String getStrValidDate() {
		return strValidDate;
	}

	/**
	 * strValidDateを設定します。
	 * @param strValidDate strValidDate
	 */
	public void setStrValidDate(final String strValidDate) {
		this.strValidDate = strValidDate;
	}

	/**
	 * strVersionを取得します。
	 * @return strVersion
	 */
	public String getStrVersion() {
		return strVersion;
	}

	/**
	 * strVersionを設定します。
	 * @param strVersion strVersion
	 */
	public void setStrVersion(final String strVersion) {
		this.strVersion = strVersion;
	}
}
