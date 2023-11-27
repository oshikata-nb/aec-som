/*
 * Created on Thu Jan 22 19:33:01 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.location;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Locationクラス.
 * @author t0011036
 */
public class Location extends LocationBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Location() {
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
