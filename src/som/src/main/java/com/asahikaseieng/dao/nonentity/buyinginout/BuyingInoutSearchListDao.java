/*
 * Created on 2010/06/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.buyinginout;

import java.util.List;

/**
 * BuyingInoutSearchListDaoクラス
 * @author t1344224
 */
public interface BuyingInoutSearchListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.buyinginout.BuyingInoutSearchList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * Listメソッド
	 * @param condition 検索条件
	 * @return List<BuyingInoutSearchList> 検索結果
	 */
	List<BuyingInoutSearchList> getSearchList(
			BuyingInoutSearchListPagerCondition condition);
}
