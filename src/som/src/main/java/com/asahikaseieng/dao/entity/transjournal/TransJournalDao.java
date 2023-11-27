/*
 * Created on Wed Apr 29 11:49:04 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.transjournal;

/**
 * TransJournalDaoインターフェース.
 * @author t0011036
 */
public interface TransJournalDao {

	/** BEANアノテーション. */
	Class BEAN = TransJournal.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean TransJournal
	 * @return Insert件数
	 */
	int insert(TransJournal bean);

	/**
	 * Update.
	 * @param bean TransJournal
	 * @return Update件数
	 */
	int update(TransJournal bean);

	/**
	 * Delete.
	 * @param bean TransJournal
	 * @return Delete件数
	 */
	int delete(TransJournal bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "DEN_NO,DEN_YMD,GYO_NO";

	/**
	 * エンティティ取得.
	 * @param denNo denNo
	 * @param denYmd denYmd
	 * @param gyoNo gyoNo
	 * @return TransJournal
	 */
	TransJournal getEntity(String denNo, String denYmd,
			java.math.BigDecimal gyoNo);
}
