/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.company;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.company.CompanyForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.company.CompanyForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 自社のAuto Complete用ロジック
 * @author t0011036
 */
public class CompanyForAutoCompleteLogicImpl implements
		CompanyForAutoCompleteLogic {
	/* 自社操作DAO */
	private CompanyForAutoCompleteDao companyForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public CompanyForAutoCompleteLogicImpl() {
	}

	/**
	 * 検索画面用自社のオートコンプリート用データの取得
	 * @param companyCd 自社コードまたは自社名称
	 * @return List<CompanyForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<CompanyForAutoComplete> getSearchList(final String companyCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(companyCd);
		List<CompanyForAutoComplete> list = companyForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * companyForAutoCompleteDaoを設定します。
	 * @param companyForAutoCompleteDao companyForAutoCompleteDao
	 */
	public void setCompanyForAutoCompleteDao(
			final CompanyForAutoCompleteDao companyForAutoCompleteDao) {
		this.companyForAutoCompleteDao = companyForAutoCompleteDao;
	}
}
