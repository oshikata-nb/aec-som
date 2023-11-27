/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.autocomplete.item.pkgdirection;

import java.util.List;

/**
 * PkgDirectionItemForAutoCompleteDaoクラス
 * @author tosco
 */
public interface PkgDirectionItemForAutoCompleteDao {

	/** BEANアノテーション */
	Class<PkgDirectionItemForAutoComplete> BEAN = PkgDirectionItemForAutoComplete.class;

	// 品目コード・名称で検索-----------------------------------------------------
	/** ARGSアノテーション getSearchList */
	String getSearchList_ARGS = "itemCd,rowlimit,repack";

	/**
	 * 検索画面用品目一覧取得用
	 * @param itemCd 品目コードまたは品目名
	 * @param repack 詰替フラグ
	 * @param rowlimit 行上限
	 * @return List<ItemForAutoComplete>
	 */
	List<PkgDirectionItemForAutoComplete> getSearchList(final String itemCd,
			final String rowlimit, final String repack);

	/** ARGSアノテーション getDetailList */
	String getDetailList_ARGS = "itemCd,rowlimit,repack";

	/**
	 * 検索画面用品目一覧取得用
	 * @param itemCd 品目コードまたは品目名
	 * @param repack 詰替フラグ
	 * @param rowlimit 行上限
	 * @return List<ItemForAutoComplete>
	 */
	List<PkgDirectionItemForAutoComplete> getDetailList(final String itemCd,
			final String rowlimit, final String repack);

	// 他社コード１で検索------------------------------------------------------------------------
	/** ARGSアノテーション getOtherCompany1SearchList */
	String getOtherCompany1SearchList_ARGS = "otherCompany1,rowlimit";

	/**
	 * 検索画面用品目一覧取得用（他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @param rowlimit 行上限
	 * @return List<ItemForAutoComplete>
	 */
	List<PkgDirectionItemForAutoComplete> getOtherCompany1SearchList(
			final String otherCompany1, final String rowlimit);

	/** ARGSアノテーション getOtherCompany1DetailList */
	String getOtherCompany1DetailList_ARGS = "otherCompany1,rowlimit";

	/**
	 * 検索画面用品目詳細画面用（他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @param rowlimit 行上限
	 * @return List<ItemForAutoComplete>
	 */
	List<PkgDirectionItemForAutoComplete> getOtherCompany1DetailList(
			final String otherCompany1, final String rowlimit);

}
