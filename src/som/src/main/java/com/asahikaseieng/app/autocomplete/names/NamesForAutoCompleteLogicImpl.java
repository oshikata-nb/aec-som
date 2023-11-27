/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.names;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.names.NamesForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.names.NamesForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 各種名称マスタキューのAuto Complete用ロジック
 * @author tosco
 */
public class NamesForAutoCompleteLogicImpl implements NamesForAutoCompleteLogic {

	/** 各種名称マスタキュー操作DAO */
	private NamesForAutoCompleteDao namesForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public NamesForAutoCompleteLogicImpl() {
	}

	// 名称コードまたは、名称１で検索--------------------------------
	/**
	 * 各種名称マスタのオートコンプリート用データの取得
	 * @param nameCd 名称コードまたは名称１
	 * @param nameDivision 名称区分
	 * @return List<NamesForAutoComplete> 検索結果リスト
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<NamesForAutoComplete> getSearchList(final String nameCd,
			final String nameDivision) throws NoDataException {

		String val = AecTextUtils.likeFilter(nameCd);
		List<NamesForAutoComplete> list = namesForAutoCompleteDao
				.getSearchList(val, nameDivision,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 各種名称マスタ操作DAOを設定します。
	 * @param namesForAutoCompleteDao 各種名称マスタ操作DAO
	 */
	public void setNamesForAutoCompleteDao(
			final NamesForAutoCompleteDao namesForAutoCompleteDao) {
		this.namesForAutoCompleteDao = namesForAutoCompleteDao;
	}

}
