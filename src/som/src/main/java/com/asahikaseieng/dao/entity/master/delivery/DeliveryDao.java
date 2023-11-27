/*
 * Created on Tue Jan 20 11:19:02 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.delivery;

/**
 * DeliveryDaoインターフェース.
 * @author t0011036
 */
public interface DeliveryDao {

	/** BEANアノテーション. */
	Class BEAN = Delivery.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Delivery
	 * @return Insert件数
	 */
	int insert(Delivery bean);

	/**
	 * Update.
	 * @param bean Delivery
	 * @return Update件数
	 */
	int update(Delivery bean);

	/**
	 * Delete.
	 * @param bean Delivery
	 * @return Delete件数
	 */
	int delete(Delivery bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "DELIVERY_CD";

	/**
	 * エンティティ取得.
	 * @param deliveryCd deliveryCd
	 * @return Delivery
	 */
	Delivery getEntity(final String deliveryCd);
}
