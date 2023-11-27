/*
 * Created on Tue Feb 17 09:59:07 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.frstorderhead;

/**
 * OrderHeadDaoインターフェース.
 * @author kanri-user
 */
public interface FrstOrderHeadDao {

	/** BEANアノテーション. */
	Class BEAN = FrstOrderHead.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean OrderHead
	 * @return Insert件数
	 */
	int insert(FrstOrderHead bean);

	/**
	 * Update.
	 * @param bean OrderHead
	 * @return Update件数
	 */
	int update(FrstOrderHead bean);

	/**
	 * Delete.
	 * @param bean OrderHead
	 * @return Delete件数
	 */
	int delete(FrstOrderHead bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "FRST_ORDER_NO";

	/**
	 * エンティティ取得.
	 * @param frstOrderNo frstOrderNo
	 * @return FrstOrderHead
	 */
	FrstOrderHead getEntity(String frstOrderNo);
}
