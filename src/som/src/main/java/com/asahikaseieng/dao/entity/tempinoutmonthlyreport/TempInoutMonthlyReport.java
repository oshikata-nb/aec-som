/*
 * Created on Wed Apr 14 10:59:16 JST 2010
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.tempinoutmonthlyreport;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * TempInoutMonthlyReportクラス.
 * @author kanri-user
 */
public class TempInoutMonthlyReport extends TempInoutMonthlyReportBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public TempInoutMonthlyReport() {
		super();
	}

	/**
	 * 更新日時を返す
	 * @return Timestamp
	 */
	public Timestamp getCurrentTimestamp() {
		return AecDateUtils.getCurrentTimestamp();
	}
}
