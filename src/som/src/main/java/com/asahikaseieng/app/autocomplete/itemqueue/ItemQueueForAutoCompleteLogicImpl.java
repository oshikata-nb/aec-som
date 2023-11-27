/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.itemqueue;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.itemqueue.ItemQueueForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.itemqueue.ItemQueueForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 品目マスタキューのAuto Complete用ロジック
 * @author tosco
 */
public class ItemQueueForAutoCompleteLogicImpl implements
		ItemQueueForAutoCompleteLogic {
	/** 品目マスタキュー操作DAO */
	private ItemQueueForAutoCompleteDao itemQueueForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public ItemQueueForAutoCompleteLogicImpl() {
	}

	// 品目コードまたは、品目名称で検索--------------------------------
	/**
	 * 検索画面用品目マスタキューのオートコンプリート用データの取得
	 * @param itemCd 品目コードまたは品目名称
	 * @return List<ItemQueueForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ItemQueueForAutoComplete> getSearchList(final String itemCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCd);
		List<ItemQueueForAutoComplete> list = itemQueueForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 詳細画面用品目マスタキューのオートコンプリート用データの取得
	 * @param itemCd 品目コードまたは品目名称
	 * @return List<ItemQueueForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ItemQueueForAutoComplete> getDetailList(final String itemCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCd);
		List<ItemQueueForAutoComplete> list = itemQueueForAutoCompleteDao
				.getDetailList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 品目マスタ詳細画面用-小数点桁数・端数区分付き
	 * @param itemCd 品目コードまたは品目名称
	 * @return List<ItemQueueForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ItemQueueForAutoComplete> getDetailDigitList(final String itemCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCd);
		List<ItemQueueForAutoComplete> list = itemQueueForAutoCompleteDao
				.getDetailDigitList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// 他社コードで検索----------------------------------------------------------
	/**
	 * 検索画面用品目マスタキューのオートコンプリート用データの取得(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<ItemQueueForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ItemQueueForAutoComplete> getSearchListOtherCompany(
			final String otherCompany1) throws NoDataException {
		String val = AecTextUtils.likeFilter(otherCompany1);
		List<ItemQueueForAutoComplete> list = itemQueueForAutoCompleteDao
				.getOtherCompany1SearchList(val,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 詳細画面用品目マスタキューのオートコンプリート用データの取得(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<ItemQueueForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ItemQueueForAutoComplete> getDetailListOtherCompany(
			final String otherCompany1) throws NoDataException {
		String val = AecTextUtils.likeFilter(otherCompany1);
		List<ItemQueueForAutoComplete> list = itemQueueForAutoCompleteDao
				.getOtherCompany1DetailList(val,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 詳細画面用小数点桁数・端数区分付き品目マスタキューのオートコンプリート用データの取得(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<ItemQueueForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ItemQueueForAutoComplete> getDetailDigitListOtherCompany(
			final String otherCompany1) throws NoDataException {
		String val = AecTextUtils.likeFilter(otherCompany1);
		List<ItemQueueForAutoComplete> list = itemQueueForAutoCompleteDao
				.getOtherCompany1DetailDigitList(val,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 品目マスタキュー操作DAOを設定します。
	 * @param itemQueueForAutoCompleteDao 品目マスタキュー操作DAO
	 */
	public void setItemQueueForAutoCompleteDao(
			final ItemQueueForAutoCompleteDao itemQueueForAutoCompleteDao) {
		this.itemQueueForAutoCompleteDao = itemQueueForAutoCompleteDao;
	}
}
