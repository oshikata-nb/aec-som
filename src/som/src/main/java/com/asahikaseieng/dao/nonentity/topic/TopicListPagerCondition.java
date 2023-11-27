/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.topic;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * LocationPagerConditionクラス.
 * @author tanaka
 */
public class TopicListPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public TopicListPagerCondition() {
	}
	//
	// 検索入力用.getSearchList
	//

	/* 番号 */
	private String topicId;

	/* 番号 */
	/**
	 * topicIdを取得します。
	 * @return topicId
	 */
	public String getTopicId() {
		return topicId;
	}

	/**
	 * topicIdを設定します。
	 * @param topicId topicId
	 */
	public void setTopicId(final String topicId) {
		this.topicId = topicId;
	}

}
