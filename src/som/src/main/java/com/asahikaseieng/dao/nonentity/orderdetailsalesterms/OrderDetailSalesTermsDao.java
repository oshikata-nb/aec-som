/*
 * Created on Thu Jan 22 19:58:17 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetailsalesterms;

/**
 * OrderDetailSalesTermsDaoインターフェース.
 * @author
 */
public interface OrderDetailSalesTermsDao {

	/** BEANアノテーション. */
	Class BEAN = OrderDetailSalesTerms.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "deliveryCd,balanceCd,itemCd";

	/**
	 * エンティティ取得.
	 * @param balanceCd balanceCd
	 * @param deliveryCd deliveryCd
	 * @param itemCd itemCd
	 * @return SalesTerms
	 */
	OrderDetailSalesTerms getEntity(String deliveryCd, String balanceCd, String itemCd);
}
