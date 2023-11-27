/*
 * Created on Thu Feb 19 19:53:22 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporarydepositsales;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * TemporaryDepositSalesクラス.
 * @author kanri-user
 */
public class TemporaryDepositSales extends TemporaryDepositSalesBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public TemporaryDepositSales() {
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
