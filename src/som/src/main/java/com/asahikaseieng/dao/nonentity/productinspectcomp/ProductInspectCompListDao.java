/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.productinspectcomp;

import java.util.List;

/**
 * 製品検査完了入力一覧画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface ProductInspectCompListDao {

	/** BEANアノテーション */
	Class BEAN = com.asahikaseieng.dao.nonentity.productinspectcomp.ProductInspectCompList.class;

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "condition";

	/**
	 * 製品検査完了入力一覧検索メソッド
	 * @param condition 検索条件
	 * @return List<ProductInspectCompList> 検索結果リスト
	 */
	List<ProductInspectCompList> getSearchList(final ProductInspectCompPagerCondition condition);

}
