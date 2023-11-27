/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.accept;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.entity.purchasesubcontract.PurchaseStatus;
import com.asahikaseieng.dao.nonentity.accept.AcceptList;
import com.asahikaseieng.dao.nonentity.accept.AcceptListDao;
import com.asahikaseieng.dao.nonentity.accept.AcceptListPagerCondition;
import com.asahikaseieng.dao.nonentity.acceptforreport.AcceptListConditionForReport;
import com.asahikaseieng.dao.nonentity.acceptforreport.AcceptListForReport;
import com.asahikaseieng.dao.nonentity.acceptforreport.AcceptListForReportDao;
import com.asahikaseieng.dao.nonentity.comboboxes.accept.AcceptStockingDivisionComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.accept.AcceptStockingDivisionComboboxesDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受入・仕入検索画面 ロジック実装クラス
 * @author tosco
 */
public class AcceptListLogicImpl implements AcceptListLogic {

	/** オーダー区分 1:原材料 */
	public static final BigDecimal ORDER_DIVISION1 = new BigDecimal(1);

	/** オーダー区分 3:外注製品(非直送) */
	public static final BigDecimal ORDER_DIVISION3 = new BigDecimal(3);

	/** オーダー区分 6:仕入在庫品 */
	public static final BigDecimal ORDER_DIVISION6 = new BigDecimal(6);

	/** 入荷ロット番号 オーダー区分=1の場合 */
	public static final String LOT_NO_PREFIX1 = "N";

	/** 入荷ロット番号 オーダー区分=3or6の場合 */
	public static final String LOT_NO_PREFIX2 = "P";

	/** 荷主区分 1:花王 */
	public static final BigDecimal SHIPPER_DIVISION_KAO = new BigDecimal(1);

	/** 自社／花王区分 1:自社 */
	public static final String JISHAFLAG_JISYA = "1";

	/** 自社／花王区分 2:花王 */
	public static final String JISHAFLAG_KAO = "2";

	/** 区分 その他 */
	public static final String UNIT_DIVISION_KG = "1";

	/** 取引先区分 仕入先 */
	public static final String VENDER_DIV_SI = "SI";

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	/** 受入・仕入検索画面用Dao */
	private AcceptListDao acceptListDao;

	/** 帳票Excel用Dao */
	private AcceptListForReportDao acceptListForReportDao;

	/** 仕入区分コンボボックス用DAO */
	private AcceptStockingDivisionComboboxesDao acceptStockingDivisionComboboxesDao;

	/**
	 * コンストラクタ.
	 */
	public AcceptListLogicImpl() {
	}

	/**
	 * 仕入区分コンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createAcceptStockingDivisionCombobox() {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 0:すべてを先頭に設定
		ComboBoxItems item = new ComboBoxItems();
		item.setValues("0");
		item.setLabales("0:すべて");
		res.add(item);

		// 仕入区分検索
		List<AcceptStockingDivisionComboboxes> list = acceptStockingDivisionComboboxesDao
				.findStockingDivision();
		if (list != null) {
			for (AcceptStockingDivisionComboboxes bean : list) {
				ComboBoxItems combo = new ComboBoxItems();
				combo.setValues(bean.getCategoryDivision().toString());
				combo.setLabales(bean.getCategoryDivision().toString() + ":"
						+ bean.getCategoryName().toString());
				res.add(combo);
			}
		}

		return res;
	}

	/**
	 * 購買オーダーテーブル検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<AcceptList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<AcceptList> getSearchList(
			final AcceptListPagerCondition condition) throws NoDataException {
		String strOrderQuantity = null;
		String strOrderConvertQuantity = null;
		String strAcceptQuantity = null;

		checkParams(condition);
		List<AcceptList> list = acceptListDao.getSearchList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		// 数値フォーマット
		for (AcceptList bean : list) {
			// 数量
			strOrderQuantity = checkDigitUtilsLogic.format(bean.getUnitDiv(),
				VENDER_DIV_SI, bean.getVenderCd(), bean.getOrderQuantity());
			bean.setStrOrderQuantity(strOrderQuantity);

			// 受入数量
			strAcceptQuantity = checkDigitUtilsLogic.format(bean.getUnitDiv(),
				VENDER_DIV_SI, bean.getVenderCd(), bean.getAcceptQuantity());
			bean.setStrAcceptQuantity(strAcceptQuantity);

			// 受入登録済みの場合
			if (PurchaseStatus.ACCEPTED.compareTo(bean.getStatus()) == 0) {
				// 受入重量
				strOrderConvertQuantity = checkDigitUtilsLogic.format(
					UNIT_DIVISION_KG, VENDER_DIV_SI, bean.getVenderCd(), bean
							.getAcceptConvertQuantity());
			} else {

				// 発注重量
				strOrderConvertQuantity = checkDigitUtilsLogic.format(
					UNIT_DIVISION_KG, VENDER_DIV_SI, bean.getVenderCd(), bean
							.getOrderConvertQuantity());
			}
			bean.setStrOrderConvertQuantity(strOrderConvertQuantity);
		}
		return list;
	}

	/**
	 * 帳票Excel用データ検索処理
	 * 
	 * @param condition 検索条件
	 * @return List<AcceptListForReport> 検索結果リスト
	 */
	public List<AcceptListForReport> getReportList(
			final AcceptListConditionForReport condition) {

		List<AcceptListForReport> list = acceptListForReportDao
				.getReportList(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final AcceptListPagerCondition condition) {

		if (condition == null) {
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */

	/**
	 * 数値桁数チェック用ロジッククラスを設定します。
	 * @param checkDigitUtilsLogic 数値桁数チェック用ロジッククラス
	 */
	public void setCheckDigitUtilsLogic(
			final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

	/**
	 * 受入・仕入検索画面用Daoを設定します。
	 * @param acceptListDao 受入・仕入検索画面用Dao
	 */
	public void setAcceptListDao(final AcceptListDao acceptListDao) {
		this.acceptListDao = acceptListDao;
	}

	/**
	 * 仕入区分コンボボックス用DAOを設定します。
	 * @param acceptStockingDivisionComboboxesDao 仕入区分コンボボックス用DAO
	 */
	public void setAcceptStockingDivisionComboboxesDao(
			final AcceptStockingDivisionComboboxesDao acceptStockingDivisionComboboxesDao) {
		this.acceptStockingDivisionComboboxesDao = acceptStockingDivisionComboboxesDao;
	}

	/**
	 * 帳票Excel用DAOを設定します。
	 * @param acceptListForReportDao 帳票Excel用DAO
	 */
	public void setAcceptListForReportDao(
			final AcceptListForReportDao acceptListForReportDao) {
		this.acceptListForReportDao = acceptListForReportDao;
	}

}
