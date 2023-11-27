/*
 * Created on 2009/04/06
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.productionplan;

import java.util.ArrayList;
import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.production.ProductionLineForComboboxes;
import com.asahikaseieng.dao.nonentity.comboboxes.production.ProductionLineForComboboxesDao;
import com.asahikaseieng.dao.nonentity.repproductionplanforreport.RepProductionPlanCalendar;
import com.asahikaseieng.dao.nonentity.repproductionplanforreport.RepProductionPlanCalendarDao;
import com.asahikaseieng.dao.nonentity.repproductionplanforreport.RepProductionPlanForReport;
import com.asahikaseieng.dao.nonentity.repproductionplanforreport.RepProductionPlanForReportCondition;
import com.asahikaseieng.dao.nonentity.repproductionplanforreport.RepProductionPlanForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 生産計画入力一覧画面 ロジック実装クラス
 * @author tosco
 */
public class ProductionPlanLogicImpl implements ProductionPlanLogic {

	/** 生産計画入力一覧画面用Dao */
	private RepProductionPlanForReportDao repProductionPlanForReportDao;

	/** 生産ライン操作用DAO */
	private ProductionLineForComboboxesDao productionLineForComboboxesDao;

	/** 帳票カレンダー取得用DAO */
	private RepProductionPlanCalendarDao repProductionPlanCalendarDao;

	/**
	 * コンストラクタ.
	 */
	public ProductionPlanLogicImpl() {
	}

	/**
	 * 月間生産計画表表示データ取得(全荷主)
	 * @param condition 検索条件
	 * @return List<RepProductionPlanForReport> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<RepProductionPlanForReport> getReportListAll(
			final RepProductionPlanForReportCondition condition)
			throws NoDataException {
		checkParams(condition);

		// 生産計画入力一覧の検索を行い、listに格納する。
		List<RepProductionPlanForReport> list = repProductionPlanForReportDao
				.getProductionPlanListForReport(condition);

		// 検索結果がない場合
		if (list.isEmpty()) {
			// NoDataExceptionをthrowする
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * 月間生産計画表表示データ取得(花王)
	 * @param condition 検索条件
	 * @return List<RepProductionPlanForReport> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<RepProductionPlanForReport> getReportListKao(
			final RepProductionPlanForReportCondition condition)
			throws NoDataException {
		checkParams(condition);

		// 生産計画入力一覧の検索を行い、listに格納する。
		List<RepProductionPlanForReport> list = repProductionPlanForReportDao
				.getProductionPlanListForReport(condition);

		return list;
	}

	/**
	 * カレンダーデータ取得
	 * @param procDate 処理年月
	 * @return List<RepProductionPlanCalendar> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	public List<RepProductionPlanCalendar> getCalendarList(final String procDate)
			throws NoDataException {

		List<RepProductionPlanCalendar> list = repProductionPlanCalendarDao
				.getCalendarData(procDate);
		// 検索結果がない場合
		if (list.isEmpty()) {
			// NoDataExceptionをthrowする
			throw new NoDataException();
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
	private void checkParams(final RepProductionPlanForReportCondition condition) {
		// conditionがnullであれば
		if (condition == null) {
			// IllegalArgumentExceptionをthrowする
			throw new IllegalArgumentException(
					"IllegalArgumentException : Paramater is empty.パラメータチェック.getSearchList");
		}
	}

	/* -------------------- setter -------------------- */
	/**
	 * 生産ライン操作用DAOを設定します。
	 * @param productionLineForComboboxesDao 生産ライン操作用DAO
	 */
	public void setProductionLineForComboboxesDao(
			final ProductionLineForComboboxesDao productionLineForComboboxesDao) {
		this.productionLineForComboboxesDao = productionLineForComboboxesDao;
	}

	/**
	 * 月間生産計画用Daoを設定します。
	 * @param repProductionPlanForReportDao 月間生産計画用用Dao
	 */
	public void setRepProductionPlanForReportDao(
			final RepProductionPlanForReportDao repProductionPlanForReportDao) {
		this.repProductionPlanForReportDao = repProductionPlanForReportDao;
	}

	/**
	 * 生産計画カレンダー用Daoを設定します。
	 * @param repProductionPlanCalendarDao 生産計画カレンダー用Dao
	 */
	public void setRepProductionPlanCalendarDao(
			final RepProductionPlanCalendarDao repProductionPlanCalendarDao) {
		this.repProductionPlanCalendarDao = repProductionPlanCalendarDao;
	}
}
