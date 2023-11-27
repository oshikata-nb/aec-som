/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.purchase;

import java.math.BigDecimal;

/**
 * 発注詳細画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface PurchaseDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.purchase.PurchaseDetail.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "purchaseNo,tantoCd";

	/**
	 * エンティティ取得.
	 * @param purchaseNo 購買No
	 * @param tantoCd ログインユーザー
	 * @return PurchaseDetail
	 */
	PurchaseDetail getEntity(String purchaseNo, String tantoCd);

	/** ARGSアノテーション getCountUnitprice */
	String getCountUnitprice_ARGS = "itemCd,venderCd";

	/**
	 * 仕入先別単価マスタ存在チェック
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @return int 検索結果件数(0：マスターになし)
	 */
	int getCountUnitprice(final String itemCd, final String venderCd);


	/** ARGSアノテーション getItemRelatedData */
	String getItemRelatedData_ARGS = "itemCd";
	/**
	 * 品目コード入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @return PurchaseDetail
	 */
	PurchaseDetail getItemRelatedData(final String itemCd);

	/** ARGSアノテーション getVenderRelatedData */
	String getVenderRelatedData_ARGS = "itemCd,venderCd,orderQuantity";
	/**
	 * 仕入先コード入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @param orderQuantity 発注数量
	 * @return PurchaseDetail
	 */
	PurchaseDetail getVenderRelatedData(final String itemCd, final String venderCd, final BigDecimal orderQuantity);

	/** ARGSアノテーション getOrderQuantityRelatedData */
	String getOrderQuantityRelatedData_ARGS = "itemCd,venderCd,orderQuantity";
	/**
	 * 発注数量入力時連動変更項目の検索を行う.
	 * @param itemCd 品目コード
	 * @param venderCd 仕入先コード
	 * @param orderQuantity 発注数量
	 * @return PurchaseDetail
	 */
	PurchaseDetail getOrderQuantityRelatedData(final String itemCd, final String venderCd,
			final BigDecimal orderQuantity);

	/** ARGSアノテーション getPurchaseNo */
	String getPurchaseNo_ARGS = "buySubcontractOrderNo";
	/**
	 * 新規登録時、発注番号にて購買NOの検索を行う.
	 * @param buySubcontractOrderNo 発注番号
	 * @return PurchaseDetail
	 */
	PurchaseDetail getPurchaseNo(final String buySubcontractOrderNo);
}
