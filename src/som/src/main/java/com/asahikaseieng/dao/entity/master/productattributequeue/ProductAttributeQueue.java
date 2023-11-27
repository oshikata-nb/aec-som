/*
 * Created on Fri Feb 20 17:57:12 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.productattributequeue;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * ProductAttributeQueueクラス.
 * @author kanri-user
 */
public class ProductAttributeQueue extends ProductAttributeQueueBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public ProductAttributeQueue() {
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
