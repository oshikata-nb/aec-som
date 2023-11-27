/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.billissue;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.nonentity.claim.billissue.BillIssueList;
import com.asahikaseieng.dao.nonentity.claim.billissue.BillIssuePagerCondition;
import com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.BillIssueListConditionForReport;
import com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.BillIssueListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * BalanceListLogicクラス．請求書発行
 * @author tosco
 */
public interface BillIssueListLogic {

	/**
	 * 検索処理を行う.請求書発行
	 * @param condition condition
	 * @return List<BillIssueList> 詳細データ
	 * @throws NoDataException NoDataException
	 */
	List<BillIssueList> getSearchList(final BillIssuePagerCondition condition)
			throws NoDataException;

	/**
	 * 請求書一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<BillIssueListForReport>
	 */
	List<BillIssueListForReport> getListForReport(
			final BillIssueListConditionForReport condition);

	/**
	 * 請求書発行フラグ更新
	 * @param frm 画面データ
	 * @param status 請求書発行フラグ
	 * @param tantoCd 担当者コード
	 * @throws NoDataException NoDataException
	 */
	void updateFlg(final BillIssueListForm frm, final BigDecimal status,
			final String tantoCd) throws NoDataException;
}
