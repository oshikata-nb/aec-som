/*
 * Created on Tue Mar 24 09:06:02 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.shippingdetail;

/**
 * ShippingDetailDaoインターフェース.
 * @author kanri-user
 */
public interface ShippingDetailDao {

	/** BEANアノテーション. */
	Class BEAN = ShippingDetail.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean ShippingDetail
	 * @return Insert件数
	 */
	int insert(ShippingDetail bean);

	/**
	 * Update.
	 * @param bean ShippingDetail
	 * @return Update件数
	 */
	int update(ShippingDetail bean);

	/**
	 * Delete.
	 * @param bean ShippingDetail
	 * @return Delete件数
	 */
	int delete(ShippingDetail bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "SHIPPING_NO,SHIPPING_ROW_NO";

	/**
	 * エンティティ取得.
	 * @param shippingNo shippingNo
	 * @param shippingRowNo shippingRowNo
	 * @return ShippingDetail
	 */
	ShippingDetail getEntity(String shippingNo,
			java.math.BigDecimal shippingRowNo);
}
