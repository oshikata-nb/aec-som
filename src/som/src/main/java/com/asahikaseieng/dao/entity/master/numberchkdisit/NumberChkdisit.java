/*
 * Created on Wed Feb 04 11:54:05 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.numberchkdisit;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * NumberChkdisitクラス.
 * @author t0011036
 */
public class NumberChkdisit extends NumberChkdisitBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public NumberChkdisit() {
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
