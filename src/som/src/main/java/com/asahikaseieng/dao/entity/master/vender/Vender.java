/*
 * Created on Wed Feb 04 10:04:38 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.vender;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Venderクラス.
 * @author kanri-user
 */
public class Vender extends VenderBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Vender() {
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
