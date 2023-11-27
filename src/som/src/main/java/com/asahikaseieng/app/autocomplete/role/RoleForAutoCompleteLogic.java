/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.role;

import java.util.List;

import com.asahikaseieng.dao.nonentity.autocomplete.role.RoleForAutoComplete;
import com.asahikaseieng.exception.NoDataException;

/**
 * ロールのAuto Complete用ロジック
 * @author t0011036
 */
public interface RoleForAutoCompleteLogic {
	/**
	 * 検索画面用ロールのオートコンプリート用データの取得
	 * @param roleId ロールコードまたはロール名称
	 * @return List<RoleForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	List<RoleForAutoComplete> getSearchList(String roleId)
			throws NoDataException;
}
