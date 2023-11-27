/*
 * Created on 2008/11/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.topic;

import java.util.List;

import com.asahikaseieng.dao.nonentity.topic.TopicList;
import com.asahikaseieng.dao.nonentity.topic.TopicListDao;
import com.asahikaseieng.dao.nonentity.topic.TopicListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * ロジック実装クラス.掲示板
 * @author tanaka
 */
public class TopicListLogicImpl implements TopicListLogic {

	private TopicListDao topicListDao;

	/**
	 * コンストラクタ.
	 */
	public TopicListLogicImpl() {
	}

	/**
	 * Listメソッド
	 * @param condition condition
	 * @return List
	 * @throws NoDataException NoDataException
	 */
	public List<TopicList> getSearchList(final TopicListPagerCondition condition) throws NoDataException {

		List<TopicList> list = topicListDao.getTopicList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * topicListDaoを設定します。
	 * @param topicListDao TopicListDao
	 */
	public void setTopicListDao(final TopicListDao topicListDao) {
		this.topicListDao = topicListDao;
	}

}
