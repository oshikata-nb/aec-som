/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.component;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.component.ComponentForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.component.ComponentForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 成分のAuto Complete用ロジック
 * @author t0011036
 */
public class ComponentForAutoCompleteLogicImpl implements
		ComponentForAutoCompleteLogic {
	/* 成分操作DAO */
	private ComponentForAutoCompleteDao componentForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public ComponentForAutoCompleteLogicImpl() {
	}

	/**
	 * 検索画面用成分のオートコンプリート用データの取得
	 * @param componentCd 成分コードまたは成分名称
	 * @return List<ComponentForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<ComponentForAutoComplete> getSearchList(final String componentCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(componentCd);
		List<ComponentForAutoComplete> list = componentForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * componentForAutoCompleteDaoを設定します。
	 * @param componentForAutoCompleteDao componentForAutoCompleteDao
	 */
	public void setComponentForAutoCompleteDao(
			final ComponentForAutoCompleteDao componentForAutoCompleteDao) {
		this.componentForAutoCompleteDao = componentForAutoCompleteDao;
	}
}
