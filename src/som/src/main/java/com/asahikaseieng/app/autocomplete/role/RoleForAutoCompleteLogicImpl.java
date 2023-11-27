/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.role;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.role.RoleForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.role.RoleForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * ロールのAuto Complete用ロジック
 * @author t0011036
 */
public class RoleForAutoCompleteLogicImpl implements RoleForAutoCompleteLogic {
	/* ロール操作DAO */
	private RoleForAutoCompleteDao roleForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public RoleForAutoCompleteLogicImpl() {
	}

	/**
	 * 検索画面用ロールのオートコンプリート用データの取得
	 * @param roleId ロールコードまたは勘定科目名称
	 * @return List<RoleForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<RoleForAutoComplete> getSearchList(final String roleId)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(roleId);
		List<RoleForAutoComplete> list = roleForAutoCompleteDao.getSearchList(
			val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * roleForAutoCompleteDaoを設定します。
	 * @param roleForAutoCompleteDao roleForAutoCompleteDao
	 */
	public void setRoleForAutoCompleteDao(
			final RoleForAutoCompleteDao roleForAutoCompleteDao) {
		this.roleForAutoCompleteDao = roleForAutoCompleteDao;
	}
}
