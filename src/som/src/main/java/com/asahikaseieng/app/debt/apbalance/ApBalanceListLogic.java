/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.apbalance;

import java.util.List;

import com.asahikaseieng.dao.nonentity.debt.apbalance.ApBalanceList;
import com.asahikaseieng.dao.nonentity.debt.apbalance.ApBalancePagerCondition;
import com.asahikaseieng.dao.nonentity.debt.apbalanceforreport.ApBalanceListConditionForReport;
import com.asahikaseieng.dao.nonentity.debt.apbalanceforreport.ApBalanceListForReport;
import com.asahikaseieng.dao.nonentity.debt.aprollback.ApRollback;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * ArLedgerListLogic.買掛残高一覧
 * @author tosco
 */
public interface ApBalanceListLogic {

	/**
	 * 検索処理を行う.買掛残高一覧
	 * @param condition condition
	 * @return ArBalanceList 詳細データ
	 * @throws NoDataException NoDataException
	 */
	List<ApBalanceList> getSearchList(final ApBalancePagerCondition condition)
			throws NoDataException;

	/**
	 * 買掛ヘッダーからMAX(買掛締め日)の年月を取得
	 * 
	 * @param organizationCd 部署コード
	 * @return ApRollback 買掛ヘッダーデータ(買掛締め日の翌年月)
	 * @throws NoDataException NoDataException
	 */
	ApRollback getSearch(final String organizationCd) throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<ApBalanceListForReport>
	 */
	List<ApBalanceListForReport> getListForReport(
			final ApBalanceListConditionForReport condition);
}
