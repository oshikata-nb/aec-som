/*
 * Created on Fri Jan 23 16:47:43 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.paymentheader;

/**
 * PaymentHeaderDaoインターフェース.
 * @author t0011036
 */
public interface PaymentHeaderDao {

	/** BEANアノテーション. */
	Class BEAN = PaymentHeader.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean PaymentHeader
	 * @return Insert件数
	 */
	int insert(PaymentHeader bean);

	/**
	 * Update.
	 * @param bean PaymentHeader
	 * @return Update件数
	 */
	int update(PaymentHeader bean);

	/**
	 * Delete.
	 * @param bean PaymentHeader
	 * @return Delete件数
	 */
	int delete(PaymentHeader bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "PAYMENT_NO";

	/**
	 * エンティティ取得.
	 * @param paymentNo paymentNo
	 * @return PaymentHeader
	 */
	PaymentHeader getEntity(final String paymentNo);
}
