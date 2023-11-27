/*
 * Created on Tue Apr 28 09:03:33 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.fbheader;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * FbHeaderクラス.
 * @author t0011036
 */
public class FbHeader extends FbHeaderBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public FbHeader() {
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
