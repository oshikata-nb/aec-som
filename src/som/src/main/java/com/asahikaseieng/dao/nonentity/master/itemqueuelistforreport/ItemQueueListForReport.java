/*
 * Created on 2009/05/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemqueuelistforreport;

/**
 * ItemQueueListForReportクラス.
 * @author t0011036
 */
public class ItemQueueListForReport extends ItemQueueListForReportBase {

	private static final long serialVersionUID = 1L;

	private String strActiveDate;

	/**
	 * コンストラクタ.
	 */
	public ItemQueueListForReport() {
		super();
	}

	/**
	 * strActiveDateを取得します。
	 * @return strActiveDate
	 */
	public String getStrActiveDate() {
		return strActiveDate;
	}

	/**
	 * strActiveDateを設定します。
	 * @param strActiveDate strActiveDate
	 */
	public void setStrActiveDate(final String strActiveDate) {
		this.strActiveDate = strActiveDate;
	}
}
