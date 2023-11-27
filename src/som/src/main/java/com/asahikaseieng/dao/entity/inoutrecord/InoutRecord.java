/*
 * Created on Thu Jan 22 15:02:22 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.inoutrecord;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * InoutRecordクラス.
 * @author t0011036
 */
public class InoutRecord extends InoutRecordBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public InoutRecord() {
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
