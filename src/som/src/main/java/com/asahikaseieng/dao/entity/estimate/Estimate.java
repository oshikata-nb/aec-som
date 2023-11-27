/*
 * Created on Mon Mar 16 17:00:51 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.estimate;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Estimateクラス.
 * @author t0011036
 */
public class Estimate extends EstimateBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Estimate() {
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
