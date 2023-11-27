/*
 * Created on Thu Jan 22 15:02:33 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.inoutsource;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * InoutSourceクラス.
 * @author t0011036
 */
public class InoutSource extends InoutSourceBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public InoutSource() {
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
