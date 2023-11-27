/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.post;

import java.math.BigDecimal;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.master.post.Post;
import com.asahikaseieng.dao.entity.master.post.PostDao;
import com.asahikaseieng.dao.nonentity.master.postdetail.PostDetail;
import com.asahikaseieng.dao.nonentity.master.postdetail.PostDetailDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 役職詳細ロジック 実装クラス.
 * @author t0011036
 */
public class PostDetailLogicImpl implements PostDetailLogic {

	private PostDao postDao;

	private PostDetailDao postDetailDao;

	/**
	 * コンストラクタ.
	 */
	public PostDetailLogicImpl() {
	}

	/**
	 * 役職検索（登録用）
	 * @param postId 役職コード
	 * @return Post
	 */
	public Post getEntity(final BigDecimal postId) {
		if (postId == null || postId == new BigDecimal("0")) {
			throw new IllegalArgumentException("postId is empty");
		}

		Post bean = postDao.getEntity(postId);
		return bean;
	}

	/**
	 * 役職検索（詳細用）
	 * @param postCd 役職コード
	 * @return PostDetail
	 */
	public PostDetail getDetailEntity(final String postCd) {
		PostDetail bean = postDetailDao.getEntity(postCd);
		return bean;
	}

	/**
	 * 更新登録
	 * @param bean 登録データ
	 */
	public void update(final Post bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			postDao.update(bean);
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/**
	 * 追加登録
	 * @param bean 登録データ
	 */
	public void insert(final Post bean) {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			postDao.insert(bean);
		} catch (SQLRuntimeException e) {
			/* 一意制約エラー */
			throw new DuplicateRuntimeException(0);
		}
	}

	/**
	 * 削除
	 * @param bean 削除データ
	 * @throws NoDataException NoDataException
	 */
	public void delete(final Post bean) throws NoDataException {
		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			postDao.delete(bean);
		} catch (SQLRuntimeException e) {
			/* データなしエラー */
			throw new NoDataException();
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * postDaoを設定します。
	 * @param postDao postDao
	 */
	public void setPostDao(final PostDao postDao) {
		this.postDao = postDao;
	}

	/**
	 * postDetailDaoを設定します。
	 * @param postDetailDao postDetailDao
	 */
	public void setPostDetailDao(final PostDetailDao postDetailDao) {
		this.postDetailDao = postDetailDao;
	}
}
