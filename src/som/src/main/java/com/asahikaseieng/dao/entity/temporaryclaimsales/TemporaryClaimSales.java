/*
 * Created on Thu Feb 19 19:53:00 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporaryclaimsales;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * TemporaryClaimSalesクラス.
 * @author kanri-user
 */
public class TemporaryClaimSales extends TemporaryClaimSalesBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public TemporaryClaimSales() {
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
