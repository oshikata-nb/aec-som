/*
 * Created on 2009/03/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.reason;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.reason.ReasonForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * 理由マスタのAuto Complete用ロジック
 * @author tosco
 */
public interface ReasonForAutoCompleteLogic {
	//理由コードまたは、理由内容で検索--------------------------------
	/**
	 * 理由マスタのオートコンプリート用データの取得
	 * @param ryCd 理由コードまたは理由内容
	 * @return List<AccountsForAutoComplete> 検索結果リスト
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<ReasonForAutoComplete> getSearchList(String ryCd) throws NoDataException;
}
