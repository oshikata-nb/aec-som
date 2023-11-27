/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.purchasedelivery;


/**
 * 納期回答まとめ入力画面 ヘッダ部表示用Daoインターフェース.
 *
 * @author tosco
 */
public interface PurchaseDeliverySumDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.purchasedelivery.PurchaseDeliverySumDetail.class;

	/** ARGSアノテーション getCount */
	String getCount_ARGS = "orderSheetNo";
	/**
	 * 入力された発注書NOに該当するまとめ入力対象データの件数を取得する.
	 * @param orderSheetNo 発注書NO
	 * @return PurchaseDeliverySumDetail 検索結果
	 */
	PurchaseDeliverySumDetail getCount(final String orderSheetNo);

	/** ARGSアノテーション getHeader */
	String getHeader_ARGS = "orderSheetNo";
	/**
	 * ヘッダ部検索メソッド
	 * @param orderSheetNo 発注書NO
	 * @return PurchaseDeliverySumDetail 検索結果
	 */
	PurchaseDeliverySumDetail getHeader(final String orderSheetNo);

	/** ARGSアノテーション getLocation */
	String getLocation_ARGS = "orderSheetNo";
	/**
	 * ヘッダ部の納入先取得メソッド
	 * @param orderSheetNo 発注書NO
	 * @return String 納入先
	 */
	String getLocation(final String orderSheetNo);

}
