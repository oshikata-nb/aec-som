/*
 * Created on Tue Feb 17 11:46:26 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporarydepositcredit;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * TemporaryDepositCreditクラス.
 * @author kanri-user
 */
public class TemporaryDepositCredit extends TemporaryDepositCreditBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public TemporaryDepositCredit() {
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
