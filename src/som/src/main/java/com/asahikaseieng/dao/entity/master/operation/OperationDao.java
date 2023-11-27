/*
 * Created on Fri Feb 20 17:47:53 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.operation;

/**
 * OperationDaoインターフェース.
 * @author kanri-user
 */
public interface OperationDao {

	/** BEANアノテーション. */
	Class BEAN = Operation.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Operation
	 * @return Insert件数
	 */
	int insert(Operation bean);

	/**
	 * Update.
	 * @param bean Operation
	 * @return Update件数
	 */
	int update(Operation bean);

	/**
	 * Delete.
	 * @param bean Operation
	 * @return Delete件数
	 */
	int delete(Operation bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "OPERATION_CD";

	/**
	 * エンティティ取得.
	 * @param operationCd operationCd
	 * @return Operation
	 */
	Operation getEntity(String operationCd);
}
