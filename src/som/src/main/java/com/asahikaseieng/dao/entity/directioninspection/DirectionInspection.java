/*
 * Created on Thu Jan 22 13:14:41 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directioninspection;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * DirectionInspectionクラス.
 * @author t0011036
 */
public class DirectionInspection extends DirectionInspectionBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public DirectionInspection() {
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
