/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item.pkgdirection;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.item.pkgdirection.PkgDirectionItemForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.item.pkgdirection.PkgDirectionItemForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 包装指図－品目マスタのAuto Complete用ロジック
 * @author tosco
 */
public class PkgDirectionItemForAutoCompleteLogicImpl implements
		PkgDirectionItemForAutoCompleteLogic {

	/** 包装指図－品目オートコンプリート用Dao */
	private PkgDirectionItemForAutoCompleteDao pkgDirectionItemForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public PkgDirectionItemForAutoCompleteLogicImpl() {
	}

	// 品目コードまたは、品目名称で検索--------------------------------
	/**
	 * 検索画面用品目マスタのオートコンプリート用データの取得
	 * @param itemCd 品目コードまたは品目名称
	 * @param repack 詰替フラグ
	 * @return List<PkgDirectionItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<PkgDirectionItemForAutoComplete> getSearchList(
			final String itemCd, final String repack) throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCd);
		List<PkgDirectionItemForAutoComplete> list = pkgDirectionItemForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT, repack);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 詳細画面用品目マスタのオートコンプリート用データの取得
	 * @param itemCd 品目コードまたは品目名称
	 * @param repack 詰替フラグ
	 * @return List<PkgDirectionItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<PkgDirectionItemForAutoComplete> getDetailList(
			final String itemCd, final String repack) throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCd);
		List<PkgDirectionItemForAutoComplete> list = pkgDirectionItemForAutoCompleteDao
				.getDetailList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT, repack);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// 他社コードで検索----------------------------------------------------------
	/**
	 * 検索画面用品目マスタのオートコンプリート用データの取得(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<PkgDirectionItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<PkgDirectionItemForAutoComplete> getSearchListOtherCompany(
			final String otherCompany1) throws NoDataException {
		String val = AecTextUtils.likeFilter(otherCompany1);
		List<PkgDirectionItemForAutoComplete> list = pkgDirectionItemForAutoCompleteDao
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
	 * @return List<PkgDirectionItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<PkgDirectionItemForAutoComplete> getDetailListOtherCompany(
			final String otherCompany1) throws NoDataException {
		String val = AecTextUtils.likeFilter(otherCompany1);
		List<PkgDirectionItemForAutoComplete> list = pkgDirectionItemForAutoCompleteDao
				.getOtherCompany1DetailList(val,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 包装指図－品目オートコンプリート用Daoを設定します。
	 * @param pkgDirectionItemForAutoCompleteDao 包装指図－品目オートコンプリート用Dao
	 */
	public void setPkgDirectionItemForAutoCompleteDao(
			final PkgDirectionItemForAutoCompleteDao pkgDirectionItemForAutoCompleteDao) {
		this.pkgDirectionItemForAutoCompleteDao = pkgDirectionItemForAutoCompleteDao;
	}

}
