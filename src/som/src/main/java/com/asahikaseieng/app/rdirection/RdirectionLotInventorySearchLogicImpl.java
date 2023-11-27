/*
 * Created on 2009/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.convinventory.ConvInventoryLogic;
import com.asahikaseieng.app.convinventory.ConvInventoryResult;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionLotInventorySearchList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionLotInventorySearchListDao;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionLotInventorySearchListPagerCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造実績－ロット検索ポップアップ画面ロジック 実装クラス.
 * @author tosco
 */
public class RdirectionLotInventorySearchLogicImpl implements
		RdirectionLotInventorySearchLogic {

	/** 製造実績－ロット検索ポップアップ画面Dao */
	private RdirectionLotInventorySearchListDao rdirectionLotInventorySearchListDao;

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/** 在庫数量換算ロジッククラス */
	private ConvInventoryLogic convInventoryLogic;

	/**
	 * コンストラクタ.
	 */
	public RdirectionLotInventorySearchLogicImpl() {
	}

	/**
	 * ロット検索一覧検索処理
	 * @param condition 検索条件
	 * @return List<RdirectionLotInventorySearchList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<RdirectionLotInventorySearchList> getSearchList(
			final RdirectionLotInventorySearchListPagerCondition condition)
			throws NoDataException {
		checkParams(condition);
		List<RdirectionLotInventorySearchList> list = rdirectionLotInventorySearchListDao
				.getList(condition);
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 数値フォーマット
		for (RdirectionLotInventorySearchList bean : list) {

			BigDecimal inventoryQty = bean.getInventoryQty();

			// 在庫数量フォーマット編集
			String strQty = checkDigitUtilsLogic.format(
				RdirectionConst.UNIT_DIV_HAIGO, inventoryQty);
			bean.setStrInventoryQty(strQty);

			// 荷姿、端数算出
			ConvInventoryResult result = convInventoryLogic.inventoryToPack(
				bean.getItemCd(), inventoryQty);
			if (BigDecimal.ONE.compareTo(result.getCode()) == 0) {
				bean.setStrInoutQty(null);
				bean.setStrFraction(null);
			} else {
				// 荷姿数
				bean.setStrInoutQty(checkDigitUtilsLogic.format(bean
						.getUnitDiv(), result.getPackQty()));
				// 端数(重量)
				bean.setStrFraction(checkDigitUtilsLogic.format(
					RdirectionConst.UNIT_DIV_HAIGO, result.getFractionWeight()));
			}
		}
		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(
			final RdirectionLotInventorySearchListPagerCondition condition) {
		if (null == condition) {
			throw new IllegalArgumentException("null == condition");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 製造実績－ロット検索ポップアップ画面Dao設定.
	 * @param rdirectionLotInventorySearchListDao 製造実績－ロット検索ポップアップ画面Dao
	 */
	public void setRdirectionLotInventorySearchListDao(
			final RdirectionLotInventorySearchListDao rdirectionLotInventorySearchListDao) {
		this.rdirectionLotInventorySearchListDao = rdirectionLotInventorySearchListDao;
	}

	/**
	 * 数値桁数チェック用ロジッククラス設定.
	 * @param checkDigitUtilsLogic 数値桁数チェック用ロジッククラス
	 */
	public void setCheckDigitUtilsLogic(
			final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

	/**
	 * 在庫数量換算ロジッククラスを設定します。
	 * @param convInventoryLogic 在庫数量換算ロジッククラス
	 */
	public void setConvInventoryLogic(
			final ConvInventoryLogic convInventoryLogic) {
		this.convInventoryLogic = convInventoryLogic;
	}
}
