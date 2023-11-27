/*
 * Created on 2009/02/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.delivery.buyingapproval;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.delivery.buyingapproval.BuyingApprovalDeliveryForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.delivery.buyingapproval.BuyingApprovalDeliveryForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 納入先マスタのAuto Complete用ロジック
 * @author tosco
 */
public class BuyingApprovalDeliveryForAutoCompleteLogicImpl implements
		BuyingApprovalDeliveryForAutoCompleteLogic {
	/** 納入先マスタ操作DAO */
	private BuyingApprovalDeliveryForAutoCompleteDao buyingApprovalDeliveryForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public BuyingApprovalDeliveryForAutoCompleteLogicImpl() {
	}

	// 納入先コードまたは、納入先名称で検索--------------------------------
	/**
	 * 検索画面用納入先マスタのオートコンプリート用データの取得
	 * @param itemCd 納入先コードまたは納入先名称
	 * @return List<BuyingApprovalDeliveryForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<BuyingApprovalDeliveryForAutoComplete> getSearchList(
			final String itemCd) throws NoDataException {
		String val = AecTextUtils.likeFilter(itemCd);
		List<BuyingApprovalDeliveryForAutoComplete> list = buyingApprovalDeliveryForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	// setter---------------------------------------------------------------
	/**
	 * 納入先マスタ操作DAOを設定します。
	 * @param buyingApprovalDeliveryForAutoCompleteDao 納入先マスタ操作DAO
	 */
	public void setBuyingApprovalDeliveryForAutoCompleteDao(
			final BuyingApprovalDeliveryForAutoCompleteDao buyingApprovalDeliveryForAutoCompleteDao) {
		this.buyingApprovalDeliveryForAutoCompleteDao = buyingApprovalDeliveryForAutoCompleteDao;
	}

}
