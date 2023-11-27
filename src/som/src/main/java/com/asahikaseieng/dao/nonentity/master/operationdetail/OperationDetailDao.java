/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationdetail;

/**
 * OperationDetailDaoクラス
 * @author t0011036
 */
public interface OperationDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.operationdetail.OperationDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "operationCd";

	/**
	 * OperationDetailメソッド
	 * 
	 * @param operationCd operationCd
	 * @return OperationDetail
	 */
	OperationDetail getEntity(final String operationCd);
}
