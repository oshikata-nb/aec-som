/*
 * Created on 2009/02/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shipping;

import java.util.List;

/**
 * ロット検索(ポップアップ)Daoクラス
 * @author tosco
 */
public interface ShippingLotSearchListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shipping.ShippingLotSearchList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";
	/**
	 * ロット検索メソッド
	 * 
	 * @param condition 検索条件
	 * @return List<ShippingLotSearchList> 検索結果一覧
	 */
	List<ShippingLotSearchList> getSearchList(final ShippingLotSearchListPagerCondition condition);

}
