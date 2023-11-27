/*
 * Created on Fri Jan 23 17:01:24 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderdetailpurchasesubcontract;

import java.util.List;


/**
 * PurchaseSubcontractDaoインターフェース.
 * @author kanri-user
 */
public interface OrderDetailPurchaseSubcontractDao {

	/** BEANアノテーション. */
	Class BEAN = OrderDetailPurchaseSubcontract.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "orderNo,orderRowNo";

	/**
	 * エンティティ取得.
	 * @param orderNo orderNo
	 * @param orderRowNo orderRowNo
	 * @return OrderDetailPurchaseSubcontract
	 */
	OrderDetailPurchaseSubcontract getEntity(String orderNo, String orderRowNo);

	/** ARGSアノテーション deleteByOrderNo(). */
	String deleteByOrderNo_ARGS = "orderNo";

	/**
	 * 削除.
	 * @param orderNo orderNo
	 * @return OrderDetailAspProduction
	 */
	int deleteByOrderNo(String orderNo);
	
	/** ARGSアノテーション deleteByOrderNoRowNo(). */
	String deleteByOrderNoRowNo_ARGS = "orderNo, orderRowNo";

	/**
	 * 削除.
	 * @param orderNo orderNo
	 * @param orderRowNo orderRowNo
	 * @return OrderDetailAspProduction
	 */
	int deleteByOrderNoRowNo(String orderNo, String orderRowNo);

	/** ARGSアノテーション getPurchaseNo(). */
	String getPurchaseNo_ARGS = "buySubcontractOrderNo";

	/**
	 * 購買No取得.
	 * @param buySubcontractOrderNo 発注番号
	 * @return OrderDetailPurchaseSubcontract
	 */
	OrderDetailPurchaseSubcontract getPurchaseNo(String buySubcontractOrderNo);

	/**
	 * 購買Noリスト取得.
	 * @param orderNo 受注番号
	 * @return List<OrderDetailPurchaseSubcontract>
	 */
	List<OrderDetailPurchaseSubcontract> getPurchaseNoList(String orderNo);
}

