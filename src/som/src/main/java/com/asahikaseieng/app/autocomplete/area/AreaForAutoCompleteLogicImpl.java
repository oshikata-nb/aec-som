/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.area;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.area.AreaForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.area.AreaForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 地区のAuto Complete用ロジック
 * @author t0011036
 */
public class AreaForAutoCompleteLogicImpl implements AreaForAutoCompleteLogic {
	/* 地区操作DAO */
	private AreaForAutoCompleteDao areaForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public AreaForAutoCompleteLogicImpl() {
	}

	/**
	 * 検索画面用地区のオートコンプリート用データの取得
	 * @param areaCd 地区コードまたは地区名称
	 * @return List<AreaForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<AreaForAutoComplete> getSearchList(final String areaCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(areaCd);
		List<AreaForAutoComplete> list = areaForAutoCompleteDao.getSearchList(
			val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * areaForAutoCompleteDaoを設定します。
	 * @param areaForAutoCompleteDao areaForAutoCompleteDao
	 */
	public void setAreaForAutoCompleteDao(
			final AreaForAutoCompleteDao areaForAutoCompleteDao) {
		this.areaForAutoCompleteDao = areaForAutoCompleteDao;
	}
}
