/*
 * Created on 2022/08/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directionfileimport;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * DirectionHeaderTempクラス.
 * @author 
 */
public class DirectionHeaderTemp extends DirectionHeaderTempBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public DirectionHeaderTemp() {
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
