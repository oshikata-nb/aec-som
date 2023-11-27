/*
 * Created on Tue Feb 17 09:59:07 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderhead;

/**
 * OrderHeadDaoインターフェース.
 * @author kanri-user
 */
public interface OrderHeadDao {

	/** BEANアノテーション. */
	Class BEAN = OrderHead.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean OrderHead
	 * @return Insert件数
	 */
	int insert(OrderHead bean);

	/**
	 * Update.
	 * @param bean OrderHead
	 * @return Update件数
	 */
	int update(OrderHead bean);

	/**
	 * Delete.
	 * @param bean OrderHead
	 * @return Delete件数
	 */
	int delete(OrderHead bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ORDER_NO";

	/**
	 * エンティティ取得.
	 * @param orderNo orderNo
	 * @return OrderHead
	 */
	OrderHead getEntity(String orderNo);
}
