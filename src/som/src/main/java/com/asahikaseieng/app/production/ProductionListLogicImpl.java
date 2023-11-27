/*
 * Created on 2009/04/06
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.production;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.app.checkdigit.CheckDigitUtilsLogic;
import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.production.ProductionLineForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.production.ProductionLineForComboboxesDao;
import com.asahikaseieng.dao.nonentity.production.ProductionList;
import com.asahikaseieng.dao.nonentity.production.ProductionListDao;
import com.asahikaseieng.dao.nonentity.production.ProductionPagerCondition;
import com.asahikaseieng.dao.nonentity.productionforreport.ProductionDetailListForReport;
import com.asahikaseieng.dao.nonentity.productionforreport.ProductionDetailListForReportDao;
import com.asahikaseieng.dao.nonentity.productionforreport.ProductionHeaderListForReport;
import com.asahikaseieng.dao.nonentity.productionforreport.ProductionHeaderListForReportDao;
import com.asahikaseieng.dao.nonentity.productionforreport.ProductionListConditionForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 生産計画入力一覧画面 ロジック実装クラス
 * @author tosco
 */
public class ProductionListLogicImpl implements ProductionListLogic {

	/** 生産計画入力一覧画面用Dao */
	private ProductionListDao productionListDao;

	/** 生産ライン操作用DAO */
	private ProductionLineForComboboxesDao productionLineForComboboxesDao;

	/** 数値桁数チェック用ロジッククラス */
	private CheckDigitUtilsLogic checkDigitUtilsLogic;

	private ProductionHeaderListForReportDao productionHeaderListForReportDao;

	private ProductionDetailListForReportDao productionDetailListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public ProductionListLogicImpl() {
	}

	/**
	 * 一覧検索処理
	 * @param condition 検索条件
	 * @return List<ProductionList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<ProductionList> getSearchList(
			final ProductionPagerCondition condition) throws NoDataException {
		checkParams(condition);

		// 生産計画入力一覧の検索を行い、listに格納する。
		List<ProductionList> list = productionListDao.getSearchList(condition);

		// 検索結果がない場合
		if (list.isEmpty()) {
			// NoDataExceptionをthrowする
			throw new NoDataException();
		}

		for (ProductionList bean : list) {
			if (bean.getSumOrderQty() != null) {
				bean
						.setStrSumOrderQty(checkDigitUtilsLogic.format(bean
								.getUnitOfOperationManagement(), bean
								.getSumOrderQty()));
			}
		}

		return list;
	}

	/**
	 * 帳票Excelヘッダ
	 * @param condition 検索条件
	 * @return List<ProductionList> 検索結果リスト
	 */
	public List<ProductionHeaderListForReport> getHeaderList(
			final ProductionListConditionForReport condition) {

		// 生産計画入力一覧の検索を行い、listに格納する。
		List<ProductionHeaderListForReport> list = productionHeaderListForReportDao
				.getHeaderListForReport(condition);

		// 検索結果がない場合
		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * 帳票Excel詳細
	 * @param condition 検索条件
	 * @return List<ProductionList> 検索結果リスト
	 */
	public List<ProductionDetailListForReport> getDetailList(
			final ProductionListConditionForReport condition) {

		// 生産計画入力一覧の検索を行い、listに格納する。
		List<ProductionDetailListForReport> list = productionDetailListForReportDao
				.getDetailListForReport(condition);

		// 検索結果がない場合
		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * 生産ラインを全件取得する
	 * @return List<ProductionLineForComboboxes>
	 */
	public List<ProductionLineForComboboxes> getAllLines() {
		return productionLineForComboboxesDao.findAll();
	}

	/**
	 * 生産ラインコンボボックス作成
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	public List<ComboBoxItems> createLineCombobox(final boolean zero) {
		List<ComboBoxItems> res = new ArrayList<ComboBoxItems>();

		// 0:すべてを先頭に設定
		if (zero) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues("0");
			item.setLabales("すべて");
			res.add(item);
		}

		// 生産ライン検索
		List<ProductionLineForComboboxes> lineList = getAllLines();
		for (ProductionLineForComboboxes bean : lineList) {
			ComboBoxItems item = new ComboBoxItems();
			item.setValues(bean.getProductionLine());
			item.setLabales(bean.getProductionLineName());
			res.add(item);
		}
		return res;
	}

	/**
	 * パラメータチェック
	 * @param condition 検索条件
	 */
	private void checkParams(final ProductionPagerCondition condition) {
		// conditionがnullであれば
		if (condition == null) {
			// IllegalArgumentExceptionをthrowする
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */
	/**
	 * 生産計画入力一覧画面用Daoを設定します。
	 * @param productionListDao 生産計画入力一覧画面用Dao
	 */
	public void setProductionListDao(final ProductionListDao productionListDao) {
		this.productionListDao = productionListDao;
	}

	/**
	 * 生産ライン操作用DAOを設定します。
	 * @param productionLineForComboboxesDao 生産ライン操作用DAO
	 */
	public void setProductionLineForComboboxesDao(
			final ProductionLineForComboboxesDao productionLineForComboboxesDao) {
		this.productionLineForComboboxesDao = productionLineForComboboxesDao;
	}

	/**
	 * 数値桁数チェック用ロジッククラスを設定します。
	 * @param checkDigitUtilsLogic 数値桁数チェック用ロジッククラス
	 */
	public void setCheckDigitUtilsLogic(
			final CheckDigitUtilsLogic checkDigitUtilsLogic) {
		this.checkDigitUtilsLogic = checkDigitUtilsLogic;
	}

	/**
	 * productionDetailListForReportDao設定します。
	 * @param productionDetailListForReportDao productionDetailListForReportDao
	 */
	public void setProductionDetailListForReportDao(
			final ProductionDetailListForReportDao productionDetailListForReportDao) {
		this.productionDetailListForReportDao = productionDetailListForReportDao;
	}

	/**
	 * productionHeaderListForReportDaoを設定します。
	 * @param productionHeaderListForReportDao productionHeaderListForReportDao
	 */
	public void setProductionHeaderListForReportDao(
			final ProductionHeaderListForReportDao productionHeaderListForReportDao) {
		this.productionHeaderListForReportDao = productionHeaderListForReportDao;
	}

}
