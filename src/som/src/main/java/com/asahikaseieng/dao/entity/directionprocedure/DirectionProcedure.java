/*
 * Created on Thu Jan 22 13:14:55 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directionprocedure;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * DirectionProcedureクラス.
 * @author t0011036
 */
public class DirectionProcedure extends DirectionProcedureBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public DirectionProcedure() {
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
