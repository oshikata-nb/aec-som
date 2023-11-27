/*
 * Created on Wed Jan 28 07:54:24 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.operationgroup;

/**
 * OperationGroupDaoインターフェース.
 * @author t0011036
 */
public interface OperationGroupDao {

	/** BEANアノテーション. */
	Class BEAN = OperationGroup.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean OperationGroup
	 * @return Insert件数
	 */
	int insert(OperationGroup bean);

	/**
	 * Update.
	 * @param bean OperationGroup
	 * @return Update件数
	 */
	int update(OperationGroup bean);

	/**
	 * Delete.
	 * @param bean OperationGroup
	 * @return Delete件数
	 */
	int delete(OperationGroup bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "OPERATION_GROUP_CD";

	/**
	 * エンティティ取得.
	 * @param operationGroupCd operationGroupCd
	 * @return OperationGroup
	 */
	OperationGroup getEntity(final String operationGroupCd);
}
