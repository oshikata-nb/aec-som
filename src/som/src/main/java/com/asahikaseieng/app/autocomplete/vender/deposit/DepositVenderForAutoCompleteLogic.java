/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.vender.deposit;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.vender.deposit.DepositVenderForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 取引先(入金用)のAuto Complete用ロジック
 * @author tosco
 */
public interface DepositVenderForAutoCompleteLogic {
	/* 取引先コードまたは、取引先略称で検索 */
	/**
	 * 検索画面用取引先マスタのオートコンプリート用データの取得
	 * @param itemCd 取引先コードまたは取引先略称
	 * @param venderDivision 取引先区分
	 * @return List<DepositVenderForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<DepositVenderForAutoComplete> getSearchList(String itemCd,
			String venderDivision) throws NoDataException;
}
