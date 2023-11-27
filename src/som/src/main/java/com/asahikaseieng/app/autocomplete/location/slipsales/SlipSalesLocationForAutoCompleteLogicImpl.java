/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.slipsales;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.location.slipsales.SlipSalesLocationForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.location.slipsales.SlipSalesLocationForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 売上伝票出力画面用 ロケーションマスタのAuto Complete用ロジック
 * @author tosco
 */
public class SlipSalesLocationForAutoCompleteLogicImpl implements
		SlipSalesLocationForAutoCompleteLogic {
	/** 売上伝票出力画面用ロケーションマスタ操作DAO */
	private SlipSalesLocationForAutoCompleteDao slipSalesLocationForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public SlipSalesLocationForAutoCompleteLogicImpl() {
	}

	// ロケーションコードまたは、ロケーション名称で検索--------------------------------
	/**
	 * 売上伝票出力画面用ロケーションマスタのオートコンプリート用データの取得
	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @return List<SlipSalesLocationForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<SlipSalesLocationForAutoComplete> getSearchList(
			final String locationCd) throws NoDataException {

		String val = AecTextUtils.likeFilter(locationCd);
		List<SlipSalesLocationForAutoComplete> list = slipSalesLocationForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 売上伝票出力画面用ロケーションマスタ操作DAOを設定します。
	 * @param slipSalesLocationForAutoCompleteDao 売上伝票出力画面用のロケーションのDAO
	 */
	public void setSlipSalesLocationForAutoCompleteDao(
			final SlipSalesLocationForAutoCompleteDao slipSalesLocationForAutoCompleteDao) {
		this.slipSalesLocationForAutoCompleteDao = slipSalesLocationForAutoCompleteDao;
	}

}
