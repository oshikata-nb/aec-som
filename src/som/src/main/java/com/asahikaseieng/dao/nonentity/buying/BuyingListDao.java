/*
 * Created on 2009/01/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.buying;

import java.util.List;

/**
 * 仕入一覧画面用Daoインターフェース.
 * 
 * @author tosco
 */
public interface BuyingListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.buying.BuyingList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 仕入一覧検索
	 * @param condition 検索条件
	 * @return List<BuyingList> 検索結果リスト
	 */
	List<BuyingList> getSearchList(final BuyingPagerCondition condition);

	/** ARGSアノテーション updateByuing */
	String updateBuying_ARGS = "tantoCd, slipNo";

	/**
	 * 仕入れ伝票発行時更新処理
	 * 
	 * @param tantoCd tantoCd
	 * @param slipNo slipNo
	 * @return 更新件数
	 */
	int updateBuying(String tantoCd, String slipNo);

}
