/*
 * Created on Mon Feb 09 15:26:31 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetailaspproduction;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * OrderDetailAspProductionクラス.
 * @author kanri-user
 */
public class OrderDetailAspProduction extends OrderDetailAspProductionBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public OrderDetailAspProduction() {
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
