/*
 * Created on Thu Jan 15 17:09:31 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.articleattributequeue;

import java.sql.Timestamp;

import com.asahikaseieng.utils.AecDateUtils;

/**
 * ArticleAttributeQueueクラス.
 * @author t0011036
 */
public class ArticleAttributeQueue extends ArticleAttributeQueueBase {

	private static final long serialVersionUID = 1L;

	/** TIMESTAMP_PROPERTY */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public ArticleAttributeQueue() {
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
