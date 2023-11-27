/*
 * Created on Fri Jan 08 08:29:04 JST 2010
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporarypayablecredit;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * TemporaryPayableCreditクラス.
 * @author t0011036
 */
public class TemporaryPayableCredit extends TemporaryPayableCreditBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public TemporaryPayableCredit() {
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
