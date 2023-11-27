/*
 * Created on Fri Jan 16 10:23:58 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.bank;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Bankクラス.
 * @author t0011036
 */
public class Bank extends BankBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Bank() {
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
