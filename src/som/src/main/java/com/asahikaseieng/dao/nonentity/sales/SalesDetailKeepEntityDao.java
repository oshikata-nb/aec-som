/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.sales;



/**
 * 売上メンテ 売上詳細(預り品)画面_売上トランザクション用Daoインターフェース.
 *
 * @author tosco
 */
public interface SalesDetailKeepEntityDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.sales.SalesDetailKeepEntity.class;

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
	SalesDetailKeepEntity getEntity(String salesNo);

	/** ARGSアノテーション getCategoryName(). */
	String getCategoryName_ARGS = "categoryDivision";
	/**
	 * 売上区分より売上区分名称を取得.
	 * @param categoryDivision 売上区分
	 * @return String
	 */
	String getCategoryName(String categoryDivision);

	/** ARGSアノテーション getEntity(). */
	String getSalesByItem_ARGS = "itemCd";
	/**
	 * 品目関連情報を取得.
	 * @param itemCd 品目コード
	 * @return SalesDetailNormalEntity
	 */
	SalesDetailKeepEntity getSalesByItem(String itemCd);

	/** ARGSアノテーション getBalanceCdByDeliveryCdItemCd(). */
	String getBalanceCdByDeliveryCdItemCd_ARGS = "deliveryCd,itemCd";
	/**
	 * 納入先コード、品目コードより帳合コードを取得.
	 * @param deliveryCd 納入先コード
	 * @param itemCd 品目コード
	 * @return String
	 */
	String getBalanceCdByDeliveryCdItemCd(String deliveryCd, String itemCd);

}
