/*
 * Created on 2009/04/06
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.productionplan;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.production.ProductionLineForComboboxes;
import com.asahikaseieng.dao.nonentity.repproductionplanforreport.RepProductionPlanCalendar;
import com.asahikaseieng.dao.nonentity.repproductionplanforreport.RepProductionPlanForReport;
import com.asahikaseieng.dao.nonentity.repproductionplanforreport.RepProductionPlanForReportCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 生産計画入力一覧 ロジッククラス interface.
 * @author tosco
 */
public interface ProductionPlanLogic {

	/**
	 * 検索処理を行う(全荷主)
	 * @param condition 検索条件
	 * @return List<RepProductionPlanForReport> 生産計画入力一覧 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<RepProductionPlanForReport> getReportListAll(
			final RepProductionPlanForReportCondition condition)
			throws NoDataException;

	/**
	 * 検索処理を行う(花王)
	 * @param condition 検索条件
	 * @return List<RepProductionPlanForReport> 生産計画入力一覧 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<RepProductionPlanForReport> getReportListKao(
			final RepProductionPlanForReportCondition condition)
			throws NoDataException;

	/**
	 * 月間生産計画表表示用カレンダーリスト取得
	 * @param procDate 処理年月
	 * @return List<RepProductionPlanCalendar> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<RepProductionPlanCalendar> getCalendarList(final String procDate)
			throws NoDataException;

	/**
	 * 生産ラインを全件取得する
	 * @return List<ProductionLineForComboboxes>
	 */
	List<ProductionLineForComboboxes> getAllLines();

	/**
	 * 生産ラインコンボボックス作成
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createLineCombobox(final boolean zero);

}
