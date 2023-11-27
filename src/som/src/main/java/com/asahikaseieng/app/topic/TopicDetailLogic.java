/*
 * Created on 2008/06/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.topic;

import com.asahikaseieng.dao.entity.topic.Topic;
import com.asahikaseieng.exception.NoDataException;

/**
 * 不具合詳細 ロジッククラス interface.
 * @author tosco
 */
public interface TopicDetailLogic {

	/**
	 * 検索処理を行う.不具合
	 * @param topicId トピックID
	 * @throws NoDataException NoDataException
	 * @return Topic
	 */
	Topic getEntity(final java.math.BigDecimal topicId) throws NoDataException;

	/**
	 * 登録処理を行う.
	 * @param bean 更新対象ビーン
	 * @throws NoDataException データ無しの例外
	 */
	void update(final Topic bean) throws NoDataException;

	/**
	 * 登録処理を行う.
	 * @param bean 登録対象ビーン
	 */
	void insert(final Topic bean);

	/**
	 * 登録処理を行う.
	 * @param bean 削除対象ビーン
	 * @throws NoDataException データ無しの例外
	 */
	void delete(final Topic bean) throws NoDataException;

}
