/*
 * Created on Fri Jan 16 16:23:20 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.carry;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Carryクラス.
 * @author t0011036
 */
public class Carry extends CarryBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Carry() {
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
