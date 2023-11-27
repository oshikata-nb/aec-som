/*
 * Created on Thu Feb 19 19:53:22 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporarydepositsales;

/**
 * TemporaryDepositSalesDaoインターフェース.
 * @author kanri-user
 */
public interface TemporaryDepositSalesDao {

	/** BEANアノテーション. */
	Class BEAN = TemporaryDepositSales.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TemporaryDepositSales
	 * @return Insert件数
	 */
	int insert(TemporaryDepositSales bean);

	/**
	 * Update.
	 * @param bean TemporaryDepositSales
	 * @return Update件数
	 */
	int update(TemporaryDepositSales bean);

	/**
	 * Delete.
	 * @param bean TemporaryDepositSales
	 * @return Delete件数
	 */
	int delete(TemporaryDepositSales bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "";

	/**
	 * エンティティ取得.
	 * @return TemporaryDepositSales
	 */
	TemporaryDepositSales getEntity();
}
