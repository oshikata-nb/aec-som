/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.tanklock;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.tanklock.TankLockLineForComboboxes;
import com.asahikaseieng.dao.nonentity.tanklock.TankLockList;
import com.asahikaseieng.dao.nonentity.tanklock.TankLockPagerCondition;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockDetailListForReport;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockFormulaListForReport;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockHeaderListForReport;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockInspectionListForReport;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockListConditionForReport;
import com.asahikaseieng.dao.nonentity.tanklockforreport.TankLockProcedureListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 調合タンク底弁インターロック解除/再設定 ロジッククラス interface.
 * @author tosco
 */
public interface TankLockListLogic {

	/**
	 * 検索処理を行う
	 * @param condition 検索条件
	 * @return List<TankLockList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<TankLockList> getSearchList(final TankLockPagerCondition condition)
			throws NoDataException;

	/**
	 * 調合タンク底弁インターロック解除/再設定の登録処理を行う
	 * @param searchList 検索結果
	 * @param loginUserId ログインユーザ
	 * @throws Exception 例外
	 */
	void update(final List<TankLockList> searchList, final String loginUserId)
			throws Exception;

	/**
	 * 生産ラインを全件取得する
	 * @return List<TankLockLineForComboboxes>
	 */
	List<TankLockLineForComboboxes> getAllLines();

	/**
	 * 生産ラインコンボボックス作成
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createLineCombobox(final boolean zero);

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<TankLockListForReport> 検索結果リスト
	 */
	List<TankLockHeaderListForReport> getHeaderReportList(
			final TankLockListConditionForReport condition);

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<TankLockDetailListForReport> 検索結果リスト
	 */
	List<TankLockDetailListForReport> getDetailReportList(
			final TankLockListConditionForReport condition);

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<TankLockDetailListForReport> 検索結果リスト
	 */
	List<TankLockProcedureListForReport> getProcedureReportList(
			final TankLockListConditionForReport condition);

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<TankLockFormulaListForReport> 検索結果リスト
	 */
	List<TankLockFormulaListForReport> getFormulaReportList(
			final TankLockListConditionForReport condition);

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<TankLockInspectionListForReport> 検索結果リスト
	 */
	List<TankLockInspectionListForReport> getInspectionReportList(
			final TankLockListConditionForReport condition);

}
