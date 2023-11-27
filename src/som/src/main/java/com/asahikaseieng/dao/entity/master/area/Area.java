/*
 * Created on Thu Jan 15 16:25:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.area;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Areaクラス.
 * @author t0011036
 */
public class Area extends AreaBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Area() {
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
