/*
 * Created on Tue Mar 24 09:04:36 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.shipping;

/**
 * ShippingDaoインターフェース.
 * @author kanri-user
 */
public interface ShippingDao {

	/** BEANアノテーション. */
	Class BEAN = Shipping.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Shipping
	 * @return Insert件数
	 */
	int insert(Shipping bean);

	/**
	 * Update.
	 * @param bean Shipping
	 * @return Update件数
	 */
	int update(Shipping bean);

	/**
	 * Delete.
	 * @param bean Shipping
	 * @return Delete件数
	 */
	int delete(Shipping bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "SHIPPING_NO";

	/**
	 * エンティティ取得.
	 * @param shippingNo shippingNo
	 * @return Shipping
	 */
	Shipping getEntity(String shippingNo);
}
