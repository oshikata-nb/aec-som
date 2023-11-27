/*
 * Created on Mon Jan 19 17:33:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.commonattributequeue;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * CommonAttributeQueueクラス.
 * @author t0011036
 */
public class CommonAttributeQueue extends CommonAttributeQueueBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public CommonAttributeQueue() {
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
