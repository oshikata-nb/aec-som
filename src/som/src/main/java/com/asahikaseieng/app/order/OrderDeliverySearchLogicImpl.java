/*
 * Created on 2009/04/13
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order;

import java.util.List;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.orderdelivery.OrderDeliverySearchList;
import com.asahikaseieng.dao.nonentity.orderdelivery.OrderDeliverySearchListDao;
import com.asahikaseieng.dao.nonentity.orderdelivery.OrderDeliverySearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 納入先検索(ポップアップ)ロジック 実装クラス.
 * @author tosco
 */
public class OrderDeliverySearchLogicImpl implements OrderDeliverySearchLogic {

	/** 納入先検索(ポップアップ)Dao */
	private OrderDeliverySearchListDao orderDeliverySearchDao;

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/**
	 * コンストラクタ.
	 */
	public OrderDeliverySearchLogicImpl() {
	}

	/**
	 * 納入先検索処理を行う.
	 * @param condition 検索条件
	 * @return List<OrderDeliverySearchList> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	public List<OrderDeliverySearchList> getList(
			final OrderDeliverySearchListPagerCondition condition)
			throws NoDataException {

		checkParams(condition);

		List<OrderDeliverySearchList> list = orderDeliverySearchDao
				.getSearchList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		for (OrderDeliverySearchList bean : list) {
			if (bean.getLeadTime() != null && !bean.getLeadTime().equals("")) {
				// リードタイムのフォーマット
				bean.setStrLeadTime(checkDigitUtilsLogic.format("SONOTA", bean
						.getLeadTime()));
			}
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(
			final OrderDeliverySearchListPagerCondition condition) {
		if (null == condition) {
			throw new IllegalArgumentException("null == condition");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 納入先検索(ポップアップ)Daoを設定します。
	 * @param orderDeliverySearchDao 納入先検索(ポップアップ)Dao
	 */
	public void setOrderDeliverySearchDao(
			final OrderDeliverySearchListDao orderDeliverySearchDao) {
		this.orderDeliverySearchDao = orderDeliverySearchDao;
	}

	/**
	 * 数値桁数チェック用ロジッククラスを設定します。
	 * @param checkDigitUtilsLogic 数値桁数チェック用ロジッククラス
	 */
	public void setCheckDigitUtilsLogic(
			final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}
}
