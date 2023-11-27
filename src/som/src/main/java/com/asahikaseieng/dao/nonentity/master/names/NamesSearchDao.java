/*
 * Created on Fri Sep 21 15:14:21 JST 2007
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.names;

import java.util.List;

/**
 * NamesDaoインターフェース.
 * 
 * @author kanri-user
 */
public interface NamesSearchDao {

	/** BEANアノテーション. */
	Class<NamesSearch> BEAN = NamesSearch.class;

	//
	// インスタンスメソッド
	//

	/** ARGSアノテーション getInspectionEntity(). */
	String getInspectionEntity_ARGS = "NAME_CD";

	/**
	 * 検査検索
	 * 
	 * @param nameCd
	 *            nameCd
	 * @return Names
	 */
	NamesSearch getInspectionEntity(String nameCd);

	/** ARGSアノテーション getEntity(). */
	String getEntity_ARGS = "NAME_CD,NAME_DIVISION";

	/**
	 * エンティティ取得.
	 * 
	 * @param nameCd
	 *            nameCd
	 * @param nameDivision
	 *            nameDivision
	 * @return Names
	 */
	NamesSearch getEntity(String nameCd, String nameDivision);

	/** ARGSアノテーション getEntity(). */
	String getEntityCndLocale_ARGS = "NAME_CD,NAME_DIVISION,LANGUAGE_ID";

	/**
	 * エンティティ取得.
	 * 
	 * @param nameCd
	 *            nameCd
	 * @param nameDivision
	 *            nameDivision
	 * @return Names
	 */
	NamesSearch getEntityCndLocale(String nameCd, String nameDivision,
			String languageId);

	/**
	 * リスト取得.
	 * 
	 * @return Namesのリスト
	 */
	List<NamesSearch> getList();

	/** ARGSアノテーション getComboList(). */
	String getComboList_ARGS = "NAME_DIVISION, LANGUAGE_ID";

	/** QUERYアノテーション getComboList(). */
	String getComboList_QUERY = "order by NAME_CD";

	/**
	 * 基準単位データ取得処理.
	 * 
	 * @param nameDivision
	 *            nameDivision
	 * @return Namesのリスト
	 */
	List<NamesSearch> getComboList(String nameDivision, String languageId);

	/** ARGSアノテーション getComboListOrderName(). */
	String getComboListOrderName_ARGS = "NAME_DIVISION";

	/** QUERYアノテーション getComboListOrderName(). */
	String getComboListOrderName_QUERY = "ORDER BY NLSSORT(NAME01, 'NLS_SORT=JAPANESE_M')";

	/**
	 * 名前リスト取得処理（名称順）.
	 * 
	 * @param nameDivision
	 *            nameDivision
	 * @return Namesのリスト
	 */
	List<NamesSearch> getComboListOrderName(String nameDivision);

	/** ARGSアノテーション getRecipeStatusList(). */
	String getRecipeStatusList_ARGS = "nameCd";

	/**
	 * ステータス一覧取得処理.
	 * 
	 * @param nameCd
	 *            コード
	 * @return Namesのリスト
	 */
	List<NamesSearch> getRecipeStatusList(String nameCd);

	/**
	 * ステータス一覧取得処理.納期回答
	 * 
	 * @return Namesのリスト
	 */
	List<NamesSearch> getPurchaseStatusListDelivery();

	/**
	 * ステータス一覧取得処理.受入
	 * 
	 * @return Namesのリスト
	 */
	List<NamesSearch> getPurchaseStatusListAccept();

	/**
	 * ステータス一覧取得処理.受入検査
	 * 
	 * @return Namesのリスト
	 */
	List<NamesSearch> getPurchaseStatusListInspect();

	/**
	 * 購入品区分一覧取得処理.納期回答
	 * 
	 * @return Namesのリスト
	 */
	List<NamesSearch> getPurchaseMatelialDivisonListDelivery();

	/** ARGSアノテーション getNamesList(). */
	String getNamesList_ARGS = "nameDivision, languageId";

	/**
	 * コンボ用データリスト取得処理.
	 * 
	 * @param nameDivision
	 *            nameDivision
	 * @param languageId
	 *            languageId
	 * @return Namesのリスト
	 */
	List<NamesSearch> getNamesList(String nameDivision, String languageId);

	/** ARGSアノテーション getNamesListWithCondition(). */
	String getNamesListWithCondition_ARGS = "nameDivision, languageId, nameCd, name01, name02, name03";

	/**
	 * コンボ用データリスト取得処理(絞り込み条件付き).
	 * 
	 * @param nameDivision
	 *            nameDivision
	 * @param languageId
	 *            languageId
	 * @param nameCd
	 *             nameCd
	 * @param name01
	 *             name01
	 * @param name02
	 *             name02
	 * @param name03
	 *             name03
	 * @return Namesのリスト
	 */
	List<NamesSearch> getNamesListWithCondition(String nameDivision,
			String languageId, String nameCd, String name01, String name02,
			String name03);

	/** ARGSアノテーション getNamesList(). */
	String getNamesListOrderbySeq_ARGS = "nameDivision, nameCd, name01, name02, name03, languageId";

	/**
	 * コンボ用データリスト取得処理.
	 * 
	 * @param nameDivision
	 *            nameDivision
	 * @param languageId
	 *            languageId
	 * @return Namesのリスト
	 */
	List<NamesSearch> getNamesListOrderbySeq(String nameDivision,
			String nameCd, String name01, String name02, String name03,
			String languageId);

	/** ARGSアノテーション getNamesListWithConditionList(). */
	String getNamesListWithConditionList_ARGS = "nameDivision, languageId, nameCd, name01, name02, name03";

	/**
	 * コンボ用データリスト取得処理(絞り込み条件付き in).
	 * 
	 * @param nameDivision
	 *            nameDivision
	 * @param languageId
	 *            languageId
	 * @param nameCd
	 *             nameCd
	 * @param name01
	 *             name01
	 * @param name02
	 *             name02
	 * @param name03
	 *             name03
	 * @return Namesのリスト
	 */
	List<NamesSearch> getNamesListWithConditionList(String nameDivision,
			String languageId, List<String> nameCd, List<String> name01,
			List<String> name02, List<String> name03);
}
