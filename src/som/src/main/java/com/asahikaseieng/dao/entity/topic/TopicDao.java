/*
 * Created on Wed Nov 26 20:05:51 JST 2008
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.topic;
import java.util.List;

/**
 * TopicDaoインターフェース.
 * @author a1020630
 */
public interface TopicDao {

	/** BEANアノテーション. */
	Class BEAN = Topic.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Topic
	 * @return Insert件数
	 */
	int insert(Topic bean);

	/**
	 * Update.
	 * @param bean Topic
	 * @return Update件数
	 */
	int update(Topic bean);

	/**
	 * Delete.
	 * @param bean Topic
	 * @return Delete件数
	 */
	int delete(Topic bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "TOPIC_ID";

	/**
	 * エンティティ取得.
	 * @param topicId topicId
	 * @return Topic
	 */
	Topic getEntity(java.math.BigDecimal topicId);

	/**
	 * リスト取得.
	 * @return Topicのリスト
	 */
	List<Topic> getList();

	//
	// 追加メソッドはこの下に記述して下さい
	//

	/**
	 * ##ここにメソッドの説明を書いてください##
	 * @return 連番取得
	 */
	java.math.BigDecimal getNextId();
}

