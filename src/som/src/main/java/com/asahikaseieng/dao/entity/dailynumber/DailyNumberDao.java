/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.entity.dailynumber;

/**
 * 日毎発番管理テーブルDaoインターフェース.
 *
 * @author tosco
 */
public interface DailyNumberDao {

	/** BEANアノテーション */
	Class BEAN = DailyNumber.class;

	//
	// インスタンスメソッド
	//

	/**
	 * Insert.
	 * @param bean Dailynumber
	 * @return Insert件数
	 */
	int insert(DailyNumber bean);

	/**
	 * Update.
	 * @param bean Dailynumber
	 * @return Update件数
	 */
	int update(DailyNumber bean);

	/**
	 * Delete.
	 * @param bean Dailynumber
	 * @return Delete件数
	 */
	int delete(DailyNumber bean);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "KEY,DATEKEY";

	/**
	 * エンティティ取得.
	 * @param key ＫＥＹコード
	 * @param datakey 西暦８桁(yyyyMMdd)
	 * @return DailyNumber
	 */
	DailyNumber getEntity(String key, String datakey);
}
