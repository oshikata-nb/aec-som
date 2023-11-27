/*
 * Created on Tue Jan 20 11:19:02 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.delivery;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Deliveryクラス.
 * @author t0011036
 */
public class Delivery extends DeliveryBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Delivery() {
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
