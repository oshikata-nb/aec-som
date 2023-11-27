/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.salestermsandestimate;

import java.util.List;

import com.asahikaseieng.dao.nonentity.comboboxes.salestermsandestimate.SalestermsandestimateNamesListForComboboxes;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimateitemcheck.SalestermsAndEstimateItemCheck;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelist.SalestermsAndEstimateList;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelist.SalestermsAndEstimateListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport.SalestermsAndEstimateListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport.SalestermsAndEstimateListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 販売条件・見積単価 コピー作成・削除一覧ロジック interface.
 * @author t0011036
 */
public interface SalestermsAndEstimateListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<SalesTermsList>
	 */
	List<SalestermsAndEstimateList> getList(final SalestermsAndEstimateListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<SalesTermsListForReport>
	 */
	List<SalestermsAndEstimateListForReport> getListForReport(
			final SalestermsAndEstimateListConditionForReport condition);
	
	/**
	 * 販売条件マスタの該当品目有無確認用.
	 * @param itemCd 品目コード
	 * @return SalestermsAndEstimateItemCheck
	 */
	SalestermsAndEstimateItemCheck getSalestermsItemCount(final String itemCd);
	
	/**
	 * 見積ファイルの該当品目有無確認用.
	 * @param itemCd 品目コード
	 * @return SalestermsAndEstimateItemCheck
	 */
	SalestermsAndEstimateItemCheck getEstimateItemCount(final String itemCd);
	
	/**
	 * 販売条件・見積単価コピー・削除確定or確定取消処理を行う(詳細画面の「確定」or「確定取消」ボタン押下時).
	 * @param frm 販売条件・見積単価コピー・削除詳細画面フォーム
	 * @param tantoCd ログイン者コード
	 */
	void conSalestermsAndEstimate(final SalestermsAndEstimateListForm frm, final String tantoCd);
	
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
	 * 処理区分取得
	 * @return List<SalestermsandestimateNamesListForComboboxes>
	 */
	List<SalestermsandestimateNamesListForComboboxes> getProcessDivisionList();

	/**
	 * ステータス取得
	 * @return List<SalestermsandestimateNamesListForComboboxes>
	 */
	List<SalestermsandestimateNamesListForComboboxes> getStatusList();
	
}
