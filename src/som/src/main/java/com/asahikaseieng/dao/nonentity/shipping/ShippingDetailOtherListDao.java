/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.shipping;

import java.util.List;


/**
 * 出荷指図メンテ 詳細画面（花王・その他）用Daoインターフェース.
 *
 * @author tosco
 */
public interface ShippingDetailOtherListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shipping.ShippingDetailOtherList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "shippingNo";
	/**
	 * エンティティ取得.
	 * @param shippingNo 出荷番号
	 * @return ShippingShippingDetail
	 */
	/**
	 * 出荷指図詳細データ取得.
	 * @param shippingNo 出荷番号
	 * @return List<ShippingDetailOtherList> 出荷指図詳細データ
	 */
	List<ShippingDetailOtherList> getEntity(String shippingNo);

}
