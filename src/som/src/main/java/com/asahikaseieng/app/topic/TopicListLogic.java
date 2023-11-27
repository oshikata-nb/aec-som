/*
 * Created on 2007/03/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.topic;

import java.util.List;

import com.asahikaseieng.dao.nonentity.topic.TopicList;
import com.asahikaseieng.dao.nonentity.topic.TopicListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 掲示板 interface.
 * @author tanaka
 */
public interface TopicListLogic {

	/**
	 * 検索処理を行う.
	 * @param condition condition
	 * @throws NoDataException NoDataException
	 * @return List<TopicList>
	 */
	List<TopicList> getSearchList(final TopicListPagerCondition condition) throws NoDataException;

}
