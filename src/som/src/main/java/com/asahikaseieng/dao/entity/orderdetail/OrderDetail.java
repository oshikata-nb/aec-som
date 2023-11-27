/*
 * Created on Tue Feb 17 09:58:54 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderdetail;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * OrderDetailクラス.
 * @author kanri-user
 */
public class OrderDetail extends OrderDetailBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public OrderDetail() {
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
