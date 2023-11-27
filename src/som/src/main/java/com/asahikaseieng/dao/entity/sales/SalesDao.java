/*
 * Created on Fri Feb 27 17:24:31 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.sales;

/**
 * SalesDaoインターフェース.
 * @author kanri-user
 */
public interface SalesDao {

	/** BEANアノテーション. */
	Class BEAN = Sales.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Sales
	 * @return Insert件数
	 */
	int insert(Sales bean);

	/**
	 * Update.
	 * @param bean Sales
	 * @return Update件数
	 */
	int update(Sales bean);

	/**
	 * Delete.
	 * @param bean Sales
	 * @return Delete件数
	 */
	int delete(Sales bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "SALES_NO";

	/**
	 * エンティティ取得.
	 * @param salesNo salesNo
	 * @return Sales
	 */
	Sales getEntity(String salesNo);
}
