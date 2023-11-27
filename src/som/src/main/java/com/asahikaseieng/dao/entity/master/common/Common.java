/*
 * Created on Mon Jan 19 17:30:02 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.common;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Commonクラス.
 * @author t0011036
 */
public class Common extends CommonBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Common() {
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
