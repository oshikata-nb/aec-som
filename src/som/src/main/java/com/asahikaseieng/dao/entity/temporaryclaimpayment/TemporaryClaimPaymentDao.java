/*
 * Created on Tue Feb 17 17:43:21 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporaryclaimpayment;

/**
 * TemporaryClaimPaymentDaoインターフェース.
 * @author kanri-user
 */
public interface TemporaryClaimPaymentDao {

	/** BEANアノテーション. */
	Class BEAN = TemporaryClaimPayment.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TemporaryClaimPayment
	 * @return Insert件数
	 */
	int insert(TemporaryClaimPayment bean);

	/**
	 * Update.
	 * @param bean TemporaryClaimPayment
	 * @return Update件数
	 */
	int update(TemporaryClaimPayment bean);

	/**
	 * Delete.
	 * @param bean TemporaryClaimPayment
	 * @return Delete件数
	 */
	int delete(TemporaryClaimPayment bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ROW_NO,SLIP_NO";

	/**
	 * エンティティ取得.
	 * @param rowNo rowNo
	 * @param slipNo slipNo
	 * @return TemporaryClaimPayment
	 */
	TemporaryClaimPayment getEntity(java.math.BigDecimal rowNo, String slipNo);
}
