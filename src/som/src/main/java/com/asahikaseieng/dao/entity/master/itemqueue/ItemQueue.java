/*
 * Created on Thu Jan 22 16:04:53 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.itemqueue;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * ItemQueueクラス.
 * @author t0011036
 */
public class ItemQueue extends ItemQueueBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public ItemQueue() {
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
