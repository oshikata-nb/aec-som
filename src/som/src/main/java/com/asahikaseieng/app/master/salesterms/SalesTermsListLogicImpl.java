/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.salesterms;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.salestermslist.SalesTermsList;
import com.asahikaseieng.dao.nonentity.master.salestermslist.SalesTermsListDao;
import com.asahikaseieng.dao.nonentity.master.salestermslist.SalesTermsListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.salestermslistforreport.SalesTermsListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.salestermslistforreport.SalesTermsListForReport;
import com.asahikaseieng.dao.nonentity.master.salestermslistforreport.SalesTermsListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 販売条件一覧ロジック 実装クラス.
 * @author t0011036
 */
public class SalesTermsListLogicImpl implements SalesTermsListLogic {

	private SalesTermsListDao salesTermsListDao;

	private SalesTermsListForReportDao salesTermsListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public SalesTermsListLogicImpl() {
	}

	/**
	 * 販売条件一覧検索
	 * @param condition 検索条件
	 * @return List<SalesTermsList>
	 * @throws NoDataException NoDataException
	 */
	public List<SalesTermsList> getList(
			final SalesTermsListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<SalesTermsList> list = salesTermsListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final SalesTermsListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 販売条件一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<SalesTermsListForReport>
	 */
	public List<SalesTermsListForReport> getListForReport(
			final SalesTermsListConditionForReport condition) {
		List<SalesTermsListForReport> list = salesTermsListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * salesTermsListDaoを設定します。
	 * @param salesTermsListDao salesTermsListDao
	 */
	public void setSalesTermsListDao(final SalesTermsListDao salesTermsListDao) {
		this.salesTermsListDao = salesTermsListDao;
	}

	/**
	 * salesTermsListForReportDaoを設定します。
	 * @param salesTermsListForReportDao salesTermsListForReportDao
	 */
	public void setSalesTermsListForReportDao(
			final SalesTermsListForReportDao salesTermsListForReportDao) {
		this.salesTermsListForReportDao = salesTermsListForReportDao;
	}
}
