/*
 * Created on Wed Nov 26 20:05:51 JST 2008
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.topic;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * Topicクラス.
 * @author a1020630
 */
public class Topic extends TopicBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public Topic() {
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
