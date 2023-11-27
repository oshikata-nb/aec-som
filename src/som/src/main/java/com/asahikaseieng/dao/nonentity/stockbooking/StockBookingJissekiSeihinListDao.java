/*
 * Created on 2009/05/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.stockbooking;

import java.util.List;

/**
 * 製品入出庫実績取得用Daoインターフェース.
 *
 * @author
 */
public interface StockBookingJissekiSeihinListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.stockbooking.StockBookingJissekiSeihinList.class;

	/** ARGSアノテーション getResultList */
	String getResultList_ARGS = "directionNo";

	/**
	 * 製品入出庫実績取得メソッド
	 * @param directionNo 包装指図番号
	 * @return List<StockBookingJissekiSeihinList> 製品入出庫実績リスト
	 */
	List<StockBookingJissekiSeihinList> getResultList(final String directionNo);

}
