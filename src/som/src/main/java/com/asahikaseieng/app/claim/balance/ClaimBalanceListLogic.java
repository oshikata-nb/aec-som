/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.balance;

import java.util.List;

import com.asahikaseieng.dao.nonentity.claim.balance.ClaimBalanceList;
import com.asahikaseieng.dao.nonentity.claim.balance.ClaimBalancePagerCondition;
import com.asahikaseieng.dao.nonentity.claim.balancelistforreport.ClaimBalanceListConditionForReport;
import com.asahikaseieng.dao.nonentity.claim.balancelistforreport.ClaimBalanceListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * BalanceListLogicクラス．請求残高一覧
 * @author tosco
 */
public interface ClaimBalanceListLogic {

	/**
	 * 検索処理を行う.請求残高一覧
	 * @param condition condition
	 * @return BalanceList 詳細データ
	 * @throws NoDataException NoDataException
	 */
	List<ClaimBalanceList> getSearchList(
			final ClaimBalancePagerCondition condition) throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<ClaimBalanceListForReport>
	 */
	List<ClaimBalanceListForReport> getListForReport(
			final ClaimBalanceListConditionForReport condition);
}
