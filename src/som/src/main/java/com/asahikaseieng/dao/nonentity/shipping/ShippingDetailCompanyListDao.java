/*
 * Created on 2009/02/20
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
public interface ShippingDetailCompanyListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shipping.ShippingDetailCompanyList.class;

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
	 * @return List<ShippingDetailCompanyList> 出荷指図詳細データ
	 */
	List<ShippingDetailCompanyList> getEntity(String shippingNo);

}
