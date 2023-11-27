/*
 * Created on Tue Mar 24 09:06:02 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.shippingdetail;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * ShippingDetailクラス.
 * @author kanri-user
 */
public class ShippingDetail extends ShippingDetailBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public ShippingDetail() {
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
