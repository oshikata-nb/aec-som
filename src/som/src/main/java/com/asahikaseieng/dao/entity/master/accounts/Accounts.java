/*
 * Created on Wed Jan 14 18:26:16 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.accounts;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Accountsクラス.
 * @author t0011036
 */
public class Accounts extends AccountsBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Accounts() {
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
