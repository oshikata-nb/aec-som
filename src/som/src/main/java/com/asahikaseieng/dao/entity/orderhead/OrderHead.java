/*
 * Created on Tue Feb 17 09:59:07 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderhead;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * OrderHeadクラス.
 * @author kanri-user
 */
public class OrderHead extends OrderHeadBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public OrderHead() {
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
