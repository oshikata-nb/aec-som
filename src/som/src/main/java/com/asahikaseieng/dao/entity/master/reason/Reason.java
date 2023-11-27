/*
 * Created on Fri Jan 23 16:47:12 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.reason;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Reasonクラス.
 * @author kanri-user
 */
public class Reason extends ReasonBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Reason() {
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
