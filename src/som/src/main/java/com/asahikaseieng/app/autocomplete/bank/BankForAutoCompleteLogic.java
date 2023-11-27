/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.bank;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.bank.BankForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 銀行のAuto Complete用ロジック
 * @author t0011036
 */
public interface BankForAutoCompleteLogic {
	/**
	 * 検索画面用銀行のオートコンプリート用データの取得
	 * @param bankCd 銀行コードまたは銀行名称
	 * @return List<BankForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<BankForAutoComplete> getBankSearchList(String bankCd)
			throws NoDataException;

	/**
	 * 検索画面用銀行のオートコンプリート用データの取得
	 * @param bankCd 銀行コードまたは銀行名称
	 * @param branchCd 支店コードまたは支店名称
	 * @return List<BankForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<BankForAutoComplete> getBranchSearchList(String bankCd, String branchCd)
			throws NoDataException;

	/**
	 * 検索画面用銀行のオートコンプリート用データの取得
	 * @param bankMasterCd 銀行マスタコードまたは銀行名称
	 * @return List<BankForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<BankForAutoComplete> getSearchList(String bankMasterCd)
			throws NoDataException;
}
