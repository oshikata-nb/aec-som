/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.accounts;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.accounts.AccountsForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 科目マスタのAuto Complete用ロジック
 * @author tosco
 */
public interface AccountsForAutoCompleteLogic {
	//科目コードまたは、科目名称で検索--------------------------------
	/**
	 * 科目マスタのオートコンプリート用データの取得
	 * @param accountsCd 科目コードまたは科目名称
	 * @return List<AccountsForAutoComplete> 検索結果リスト
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<AccountsForAutoComplete> getSearchList(String accountsCd) throws NoDataException;
}
