/*
 * Created on Wed Apr 29 11:48:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.fbtrailer;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * FbTrailerクラス.
 * @author t0011036
 */
public class FbTrailer extends FbTrailerBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public FbTrailer() {
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
