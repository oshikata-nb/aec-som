/*
 * Created on Mon Mar 09 08:51:33 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.financialclass;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * FinancialClassクラス.
 * @author t0011036
 */
public class FinancialClass extends FinancialClassBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public FinancialClass() {
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
