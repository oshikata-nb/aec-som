/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.search.balance;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.nonentity.master.balancelist.BalanceList;
import com.asahikaseieng.dao.nonentity.master.balancelist.BalanceListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.balancelistdetail.BalanceListDetail;
import com.asahikaseieng.exception.NoDataException;

/**
 * 帳合検索ロジック interface.
 * @author t0011036
 */
public interface BalanceSearchLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<BalanceList>
	 */
	List<BalanceList> getList(final BalanceListPagerCondition condition)
			throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param balanceCd 帳合コード
	 * @param balanceType 区分
	 * @return BalanceListDetail
	 */
	BalanceListDetail getEntity(final String balanceCd,
			final BigDecimal balanceType);
}
