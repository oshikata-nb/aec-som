/*
 * Created on Tue Feb 17 17:51:21 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.purchaseattributequeue;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * PurchaseAttributeQueueクラス.
 * @author kanri-user
 */
public class PurchaseAttributeQueue extends PurchaseAttributeQueueBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public PurchaseAttributeQueue() {
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
