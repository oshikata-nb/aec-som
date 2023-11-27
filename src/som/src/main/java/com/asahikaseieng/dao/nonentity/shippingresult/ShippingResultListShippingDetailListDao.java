/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.shippingresult;

import java.util.List;

/**
 *  出荷実績一覧画面出荷詳細データ用Daoインターフェース.
 *
 * @author tosco
 */
public interface ShippingResultListShippingDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultListShippingDetailList.class;

	/** ARGSアノテーション getList(). */
	String getList_ARGS = "SHIPPING_NO";
	/**
	 * 出荷詳細リスト取得
	 * @param shippingNo 出荷番号
	 * @return List<ShippingResultListShippingDetailList> 出荷詳細リスト
	 */
	List<ShippingResultListShippingDetailList> getList(String shippingNo);

}
