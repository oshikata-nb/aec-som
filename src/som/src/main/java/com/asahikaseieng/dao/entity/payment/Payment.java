/*
 * Created on Wed Feb 04 10:13:16 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.payment;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Paymentクラス.
 * @author kanri-user
 */
public class Payment extends PaymentBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Payment() {
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
