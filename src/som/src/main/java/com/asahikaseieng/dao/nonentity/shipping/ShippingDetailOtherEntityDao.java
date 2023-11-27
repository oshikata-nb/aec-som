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
public interface ShippingDetailOtherEntityDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shipping.ShippingDetailOtherEntity.class;

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
	ShippingDetailOtherEntity getEntity(String shippingNo);

	/** ARGSアノテーション getItem(). */
	String getItem_ARGS = "itemCd";
	/**
	 * 品目情報取得.
	 * @param itemCd itemCd
	 * @return ShippingDetailOtherEntity 品目情報
	 */
	ShippingDetailOtherEntity getItem(String itemCd);

}
