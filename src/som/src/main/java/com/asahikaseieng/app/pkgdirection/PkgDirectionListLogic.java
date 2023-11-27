/*
 * Created on 2009/02/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.entity.keikakuhoso.KeikakuHoso;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionList;
import com.asahikaseieng.dao.nonentity.pkgdirection.PkgDirectionListPagerCondition;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderDetailListForReport;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderFormulaListForReport;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderHeaderListForReport;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderInspectionListForReport;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderListConditionForReport;
import com.asahikaseieng.dao.nonentity.pkgdirectionforreport.PkgDirectionOrderProcedureListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 包装指図－検索画面 ロジッククラス interface.
 * @author tosco
 */
public interface PkgDirectionListLogic {

	/**
	 * 検索処理を行う.全検索
	 * @param condition 検索条件
	 * @return List<PkgDirectionList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<PkgDirectionList> getSearchList(
			final PkgDirectionListPagerCondition condition)
			throws NoDataException;

	/**
	 * 包装指図書発行処理
	 * @param bean 一覧データ
	 * @param tantoCd 更新者
	 * @throws PkgDirectionLogicException エラー発生時
	 * @return KeikakuHoso 包装計画
	 */
	KeikakuHoso issueDirection(final PkgDirectionList bean, final String tantoCd)
			throws PkgDirectionLogicException;

	/**
	 * 計装I/Fテーブルの登録処理を行う. : 包装計画
	 * @param bean 登録包装計画
	 * @throws PkgDirectionLogicException 更新エラー
	 */
	void insertIfTable(final KeikakuHoso bean)
			throws PkgDirectionLogicException;

	/**
	 * ラベル発行処理（製造指図ヘッダー更新）を行う
	 * @param searchList 一覧データ
	 * @param tantoCd 更新者
	 */
	void issueLabel(final List<PkgDirectionList> searchList,
			final String tantoCd);

	/**
	 * 指図発行時に手持ち在庫をチェックして不足なら警告を出す。
	 * @param directionNo 製造指図番号
	 * @param div 製造指図番号
	 * @return 在庫不足量警告メッセージ
	 * @throws PkgDirectionLogicException 例外
	 */
	List<String> checkInventoryFormula(final String directionNo,
			final BigDecimal div) throws PkgDirectionLogicException;

	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgDirectionOrderListForReport> 検索結果リスト
	 */
	List<PkgDirectionOrderHeaderListForReport> getHeaderReportList(
			final PkgDirectionOrderListConditionForReport condition);

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgDirectionOrderDetailListForReport> 検索結果リスト
	 */
	List<PkgDirectionOrderDetailListForReport> getDetailReportList(
			final PkgDirectionOrderListConditionForReport condition);

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgDirectionOrderDetailListForReport> 検索結果リスト
	 */
	List<PkgDirectionOrderProcedureListForReport> getProcedureReportList(
			final PkgDirectionOrderListConditionForReport condition);

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgDirectionOrderFormulaListForReport> 検索結果リスト
	 */
	List<PkgDirectionOrderFormulaListForReport> getFormulaReportList(
			final PkgDirectionOrderListConditionForReport condition);

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<PkgDirectionOrderInspectionListForReport> 検索結果リスト
	 */
	List<PkgDirectionOrderInspectionListForReport> getInspectionReportList(
			final PkgDirectionOrderListConditionForReport condition);
}
