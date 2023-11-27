/*
 * Created on Tue Feb 17 10:00:17 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.aspoperation;

/**
 * AspOperationDaoインターフェース.
 * @author kanri-user
 */
public interface AspOperationDao {

	/** BEANアノテーション. */
	Class BEAN = AspOperation.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean AspOperation
	 * @return Insert件数
	 */
	int insert(AspOperation bean);

	/**
	 * Update.
	 * @param bean AspOperation
	 * @return Update件数
	 */
	int update(AspOperation bean);

	/**
	 * Delete.
	 * @param bean AspOperation
	 * @return Delete件数
	 */
	int delete(AspOperation bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "OPERATION_CD";

	/**
	 * エンティティ取得.
	 * @param operationCd operationCd
	 * @return AspOperation
	 */
	AspOperation getEntity(String operationCd);
}
