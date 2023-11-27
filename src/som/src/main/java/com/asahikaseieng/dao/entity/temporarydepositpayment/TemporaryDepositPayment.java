/*
 * Created on Tue Feb 17 17:45:08 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporarydepositpayment;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * TemporaryDepositPaymentクラス.
 * @author kanri-user
 */
public class TemporaryDepositPayment extends TemporaryDepositPaymentBase {

	private static final long serialVersionUID = 1L;


	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public TemporaryDepositPayment() {
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
