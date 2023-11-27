/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.sales;


/**
 * 売上メンテ 売上詳細(標準)画面_売上トランザクション用Daoインターフェース.
 *
 * @author tosco
 */
public interface SalesDetailStandardEntityDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.sales.SalesDetailStandardEntity.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "salesNo";
	/**
	 * エンティティ取得.
	 * @param salesNo 売上番号
	 * @return SalesDetailNormalEntity
	 */
	SalesDetailStandardEntity getEntity(String salesNo);

	/** ARGSアノテーション getEntity(). */
	String getSalesByItem_ARGS = "itemCd";
	/**
	 * 品目関連情報を取得.
	 * @param itemCd 品目コード
	 * @return SalesDetailNormalEntity
	 */
	SalesDetailStandardEntity getSalesByItem(String itemCd);

}
