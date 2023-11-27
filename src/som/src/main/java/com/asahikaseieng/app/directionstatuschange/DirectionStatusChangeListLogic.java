/*
 * Created on 2009/05/28
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.directionstatuschange;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.directionstatuschange.DirectionStatusChangeLineForComboboxes;
import com.asahikaseieng.dao.nonentity.directionstatuschange.DirectionStatusChangeList;
import com.asahikaseieng.dao.nonentity.directionstatuschange.DirectionStatusChangePagerCondition;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeDetailListForReport;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeFormulaListForReport;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeHeaderListForReport;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeInspectionListForReport;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeListConditionForReport;
import com.asahikaseieng.dao.nonentity.directionstatuschangeforreport.DirectionStatusChangeProcedureListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造指図ステータス変更 ロジッククラス interface.
 * @author tosco
 */
public interface DirectionStatusChangeListLogic {

	/**
	 * 製造指図ステータス変更 検索処理を行う
	 * @param condition 検索条件
	 * @return List<DirectionStatusChangeList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<DirectionStatusChangeList> getSearchList(
			final DirectionStatusChangePagerCondition condition)
			throws NoDataException;

	/**
	 * 生産ラインを全件取得する
	 * @return List<DirectionStatusChangeLineForComboboxes>
	 */
	List<DirectionStatusChangeLineForComboboxes> getAllLines();

	/**
	 * 生産ラインコンボボックス作成
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createLineCombobox(final boolean zero);

	/**
	 * ステータス変更
	 * @param bean データ
	 * @param tantoCd 更新者
	 */
	void changeStatus(final DirectionStatusChangeList bean, final String tantoCd);

	/**
	 * 計装IF済みフラグ変更
	 * @param bean データ
	 * @param tantoCd 更新者
	 * @param value 更新データ
	 */
	void changeSumiflg(final DirectionStatusChangeList bean,
			final String tantoCd, final String value);

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionStatusChangeListForReport> 検索結果リスト
	 */
	List<DirectionStatusChangeHeaderListForReport> getHeaderReportList(
			final DirectionStatusChangeListConditionForReport condition);

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionStatusChangeDetailListForReport> 検索結果リスト
	 */
	List<DirectionStatusChangeDetailListForReport> getDetailReportList(
			final DirectionStatusChangeListConditionForReport condition);

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionStatusChangeDetailListForReport> 検索結果リスト
	 */
	List<DirectionStatusChangeProcedureListForReport> getProcedureReportList(
			final DirectionStatusChangeListConditionForReport condition);

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionStatusChangeFormulaListForReport> 検索結果リスト
	 */
	List<DirectionStatusChangeFormulaListForReport> getFormulaReportList(
			final DirectionStatusChangeListConditionForReport condition);

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionStatusChangeInspectionListForReport> 検索結果リスト
	 */
	List<DirectionStatusChangeInspectionListForReport> getInspectionReportList(
			final DirectionStatusChangeListConditionForReport condition);

}
