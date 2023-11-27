/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.buyingapproval;

import java.util.List;

/**
 * 仕入承認画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface BuyingApprovalListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.buyingapproval.BuyingApprovalList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 仕入承認画面メソッド
	 * @param condition 検索条件
	 * @return List<BuyingApprovalList> 検索結果リスト
	 */
	List<BuyingApprovalList> getSearchList(final BuyingApprovalPagerCondition condition);

}
