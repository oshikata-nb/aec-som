/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.offsetgroup;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.offsetgroup.OffsetGroupForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.offsetgroup.OffsetGroupForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 相殺グループのAuto Complete用ロジック
 * @author t0011036
 */
public class OffsetGroupForAutoCompleteLogicImpl implements
		OffsetGroupForAutoCompleteLogic {
	/* 相殺グループ操作DAO */
	private OffsetGroupForAutoCompleteDao offsetGroupForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public OffsetGroupForAutoCompleteLogicImpl() {
	}

	/**
	 * 検索画面用相殺グループのオートコンプリート用データの取得
	 * @param offsetGroupCd 相殺グループコードまたは相殺グループ名称
	 * @return List<OffsetGroupForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<OffsetGroupForAutoComplete> getSearchList(
			final String offsetGroupCd) throws NoDataException {
		String val = AecTextUtils.likeFilter(offsetGroupCd);
		List<OffsetGroupForAutoComplete> list = offsetGroupForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * offsetGroupForAutoCompleteDaoを設定します。
	 * @param offsetGroupForAutoCompleteDao offsetGroupForAutoCompleteDao
	 */
	public void setOffsetGroupForAutoCompleteDao(
			final OffsetGroupForAutoCompleteDao offsetGroupForAutoCompleteDao) {
		this.offsetGroupForAutoCompleteDao = offsetGroupForAutoCompleteDao;
	}
}
