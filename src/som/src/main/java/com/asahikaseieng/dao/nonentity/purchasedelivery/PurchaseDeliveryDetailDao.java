/*
 * Created on 2009/03/12
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.purchasedelivery;


/**
 * 納期回答入力画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface PurchaseDeliveryDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliveryDetail.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "purchaseNo";
	/**
	 * 購買データ取得.
	 * @param purchaseNo 購買NO
	 * @return PurchaseDeliveryDetail 購買データ
	 */
	PurchaseDeliveryDetail getEntity(String purchaseNo);

}
