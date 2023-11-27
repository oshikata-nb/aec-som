/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.shippingresult;


/**
 * 出荷実績 出荷実績詳細画面_出荷ヘッダー用Daoインターフェース.
 *
 * @author tosco
 */
public interface ShippingResultDetailEntityDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultDetailEntity.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "shippingNo";
	/**
	 * エンティティ取得.
	 * @param shippingNo 出荷番号
	 * @return ShippingResultDetailEntity
	 */
	ShippingResultDetailEntity getEntity(String shippingNo);

}
