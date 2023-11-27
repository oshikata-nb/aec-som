/*
 * Created on Fri Jan 23 13:58:09 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.names;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Namesクラス.
 * @author t0011036
 */
public class Names extends NamesBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Names() {
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
