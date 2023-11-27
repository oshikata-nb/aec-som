/*
 * Created on Thu Jan 22 18:50:34 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.line;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Lineクラス.
 * @author t0011036
 */
public class Line extends LineBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Line() {
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
