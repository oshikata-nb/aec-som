/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.itemqueue;

import java.util.List;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.master.search.itemqueue.ItemQueueSearchList;
import com.asahikaseieng.dao.nonentity.master.search.itemqueue.ItemQueueSearchListDao;
import com.asahikaseieng.dao.nonentity.master.search.itemqueue.ItemQueueSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目マスタキュー検索(ポップアップ)ロジック 実装クラス.
 * @author tosco
 */
public class ItemQueueSearchLogicImpl implements ItemQueueSearchLogic {

	/** 品目マスタキュー検索(ポップアップ)Dao */
	private ItemQueueSearchListDao itemQueueSearchListDao;

	/** チェックユーティリティ */
	private CheckDigitUtilsLogic checker;

	/**
	 * コンストラクタ.
	 */
	public ItemQueueSearchLogicImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemQueueSearchList> getList(
			final ItemQueueSearchListPagerCondition condition)
			throws NoDataException {
		checkParams(condition);
		List<ItemQueueSearchList> list = itemQueueSearchListDao
				.getSearchList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		// 数値情報取得
		for (ItemQueueSearchList bean : list) {
			NumberChkDisitDetail checkDetail = checker.getCheckDigit(bean
					.getUnitDiv(), null, null);

			// 少数点桁数
			if (checkDetail.getSmallnumLength() == null) {
				bean.setSmallnumLength("0");
			} else {
				bean.setSmallnumLength(checkDetail.getSmallnumLength()
						.toString());
			}

			// 端数区分
			if (checkDetail.getRoundDivision() == null) {
				bean.setRoundDivision("0");
			} else {
				bean
						.setRoundDivision(checkDetail.getRoundDivision()
								.toString());
			}
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final ItemQueueSearchListPagerCondition condition) {
		if (null == condition) {
			throw new IllegalArgumentException("null == condition");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 品目マスタキュー検索(ポップアップ)Daoを設定します。
	 * @param itemQueueSearchListDao 品目マスタキュー検索(ポップアップ)Dao
	 */
	public void setItemDao(final ItemQueueSearchListDao itemQueueSearchListDao) {
		this.itemQueueSearchListDao = itemQueueSearchListDao;
	}

	/**
	 * チェックユーティリティを設定します。
	 * @param checker チェックユーティリティ
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

}
