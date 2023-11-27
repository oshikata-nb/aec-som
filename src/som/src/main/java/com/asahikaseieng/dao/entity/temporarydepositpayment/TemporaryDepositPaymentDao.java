/*
 * Created on Tue Feb 17 17:45:08 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporarydepositpayment;

/**
 * TemporaryDepositPaymentDaoインターフェース.
 * @author kanri-user
 */
public interface TemporaryDepositPaymentDao {

	/** BEANアノテーション. */
	Class BEAN = TemporaryDepositPayment.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TemporaryDepositPayment
	 * @return Insert件数
	 */
	int insert(TemporaryDepositPayment bean);

	/**
	 * Update.
	 * @param bean TemporaryDepositPayment
	 * @return Update件数
	 */
	int update(TemporaryDepositPayment bean);

	/**
	 * Delete.
	 * @param bean TemporaryDepositPayment
	 * @return Delete件数
	 */
	int delete(TemporaryDepositPayment bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ROW_NO,SLIP_NO";

	/**
	 * エンティティ取得.
	 * @param rowNo rowNo
	 * @param slipNo slipNo
	 * @return TemporaryDepositPayment
	 */
	TemporaryDepositPayment getEntity(java.math.BigDecimal rowNo, String slipNo);
}
