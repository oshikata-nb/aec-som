/*
 * Created on Thu Jan 22 13:17:18 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.erasercsm;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * EraserCsmクラス.
 * @author t0011036
 */
public class EraserCsm extends EraserCsmBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public EraserCsm() {
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
