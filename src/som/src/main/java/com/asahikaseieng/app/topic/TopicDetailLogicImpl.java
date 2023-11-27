/*
 * Created on 2008/06/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.topic;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;
import org.seasar.framework.exception.SQLRuntimeException;

import com.asahikaseieng.dao.entity.topic.Topic;
import com.asahikaseieng.dao.entity.topic.TopicDao;
import com.asahikaseieng.exception.DuplicateRuntimeException;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 組織マスタ詳細 ロジック実装クラス
 * @author tosco
 */
public class TopicDetailLogicImpl implements TopicDetailLogic {

	private TopicDao topicDao;

	/**
	 * コンストラクタ.ロケーションマスタ詳細ロジック
	 */
	public TopicDetailLogicImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public Topic getEntity(final java.math.BigDecimal topicId)
			throws NoDataException {
		Topic bean = topicDao.getEntity(topicId);

		if (bean == null) {
			throw new NoDataException();
		}

		return bean;
	}

	/**
	 * 更新処理を行う.
	 * @param bean 更新対象ビーン
	 * @throws NoDataException データ無しの例外
	 * 
	 */
	public void update(final Topic bean) throws NoDataException {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 更新処理 */
			int updateNum = topicDao.update(bean);

			if (updateNum != 1) {
				/* 排他エラー */
				throw new OptimisticLockRuntimeException();
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 更新エラー */
			throw new NoDataException();
		}
	}

	/**
	 * 登録処理を行う.
	 * @param bean 登録対象ビーン
	 */
	public void insert(final Topic bean) {
		int insertNum;

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		bean.setTopicId(topicDao.getNextId());
		if (bean.getTopicId() == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 追加処理 */
			insertNum = topicDao.insert(bean);

			if (insertNum != 1) {
				/* 排他エラー */
				throw new OptimisticLockRuntimeException();
			}

		} catch (SQLRuntimeException e) {
			throw new DuplicateRuntimeException(0);
		}

	}

	/**
	 * 削除処理を行う.
	 * @param bean 削除対象ビーン
	 * @throws NoDataException データ無しの例外
	 * 
	 */
	public void delete(final Topic bean) throws NoDataException {

		if (bean == null) {
			throw new IllegalArgumentException("bean == null");
		}

		try {
			/* 削除処理 */
			int deleteNum = topicDao.delete(bean);

			if (deleteNum != 1) {
				throw new OptimisticLockRuntimeException();
			}

		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 削除エラー */
			throw new NoDataException();
		}
	}

	// /**
	// * パラメータチェック.Stringバージョン
	// * @param cd 検索条件:TOPIC
	// * @throws IllegalArgumentException
	// */
	// private void checkParams(final java.math.BigDecimal id) throws
	// IllegalArgumentException {
	//
	// if (StringUtils.isEmpty(id)) {
	// throw new IllegalArgumentException(
	// "TopicLogic IllegalArgumentException : Paramater is empty
	// checkParams(id).");
	// }
	// }

	/* -------------------- setter -------------------- */

	/**
	 * topicDaoを設定します。
	 * @param topicDao topicDao
	 */
	public void setTopicDao(final TopicDao topicDao) {
		this.topicDao = topicDao;
	}

}
