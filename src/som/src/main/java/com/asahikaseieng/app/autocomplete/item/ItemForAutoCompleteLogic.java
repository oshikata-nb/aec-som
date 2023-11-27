/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.item.ItemForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目マスタのAuto Complete用ロジック
 * @author t0011036
 */
public interface ItemForAutoCompleteLogic {
	// 品目コードまたは、品目名称で検索--------------------------------
	/**
	 * 検索画面用品目マスタのオートコンプリート用データの取得
	 * @param itemCd 品目コードまたは品目名称
	 * @return List<ItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ItemForAutoComplete> getSearchList(String itemCd)
			throws NoDataException;

	/**
	 * 詳細画面用品目マスタのオートコンプリート用データの取得
	 * @param itemCd 品目コードまたは品目名称
	 * @return List<ItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ItemForAutoComplete> getDetailList(String itemCd)
			throws NoDataException;

	/**
	 * 品目マスタ詳細画面用-小数点桁数・端数区分付き
	 * @param itemCd 品目コードまたは品目名称
	 * @return List<ItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ItemForAutoComplete> getDetailDigitList(String itemCd)
			throws NoDataException;

	/**
	 * 検索画面用品目マスタのオートコンプリート用データの取得
	 * @param itemCd 品目コードまたは品目名称
	 * @return List<ItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ItemForAutoComplete> getDetailDigitPriceList(String itemCd)
			throws NoDataException;

	// 他社コードで検索----------------------------------------------------------
	/**
	 * 検索画面用品目マスタのオートコンプリート用データの取得(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<ItemQueueForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ItemForAutoComplete> getSearchListOtherCompany(String otherCompany1)
			throws NoDataException;

	/**
	 * 詳細画面用品目マスタのオートコンプリート用データの取得(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<ItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ItemForAutoComplete> getDetailListOtherCompany(String otherCompany1)
			throws NoDataException;

	/**
	 * 検索画面用品目マスタのオートコンプリート用データの取得(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<ItemQueueForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ItemForAutoComplete> getOtherCompanyCdDetailDigitPriceList(
			String otherCompany1) throws NoDataException;

	/**
	 * 検索画面用品目マスタのオートコンプリート用データの取得(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<ItemQueueForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ItemForAutoComplete> getOtherCompanyCdDetailDigitList(
			String otherCompany1) throws NoDataException;
}
