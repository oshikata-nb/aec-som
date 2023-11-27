/*
 * Created on 2009/04/08
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.production;

import java.util.List;


/**
 * 生産計画入力画面　明細部　表示用　Daoインターフェース.
 *
 * @author tosco
 */
public interface ProductionDetailListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.production.ProductionDetailList.class;

	/** ARGSアノテーション getEntity */
	String getEntity_ARGS = "itemCd,orderLet";

	/**
	 * 明細部検索メソッド
	 * @param itemCd 品目コード
	 * @param orderLet 生産計画年月
	 * @return List<ProductionDetailList> 検索結果リスト
	 */
	List<ProductionDetailList> getEntity(final String itemCd, final String orderLet);

	/** ARGSアノテーション getEntity */
	String getCountList_ARGS = "itemCd,orderLet";
	/**
	 * 明細部検索メソッド
	 * @param itemCd 品目コード
	 * @param orderLet 生産計画年月
	 * @return int 検索結果件数 
	 * */
	int getCountList(final String itemCd, final String orderLet);

	/** ARGSアノテーション getEntity */
	String getNewEntity_ARGS = "itemCd,orderLet";
	/**
	 * 明細部　新規データ作成メソッド
	 * @param orderLet 生産計画年月
	 * @return List<ProductionDetailList> 検索結果リスト
	 */
	//List<ProductionDetailList> getNewEntity(final String orderLet);

}
