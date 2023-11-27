/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.bank;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.bank.BankForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.bank.BankForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 銀行のAuto Complete用ロジック
 * @author t0011036
 */
public class BankForAutoCompleteLogicImpl implements BankForAutoCompleteLogic {
	/* 銀行操作DAO */
	private BankForAutoCompleteDao bankForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public BankForAutoCompleteLogicImpl() {
	}

	/**
	 * 検索画面用銀行のオートコンプリート用データの取得
	 * @param bankCd 銀行コードまたは銀行名称
	 * @return List<BankForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<BankForAutoComplete> getBankSearchList(final String bankCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(bankCd);
		List<BankForAutoComplete> list = bankForAutoCompleteDao
				.getBankSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 検索画面用銀行のオートコンプリート用データの取得
	 * @param bankCd 銀行コードまたは銀行名称
	 * @param branchCd 支店コードまたは支店名称
	 * @return List<BankForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<BankForAutoComplete> getBranchSearchList(final String bankCd,
			final String branchCd) throws NoDataException {
		String val1 = AecTextUtils.likeFilter(bankCd);
		String val2 = AecTextUtils.likeFilter(branchCd);
		List<BankForAutoComplete> list = bankForAutoCompleteDao
				.getBranchSearchList(val1, val2,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 検索画面用銀行のオートコンプリート用データの取得
	 * @param bankMasterCd 銀行マスタコードまたは銀行名称
	 * @return List<BankForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<BankForAutoComplete> getSearchList(final String bankMasterCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(bankMasterCd);
		List<BankForAutoComplete> list = bankForAutoCompleteDao.getSearchList(
			val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * bankForAutoCompleteDaoを設定します。
	 * @param bankForAutoCompleteDao bankForAutoCompleteDao
	 */
	public void setBankForAutoCompleteDao(
			final BankForAutoCompleteDao bankForAutoCompleteDao) {
		this.bankForAutoCompleteDao = bankForAutoCompleteDao;
	}
}
