/*
 * Created on Tue Feb 17 09:58:54 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.frstorderdetail;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * OrderDetailクラス.
 * @author kanri-user
 */
public class FrstOrderDetail extends FrstOrderDetailBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public FrstOrderDetail() {
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
