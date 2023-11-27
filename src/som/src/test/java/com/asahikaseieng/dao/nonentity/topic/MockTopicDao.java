/*
 * Created on 2008/11/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.topic;

import java.util.ArrayList;
import java.util.List;

/**
 * MockTopicDaoクラス
 * @author a1020630
 */
public class MockTopicDao implements TopicListDao {

	/**
	 * コンストラクタ.
	 */
	public MockTopicDao() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public List<TopicList> getTopicList(final TopicListPagerCondition condition) {
		List<TopicList> list = new ArrayList<TopicList>();
		//TODO 実装
		return list;
	}
}

