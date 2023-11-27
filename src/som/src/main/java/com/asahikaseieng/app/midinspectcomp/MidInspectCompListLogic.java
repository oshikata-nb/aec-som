/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.midinspectcomp;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.midinspectcomp.MidInspectCompLineForComboboxes;
import com.asahikaseieng.dao.nonentity.midinspectcomp.MidInspectCompList;
import com.asahikaseieng.dao.nonentity.midinspectcomp.MidInspectCompPagerCondition;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompDetailListForReport;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompFormulaListForReport;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompListConditionForReport;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompHeaderListForReport;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompInspectionListForReport;
import com.asahikaseieng.dao.nonentity.midinspectcompforreport.MidInspectCompProcedureListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 中間品検査完了入力 ロジッククラス interface.
 * @author tosco
 */
public interface MidInspectCompListLogic {

	/**
	 * 中間品検査完了入力 検索処理を行う
	 * @param condition 検索条件
	 * @return List<MidInspectCompList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<MidInspectCompList> getSearchList(
			final MidInspectCompPagerCondition condition)
			throws NoDataException;

	/**
	 * 中間品検査完了入力 登録処理
	 * @param bean 中間品検査完了一覧データ
	 * @param loginUserId ログインユーザ
	 * @throws Exception 例外
	 */
	void update(final MidInspectCompList bean, final String loginUserId)
			throws Exception;

	/**
	 * 計装I/Fテーブルの登録処理を行う. : タンクインターロック解除
	 * @param bean 中間品検査完了一覧データ
	 * @param tantoCd 更新者
	 * @throws MidInspectCompLogicException 更新エラー
	 */
	void updateIfTable(final MidInspectCompList bean, final String tantoCd)
			throws MidInspectCompLogicException;

	/**
	 * 生産ラインを全件取得する
	 * @return List<MidInspectCompLineForComboboxes>
	 */
	List<MidInspectCompLineForComboboxes> getAllLines();

	/**
	 * 生産ラインコンボボックス作成
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createLineCombobox(final boolean zero);

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<MidInspectCompListForReport> 検索結果リスト
	 */
	List<MidInspectCompHeaderListForReport> getHeaderReportList(
			final MidInspectCompListConditionForReport condition);

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<MidInspectCompDetailListForReport> 検索結果リスト
	 */
	List<MidInspectCompDetailListForReport> getDetailReportList(
			final MidInspectCompListConditionForReport condition);

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<MidInspectCompDetailListForReport> 検索結果リスト
	 */
	List<MidInspectCompProcedureListForReport> getProcedureReportList(
			final MidInspectCompListConditionForReport condition);

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<MidInspectCompFormulaListForReport> 検索結果リスト
	 */
	List<MidInspectCompFormulaListForReport> getFormulaReportList(
			final MidInspectCompListConditionForReport condition);

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<MidInspectCompInspectionListForReport> 検索結果リスト
	 */
	List<MidInspectCompInspectionListForReport> getInspectionReportList(
			final MidInspectCompListConditionForReport condition);

}
