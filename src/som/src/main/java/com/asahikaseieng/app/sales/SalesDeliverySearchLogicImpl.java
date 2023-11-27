/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.sales;

import java.util.List;

import com.asahikaseieng.dao.nonentity.sales.SalesDeliverySearchList;
import com.asahikaseieng.dao.nonentity.sales.SalesDeliverySearchListDao;
import com.asahikaseieng.dao.nonentity.sales.SalesDeliverySearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 納入先検索(ポップアップ)ロジック 実装クラス.
 * @author tosco
 */
public class SalesDeliverySearchLogicImpl implements SalesDeliverySearchLogic {

	/** 納入先検索(ポップアップ)Dao */
	private SalesDeliverySearchListDao salesDeliverySearchDao;

	/**
	 * コンストラクタ.
	 */
	public SalesDeliverySearchLogicImpl() {
	}

	/**
	 * 納入先検索処理を行う.
	 * @param condition 検索条件
	 * @return List<ShippingOrderSearch> 検索結果リスト
	 * @throws NoDataException NoDataException
	 */
	public List<SalesDeliverySearchList> getList(final SalesDeliverySearchListPagerCondition condition)
		throws NoDataException {

		checkParams(condition);

		List<SalesDeliverySearchList> list = salesDeliverySearchDao.getSearchList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final SalesDeliverySearchListPagerCondition condition) {
		if (null == condition) {
			throw new IllegalArgumentException("null == condition");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 納入先検索(ポップアップ)Daoを設定します。
	 * @param salesDeliverySearchDao 納入先検索(ポップアップ)Dao
	 */
	public void setSalesDeliverySearchDao(final SalesDeliverySearchListDao salesDeliverySearchDao) {
		this.salesDeliverySearchDao = salesDeliverySearchDao;
	}

}
