/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.util.List;
import java.util.TreeMap;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.comboboxes.rdirection.RdirectionLineForComboboxes;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderList;
import com.asahikaseieng.dao.nonentity.rdirection.RdirectionDirectionHeaderListPagerCondition;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultDetailListForReport;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultFormulaListForReport;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultHeaderListForReport;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultInspectionListForReport;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultListConditionForReport;
import com.asahikaseieng.dao.nonentity.rdirectionorforreport.DirectionResultProcedureListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 製造実績検索 ロジッククラス interface.
 * @author tosco
 */
public interface RdirectionListLogic {

	/**
	 * 検索一覧検索処理
	 * @param condition 検索条件
	 * @return List<RdirectionDirectionHeaderList> 検索結果リスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<RdirectionDirectionHeaderList> getSearchList(
			RdirectionDirectionHeaderListPagerCondition condition)
			throws NoDataException;

	/**
	 * 生産ラインを全件取得する
	 * @return List<MgrecipeLine>
	 */
	List<RdirectionLineForComboboxes> getAllLines();

	/**
	 * 生産ラインコンボボックス作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createLineCombobox();

	/**
	 * 製造指図ヘッダの更新を行う。
	 * @param bean データ
	 */
	void updateDirectionHeader(RdirectionDirectionHeaderList bean);

	/**
	 * 製造記録発行
	 * @param list List<DirectionDirectionHeaderList>
	 * @param tantoCd 担当者コード
	 * @return TreeMap<String, String> 発行対象製造指図番号
	 * @throws RdirectionLogicException エラー発生時
	 */
	TreeMap<String, String> issuance(
			final List<RdirectionDirectionHeaderList> list, final String tantoCd)
			throws RdirectionLogicException;

	/**
	 * 一括完了
	 * @param list List<DirectionDirectionHeaderList>
	 * @param tantoCd 担当者コード
	 * @return [true:OK][false:NG]
	 * @throws RdirectionLogicException エラー発生時
	 */
	boolean complete(List<RdirectionDirectionHeaderList> list, String tantoCd)
			throws RdirectionLogicException;

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionResultListForReport> 検索結果リスト
	 */
	List<DirectionResultHeaderListForReport> getHeaderReportList(
			final DirectionResultListConditionForReport condition);

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionResultDetailListForReport> 検索結果リスト
	 */
	List<DirectionResultDetailListForReport> getDetailReportList(
			final DirectionResultListConditionForReport condition);

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionResultDetailListForReport> 検索結果リスト
	 */
	List<DirectionResultProcedureListForReport> getProcedureReportList(
			final DirectionResultListConditionForReport condition);

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionResultFormulaListForReport> 検索結果リスト
	 */
	List<DirectionResultFormulaListForReport> getFormulaReportList(
			final DirectionResultListConditionForReport condition);

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<DirectionResultInspectionListForReport> 検索結果リスト
	 */
	List<DirectionResultInspectionListForReport> getInspectionReportList(
			final DirectionResultListConditionForReport condition);

}
