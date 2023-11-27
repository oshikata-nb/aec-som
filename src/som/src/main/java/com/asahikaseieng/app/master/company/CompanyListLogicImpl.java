/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.company;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.companydetail.CompanyDetail;
import com.asahikaseieng.dao.nonentity.master.companydetail.CompanyDetailDao;
import com.asahikaseieng.dao.nonentity.master.companylist.CompanyList;
import com.asahikaseieng.dao.nonentity.master.companylist.CompanyListDao;
import com.asahikaseieng.dao.nonentity.master.companylist.CompanyListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.companylistforreport.CompanyListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.companylistforreport.CompanyListForReport;
import com.asahikaseieng.dao.nonentity.master.companylistforreport.CompanyListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 自社一覧ロジック 実装クラス.
 * @author t0011036
 */
public class CompanyListLogicImpl implements CompanyListLogic {

	private CompanyListDao companyListDao;

	private CompanyListForReportDao companyListForReportDao;

	private CompanyDetailDao companyDetailDao;

	/**
	 * コンストラクタ.
	 */
	public CompanyListLogicImpl() {
	}

	/**
	 * 自社一覧検索
	 * @param condition 検索条件
	 * @return List<CompanyList>
	 * @throws NoDataException NoDataException
	 */
	public List<CompanyList> getList(final CompanyListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<CompanyList> list = companyListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final CompanyListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 自社一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<CompanyListForReport>
	 */
	public List<CompanyListForReport> getListForReport(
			final CompanyListConditionForReport condition) {
		List<CompanyListForReport> list = companyListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * 登録チェック
	 * @return true:登録済み false:未登録
	 */
	public boolean checkUpdateData() {
		CompanyDetail bean = companyDetailDao.getEntity(null);

		if (bean == null) {
			return false;
		}

		return true;
	}

	/* -------------------- setter -------------------- */

	/**
	 * companyListDaoを設定します。
	 * @param companyListDao companyListDao
	 */
	public void setCompanyListDao(final CompanyListDao companyListDao) {
		this.companyListDao = companyListDao;
	}

	/**
	 * companyListForReportDaoを設定します。
	 * @param companyListForReportDao companyListForReportDao
	 */
	public void setCompanyListForReportDao(
			final CompanyListForReportDao companyListForReportDao) {
		this.companyListForReportDao = companyListForReportDao;
	}

	/**
	 * companyDetailDaoを設定します。
	 * @param companyDetailDao companyDetailDao
	 */
	public void setCompanyDetailDao(final CompanyDetailDao companyDetailDao) {
		this.companyDetailDao = companyDetailDao;
	}
}
