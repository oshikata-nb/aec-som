/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.purchase;

import java.util.List;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.dao.nonentity.purchase.PurchaseList;
import com.asahikaseieng.dao.nonentity.purchase.PurchaseListDao;
import com.asahikaseieng.dao.nonentity.purchase.PurchasePagerCondition;
import com.asahikaseieng.dao.nonentity.purchaseforreport.PurchaseListConditionForReport;
import com.asahikaseieng.dao.nonentity.purchaseforreport.PurchaseListForReport;
import com.asahikaseieng.dao.nonentity.purchaseforreport.PurchaseListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 発注一覧 ロジック実装クラス
 * @author tosco
 */
public class PurchaseListLogicImpl implements PurchaseListLogic {

	private static final String UNIT_DIVISION_KG = "1";

	/** 発注一覧画面用Dao */
	private PurchaseListDao purchaseListDao;

	private PurchaseListForReportDao purchaseListForReportDao;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/**
	 * コンストラクタ
	 */
	public PurchaseListLogicImpl() {
	}

	/**
	 * 一覧検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<PurchaseList>発注一覧 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<PurchaseList> getSearchList(
			final PurchasePagerCondition condition) throws NoDataException {
		checkParams(condition);
		// 発注一覧の検索を行い、listに格納する。
		List<PurchaseList> list = purchaseListDao.getSearchList(condition);

		// 検索結果がない場合
		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 一覧画面フォーマット
		for (PurchaseList bean : list) {
			bean.setStrOrderQuantity(checker.format(bean.getUnitDiv(), "SI",
				bean.getVenderCd(), bean.getOrderQuantity())); // 発注数量

			bean.setStrOrderConvertQuantity(checker.format(UNIT_DIVISION_KG,
				"SI", bean.getVenderCd(), bean.getOrderConvertQuantity())); // 重量
			bean.setStrCheckQuantity(checker.format(bean.getUnitDiv(), "SI",
				bean.getVenderCd(), bean.getCheckQuantity())); // 不足量
		}
		return list;
	}

	/**
	 * 帳票Excel
	 * 
	 * @param condition 検索条件
	 * @return List<PurchaseListForReport>帳票Excel 検索結果リスト
	 */
	public List<PurchaseListForReport> getReportList(
			final PurchaseListConditionForReport condition) {
		// 発注一覧の検索を行い、listに格納する。
		List<PurchaseListForReport> list = purchaseListForReportDao
				.getReportList(condition);

		// 検索結果がない場合
		if (list.isEmpty()) {
			return null;
		}
		return list;

	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final PurchasePagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 発注一覧画面用Daoを設定します。
	 * 
	 * @param purchaseListDao 発注一覧画面用Dao
	 */
	public void setPurchaseListDao(final PurchaseListDao purchaseListDao) {
		this.purchaseListDao = purchaseListDao;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

	/**
	 * 帳票一覧用DAOを設定します。
	 * @param purchaseListForReportDao purchaseListForReportDao
	 */
	public void setPurchaseListForReportDao(
			final PurchaseListForReportDao purchaseListForReportDao) {
		this.purchaseListForReportDao = purchaseListForReportDao;
	}

}
