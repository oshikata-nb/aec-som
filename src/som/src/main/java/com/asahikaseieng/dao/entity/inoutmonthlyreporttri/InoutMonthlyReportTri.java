/*
 * Created on Tue Apr 27 09:43:26 JST 2010
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.inoutmonthlyreporttri;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * InoutMonthlyReportTriクラス.
 * @author kanri-user
 */
public class InoutMonthlyReportTri extends InoutMonthlyReportTriBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public InoutMonthlyReportTri() {
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
