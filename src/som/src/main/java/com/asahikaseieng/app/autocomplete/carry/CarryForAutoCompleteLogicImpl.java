/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.carry;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.carry.CarryForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.carry.CarryForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 運送会社のAuto Complete用ロジック
 * @author t0011036
 */
public class CarryForAutoCompleteLogicImpl implements CarryForAutoCompleteLogic {
	/* 運送会社操作DAO */
	private CarryForAutoCompleteDao carryForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public CarryForAutoCompleteLogicImpl() {
	}

	/**
	 * 検索画面用運送会社のオートコンプリート用データの取得
	 * @param carryCd 運送会社コードまたは運送会社名称
	 * @return List<CarryForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<CarryForAutoComplete> getSearchList(final String carryCd)
			throws NoDataException {
		String val = AecTextUtils.likeFilter(carryCd);
		List<CarryForAutoComplete> list = carryForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	// setter---------------------------------------------------------------

	/**
	 * carryForAutoCompleteDaoを設定します。
	 * @param carryForAutoCompleteDao carryForAutoCompleteDao
	 */
	public void setCarryForAutoCompleteDao(
			final CarryForAutoCompleteDao carryForAutoCompleteDao) {
		this.carryForAutoCompleteDao = carryForAutoCompleteDao;
	}
}
