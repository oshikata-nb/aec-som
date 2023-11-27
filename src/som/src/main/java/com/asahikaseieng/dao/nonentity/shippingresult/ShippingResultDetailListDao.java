/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.shippingresult;

import java.util.List;


/**
 * 出荷実績 出荷実績詳細画面_出荷詳細用Daoインターフェース.
 *
 * @author tosco
 */
public interface ShippingResultDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultDetailList.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "shippingNo";
	/**
	 * 出荷詳細データ取得.
	 * @param shippingNo 出荷番号
	 * @return List<ShippingResultDetailList> 出荷詳細データ
	 */
	List<ShippingResultDetailList> getEntity(String shippingNo);

	/** QUERYアノテーション deleteByShippingNo(). */
	String deleteByShippingNo_QUERY = "SHIPPING_NO = ?";
	/**
	 * 出荷詳細テーブル削除処理.(出荷番号をKEYに削除)
	 * @param shippingNo 出荷番号
	 * @return Delete件数
	 */
	int deleteByShippingNo(String shippingNo);

}
