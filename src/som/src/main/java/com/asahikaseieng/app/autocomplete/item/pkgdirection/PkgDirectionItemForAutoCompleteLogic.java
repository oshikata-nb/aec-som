/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.item.pkgdirection;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.item.pkgdirection.PkgDirectionItemForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装指図－品目マスタキューのAuto Complete用ロジック
 * @author tosco
 */
public interface PkgDirectionItemForAutoCompleteLogic {

	// 品目コードまたは、品目名称で検索--------------------------------
	/**
	 * 検索画面用品目マスタのオートコンプリート用データの取得
	 * @param itemCd 品目コードまたは品目名称
	 * @param repack 詰替フラグ
	 * @return List<PkgDirectionItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<PkgDirectionItemForAutoComplete> getSearchList(String itemCd,
			String repack) throws NoDataException;

	/**
	 * 詳細画面用品目マスタのオートコンプリート用データの取得
	 * @param itemCd 品目コードまたは品目名称
	 * @param repack 詰替フラグ
	 * @return List<PkgDirectionItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<PkgDirectionItemForAutoComplete> getDetailList(String itemCd,
			String repack) throws NoDataException;

	// 他社コードで検索----------------------------------------------------------
	/**
	 * 検索画面用品目マスタのオートコンプリート用データの取得(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<PkgDirectionItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<PkgDirectionItemForAutoComplete> getSearchListOtherCompany(
			String otherCompany1) throws NoDataException;

	/**
	 * 詳細画面用品目マスタのオートコンプリート用データの取得(他社コード１で検索)
	 * @param otherCompany1 他社コード１
	 * @return List<PkgDirectionItemForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<PkgDirectionItemForAutoComplete> getDetailListOtherCompany(
			String otherCompany1) throws NoDataException;

}
