/*
 * Created on 2007/08/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemchangehistory;

import java.math.BigDecimal;
import java.util.List;

/**
 * ItemchangehistoryDaoクラス
 * @author kanri-user
 */
public interface ItemchangehistoryDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.master.itemchangehistory.Itemchangehistory.class;

	/** ARGSアノテーション getChangeHitoryList */
	String getChangeHitoryList_ARGS = "";

	/**
	 * getChangeHitoryListメソッド
	 * @param condition 検索条件
	 * @return Itemchangehistory 検索結果
	 */
	List<Itemchangehistory> getChangeHitoryList(final ItemchangehistoryCondition condition);

	/** ARGSアノテーション deleteList */
	String deleteList_ARGS = "menuId, itemCd";
	/**
	 * レコード削除.
	 * @param menuId メニュー番号
	 * @param itemCd 品目コード
	 * @return 削除件数
	 */
	int deleteList(final BigDecimal menuId, final String itemCd);

}
