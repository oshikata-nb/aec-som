/*
 * Created on Wed Feb 04 10:13:16 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.payment;

/**
 * PaymentDaoインターフェース.
 * @author kanri-user
 */
public interface PaymentDao {

	/** BEANアノテーション. */
	Class BEAN = Payment.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Payment
	 * @return Insert件数
	 */
	int insert(Payment bean);

	/**
	 * Update.
	 * @param bean Payment
	 * @return Update件数
	 */
	int update(Payment bean);

	/**
	 * Delete.
	 * @param bean Payment
	 * @return Delete件数
	 */
	int delete(Payment bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ROW_NO,SLIP_NO";

	/**
	 * エンティティ取得.
	 * @param rowNo rowNo
	 * @param slipNo slipNo
	 * @return Payment
	 */
	Payment getEntity(java.math.BigDecimal rowNo, String slipNo);
}
