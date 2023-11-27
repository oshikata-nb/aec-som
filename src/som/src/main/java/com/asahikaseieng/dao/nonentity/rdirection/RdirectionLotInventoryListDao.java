/*
 * Created on 2009/05/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.rdirection;

import java.util.List;

/**
 * ロット在庫取得用Daoインターフェース.
 *
 * @author
 */
public interface RdirectionLotInventoryListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.rdirection.RdirectionLotInventoryList.class;

	/** ARGSアノテーション getList */
	String getLocationList_ARGS = "itemCd,lotNo";

	/**
	 * ロケーションリスト取得メソッド
	 * @param itemCd 品目コード
	 * @param lotNo ロット番号
	 * @return List<RdirectionLotInventoryList> ロット在庫リスト
	 */
	List<RdirectionLotInventoryList> getLocationList(final String itemCd, final String lotNo);
}
