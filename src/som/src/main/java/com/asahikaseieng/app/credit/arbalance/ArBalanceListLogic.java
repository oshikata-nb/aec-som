/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arbalance;

import java.util.List;

import com.asahikaseieng.dao.nonentity.credit.arbalance.ArBalanceList;
import com.asahikaseieng.dao.nonentity.credit.arbalance.ArBalancePagerCondition;
import com.asahikaseieng.dao.nonentity.credit.arbalanceforreport.ArBalanceListConditionForReport;
import com.asahikaseieng.dao.nonentity.credit.arbalanceforreport.ArBalanceListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 
 * ArBalanceListLogic.売掛残高一覧
 * @author tosco
 */
public interface ArBalanceListLogic {

	/**
	 * 検索処理を行う.売掛残高一覧
	 * @param condition 検索条件
	 * @return List<ArBalanceList> 一覧データ
	 * @throws NoDataException NoDataException
	 */
	List<ArBalanceList> getSearchList(final ArBalancePagerCondition condition)
			throws NoDataException;

	/**
	 * 検索処理を行う.MAX(締め日)の年月
	 * @param sectionCd 部門コード
	 * @return ArBalanceList 詳細データ
	 * @throws NoDataException NoDataException
	 */
	ArBalanceList getSearchDate(final String sectionCd) throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<ArBalanceListForReport>
	 */
	List<ArBalanceListForReport> getListForReport(
			final ArBalanceListConditionForReport condition);
}
