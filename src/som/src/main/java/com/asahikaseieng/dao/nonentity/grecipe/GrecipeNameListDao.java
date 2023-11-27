/*
 * Created on 2009/03/16
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.grecipe;

import java.util.List;

/**
 * 各種名称マスタメンテ 詳細画面用Daoインターフェース.
 *
 * @author tosco
 */
public interface GrecipeNameListDao {

	/** BEANアノテーション */
	Class<GrecipeNameList> BEAN = GrecipeNameList.class;
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
	GrecipeNameList getEntity(String nameDivision);

	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "division";

	/**
	 * 一覧検索メソッド
	 * @param division 名称区分
	 * @return List<MgrecipeList> 検索結果リスト
	 */
	List<GrecipeNameList> getSearchList(final String division);

}
