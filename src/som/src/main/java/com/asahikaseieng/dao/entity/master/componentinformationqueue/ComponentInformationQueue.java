/*
 * Created on Tue Jan 20 09:29:15 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.componentinformationqueue;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * ComponentInformationQueueクラス.
 * @author t0011036
 */
public class ComponentInformationQueue extends ComponentInformationQueueBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public ComponentInformationQueue() {
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
