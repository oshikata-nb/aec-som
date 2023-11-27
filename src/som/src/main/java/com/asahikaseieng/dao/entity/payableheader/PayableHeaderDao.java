/*
 * Created on Wed Feb 04 10:09:32 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.payableheader;

/**
 * PayableHeaderDaoインターフェース.
 * @author kanri-user
 */
public interface PayableHeaderDao {

	/** BEANアノテーション. */
	Class BEAN = PayableHeader.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean PayableHeader
	 * @return Insert件数
	 */
	int insert(PayableHeader bean);

	/**
	 * Update.
	 * @param bean PayableHeader
	 * @return Update件数
	 */
	int update(PayableHeader bean);

	/**
	 * Delete.
	 * @param bean PayableHeader
	 * @return Delete件数
	 */
	int delete(PayableHeader bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "PAYABLE_NO";

	/**
	 * エンティティ取得.
	 * @param payableNo payableNo
	 * @return PayableHeader
	 */
	PayableHeader getEntity(String payableNo);
}
