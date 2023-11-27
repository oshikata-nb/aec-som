/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.mgrecipe;

import java.util.List;

/**
 * 各種名称マスタメンテ 詳細画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface MgrecipeNameListDao {

	/** BEANアノテーション */
	Class<MgrecipeNameList> BEAN = com.asahikaseieng.dao.nonentity.mgrecipe.MgrecipeNameList.class;
	/** レシピステータスの種別 */
	String RECIPE_STATUS_KEY = "RSTS";
	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "NAME_DIVISION";

	/**
	 * エンティティ取得.
	 * @param nameDivision nameDivision
	 * @return Mgrecipe
	 */
	MgrecipeNameList getEntity(String nameDivision);

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "division";

	/**
	 * 一覧検索メソッド
	 * @param division 名称区分
	 * @return List<MgrecipeList> 検索結果リスト
	 */
	List<MgrecipeNameList> getSearchList(final String division);

}
