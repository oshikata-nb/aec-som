/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.shipping;



/**
 * 出荷指図メンテ 詳細画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface ShippingDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shipping.ShippingDetailList.class;

	//
	// インスタンスメソッド
	//

	/** QUERYアノテーション deleteByShippingNo(). */
	String deleteByShippingNo_QUERY = "SHIPPING_NO = ?";
	/**
	 * 出荷指図詳細テーブル削除処理.(出荷番号をKEYに削除)
	 * @param shippingNo 出荷番号
	 * @return Delete件数
	 */
	int deleteByShippingNo(String shippingNo);
}
