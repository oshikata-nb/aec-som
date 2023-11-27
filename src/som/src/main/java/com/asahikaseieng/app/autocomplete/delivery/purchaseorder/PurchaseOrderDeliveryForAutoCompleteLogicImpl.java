/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.delivery.purchaseorder;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.delivery.purchaseorder.PurchaseOrderDeliveryForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.delivery.purchaseorder.PurchaseOrderDeliveryForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 納入先マスタのAuto Complete用ロジック
 * @author tosco
 */
public class PurchaseOrderDeliveryForAutoCompleteLogicImpl implements
		PurchaseOrderDeliveryForAutoCompleteLogic {
	/** 納入先マスタ操作DAO */
	private PurchaseOrderDeliveryForAutoCompleteDao purchaseOrderDeliveryForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public PurchaseOrderDeliveryForAutoCompleteLogicImpl() {
	}

	// 納入先コードまたは、納入先名称で検索--------------------------------
	/**
	 * 検索画面用納入先マスタのオートコンプリート用データの取得
	 * @param itemCd 納入先コードまたは納入先名称
	 * @return List<PurchaseOrderDeliveryForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<PurchaseOrderDeliveryForAutoComplete> getSearchList(
			final String itemCd) throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCd);
		List<PurchaseOrderDeliveryForAutoComplete> list = purchaseOrderDeliveryForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 納入先マスタ操作DAOを設定します。
	 * @param purchaseOrderDeliveryForAutoCompleteDao 納入先マスタ操作DAO
	 */
	public void setPurchaseOrderDeliveryForAutoCompleteDao(
			final PurchaseOrderDeliveryForAutoCompleteDao purchaseOrderDeliveryForAutoCompleteDao) {
		this.purchaseOrderDeliveryForAutoCompleteDao = purchaseOrderDeliveryForAutoCompleteDao;
	}

}
