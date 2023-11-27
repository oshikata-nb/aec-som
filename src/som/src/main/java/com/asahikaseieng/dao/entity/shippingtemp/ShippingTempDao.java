/*
 * Created on Tue Feb 17 16:40:15 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.shippingtemp;

/**
 * ShippingTempDaoインターフェース.
 * @author kanri-user
 */
public interface ShippingTempDao {

	/** BEANアノテーション. */
	Class BEAN = ShippingTemp.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean ShippingTemp
	 * @return Insert件数
	 */
	int insert(ShippingTemp bean);

	/**
	 * Update.
	 * @param bean ShippingTemp
	 * @return Update件数
	 */
	int update(ShippingTemp bean);

	/**
	 * Delete.
	 * @param bean ShippingTemp
	 * @return Delete件数
	 */
	int delete(ShippingTemp bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "SENDING_OFF_NUMBER,SHIPPING_INSTRUCT_DATE";

	/**
	 * エンティティ取得.
	 * @param sendingOffNumber sendingOffNumber
	 * @param shippingInstructDate shippingInstructDate
	 * @return ShippingTemp
	 */
	ShippingTemp getEntity(String sendingOffNumber,
			java.sql.Timestamp shippingInstructDate);
}
