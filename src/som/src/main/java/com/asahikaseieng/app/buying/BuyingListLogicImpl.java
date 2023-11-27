/*
 * Created on 2009/01/13
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.buying;

import java.util.ArrayList;
import java.util.List;

import org.seasar.dao.NotSingleRowUpdatedRuntimeException;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.buying.BuyingList;
import com.asahikaseieng.dao.nonentity.buying.BuyingListDao;
import com.asahikaseieng.dao.nonentity.buying.BuyingPagerCondition;
import com.asahikaseieng.dao.nonentity.buyingforreport.BuyingListConditionForReport;
import com.asahikaseieng.dao.nonentity.buyingforreport.BuyingListForReport;
import com.asahikaseieng.dao.nonentity.buyingforreport.BuyingListForReportDao;
import com.asahikaseieng.dao.nonentity.comboboxes.buying.BuyingStockingDivisionComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.buying.BuyingStockingDivisionComboboxesDao;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.exception.OptimisticLockRuntimeException;

/**
 * 仕入詳細 ロジック実装クラス
 * @author tosco
 */
public class BuyingListLogicImpl implements BuyingListLogic {

	/** 仕入一覧画面用Dao */
	private BuyingListDao buyingListDao;

	/** 仕入区分コンボボックス用DAO */
	private BuyingStockingDivisionComboboxesDao buyingStockingDivisionComboboxesDao;

	/** 帳票Excel用用DAO */
	private BuyingListForReportDao buyingListForReportDao;

	/** 数値桁数チェック ロジッククラス */
	private CheckDigitUtilsLogic checker;

	/**
	 * コンストラクタ.
	 */
	public BuyingListLogicImpl() {
	}

	/**
	 * 一覧検索処理
	 * @param condition 検索条件
	 * @return List<BuyingList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<BuyingList> getSearchList(final BuyingPagerCondition condition)
			throws NoDataException {
		checkParams(condition);

		// 仕入一覧の検索を行い、listに格納する。
		List<BuyingList> list = buyingListDao.getSearchList(condition);

		for (BuyingList bean : list) {
			// 購入数量
			bean.setStrStockingQuantity(checker.format(bean
					.getUnitOfOperationManagement(), "SI", bean.getVenderCd(),
				bean.getStockingQuantity()));

			// 単価
			bean.setStrHousingUnitprice(checker.format("SITANKA", "SI", bean
					.getVenderCd(), bean.getHousingUnitprice()));

			// 金額
			bean.setStrStockingAmount(checker.format("SIKINGAKU", "SI", bean
					.getVenderCd(), bean.getStockingAmount()));
		}

		// 検索結果がない場合
		if (list.isEmpty()) {
			// NoDataExceptionをthrowする
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 一覧検索処理
	 * @param condition 検索条件
	 * @return List<BuyingList> 検索結果リスト
	 */
	public List<BuyingListForReport> getReportList(
			final BuyingListConditionForReport condition) {
		// 仕入一覧の検索を行い、listに格納する。
		List<BuyingListForReport> list = buyingListForReportDao
				.getReportList(condition);

		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	/**
	 * 仕入区分コンボボックスを取得する
	 * @return List<BuyingStockingDivisionComboboxes>
	 */
	public List<BuyingStockingDivisionComboboxes> getBuyingStockingDivisionComboboxes() {
		// 仕入れ区分コンボボックスを取得(検索用のため、取消の区分はなし)
		return buyingStockingDivisionComboboxesDao.findStockingDivision(false);
	}

	/**
	 * 仕入区分コンボボックス作成
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createBuyingStockingDivisionCombobox(
			final boolean zero) {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 0:すべてを先頭に設定
		if (zero) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues("0");
			item.setLabales("すべて");
			res.add(item);
		}
		// 仕入区分検索
		List<BuyingStockingDivisionComboboxes> list = getBuyingStockingDivisionComboboxes();
		if (list != null) {
			for (BuyingStockingDivisionComboboxes bean : list) {
				ComboBoxItems item = new ComboBoxItems();
				item.setValues(bean.getCategoryDivision().toString());
				item.setLabales(bean.getCategoryName().toString());
				res.add(item);
			}
		}

		return res;
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final BuyingPagerCondition condition) {
		// conditionがnullであれば
		if (condition == null) {
			// IllegalArgumentExceptionをthrowする
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/**
	 * 伝票発行フラグ更新
	 * @param tantoCd tantoCd
	 * @param buyingNo buyingNo
	 */
	public void updateBuying(final String tantoCd,
			final ArrayList<String> buyingNo) {
		try {
			for (int i = 0; i < buyingNo.size(); i++) {

				String slipNo = buyingNo.get(i);
				/* 更新処理 */
				buyingListDao.updateBuying(tantoCd, slipNo);
			}
		} catch (NotSingleRowUpdatedRuntimeException e) {
			/* 排他エラー */
			throw new OptimisticLockRuntimeException();
		}
	}

	/* -------------------- setter -------------------- */
	/**
	 * 仕入一覧画面用Daoを設定します。
	 * @param buyingListDao 仕入一覧画面用Dao
	 */
	public void setBuyingListDao(final BuyingListDao buyingListDao) {
		this.buyingListDao = buyingListDao;
	}

	/**
	 * 仕入区分コンボボックス用DAOを設定します。
	 * @param buyingStockingDivisionComboboxesDao 仕入区分コンボボックス用DAO
	 */
	public void setBuyingStockingDivisionComboboxesDao(
			final BuyingStockingDivisionComboboxesDao buyingStockingDivisionComboboxesDao) {
		this.buyingStockingDivisionComboboxesDao = buyingStockingDivisionComboboxesDao;
	}

	/**
	 * 数値桁数チェック ロジッククラスを設定します。
	 * @param checker checker
	 */
	public void setChecker(final CheckDigitUtilsLogic checker) {
		this.checker = checker;
	}

	/**
	 * buyingListForReportDao を設定します。
	 * @param buyingListForReportDao buyingListForReportDao
	 */
	public void setBuyingListForReportDao(
			final BuyingListForReportDao buyingListForReportDao) {
		this.buyingListForReportDao = buyingListForReportDao;
	}
}
