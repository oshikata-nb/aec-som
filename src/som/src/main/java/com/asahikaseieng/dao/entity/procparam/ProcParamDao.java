/*
 * Created on Mon Mar 03 10:10:51 JST 2014
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.procparam;

/**
 * ProcParamDaoインターフェース.
 * @author atts
 */
public interface ProcParamDao {

	/** BEANアノテーション. */
	Class BEAN = ProcParam.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean ProcParam
	 * @return Insert件数
	 */
	int insert(ProcParam bean);

	/**
	 * Update.
	 * @param bean ProcParam
	 * @return Update件数
	 */
	int update(ProcParam bean);

	/**
	 * Delete.
	 * @param bean ProcParam
	 * @return Delete件数
	 */
	int delete(ProcParam bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "PROC_CD";

	/**
	 * エンティティ取得.
	 * @param procCd procCd
	 * @return ProcParam
	 */
	ProcParam getEntity(String procCd);
}
