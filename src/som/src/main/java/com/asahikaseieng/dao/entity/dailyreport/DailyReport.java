/*
 * Created on Thu Feb 12 18:54:08 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.dailyreport;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * DailyReportクラス.
 * @author kanri-user
 */
public class DailyReport extends DailyReportBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public DailyReport() {
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
