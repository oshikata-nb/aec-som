/*
 * Created on Tue Feb 17 09:58:54 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.frstorderdetail;

import java.util.List;

/**
 * OrderDetailDaoインターフェース.
 * @author kanri-user
 */
public interface FrstOrderDetailDao {

	/** BEANアノテーション. */
	Class BEAN = FrstOrderDetail.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean OrderDetail
	 * @return Insert件数
	 */
	int insert(FrstOrderDetail bean);

	/**
	 * Update.
	 * @param bean OrderDetail
	 * @return Update件数
	 */
	int update(FrstOrderDetail bean);

	/**
	 * Delete.
	 * @param bean OrderDetail
	 * @return Delete件数
	 */
	int delete(FrstOrderDetail bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "FRST_ORDER_NO,FRST_ORDER_ROW_NO";

	/**
	 * エンティティ取得.
	 * @param orderNo orderNo
	 * @param rowNo rowNo
	 * @return OrderDetail
	 */
	FrstOrderDetail getEntity(String orderNo, java.math.BigDecimal rowNo);

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "FRST_ORDER_NO";

	/**
	 * 受注詳細リスト取得
	 * @param orderNo 受注番号
	 * @return List<OrderDetail> 受注詳細リスト
	 */
	List<FrstOrderDetail> getList(String orderNo);
}
