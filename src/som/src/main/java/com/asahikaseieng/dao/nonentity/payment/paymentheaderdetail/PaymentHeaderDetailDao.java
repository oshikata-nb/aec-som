/*
 * Created on 2009/08/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentheaderdetail;

import java.sql.Timestamp;
import java.util.List;

/**
 * PaymentHeaderDetailDaoクラス
 * @author t0011036
 */
public interface PaymentHeaderDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.paymentheaderdetail.PaymentHeaderDetail.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "organizationCd, supplierCd, paymentDate";

	/**
	 * PaymentHeaderDetailメソッド
	 * 
	 * @param organizationCd organizationCd
	 * @param supplierCd supplierCd
	 * @param paymentDate paymentDate
	 * @return List<PaymentHeaderDetail>
	 */
	List<PaymentHeaderDetail> getEntity(final String organizationCd,
			final String supplierCd, final Timestamp paymentDate);
}
