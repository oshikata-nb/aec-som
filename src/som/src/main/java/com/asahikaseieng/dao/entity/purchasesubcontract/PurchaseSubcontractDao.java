/*
 * Created on Fri Jan 23 17:01:24 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.purchasesubcontract;

import java.util.List;

/**
 * PurchaseSubcontractDaoインターフェース.
 * @author kanri-user
 */
public interface PurchaseSubcontractDao {

	/** BEANアノテーション. */
	Class BEAN = PurchaseSubcontract.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean PurchaseSubcontract
	 * @return Insert件数
	 */
	int insert(PurchaseSubcontract bean);

	/**
	 * Update.
	 * @param bean PurchaseSubcontract
	 * @return Update件数
	 */
	int update(PurchaseSubcontract bean);

	/**
	 * Delete.
	 * @param bean PurchaseSubcontract
	 * @return Delete件数
	 */
	int delete(PurchaseSubcontract bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "PURCHASE_NO";

	/**
	 * エンティティ取得.
	 * @param purchaseNo purchaseNo
	 * @return PurchaseSubcontract
	 */
	PurchaseSubcontract getEntity(String purchaseNo);

	//
	// 追加メソッドはこの下に記述して下さい
	//
	/** QUERYアノテーション deleteBySlipNo(). */
	String deleteBySlipNo_QUERY = "SLIP_NO = ?";
	/**
	 * 購買オーダーテーブル削除処理.(仕入番号をKEYに削除)
	 * @param slipNo 仕入番号
	 * @return Delete件数
	 */
	int deleteBySlipNo(String slipNo);

	/** QUERYアノテーション deleteBunnoData(). */
	String deleteByBuySubOrdNo_QUERY = "SLIP_NO IS NULL AND BUY_SUBCONTRACT_ORDER_NO = ?";
	/**
	 * 購買オーダーテーブル削除処理.(発注番号をKEYに削除)
	 * @param buySubcontractOrderNo 発注番号
	 * @return Delete件数
	 */
	int deleteByBuySubOrdNo(String buySubcontractOrderNo);

	/**
	 * 購買オーダーテーブル更新処理.null値以外の項目を更新(UnlessNull).
	 * @param bean PurchaseSubcontract
	 * @return Update件数
	 */
	int updateUnlessNull(PurchaseSubcontract bean);

	/** QUERYアノテーション getListBySlipNo(). */
	String getListBySlipNo_QUERY = "SLIP_NO = ?";
	/**
	 * 仕入番号を条件とした検索処理
	 * @param slipNo 仕入番号
	 * @return Update件数
	 */
	List<PurchaseSubcontract> getListBySlipNo(String slipNo);

	/** ARGSアノテーション getListByBuySubOrderNo(). */
	String getListByBuySubOrderNo_ARGS = "BUY_SUBCONTRACT_ORDER_NO";
	/**
	 * 発注番号を条件とした検索処理
	 * @param buySubcontractOrderNo 発注番号
	 * @return List<PurchaseSubcontract> 検索結果リスト
	 */
	List<PurchaseSubcontract> getListByBuySubOrderNo(String buySubcontractOrderNo);

}

