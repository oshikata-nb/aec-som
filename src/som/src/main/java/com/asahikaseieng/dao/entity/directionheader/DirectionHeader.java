/*
 * Created on Thu Jan 22 11:01:50 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directionheader;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * DirectionHeaderクラス.
 * @author t0011036
 */
public class DirectionHeader extends DirectionHeaderBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public DirectionHeader() {
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
