/*
 * Created on 2009/03/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgrdirection;

import java.util.List;
import java.util.TreeMap;

import org.apache.struts.action.ActionMessages;

import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionList;
import com.asahikaseieng.dao.nonentity.pkgrdirection.PkgRdirectionListPagerCondition;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionDetailListForReport;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionFormulaListForReport;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionHeaderListForReport;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionInspectionListForReport;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionListConditionForReport;
import com.asahikaseieng.dao.nonentity.pkgrdirectionforreport.PkgRdirectionProcedureListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装実績－検索画面 ロジッククラス interface.
 * @author tosco
 */
public interface PkgRdirectionListLogic {

	/**
	 * 検索処理を行う.全検索
	 * @param condition 検索条件
	 * @return List<PkgRdirectionList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<PkgRdirectionList> getSearchList(
			final PkgRdirectionListPagerCondition condition)
			throws NoDataException;

	/**
	 * 指図ステータスのチェック(包装記録発行処理のチェック）
	 * @param searchList 一覧データ
	 * @return ActionMessage エラーメッセージ
	 */
	ActionMessages checkDirectionStatus(final List<PkgRdirectionList> searchList);

	/**
	 * 包装記録発行処理（製造指図ヘッダー更新）
	 * @param searchList 一覧データ
	 * @return TreeMap<String, String> 発行対象包装指図番号
	 * @param tantoCd 更新者
	 */
	TreeMap<String, String> issueProductRecord(
			final List<PkgRdirectionList> searchList, final String tantoCd);

	/**
	 * ラベル発行処理（製造指図ヘッダー更新）を行う
	 * @param searchList 一覧データ
	 * @param tantoCd 更新者
	 */
	void issueLabel(final List<PkgRdirectionList> searchList,
			final String tantoCd);

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgRdirectionListForReport> 検索結果リスト
	 */
	List<PkgRdirectionHeaderListForReport> getHeaderReportList(
			final PkgRdirectionListConditionForReport condition);

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgRdirectionDetailListForReport> 検索結果リスト
	 */
	List<PkgRdirectionDetailListForReport> getDetailReportList(
			final PkgRdirectionListConditionForReport condition);

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgRdirectionDetailListForReport> 検索結果リスト
	 */
	List<PkgRdirectionProcedureListForReport> getProcedureReportList(
			final PkgRdirectionListConditionForReport condition);

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgRdirectionFormulaListForReport> 検索結果リスト
	 */
	List<PkgRdirectionFormulaListForReport> getFormulaReportList(
			final PkgRdirectionListConditionForReport condition);

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgRdirectionInspectionListForReport> 検索結果リスト
	 */
	List<PkgRdirectionInspectionListForReport> getInspectionReportList(
			final PkgRdirectionListConditionForReport condition);

	/**
	 * 一括完了
	 * @param list List<PkgRdirectionList>
	 * @param tantoCd 担当者コード
	 * @return [true:OK][false:NG]
	 * @throws PkgRdirectionLogicException エラー発生時
	 */
	boolean complete(final List<PkgRdirectionList> list,
			final String tantoCd) throws PkgRdirectionLogicException;

}
