/*
 * Created on Thu Jan 22 19:42:04 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.slipnumber;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * SlipNumberクラス.
 * @author kanri-user
 */
public class SlipNumber extends SlipNumberBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public SlipNumber() {
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
