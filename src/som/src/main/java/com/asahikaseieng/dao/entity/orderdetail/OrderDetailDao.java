/*
 * Created on Tue Feb 17 09:58:54 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderdetail;

import java.util.List;

/**
 * OrderDetailDaoインターフェース.
 * @author kanri-user
 */
public interface OrderDetailDao {

	/** BEANアノテーション. */
	Class BEAN = OrderDetail.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean OrderDetail
	 * @return Insert件数
	 */
	int insert(OrderDetail bean);

	/**
	 * Update.
	 * @param bean OrderDetail
	 * @return Update件数
	 */
	int update(OrderDetail bean);

	/**
	 * Delete.
	 * @param bean OrderDetail
	 * @return Delete件数
	 */
	int delete(OrderDetail bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ORDER_NO,ROW_NO";

	/**
	 * エンティティ取得.
	 * @param orderNo orderNo
	 * @param rowNo rowNo
	 * @return OrderDetail
	 */
	OrderDetail getEntity(String orderNo, java.math.BigDecimal rowNo);

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "ORDER_NO";

	/**
	 * 受注詳細リスト取得
	 * @param orderNo 受注番号
	 * @return List<OrderDetail> 受注詳細リスト
	 */
	List<OrderDetail> getList(String orderNo);
}
