/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.financialclass;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.financialclass.FinancialClassForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.financialclass.FinancialClassForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 財務分類のAuto Complete用ロジック
 * @author t0011036
 */
public class FinancialClassForAutoCompleteLogicImpl implements
		FinancialClassForAutoCompleteLogic {
	/* 財務分類操作DAO */
	private FinancialClassForAutoCompleteDao financialClassForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public FinancialClassForAutoCompleteLogicImpl() {
	}

	/**
	 * 検索画面用財務分類のオートコンプリート用データの取得
	 * @param financialClassCd 財務分類コードまたは財務分類名称
	 * @return List<FinancialClassForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<FinancialClassForAutoComplete> getSearchList(
			final String financialClassCd) throws NoDataException {
		String val = AecTextUtils.likeFilter(financialClassCd);
		List<FinancialClassForAutoComplete> list = financialClassForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * financialClassForAutoCompleteDaoを設定します。
	 * @param financialClassForAutoCompleteDao financialClassForAutoCompleteDao
	 */
	public void setFinancialClassForAutoCompleteDao(
			final FinancialClassForAutoCompleteDao financialClassForAutoCompleteDao) {
		this.financialClassForAutoCompleteDao = financialClassForAutoCompleteDao;
	}
}
