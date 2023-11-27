/*
 * Created on 2009/03/18
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.shippingresult;

import java.util.List;

import com.asahikaseieng.app.comboboxes.ComboBoxItems;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultList;
import com.asahikaseieng.dao.nonentity.shippingresult.ShippingResultListPagerCondition;
import com.asahikaseieng.dao.nonentity.shippingresultforreport.ShippingDetailResultListForReport;
import com.asahikaseieng.dao.nonentity.shippingresultforreport.ShippingResultListConditionForReport;
import com.asahikaseieng.dao.nonentity.shippingresultforreport.ShippingResultListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 出荷実績検索ロジッククラス interface.
 * @author tosco
 */
public interface ShippingResultListLogic {

	/**
	 * 運送会社コンボボックス(すべて有)作成
	 * @return List<ComboBoxItems>
	 */
	List<ComboBoxItems> createCarryAllCombobox();

	/**
	 * 検索処理を行う.全検索
	 * @param condition 検索条件
	 * @return List<ShippingList> データリスト
	 * 
	 * @throws NoDataException データが存在しない例外
	 * 
	 */
	List<ShippingResultList> getSearchList(
			final ShippingResultListPagerCondition condition)
			throws NoDataException;

	/**
	 * 出荷実績一覧検索処理(ヘッダ）
	 * 
	 * @param condition 検索条件
	 * @return List<ShippingResultListForReport> 検索結果リスト
	 */
	List<ShippingResultListForReport> getReportHeaderList(
			final ShippingResultListConditionForReport condition);

	/**
	 * 
	 * 出荷実績一覧検索処理(詳細）
	 * 
	 * @param condition 検索条件
	 * @return List<ShippingResultListForReport> 検索結果リスト
	 */
	List<ShippingDetailResultListForReport> getReportDetailList(
			final ShippingResultListConditionForReport condition);

	/**
	 * 完了登録処理を行う.
	 * @param frm フォームデータ
	 * @param tantoCd 更新者
	 * @throws NoDataException 更新対象無しエラー
	 * @throws Exception 例外
	 */
	void complete(final ShippingResultListForm frm, final String tantoCd)
			throws NoDataException, Exception;

	/**
	 * エラーログ出力処理
	 * @param strErrorCd エラーコード
	 * @param strErrorMsg エラーメッセージ
	 * @param tantoCd 担当者コード
	 * @throws Exception 例外
	 */
	void outPutErrorLog(final String strErrorCd, final String strErrorMsg,
			final String tantoCd) throws Exception;

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

}
