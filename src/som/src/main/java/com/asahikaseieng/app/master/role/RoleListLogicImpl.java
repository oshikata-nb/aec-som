/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.role;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.rolelist.RoleList;
import com.asahikaseieng.dao.nonentity.master.rolelist.RoleListDao;
import com.asahikaseieng.dao.nonentity.master.rolelist.RoleListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.rolelistforreport.RoleListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.rolelistforreport.RoleListForReport;
import com.asahikaseieng.dao.nonentity.master.rolelistforreport.RoleListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * ロール一覧ロジック 実装クラス.
 * @author t0011036
 */
public class RoleListLogicImpl implements RoleListLogic {

	private RoleListDao roleListDao;

	private RoleListForReportDao roleListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public RoleListLogicImpl() {
	}

	/**
	 * ロール一覧検索
	 * @param condition 検索条件
	 * @return List<RoleList>
	 * @throws NoDataException NoDataException
	 */
	public List<RoleList> getList(final RoleListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<RoleList> list = roleListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final RoleListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * ロール一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<RoleListForReport>
	 */
	public List<RoleListForReport> getListForReport(
			final RoleListConditionForReport condition) {
		List<RoleListForReport> list = roleListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * roleListDaoを設定します。
	 * @param roleListDao roleListDao
	 */
	public void setRoleListDao(final RoleListDao roleListDao) {
		this.roleListDao = roleListDao;
	}

	/**
	 * roleListForReportDaoを設定します。
	 * @param roleListForReportDao roleListForReportDao
	 */
	public void setRoleListForReportDao(
			final RoleListForReportDao roleListForReportDao) {
		this.roleListForReportDao = roleListForReportDao;
	}
}
