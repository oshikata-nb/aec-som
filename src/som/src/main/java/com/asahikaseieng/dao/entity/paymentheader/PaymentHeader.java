/*
 * Created on Fri Jan 23 16:47:43 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.paymentheader;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * PaymentHeaderクラス.
 * @author t0011036
 */
public class PaymentHeader extends PaymentHeaderBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public PaymentHeader() {
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
