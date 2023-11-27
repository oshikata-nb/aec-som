/*
 * Created on 2009/01/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.item;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.master.numberchkdisit.NumberChkDisitDetail;
import com.asahikaseieng.dao.nonentity.master.search.item.ItemSearchList;
import com.asahikaseieng.dao.nonentity.master.search.item.ItemSearchListDao;
import com.asahikaseieng.dao.nonentity.master.search.item.ItemSearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 品目マスタ検索(ポップアップ)ロジック 実装クラス.
 * @author tosco
 */
public class ItemSearchLogicImpl implements ItemSearchLogic {

	/** 品目マスタ検索(ポップアップ)Dao */
	private ItemSearchListDao itemSearchListDao;

	/** チェックユーティリティ */
	private CheckDigitUtilsLogic checker;

	/**
	 * コンストラクタ.
	 */
	public ItemSearchLogicImpl() {
	}

	/**
	 * {@inheritDoc}
	 */
	public List<ItemSearchList> getList(final ItemSearchListPagerCondition condition)
			throws NoDataException {
		checkParams(condition);
		List<ItemSearchList> list = itemSearchListDao.getSearchList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}
		// 数値情報取得
		for (ItemSearchList bean : list) {
			NumberChkDisitDetail checkDetail = checker.getCheckDigit(bean.getUnitDiv(), null, null);
			// 少数点桁数
			bean.setSmallnumLength(getBigDecimalString(checkDetail.getSmallnumLength()));
			// 端数区分
			bean.setRoundDivision(getBigDecimalString(checkDetail.getRoundDivision()));
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final ItemSearchListPagerCondition condition) {
		if (null == condition) {
			throw new IllegalArgumentException("null == condition");
		}
	}

	/**
	 * BigDecimalの文字列表現を取得する
	 * BigDecimal=null時はnullを返す
	 * @param dec BigDecimal
	 * @return BigDecimalの文字列表現
	 */
	protected String getBigDecimalString(final BigDecimal dec) {
		String res = null;
		if (dec != null) {
			res = dec.toString();
		}
		return res;
	}

	/* -------------------- setter -------------------- */

	/**
	 * 品目マスタ検索(ポップアップ)Daoを設定します。
	 * @param itemSearchListDao 品目マスタ検索(ポップアップ)Dao
	 */
	public void setItemDao(final ItemSearchListDao itemSearchListDao) {
		this.itemSearchListDao = itemSearchListDao;
	}

	/**
	 * チェックユーティリティを設定します。
	 * @param checker チェックユーティリティ
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

}
