/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.slipshipping;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.location.slipshipping.SlipShippingLocationForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.location.slipshipping.SlipShippingLocationForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 出荷関連帳票出力画面 ロケーションマスタのAuto Complete用ロジック
 * @author tosco
 */
public class SlipShippingLocationForAutoCompleteLogicImpl implements
		SlipShippingLocationForAutoCompleteLogic {
	/** 出荷関連帳票出力画面ロケーションマスタ操作DAO */
	private SlipShippingLocationForAutoCompleteDao slipShippingLocationForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public SlipShippingLocationForAutoCompleteLogicImpl() {
	}

	// ロケーションコードまたは、ロケーション名称で検索--------------------------------
	/**
	 * 出荷関連帳票出力画面ロケーションマスタのオートコンプリート用データの取得
	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @return List<SlipShippingLocationForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<SlipShippingLocationForAutoComplete> getSearchList(
			final String locationCd) throws NoDataException {

		String val = AecTextUtils.likeFilter(locationCd);
		List<SlipShippingLocationForAutoComplete> list = slipShippingLocationForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 出荷関連帳票出力画面ロケーションマスタ操作DAOを設定します。
	 * @param slipShippingLocationForAutoCompleteDao 出荷関連帳票出力画面のロケーションのDAO
	 */
	public void setSlipShippingLocationForAutoCompleteDao(
			final SlipShippingLocationForAutoCompleteDao slipShippingLocationForAutoCompleteDao) {
		this.slipShippingLocationForAutoCompleteDao = slipShippingLocationForAutoCompleteDao;
	}

}
