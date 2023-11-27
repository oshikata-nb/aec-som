/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.itemqueue;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.itemqueue.ItemQueueForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目マスタキューのAuto Complete用ロジック
 * @author tosco
 */
public interface ItemQueueForAutoCompleteLogic {
	// 品目コードまたは、品目名称で検索--------------------------------
	/**
	 * 検索画面用品目マスタキューのオートコンプリート用データの取得
	 * @param itemCd 品目コードまたは品目名称
	 * @return List<ItemQueueForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ItemQueueForAutoComplete> getSearchList(String itemCd)
			throws NoDataException;

	/**
	 * 詳細画面用品目マスタキューのオートコンプリート用データの取得
	 * @param itemCd 品目コードまたは品目名称
	 * @return List<ItemQueueForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ItemQueueForAutoComplete> getDetailList(String itemCd)
			throws NoDataException;

	/**
	 * 品目マスタ詳細画面用-小数点桁数・端数区分付き
	 * @param itemCd 品目コードまたは品目名称
	 * @return List<ItemQueueForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ItemQueueForAutoComplete> getDetailDigitList(String itemCd)
			throws NoDataException;

	// 他社コードで検索----------------------------------------------------------
	/**
	 * 検索画面用品目マスタキューのオートコンプリート用データの取得(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<ItemQueueForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ItemQueueForAutoComplete> getSearchListOtherCompany(
			String otherCompany1) throws NoDataException;

	/**
	 * 詳細画面用品目マスタキューのオートコンプリート用データの取得(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<ItemQueueForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ItemQueueForAutoComplete> getDetailListOtherCompany(
			String otherCompany1) throws NoDataException;

	/**
	 * 詳細画面用小数点桁数・端数区分付き品目マスタキューのオートコンプリート用データの取得(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<ItemQueueForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ItemQueueForAutoComplete> getDetailDigitListOtherCompany(
			String otherCompany1) throws NoDataException;
}
