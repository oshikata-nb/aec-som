/*
 * Created on Thu Apr 09 11:09:03 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.cal;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Calクラス.
 * @author t0011036
 */
public class Cal extends CalBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Cal() {
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
