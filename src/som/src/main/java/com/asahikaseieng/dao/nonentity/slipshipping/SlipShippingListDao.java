/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.slipshipping;

import java.util.List;

/**
 * 出荷帳票 一覧画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface SlipShippingListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.slipshipping.SlipShippingList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<SlipShippingList> 検索結果リスト
	 */

	List<SlipShippingList> getSearchList(final SlipShippingListPagerCondition condition);

}
