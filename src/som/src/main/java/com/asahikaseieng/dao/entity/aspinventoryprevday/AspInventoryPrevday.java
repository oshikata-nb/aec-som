/*
 * Created on Mon Jan 19 09:01:53 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.aspinventoryprevday;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * AspInventoryPrevdayクラス.
 * @author t0011036
 */
public class AspInventoryPrevday extends AspInventoryPrevdayBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public AspInventoryPrevday() {
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
