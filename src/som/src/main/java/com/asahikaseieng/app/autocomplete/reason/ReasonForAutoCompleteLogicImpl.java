/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reason;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.reason.ReasonForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.reason.ReasonForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 理由マスタのAuto Complete用ロジック
 * @author tosco
 */
public class ReasonForAutoCompleteLogicImpl implements
		ReasonForAutoCompleteLogic {

	/** 理由マスタ操作DAO */
	private ReasonForAutoCompleteDao reasonForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public ReasonForAutoCompleteLogicImpl() {
	}

	// 理由コードまたは、理由内容で検索--------------------------------
	/**
	 * 理由マスタのオートコンプリート用データの取得
	 * @param accountsCd 理由コードまたは理由内容
	 * @return List<AccountsForAutoComplete> 検索結果リスト
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ReasonForAutoComplete> getSearchList(final String accountsCd)
			throws NoDataException {

		String val = AecTextUtils.likeFilter(accountsCd);
		List<ReasonForAutoComplete> list = reasonForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 理由マスタ操作DAOを設定します。
	 * @param reasonForAutoCompleteDao 理由マスタ操作DAO
	 */
	public void setReasonForAutoCompleteDao(
			final ReasonForAutoCompleteDao reasonForAutoCompleteDao) {
		this.reasonForAutoCompleteDao = reasonForAutoCompleteDao;
	}

}
