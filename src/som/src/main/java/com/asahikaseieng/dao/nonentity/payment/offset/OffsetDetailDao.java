/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offset;

/**
 * 
 * OffsetDetailDao．グループ間相殺入力
 * @author tosco
 */
public interface OffsetDetailDao {

	/** BEANアノテーション. */
	Class BEAN = com.asahikaseieng.dao.nonentity.payment.offset.OffsetDetail.class;

	//
	// インスタンスメソッド
	//
	/**
	 * Insert.
	 * @param bean Unitprice
	 * @return Insert件数
	 */
	int insert(OffsetDetail bean);

	/**
	 * update.
	 * @param bean Unitprice
	 * @return update件数
	 */
	int update(OffsetDetail bean);

	/**
	 * delete.
	 * @param bean Unitprice
	 * @return delete件数
	 */
	int delete(OffsetDetail bean);

	/** ARGSアノテーション getDetailData(). */
	String getOffsetHeader_ARGS = "offsetNo";

	/**
	 * エンティティ取得.
	 * @param offsetNo 相殺番号
	 * @return OffsetDetail 相殺ヘッダー
	 */
	OffsetDetail getOffsetHeader(String offsetNo);

}
