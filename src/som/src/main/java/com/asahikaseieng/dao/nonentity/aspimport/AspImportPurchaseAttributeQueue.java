/*
 * Created on Tue Feb 17 17:51:21 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.aspimport;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * AspImportPurchaseAttributeQueueクラス.
 * @author 
 */
public class AspImportPurchaseAttributeQueue extends AspImportPurchaseAttributeQueueBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public AspImportPurchaseAttributeQueue() {
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
