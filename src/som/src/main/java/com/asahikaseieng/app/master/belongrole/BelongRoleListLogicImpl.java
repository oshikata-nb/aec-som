/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.belongrole;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.belongrolelist.BelongRoleList;
import com.asahikaseieng.dao.nonentity.master.belongrolelist.BelongRoleListDao;
import com.asahikaseieng.dao.nonentity.master.belongrolelist.BelongRoleListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.belongrolelistforreport.BelongRoleListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.belongrolelistforreport.BelongRoleListForReport;
import com.asahikaseieng.dao.nonentity.master.belongrolelistforreport.BelongRoleListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 所属・ロール組合せ一覧ロジック 実装クラス.
 * @author t0011036
 */
public class BelongRoleListLogicImpl implements BelongRoleListLogic {

	private BelongRoleListDao belongRoleListDao;

	private BelongRoleListForReportDao belongRoleListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public BelongRoleListLogicImpl() {
	}

	/**
	 * 所属・ロール組合せ一覧検索
	 * @param condition 検索条件
	 * @return List<BelongList>
	 * @throws NoDataException NoDataException
	 */
	public List<BelongRoleList> getList(
			final BelongRoleListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<BelongRoleList> list = belongRoleListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final BelongRoleListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 所属・ロール組合せ一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<BelongRoleListForReport>
	 */
	public List<BelongRoleListForReport> getListForReport(
			final BelongRoleListConditionForReport condition) {
		List<BelongRoleListForReport> list = belongRoleListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * belongRoleListDaoを設定します。
	 * @param belongRoleListDao belongRoleListDao
	 */
	public void setBelongRoleListDao(final BelongRoleListDao belongRoleListDao) {
		this.belongRoleListDao = belongRoleListDao;
	}

	/**
	 * belongRoleListForReportDaoを設定します。
	 * @param belongRoleListForReportDao belongRoleListForReportDao
	 */
	public void setBelongRoleListForReportDao(
			final BelongRoleListForReportDao belongRoleListForReportDao) {
		this.belongRoleListForReportDao = belongRoleListForReportDao;
	}
}
