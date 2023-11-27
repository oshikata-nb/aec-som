/*
 * Created on Tue Mar 24 09:04:36 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.shipping;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Shippingクラス.
 * @author kanri-user
 */
public class Shipping extends ShippingBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Shipping() {
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
