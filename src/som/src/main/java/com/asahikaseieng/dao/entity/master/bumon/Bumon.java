/*
 * Created on Fri Jan 16 15:04:21 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.bumon;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Bumonクラス.
 * @author t0011036
 */
public class Bumon extends BumonBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Bumon() {
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
