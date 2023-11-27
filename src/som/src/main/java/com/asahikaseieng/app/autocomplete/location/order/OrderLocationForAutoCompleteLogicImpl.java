/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.location.order;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.location.order.OrderLocationForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.location.order.OrderLocationForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 受注画面用ロケーションマスタのAuto Complete用ロジック
 * @author tosco
 */
public class OrderLocationForAutoCompleteLogicImpl implements
		OrderLocationForAutoCompleteLogic {
	/** 受注画面用ロケーションマスタ操作DAO */
	private OrderLocationForAutoCompleteDao orderLocationForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public OrderLocationForAutoCompleteLogicImpl() {
	}

	// ロケーションコードまたは、ロケーション名称で検索--------------------------------
	/**
	 * 受注画面用ロケーションマスタのオートコンプリート用データの取得
	 * 
	 * @param locationCd ロケーションコードまたはロケーション名称
	 * @param itemCd 品目コード
	 * 
	 * @return List<OrderLocationListForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 * 
	 */
	public List<OrderLocationForAutoComplete> getSearchList(
			final String locationCd, final String itemCd)
			throws NoDataException {

		String val = AecTextUtils.likeFilter(locationCd);
		List<OrderLocationForAutoComplete> list = orderLocationForAutoCompleteDao
				.getSearchList(val, itemCd, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 出荷指図画面用ロケーションマスタ操作DAOを設定します。
	 * 
	 * @param orderLocationForAutoCompleteDao 受注画面用ロケーションマスタ操作DAO
	 */
	public void setOrderLocationListForAutoCompleteDao(
			final OrderLocationForAutoCompleteDao orderLocationForAutoCompleteDao) {
		this.orderLocationForAutoCompleteDao = orderLocationForAutoCompleteDao;
	}

}
