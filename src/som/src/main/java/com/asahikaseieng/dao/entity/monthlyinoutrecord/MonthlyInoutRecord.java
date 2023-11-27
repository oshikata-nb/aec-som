/*
 * Created on Thu Jan 22 20:07:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.monthlyinoutrecord;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * MonthlyInoutRecordクラス.
 * @author t0011036
 */
public class MonthlyInoutRecord extends MonthlyInoutRecordBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public MonthlyInoutRecord() {
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
