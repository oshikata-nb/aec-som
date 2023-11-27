/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.shipping;


/**
 * 出荷指図メンテ 出荷指図詳細画面_出荷指図ヘッダー用Daoインターフェース.
 *
 * @author tosco
 */
public interface ShippingDetailCompanyEntityDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shipping.ShippingDetailCompanyEntity.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "shippingNo";
	/**
	 * エンティティ取得.
	 * @param shippingNo shippingNo
	 * @return ShippingDetailOtherEntity
	 */
	ShippingDetailCompanyEntity getEntity(String shippingNo);

}
