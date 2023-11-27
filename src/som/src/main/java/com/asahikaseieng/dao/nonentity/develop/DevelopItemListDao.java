/*
 * Created on 2007/12/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.develop;

import java.util.List;


/**
 * DevelopItemListDaoクラス
 * @author zen
 */
public interface DevelopItemListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.develop.DevelopItemList.class;

	/** ARGSアノテーション getDevelopItem */
	String getDevelopItem_ARGS = "itemCd";

	/**
	 * DevelopItemメソッド
	 *
     * @param itemCd itemCd
	 * @return DevelopItem
	 */
	List<DevelopItemList> getDevelopItem(final String itemCd);

//	/** ARGSアノテーション getDevelopItemList */
//	String getDevelopItemList_ARGS = "developmentRequestNo, itemCd";
//
//	/**
//	 * getDevelopItemListメソッド
//     * @param developmentRequestNo	developmentRequestNo
//     * @param itemCd itemCd
//	 * @return DevelopItemList
//	 */
//	List<DevelopItemList> getDevelopItemList(
//		final String developmentRequestNo, final String itemCd);

	/** ARGSアノテーション getItemList */
	String getItemList_ARGS = "developmentRequestNo";

	/**
	 * getItemListメソッド
	 * @param developmentRequestNo 依頼番号
	 * @return 品目リスト
	 */
	List<DevelopItemList> getItemList(final String developmentRequestNo);

	/** ARGSアノテーション getDevelopRequestNo */
	String getDevelopRequestNo_ARGS = "developmentRequestNo";

	/**
	 * getDevelopRequestNoメソッド
	 * @param developmentRequestNo 依頼番号
	 * @return 品目リスト
	 */
	List<DevelopItemList> getDevelopRequestNo(final String developmentRequestNo);
}
