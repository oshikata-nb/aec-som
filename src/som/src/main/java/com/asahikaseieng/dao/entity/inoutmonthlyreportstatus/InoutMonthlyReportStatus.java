/*
 * Created on Tue Apr 27 09:42:57 JST 2010
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.inoutmonthlyreportstatus;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * InoutMonthlyReportStatusクラス.
 * @author kanri-user
 */
public class InoutMonthlyReportStatus extends InoutMonthlyReportStatusBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public InoutMonthlyReportStatus() {
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
