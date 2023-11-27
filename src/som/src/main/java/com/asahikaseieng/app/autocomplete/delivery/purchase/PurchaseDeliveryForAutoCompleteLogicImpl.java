/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.delivery.purchase;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.delivery.purchase.PurchaseDeliveryForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.delivery.purchase.PurchaseDeliveryForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 納入先マスタのAuto Complete用ロジック
 * @author tosco
 */
public class PurchaseDeliveryForAutoCompleteLogicImpl implements
		PurchaseDeliveryForAutoCompleteLogic {
	/** 納入先マスタ操作DAO */
	private PurchaseDeliveryForAutoCompleteDao purchaseDeliveryForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public PurchaseDeliveryForAutoCompleteLogicImpl() {
	}

	// 納入先コードまたは、納入先名称で検索--------------------------------
	/**
	 * 検索画面用納入先マスタのオートコンプリート用データの取得
	 * @param itemCd 納入先コードまたは納入先名称
	 * @return List<PurchaseDeliveryForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<PurchaseDeliveryForAutoComplete> getSearchList(
			final String itemCd) throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCd);
		List<PurchaseDeliveryForAutoComplete> list = purchaseDeliveryForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// 納入先コードまたは、納入先名称で検索--------------------------------
	/**
	 * 検索画面用納入先マスタのオートコンプリート用データの取得
	 * @param itemCd 納入先コードまたは納入先名称
	 * @return List<PurchaseDeliveryForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<PurchaseDeliveryForAutoComplete> getSearchListDetail(
			final String itemCd) throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCd);
		List<PurchaseDeliveryForAutoComplete> list = purchaseDeliveryForAutoCompleteDao
				.getSearchListDetail(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 納入先マスタ操作DAOを設定します。
	 * @param purchaseDeliveryForAutoCompleteDao 納入先マスタ操作DAO
	 */
	public void setPurchaseDeliveryForAutoCompleteDao(
			final PurchaseDeliveryForAutoCompleteDao purchaseDeliveryForAutoCompleteDao) {
		this.purchaseDeliveryForAutoCompleteDao = purchaseDeliveryForAutoCompleteDao;
	}

}
