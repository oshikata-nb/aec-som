/*
 * Created on Fri Jan 16 09:17:54 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.balance;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Balanceクラス.
 * @author t0011036
 */
public class Balance extends BalanceBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Balance() {
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
