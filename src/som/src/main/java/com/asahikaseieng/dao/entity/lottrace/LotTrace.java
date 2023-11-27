/*
 * Created on Thu Jan 22 20:04:45 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.lottrace;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * LotTraceクラス.
 * @author t0011036
 */
public class LotTrace extends LotTraceBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public LotTrace() {
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
