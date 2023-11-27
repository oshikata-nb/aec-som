/*
 * Created on Thu Feb 12 18:50:10 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.directiondetail;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * DirectionDetailクラス.
 * @author kanri-user
 */
public class DirectionDetail extends DirectionDetailBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public DirectionDetail() {
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
