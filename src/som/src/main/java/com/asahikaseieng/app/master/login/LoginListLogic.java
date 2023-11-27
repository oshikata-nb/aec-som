/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.login;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.loginlist.LoginList;
import com.asahikaseieng.dao.nonentity.master.loginlist.LoginListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.loginlistforreport.LoginListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.loginlistforreport.LoginListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 担当者マスタ一覧 ロジッククラス interface.
 * @author t0011036
 */
public interface LoginListLogic {

	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<LoginList>
	 */
	List<LoginList> getList(final LoginListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<LoginListForReport>
	 */
	List<LoginListForReport> getListForReport(
			final LoginListConditionForReport condition);
}
