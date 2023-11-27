/*
 * Created on Tue May 19 19:18:17 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.salesinoutrecord;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * SalesInoutRecordクラス.
 * @author kanri-user
 */
public class SalesInoutRecord extends SalesInoutRecordBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public SalesInoutRecord() {
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
