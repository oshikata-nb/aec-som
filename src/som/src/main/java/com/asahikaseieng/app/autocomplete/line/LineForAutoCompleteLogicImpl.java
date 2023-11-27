/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.line;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.line.LineForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.line.LineForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 生産ラインのAuto Complete用ロジック
 * @author t0011036
 */
public class LineForAutoCompleteLogicImpl implements LineForAutoCompleteLogic {
	/* 生産ライン操作DAO */
	private LineForAutoCompleteDao lineForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public LineForAutoCompleteLogicImpl() {
	}

	/**
	 * 検索画面用生産ラインのオートコンプリート用データの取得
	 * @param lineCd 生産ラインコードまたは生産ライン名称
	 * @return List<LineForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<LineForAutoComplete> getSearchList(final String lineCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(lineCd);
		List<LineForAutoComplete> list = lineForAutoCompleteDao.getSearchList(
			val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * lineForAutoCompleteDaoを設定します。
	 * @param lineForAutoCompleteDao lineForAutoCompleteDao
	 */
	public void setLineForAutoCompleteDao(
			final LineForAutoCompleteDao lineForAutoCompleteDao) {
		this.lineForAutoCompleteDao = lineForAutoCompleteDao;
	}
}
