/*
 * Created on Tue Apr 28 09:03:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.fbdata;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * FbDataクラス.
 * @author t0011036
 */
public class FbData extends FbDataBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public FbData() {
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
