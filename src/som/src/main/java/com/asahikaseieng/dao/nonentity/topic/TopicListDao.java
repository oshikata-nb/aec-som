/*
 * Created on 2008/11/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.topic;

import java.util.List;

/**
 * TopicDaoクラス
 * @author a1020630
 */
public interface TopicListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.topic.TopicList.class;

	/** ARGSアノテーション topicList */
	String getTopicList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List
	 */
	List<TopicList> getTopicList(final TopicListPagerCondition condition);


}
