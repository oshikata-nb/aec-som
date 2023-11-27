/*
 * Created on 2009/03/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.shippingresult;

import java.util.List;

/**
 * ロット検索(ポップアップ)Daoクラス
 * @author tosco
 */
public interface ShippingResultLotSearchListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultLotSearchList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";
	/**
	 * ロット検索メソッド
	 * 
	 * @param condition 検索条件
	 * @return List<ShippingResultLotSearchList> 検索結果一覧
	 */
	List<ShippingResultLotSearchList> getSearchList(final ShippingResultLotSearchListPagerCondition condition);

}
