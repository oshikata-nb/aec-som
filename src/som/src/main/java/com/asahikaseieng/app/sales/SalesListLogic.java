/*
 * Created on 2009/02/27
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.sales;

import java.util.List;

import com.asahikaseieng.dao.nonentity.sales.SalesList;
import com.asahikaseieng.dao.nonentity.sales.SalesListPagerCondition;
import com.asahikaseieng.dao.nonentity.saleslistforreport.SalesInoutListForReport;
import com.asahikaseieng.dao.nonentity.saleslistforreport.SalesListForReport;
import com.asahikaseieng.dao.nonentity.saleslistforreport.SalesListReportCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 売上トランザクション一覧 ロジッククラス interface.
 * @author tosco
 */
public interface SalesListLogic {

	/**
	 * 検索処理を行う.全検索
	 * @param condition 検索条件
	 * @return List<SalesList> データリスト
	 * 
	 * @throws NoDataException データが存在しない例外
	 * 
	 */
	List<SalesList> getSearchList(final SalesListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票出力検索
	 * @param condition 検索条件
	 * @return List<SalesList> データリスト
	 * 
	 * @throws NoDataException データが存在しない例外
	 * 
	 */
	List<SalesListForReport> getListForReport(
			final SalesListReportCondition condition) throws NoDataException;

	/**
	 * 帳票出力検索
	 * @param condition 検索条件
	 * @return List<SalesList> データリスト
	 * 
	 * @throws NoDataException データが存在しない例外
	 * 
	 */
	List<SalesInoutListForReport> getInoutListForReport(
			final SalesListReportCondition condition) throws NoDataException;

	/**
	 * 正単価更新処理を行う.
	 * @param frm 売上検索画面フォーム
	 * @param tantoCd ログイン者コード
	 */
	void updateSalseUnitprice(final SalesListForm frm, final String tantoCd);

	/**
	 * エラーログ出力処理
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void outPutErrorLog(final String strErrorCd, final String strErrorMsg,
			final String tantoCd) throws Exception;
}
