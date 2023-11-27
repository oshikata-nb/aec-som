/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.sales;

import java.util.List;

/**
 * 納入先検索(ポップアップ)Daoクラス
 * @author tosco
 */
public interface SalesDeliverySearchListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.sales.SalesDeliverySearchList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";
	/**
	 * 納入先検索メソッド
	 * 
	 * @param condition 検索条件
	 * @return List<SalesDeliverySearchList> 検索結果一覧
	 */
	List<SalesDeliverySearchList> getSearchList(final SalesDeliverySearchListPagerCondition condition);

}
