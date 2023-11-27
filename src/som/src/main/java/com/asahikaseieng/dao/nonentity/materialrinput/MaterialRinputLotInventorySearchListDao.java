/*
 * Created on 2009/03/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.materialrinput;

import java.util.List;

/**
 * 外注原材料投入実績入力画面_ロット検索ポップアップ画面Daoインターフェース
 * @author tosco
 */
public interface MaterialRinputLotInventorySearchListDao {

	/** BEANアノテーション */
	Class BEAN = MaterialRinputLotInventorySearchList.class;

	/** ARGSアノテーション getList */
	String getList_ARGS = "condition";

	/**
	 * ロット検索一覧検索メソッド
	 * 
	 * @param condition 検索条件
	 * @return List<RdirectionLotInventorySearchList> 検索結果リスト
	 */
	List<MaterialRinputLotInventorySearchList> getList
		(final MaterialRinputLotInventorySearchListPagerCondition condition);

}
