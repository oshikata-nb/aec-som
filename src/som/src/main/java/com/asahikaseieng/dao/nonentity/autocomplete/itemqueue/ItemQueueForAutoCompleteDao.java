/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.itemqueue;

import java.util.List;

/**
 * ItemQueueForAutoCompleteDaoクラス
 * @author tosco
 */
public interface ItemQueueForAutoCompleteDao {

	/** BEANアノテーション */
	Class<ItemQueueForAutoComplete> BEAN = ItemQueueForAutoComplete.class;

	// 品目コード・名称で検索-----------------------------------------------------
	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "itemCd,rowlimit";

	/**
	 * 検索画面用品目キュー一覧取得用
	 * @param itemCd 品目コードまたは品目名
	 * @param rowlimit 行上限
	 * @return List<ItemQueueForAutoComplete>
	 */
	List<ItemQueueForAutoComplete> getSearchList(final String itemCd,
			final String rowlimit);

	/** ARGSアノテーション getDetailList */
	String getDetailList_ARGS = "itemCd,rowlimit";

	/**
	 * 検索画面用品目キュー一覧取得用
	 * @param itemCd 品目コードまたは品目名
	 * @param rowlimit 行上限
	 * @return List<ItemQueueForAutoComplete>
	 */
	List<ItemQueueForAutoComplete> getDetailList(final String itemCd,
			final String rowlimit);

	/** ARGSアノテーション getDetailDigitList */
	String getDetailDigitList_ARGS = "itemCd,rowlimit";

	/**
	 * 品目マスタ詳細画面用-小数点桁数・端数区分付き
	 * @param itemCd 品目コードまたは品目名
	 * @param rowlimit 行上限
	 * @return List<ItemQueueForAutoComplete>
	 */
	List<ItemQueueForAutoComplete> getDetailDigitList(final String itemCd,
			final String rowlimit);

	// 他社コード１で検索------------------------------------------------------------------------
	/** ARGSアノテーション getOtherCompany1SearchList */
	String getOtherCompany1SearchList_ARGS = "otherCompanyCd1,rowlimit";

	/**
	 * 検索画面用品目キュー一覧取得用（他社コード１で検索)
	 * @param otherCompanyCd1 他社コード１
	 * @param rowlimit 行上限
	 * @return List<ItemQueueForAutoComplete>
	 */
	List<ItemQueueForAutoComplete> getOtherCompany1SearchList(
			final String otherCompanyCd1, final String rowlimit);

	/** ARGSアノテーション getOtherCompany1DetailList */
	String getOtherCompany1DetailList_ARGS = "otherCompanyCd1,rowlimit";

	/**
	 * 検索画面用品目キュー詳細画面用（他社コード１で検索)
	 * @param otherCompanyCd1 他社コード１
	 * @param rowlimit 行上限
	 * @return List<ItemQueueForAutoComplete>
	 */
	List<ItemQueueForAutoComplete> getOtherCompany1DetailList(
			final String otherCompanyCd1, final String rowlimit);

	/** ARGSアノテーション getOtherCompany1DetailDigitList */
	String getOtherCompany1DetailDigitList_ARGS = "otherCompanyCd1,rowlimit";

	/**
	 * 検索画面用品目キュー詳細画面用-小数点桁数・端数区分付き（他社コード１で検索)
	 * @param otherCompanyCd1 他社コード１
	 * @param rowlimit 制限行数
	 * @return List<ItemQueueForAutoComplete>
	 */
	List<ItemQueueForAutoComplete> getOtherCompany1DetailDigitList(
			final String otherCompanyCd1, final String rowlimit);
}
