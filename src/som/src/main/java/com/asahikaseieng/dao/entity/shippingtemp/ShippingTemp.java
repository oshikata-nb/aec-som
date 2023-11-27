/*
 * Created on Tue Feb 17 16:40:15 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.shippingtemp;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * ShippingTempクラス.
 * @author kanri-user
 */
public class ShippingTemp extends ShippingTempBase {

	private static final long serialVersionUID = 1L;


	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public ShippingTemp() {
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
