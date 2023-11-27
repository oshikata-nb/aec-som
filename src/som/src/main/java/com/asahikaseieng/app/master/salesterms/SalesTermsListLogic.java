/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.salesterms;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.salestermslist.SalesTermsList;
import com.asahikaseieng.dao.nonentity.master.salestermslist.SalesTermsListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.salestermslistforreport.SalesTermsListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.salestermslistforreport.SalesTermsListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 販売条件一覧ロジック interface.
 * @author t0011036
 */
public interface SalesTermsListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<SalesTermsList>
	 */
	List<SalesTermsList> getList(final SalesTermsListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<SalesTermsListForReport>
	 */
	List<SalesTermsListForReport> getListForReport(
			final SalesTermsListConditionForReport condition);
}
