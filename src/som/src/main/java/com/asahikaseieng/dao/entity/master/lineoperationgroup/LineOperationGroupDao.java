/*
 * Created on Thu Jan 22 19:01:01 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.lineoperationgroup;

/**
 * LineOperationGroupDaoインターフェース.
 * @author t0011036
 */
public interface LineOperationGroupDao {

	/** BEANアノテーション. */
	Class BEAN = LineOperationGroup.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean LineOperationGroup
	 * @return Insert件数
	 */
	int insert(LineOperationGroup bean);

	/**
	 * Update.
	 * @param bean LineOperationGroup
	 * @return Update件数
	 */
	int update(LineOperationGroup bean);

	/**
	 * Delete.
	 * @param bean LineOperationGroup
	 * @return Delete件数
	 */
	int delete(LineOperationGroup bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "OPERATION_GROUP,PRODUCTION_LINE";

	/**
	 * エンティティ取得.
	 * @param operationGroup operationGroup
	 * @param productionLine productionLine
	 * @return LineOperationGroup
	 */
	LineOperationGroup getEntity(final String operationGroup,
			final String productionLine);
}
