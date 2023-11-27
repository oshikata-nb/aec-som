/*
 * Created on 2009/02/17
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.stockbooking;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.entity.directionheader.DirectionHeader;
import com.asahikaseieng.dao.nonentity.comboboxes.stockbooking.StockBookingLineForComboboxes;
import com.asahikaseieng.dao.nonentity.stockbooking.StockBookingList;
import com.asahikaseieng.dao.nonentity.stockbooking.StockBookingPagerCondition;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingDetailListForReport;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingFormulaListForReport;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingHeaderListForReport;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingInspectionListForReport;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingListConditionForReport;
import com.asahikaseieng.dao.nonentity.stockbookingforreport.StockBookingProcedureListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 検査待ち在庫計上一覧 ロジッククラス interface. 
 * @author tosco
 */
public interface StockBookingListLogic {

	/**
	 * 検索処理を行う.全検索
	 * @param condition 検索条件
	 * @return List<StockBookingList> データリスト
	 * @throws NoDataException データが存在しない例外
	 */
	List<StockBookingList> getSearchList(final StockBookingPagerCondition condition)
			throws NoDataException;

	/**
	 * 検査待ち在庫計上一覧の登録処理を行う
	 * @param bean 検査待ち在庫計上一覧データ
	 * @param loginUserId ログインユーザ
	 * @return DirectionHeader ヘッダー更新情報
	 * @throws Exception 例外
	 */
	DirectionHeader update
		(final StockBookingList bean, final String loginUserId) throws Exception;

	/**
	 * PRO_IF_PAKAGE_STOOCK_BOOKINGを実行する
	 * @param bean 製造指図ヘッダー
	 * @param tantoCd 担当者コード
	 * @throws StockBookingLogicException プロシージャ実行時エラー
	 */
	void callProIf(final DirectionHeader bean, final String tantoCd)
		throws StockBookingLogicException;

	/**
	 * エラーログ出力処理
	 * @param strModuleCd モジュールコード
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void outPutErrorLog(final String strModuleCd, final String strErrorCd,
			final String strErrorMsg, final String tantoCd) throws Exception;

	/**
	 * 生産ラインを全件取得する
	 * @return List<StockBookingLineForComboboxes>
	 */
	List<StockBookingLineForComboboxes> getAllLines();

	/**
	 * 生産ラインコンボボックス作成
	 * @param zero すべての設定可否(true:0を設定する false:0を設定しない)
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createLineCombobox(final boolean zero);
	/**
	 * 帳票Excelヘッダ一覧検索処理
	 * @param condition 検索条件
	 * @return List<StockBookingListForReport> 検索結果リスト
	 */
	List<StockBookingHeaderListForReport> getHeaderReportList(
			final StockBookingListConditionForReport condition);

	/**
	 * 帳票Excel詳細一覧検索処理
	 * @param condition 検索条件
	 * @return List<StockBookingDetailListForReport> 検索結果リスト
	 */
	List<StockBookingDetailListForReport> getDetailReportList(
			final StockBookingListConditionForReport condition);

	/**
	 * 帳票Excel工程一覧検索処理
	 * @param condition 検索条件
	 * @return List<StockBookingDetailListForReport> 検索結果リスト
	 */
	List<StockBookingProcedureListForReport> getProcedureReportList(
			final StockBookingListConditionForReport condition);

	/**
	 * 帳票Excel配合一覧検索処理
	 * @param condition 検索条件
	 * @return List<StockBookingFormulaListForReport> 検索結果リスト
	 */
	List<StockBookingFormulaListForReport> getFormulaReportList(
			final StockBookingListConditionForReport condition);

	/**
	 * 帳票Excel検査一覧検索処理
	 * @param condition 検索条件
	 * @return List<StockBookingInspectionListForReport> 検索結果リスト
	 */
	List<StockBookingInspectionListForReport> getInspectionReportList(
			final StockBookingListConditionForReport condition);
}
