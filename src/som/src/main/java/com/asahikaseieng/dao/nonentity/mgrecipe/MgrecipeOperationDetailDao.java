/*
 * Created on 2009/02/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mgrecipe;

/**
 * 工程Daoインターフェイス
 * @author tosco
 */
public interface MgrecipeOperationDetailDao {

	/** BEANアノテーション */
	Class<MgrecipeOperationDetail> BEAN = MgrecipeOperationDetail.class;

	/** ARGSアノテーション findByPrimaryKey */
	String findByPrimaryKey_ARGS = "operationCd";
	/**
	 * キー検索メソッド
	 *
	 * @param operationCd 設備コード
	 * @return MgrecipeOperationGroupDetail
	 */
	MgrecipeOperationGroupDetail findByPrimaryKey(String operationCd);
}
