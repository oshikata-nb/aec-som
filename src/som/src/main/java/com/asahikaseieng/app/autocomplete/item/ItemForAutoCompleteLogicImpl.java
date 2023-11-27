/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.item.ItemForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.item.ItemForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 品目マスタのAuto Complete用ロジック
 * @author t0011036
 */
public class ItemForAutoCompleteLogicImpl implements ItemForAutoCompleteLogic {
	/** 品目マスタ操作DAO */
	private ItemForAutoCompleteDao itemForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public ItemForAutoCompleteLogicImpl() {
	}

	// 品目コードまたは、品目名称で検索--------------------------------
	/**
	 * 検索画面用品目マスタのオートコンプリート用データの取得
	 * @param itemCd 品目コードまたは品目名称
	 * @return List<ItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ItemForAutoComplete> getSearchList(final String itemCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCd);
		List<ItemForAutoComplete> list = itemForAutoCompleteDao.getSearchList(
			val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 詳細画面用品目マスタのオートコンプリート用データの取得
	 * @param itemCd 品目コードまたは品目名称
	 * @return List<ItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ItemForAutoComplete> getDetailList(final String itemCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCd);
		List<ItemForAutoComplete> list = itemForAutoCompleteDao.getDetailList(
			val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 品目マスタ詳細画面用-小数点桁数・端数区分付き
	 * @param itemCd 品目コードまたは品目名称
	 * @return List<ItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ItemForAutoComplete> getDetailDigitList(final String itemCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCd);
		List<ItemForAutoComplete> list = itemForAutoCompleteDao
				.getDetailDigitList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 検索画面用品目マスタのオートコンプリート用データの取得
	 * @param itemCd 品目コードまたは品目名称
	 * @return List<ItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ItemForAutoComplete> getDetailDigitPriceList(final String itemCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCd);
		List<ItemForAutoComplete> list = itemForAutoCompleteDao
				.getDetailDigitPriceList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// 他社コードで検索----------------------------------------------------------
	/**
	 * 検索画面用品目マスタのオートコンプリート用データの取得(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<ItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ItemForAutoComplete> getSearchListOtherCompany(
			final String otherCompany1) throws NoDataException {
		String val = AecTextUtils.likeFilter(otherCompany1);
		List<ItemForAutoComplete> list = itemForAutoCompleteDao
				.getOtherCompany1SearchList(val,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 詳細画面用品目マスタのオートコンプリート用データの取得(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<ItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ItemForAutoComplete> getDetailListOtherCompany(
			final String otherCompany1) throws NoDataException {
		String val = AecTextUtils.likeFilter(otherCompany1);
		List<ItemForAutoComplete> list = itemForAutoCompleteDao
				.getOtherCompany1DetailList(val,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 検索画面用品目マスタのオートコンプリート用データの取得(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<ItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ItemForAutoComplete> getOtherCompanyCdDetailDigitPriceList(
			final String otherCompany1) throws NoDataException {
		String val = AecTextUtils.likeFilter(otherCompany1);
		List<ItemForAutoComplete> list = itemForAutoCompleteDao
				.getOtherCompany1DetailDigitPriceList(val,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 検索画面用品目マスタのオートコンプリート用データの取得(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<ItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ItemForAutoComplete> getOtherCompanyCdDetailDigitList(
			final String otherCompany1) throws NoDataException {
		String val = AecTextUtils.likeFilter(otherCompany1);
		List<ItemForAutoComplete> list = itemForAutoCompleteDao
				.getOtherCompany1DetailDigitList(val,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * itemForAutoCompleteDaoを設定します。
	 * @param itemForAutoCompleteDao itemForAutoCompleteDao
	 */
	public void setItemForAutoCompleteDao(
			final ItemForAutoCompleteDao itemForAutoCompleteDao) {
		this.itemForAutoCompleteDao = itemForAutoCompleteDao;
	}
}
