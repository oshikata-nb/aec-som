/*
 * Created on Thu Jan 22 18:21:03 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetailunitprice;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Unitpriceクラス.
 * @author kanri-user
 */
public class OrderDetailUnitprice extends OrderDetailUnitpriceBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public OrderDetailUnitprice() {
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
