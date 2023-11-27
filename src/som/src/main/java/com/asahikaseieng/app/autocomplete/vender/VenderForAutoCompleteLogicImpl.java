/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.vender;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.vender.VenderForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.vender.VenderForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 取引先マスタキューのAuto Complete用ロジック
 * @author tosco
 */
public class VenderForAutoCompleteLogicImpl implements
		VenderForAutoCompleteLogic {
	/** 取引先マスタキュー操作DAO */
	private VenderForAutoCompleteDao venderForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public VenderForAutoCompleteLogicImpl() {
	}

	// 取引先コードまたは、取引先名称で検索--------------------------------
	/**
	 * 検索画面用取引先マスタのオートコンプリート用データの取得
	 * @param itemCd 取引先コードまたは取引先名称
	 * @param venderDivision 取引先区分
	 * @return List<VenderForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<VenderForAutoComplete> getSearchList(final String itemCd,
			final String venderDivision) throws NoDataException {

		String val = AecTextUtils.likeFilter(itemCd);
		List<VenderForAutoComplete> list = venderForAutoCompleteDao
				.getSearchList(val, venderDivision,
					Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 取引先マスタ操作DAOを設定します。
	 * @param venderForAutoCompleteDao 取引先マスタ操作DAO
	 */
	public void setVenderForAutoCompleteDao(
			final VenderForAutoCompleteDao venderForAutoCompleteDao) {
		this.venderForAutoCompleteDao = venderForAutoCompleteDao;
	}

}
