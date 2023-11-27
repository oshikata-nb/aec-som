/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.purchasedelivery;

import java.util.List;


/**
 * 納期回答まとめ入力画面　明細部表示用Daoインターフェース.
 *
 * @author tosco
 */
public interface PurchaseDeliverySumDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliverySumDetailList.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "orderSheetNo";

	/**
	 * 明細部検索メソッド
	 * @param orderSheetNo 発注書NO
	 * @return List<PurchaseDeliverySumDetailList> 検索結果リスト
	 */
	List<PurchaseDeliverySumDetailList> getEntity(final String orderSheetNo);

}
