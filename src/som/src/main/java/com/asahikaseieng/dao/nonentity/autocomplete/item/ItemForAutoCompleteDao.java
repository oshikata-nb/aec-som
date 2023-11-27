/*
 * Created on 2009/02/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.item;

import java.util.List;

/**
 * ItemForAutoCompleteDaoクラス
 * @author tosco
 */
public interface ItemForAutoCompleteDao {

	/** BEANアノテーション */
	Class<ItemForAutoComplete> BEAN = ItemForAutoComplete.class;

	// 品目コード・名称で検索-----------------------------------------------------
	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "itemCd,rowlimit";

	/**
	 * 検索画面用品目一覧取得用
	 * @param itemCd 品目コードまたは品目名
	 * @param rowlimit 行上限
	 * @return List<ItemForAutoComplete>
	 */
	List<ItemForAutoComplete> getSearchList(final String itemCd,
			final String rowlimit);

	/** ARGSアノテーション getDetailList */
	String getDetailList_ARGS = "itemCd,rowlimit";

	/**
	 * 検索画面用品目一覧取得用
	 * @param itemCd 品目コードまたは品目名
	 * @param rowlimit 行上限
	 * @return List<ItemForAutoComplete>
	 */
	List<ItemForAutoComplete> getDetailList(final String itemCd,
			final String rowlimit);

	/** ARGSアノテーション getDetailDigitList */
	String getDetailDigitList_ARGS = "itemCd,rowlimit";

	/**
	 * 品目マスタ詳細画面用-小数点桁数・端数区分付き
	 * @param itemCd 品目コードまたは品目名
	 * @param rowlimit 行上限
	 * @return List<ItemForAutoComplete>
	 */
	List<ItemForAutoComplete> getDetailDigitList(final String itemCd,
			final String rowlimit);

	/** ARGSアノテーション getDetailDigitPriceList */
	String getDetailDigitPriceList_ARGS = "itemCd,rowlimit";

	/**
	 * 品目マスタ詳細用標準仕入単価取得用
	 * @param itemCd 品目コードまたは品目名
	 * @param rowlimit 行上限
	 * @return List<ItemForAutoComplete>
	 */
	List<ItemForAutoComplete> getDetailDigitPriceList(final String itemCd,
			final String rowlimit);

	// 他社コード１で検索------------------------------------------------------------------------
	/** ARGSアノテーション getOtherCompany1SearchList */
	String getOtherCompany1SearchList_ARGS = "otherCompany1,rowlimit";

	/**
	 * 検索画面用品目一覧取得用（他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @param rowlimit 行上限
	 * @return List<ItemForAutoComplete>
	 */
	List<ItemForAutoComplete> getOtherCompany1SearchList(
			final String otherCompany1, final String rowlimit);

	/** ARGSアノテーション getOtherCompany1DetailList */
	String getOtherCompany1DetailList_ARGS = "otherCompany1,rowlimit";

	/**
	 * 検索画面用品目詳細画面用（他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @param rowlimit 行上限
	 * @return List<ItemForAutoComplete>
	 */
	List<ItemForAutoComplete> getOtherCompany1DetailList(
			final String otherCompany1, final String rowlimit);

	/** ARGSアノテーション getOtherCompany1DetailDigitPriceList */
	String getOtherCompany1DetailDigitPriceList_ARGS = "otherCompany1,rowlimit";

	/**
	 * 品目マスタ詳細用標準仕入単価取得用（他社コード１で検索）
	 * @param otherCompany1 他社コード１
	 * @param rowlimit 行上限
	 * @return List<ItemForAutoComplete>
	 */
	List<ItemForAutoComplete> getOtherCompany1DetailDigitPriceList(
			final String otherCompany1, final String rowlimit);

	/** ARGSアノテーション getOtherCompany1DetailDigitList */
	String getOtherCompany1DetailDigitList_ARGS = "otherCompany1,rowlimit";

	/**
	 * 品目マスタ詳細用取得用-小数点桁数・端数区分付き（他社コード１で検索）
	 * @param otherCompany1 他社コード１
	 * @param rowlimit 行上限
	 * @return List<ItemForAutoComplete>
	 */
	List<ItemForAutoComplete> getOtherCompany1DetailDigitList(
			final String otherCompany1, final String rowlimit);
}
