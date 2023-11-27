/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationgroupdetail;

/**
 * OperationGroupDetailDaoクラス
 * @author t0011036
 */
public interface OperationGroupDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.operationgroupdetail.OperationGroupDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "operationGroupCd";

	/**
	 * OperationGroupDetailメソッド
	 * 
	 * @param operationGroupCd operationGroupCd
	 * @return OperationGroupDetail
	 */
	OperationGroupDetail getEntity(final String operationGroupCd);
}
