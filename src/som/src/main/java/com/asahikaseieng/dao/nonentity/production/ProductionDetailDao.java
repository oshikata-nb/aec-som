/*
 * Created on 2009/04/08
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.production;


/**
 * 生産計画入力画面 ヘッダ部表示用Daoインターフェース.
 *
 * @author tosco
 */
public interface ProductionDetailDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.production.ProductionDetail.class;

	/** ARGSアノテーション getHeader */
	String getHeader_ARGS = "itemCd,orderLet";
	/**
	 * ヘッダ部検索メソッド
	 * @param itemCd 品目コード
	 * @param orderLet 生産計画年月
	 * @return ProductionDetail 検索結果
	 */
	ProductionDetail getHeader(final String itemCd, final String orderLet);

	/** ARGSアノテーション getItemEntity */
	String getItemEntity_ARGS = "itemCd";
	/**
	 * 品目情報検索メソッド
	 * @param itemCd 品目コード
	 * @return ProductionDetail 検索結果
	 */
	ProductionDetail getItemEntity(final String itemCd);

}
