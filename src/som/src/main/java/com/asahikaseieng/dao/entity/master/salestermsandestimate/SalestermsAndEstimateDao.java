/*
 * Created on Thu Jan 22 19:58:17 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.salestermsandestimate;

/**
 * SalestermsAndEstimateDaoインターフェース.
 * @author kanri-user
 */
public interface SalestermsAndEstimateDao {

	/** BEANアノテーション. */
	Class BEAN = SalestermsAndEstimate.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean SalestermsAndEstimate
	 * @return Insert件数
	 */
	int insert(SalestermsAndEstimate bean);

	/**
	 * Update.
	 * @param bean SalestermsAndEstimate
	 * @return Update件数
	 */
	int update(SalestermsAndEstimate bean);

	/**
	 * Delete.
	 * @param bean SalestermsAndEstimate
	 * @return Delete件数
	 */
	int delete(SalestermsAndEstimate bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "PK_NO";

	/**
	 * エンティティ取得.
	 * @param pkNo pkNo
	 * @return SalestermsAndEstimate
	 */
	SalestermsAndEstimate getEntity(String pkNo);
}
