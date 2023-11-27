/*
 * Created on Wed Feb 04 18:16:43 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.login;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Loginクラス.
 * @author t0011036
 */
public class Login extends LoginBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Login() {
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
