/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.numbering;

import java.util.List;

/**
 * 日毎発番管理テーブル発番データDaoインターフェース.
 *
 * @author tosco
 */
public interface NumberingDailyNumberListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.numbering.NumberingDailyNumberList.class;

	/** ARGSアノテーション getDailyNumber */
	String getDailyNumber_ARGS = "key,datekey,initDatekey";

	/**
	 * 一覧検索メソッド
	 * @param key ＫＥＹコード
	 * @param datekey 西暦８桁(yyyyMMdd)
	 * @param initDatekey 初期データ西暦８桁(yyyyMMdd)
	 * @return List<NumberingDailyNumberList> 検索結果リスト
	 */
	List<NumberingDailyNumberList> getDailyNumber(final String key, final String datekey, final String initDatekey);
}
