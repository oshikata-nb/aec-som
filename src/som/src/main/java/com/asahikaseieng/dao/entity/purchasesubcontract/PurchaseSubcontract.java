/*
 * Created on Fri Feb 27 17:12:35 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.purchasesubcontract;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * PurchaseSubcontractクラス.
 * @author kanri-user
 */
public class PurchaseSubcontract extends PurchaseSubcontractBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public PurchaseSubcontract() {
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
