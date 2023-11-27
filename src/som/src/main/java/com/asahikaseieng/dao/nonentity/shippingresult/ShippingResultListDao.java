/*
 * Created on 2009/03/18
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.shippingresult;

import java.util.List;

/**
 *  出荷実績一覧画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface ShippingResultListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<ShippingResultList> 検索結果リスト
	 */
	List<ShippingResultList> getSearchList(final ShippingResultListPagerCondition condition);

}
