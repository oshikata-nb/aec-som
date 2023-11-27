/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.post;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.postlist.PostList;
import com.asahikaseieng.dao.nonentity.master.postlist.PostListDao;
import com.asahikaseieng.dao.nonentity.master.postlist.PostListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.postlistforreport.PostListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.postlistforreport.PostListForReport;
import com.asahikaseieng.dao.nonentity.master.postlistforreport.PostListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 役職一覧ロジック 実装クラス.
 * @author t0011036
 */
public class PostListLogicImpl implements PostListLogic {

	private PostListDao postListDao;

	private PostListForReportDao postListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public PostListLogicImpl() {
	}

	/**
	 * 役職一覧検索
	 * @param condition 検索条件
	 * @return List<PostList>
	 * @throws NoDataException NoDataException
	 */
	public List<PostList> getList(final PostListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<PostList> list = postListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final PostListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 役職一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<PostListForReport>
	 */
	public List<PostListForReport> getListForReport(
			final PostListConditionForReport condition) {
		List<PostListForReport> list = postListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * postListDaoを設定します。
	 * @param postListDao postListDao
	 */
	public void setPostListDao(final PostListDao postListDao) {
		this.postListDao = postListDao;
	}

	/**
	 * postListForReportDaoを設定します。
	 * @param postListForReportDao postListForReportDao
	 */
	public void setPostListForReportDao(
			final PostListForReportDao postListForReportDao) {
		this.postListForReportDao = postListForReportDao;
	}
}
