/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.vender.deposit;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.vender.deposit.DepositVenderForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.vender.deposit.DepositVenderForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 取引先(入金用)のAuto Complete用ロジック
 * @author tosco
 */
public class DepositVenderForAutoCompleteLogicImpl implements
		DepositVenderForAutoCompleteLogic {
	/** 取引先(入金用)操作DAO */
	private DepositVenderForAutoCompleteDao depositVenderForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public DepositVenderForAutoCompleteLogicImpl() {
	}

	/* 取引先コードまたは、取引先略称で検索 */
	/**
	 * 検索画面用取引先マスタのオートコンプリート用データの取得
	 * @param itemCd 取引先コードまたは取引先略称
	 * @param venderDivision 取引先区分
	 * @return List<DepositVenderForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<DepositVenderForAutoComplete> getSearchList(
			final String itemCd, final String venderDivision)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCd);
		List<DepositVenderForAutoComplete> list = depositVenderForAutoCompleteDao
				.getSearchList(venderDivision, val,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * depositVenderForAutoCompleteDaoを設定します。
	 * @param depositVenderForAutoCompleteDao depositVenderForAutoCompleteDao
	 */
	public void setDepositVenderForAutoCompleteDao(
			final DepositVenderForAutoCompleteDao depositVenderForAutoCompleteDao) {
		this.depositVenderForAutoCompleteDao = depositVenderForAutoCompleteDao;
	}
}
