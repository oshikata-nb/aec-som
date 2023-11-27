/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.post;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.postlist.PostList;
import com.asahikaseieng.dao.nonentity.master.postlist.PostListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.postlistforreport.PostListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.postlistforreport.PostListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 役職一覧ロジック interface.
 * @author t0011036
 */
public interface PostListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<PostList>
	 */
	List<PostList> getList(final PostListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<PostListForReport>
	 */
	List<PostListForReport> getListForReport(
			final PostListConditionForReport condition);
}
