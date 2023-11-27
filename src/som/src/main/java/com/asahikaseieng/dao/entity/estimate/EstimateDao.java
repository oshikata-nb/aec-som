/*
 * Created on Mon Mar 16 17:00:51 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.estimate;

/**
 * EstimateDaoインターフェース.
 * @author t0011036
 */
public interface EstimateDao {

	/** BEANアノテーション. */
	Class BEAN = Estimate.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Estimate
	 * @return Insert件数
	 */
	int insert(Estimate bean);

	/**
	 * Update.
	 * @param bean Estimate
	 * @return Update件数
	 */
	int update(Estimate bean);

	/**
	 * Delete.
	 * @param bean Estimate
	 * @return Delete件数
	 */
	int delete(Estimate bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ESTIMATE_NO,CONSECUTIVE_NO";

	/**
	 * エンティティ取得.
	 * @param estimateNo estimateNo
	 * @param consecutiveNo consecutiveNo
	 * @return Estimate
	 */
	Estimate getEntity(String estimateNo, java.math.BigDecimal consecutiveNo);
}
