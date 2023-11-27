/*
 * Created on 2009/02/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.login;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.login.LoginForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * ログインマスタのAuto Complete用ロジック
 * @author tosco
 */
public interface LoginForAutoCompleteLogic {
	//ログインコードまたは、ログイン名称で検索--------------------------------
	/**
	 * 検索画面用ログインマスタのオートコンプリート用データの取得
	 * @param itemCd ログインコードまたはログイン名称
	 * @return List<LoginForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<LoginForAutoComplete> getSearchList(String itemCd) throws NoDataException;
}
