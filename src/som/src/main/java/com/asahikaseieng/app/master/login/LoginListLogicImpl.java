/*
 * Created on 2009/02/01
 *
 * $copyright$
 * 
 */
package com.asahikaseieng.app.master.login;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.loginlist.LoginList;
import com.asahikaseieng.dao.nonentity.master.loginlist.LoginListDao;
import com.asahikaseieng.dao.nonentity.master.loginlist.LoginListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.loginlistforreport.LoginListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.loginlistforreport.LoginListForReport;
import com.asahikaseieng.dao.nonentity.master.loginlistforreport.LoginListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 担当者マスタ詳細 ロジック実装クラス
 * @author t0011036
 */
public class LoginListLogicImpl implements LoginListLogic {

	private LoginListDao loginListDao;

	private LoginListForReportDao loginListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public LoginListLogicImpl() {
	}

	/**
	 * 全担当者一覧検索
	 * @param condition 検索条件
	 * @return List<LoginList>
	 * @throws NoDataException NoDataException
	 */
	public List<LoginList> getList(final LoginListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<LoginList> list = loginListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final LoginListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 担当者一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<LoginListForReport>
	 */
	public List<LoginListForReport> getListForReport(
			final LoginListConditionForReport condition) {
		List<LoginListForReport> list = loginListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * loginListDaoを設定します。
	 * @param loginListDao LoginListDao
	 */
	public void setLoginListDao(final LoginListDao loginListDao) {
		this.loginListDao = loginListDao;

	}

	/**
	 * loginListForReportDaoを設定します。
	 * @param loginListForReportDao loginListForReportDao
	 */
	public void setLoginListForReportDao(
			final LoginListForReportDao loginListForReportDao) {
		this.loginListForReportDao = loginListForReportDao;
	}
}
