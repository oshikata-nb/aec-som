/*
 * Created on Tue Apr 28 09:04:05 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.transnote;

/**
 * TransNoteDaoインターフェース.
 * @author t0011036
 */
public interface TransNoteDao {

	/** BEANアノテーション. */
	Class BEAN = TransNote.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TransNote
	 * @return Insert件数
	 */
	int insert(TransNote bean);

	/**
	 * Update.
	 * @param bean TransNote
	 * @return Update件数
	 */
	int update(TransNote bean);

	/**
	 * Delete.
	 * @param bean TransNote
	 * @return Delete件数
	 */
	int delete(TransNote bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "ROW_NO,SLIP_NO";

	/**
	 * エンティティ取得.
	 * @param rowNo rowNo
	 * @param slipNo slipNo
	 * @return TransNote
	 */
	TransNote getEntity(java.math.BigDecimal rowNo, String slipNo);
}
