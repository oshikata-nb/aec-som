/*
 * Created on Thu Feb 19 19:53:00 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporaryclaimsales;

/**
 * TemporaryClaimSalesDaoインターフェース.
 * @author kanri-user
 */
public interface TemporaryClaimSalesDao {

	/** BEANアノテーション. */
	Class BEAN = TemporaryClaimSales.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TemporaryClaimSales
	 * @return Insert件数
	 */
	int insert(TemporaryClaimSales bean);

	/**
	 * Update.
	 * @param bean TemporaryClaimSales
	 * @return Update件数
	 */
	int update(TemporaryClaimSales bean);

	/**
	 * Delete.
	 * @param bean TemporaryClaimSales
	 * @return Delete件数
	 */
	int delete(TemporaryClaimSales bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "SALES_NO";

	/**
	 * エンティティ取得.
	 * @param salesNo salesNo
	 * @return TemporaryClaimSales
	 */
	TemporaryClaimSales getEntity(String salesNo);
}
