/*
 * Created on Mon Feb 09 15:11:31 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.asporder;

/**
 * AspOrderDaoインターフェース.
 * @author kanri-user
 */
public interface AspOrderDao {

	/** BEANアノテーション. */
	Class BEAN = AspOrder.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean AspOrder
	 * @return Insert件数
	 */
	int insert(AspOrder bean);

	/**
	 * Update.
	 * @param bean AspOrder
	 * @return Update件数
	 */
	int update(AspOrder bean);

	/**
	 * Delete.
	 * @param bean AspOrder
	 * @return Delete件数
	 */
	int delete(AspOrder bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ORDER_CD";

	/**
	 * エンティティ取得.
	 * @param orderCd orderCd
	 * @return AspOrder
	 */
	AspOrder getEntity(String orderCd);
}
