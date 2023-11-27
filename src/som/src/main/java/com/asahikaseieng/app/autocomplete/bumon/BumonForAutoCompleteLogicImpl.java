/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.bumon;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.bumon.BumonForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.bumon.BumonForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 会計部門マスタのAuto Complete用ロジック
 * @author tosco
 */
public class BumonForAutoCompleteLogicImpl implements BumonForAutoCompleteLogic {

	/** 会計部門マスタ操作DAO */
	private BumonForAutoCompleteDao bumonForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public BumonForAutoCompleteLogicImpl() {
	}

	// 会計部門コードまたは、会計部門名称で検索--------------------------------
	/**
	 * 会計部門マスタのオートコンプリート用データの取得
	 * @param sectionCd 会計部門コードまたは会計部門名称
	 * @return List<BumonForAutoComplete> 検索結果リスト
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<BumonForAutoComplete> getSearchList(final String sectionCd)
			throws NoDataException {

		String val = AecTextUtils.likeFilter(sectionCd);
		List<BumonForAutoComplete> list = bumonForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 会計部門マスタ操作DAOを設定します。
	 * @param bumonForAutoCompleteDao 会計部門マスタ操作DAO
	 */
	public void setBumonForAutoCompleteDao(
			final BumonForAutoCompleteDao bumonForAutoCompleteDao) {
		this.bumonForAutoCompleteDao = bumonForAutoCompleteDao;
	}

}
