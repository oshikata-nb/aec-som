/*
 * Created on 2009/02/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.autocomplete.delivery;

import java.util.List;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.autocomplete.delivery.DeliveryForAutoComplete;
import com.asahikaseieng.dao.nonentity.autocomplete.delivery.DeliveryForAutoCompleteDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.utils.AecTextUtils;

/**
 * 納入先マスタのAuto Complete用ロジック
 * @author tosco
 */
public class DeliveryForAutoCompleteLogicImpl implements
		DeliveryForAutoCompleteLogic {
	/** 納入先マスタ操作DAO */
	private DeliveryForAutoCompleteDao deliveryForAutoCompleteDao;

	/**
	 * コンストラクタ
	 */
	public DeliveryForAutoCompleteLogicImpl() {
	}

	// 納入先コードまたは、納入先名称で検索--------------------------------
	/**
	 * 納入先マスタのオートコンプリート用データの取得
	 * @param deliveryCd 納入先ココードまたは納入先コ名称
	 * @return List<DeliveryForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<DeliveryForAutoComplete> getSearchList(final String deliveryCd)
			throws NoDataException {

		String val = AecTextUtils.likeFilter(deliveryCd);
		List<DeliveryForAutoComplete> list = deliveryForAutoCompleteDao
				.getSearchList(val, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}
	
	// fromの区分を条件に設定した上で、納入先コードまたは、納入先名称で検索--------------------------------
	/**
	 * 納入先マスタのオートコンプリート用データの取得
	 * @param deliveryCd 納入先ココードまたは納入先コ名称
	 * @param deliveryDivision 区分
	 * @return List<DeliveryForAutoComplete>
	 * @throws NoDataException 検索結果が存在しない場合
	 */
	public List<DeliveryForAutoComplete> getDeliverySearchList(final String deliveryCd,final String deliveryDivision)
			throws NoDataException {

		String val = AecTextUtils.likeFilter(deliveryCd);
		List<DeliveryForAutoComplete> list = deliveryForAutoCompleteDao
				.getDeliverySearchList(val, deliveryDivision, Constants.AUTOCOMPLETTE_ROW_LIMIT);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}
	
	// setter---------------------------------------------------------------
	/**
	 * 納入先マスタ操作DAOを設定します。
	 * @param deliveryForAutoCompleteDao 納入先マスタ操作操作DAO
	 */
	public void setDeliveryForAutoCompleteDao(
			final DeliveryForAutoCompleteDao deliveryForAutoCompleteDao) {
		this.deliveryForAutoCompleteDao = deliveryForAutoCompleteDao;
	}

}
