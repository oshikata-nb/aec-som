/*
 * Created on 2009/02/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mgrecipe;

/**
 * 工程グループDaoインターフェイス
 * @author tosco
 */
public interface MgrecipeOperationGroupDetailDao {

	/** BEANアノテーション */
	Class<MgrecipeOperationGroupDetail> BEAN = MgrecipeOperationGroupDetail.class;

	/** ARGSアノテーション findByPrimaryKey */
	String findByPrimaryKey_ARGS = "operationGroupCd";
	/**
	 * キー検索メソッド
	 *
	 * @param operationGroupCd 設備グループコード
	 * @return MgrecipeOperationGroupDetail
	 */
	MgrecipeOperationGroupDetail findByPrimaryKey(String operationGroupCd);
}
