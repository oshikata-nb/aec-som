/*
 * Created on 2008/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inout;

import java.util.List;

import com.asahikaseieng.dao.nonentity.inoutrecordlist.InoutRecordList;
import com.asahikaseieng.dao.nonentity.inoutrecordlist.InoutRecordListDao;
import com.asahikaseieng.dao.nonentity.inoutrecordlist.InoutRecordListPagerCondition;
import com.asahikaseieng.dao.nonentity.inoutrecordlistforreport.InoutRecordListForReport;
import com.asahikaseieng.dao.nonentity.inoutrecordlistforreport.InoutRecordListForReportDao;
import com.asahikaseieng.dao.nonentity.inoutrecordlistforreport.InoutRecordReportCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受払照会 実装クラス.
 * @author tanaka
 */
public class InoutListLogicImpl implements InoutListLogic {

	private InoutRecordListDao inoutRecordListDao;

	private InoutRecordListForReportDao inoutRecordListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public InoutListLogicImpl() {
	}

	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<InoutRecordList>
	 * @throws NoDataException NoDataException
	 */
	public List<InoutRecordList> getList(
			final InoutRecordListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<InoutRecordList> list = inoutRecordListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final InoutRecordListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * ListForReportメソッド
	 * 
	 * @param condition condition
	 * @return List<InoutRecordListForReport>
	 * @throws NoDataException NoDataException
	 */
	public List<InoutRecordListForReport> getListForReport(
			final InoutRecordReportCondition condition) throws NoDataException {

		List<InoutRecordListForReport> list = inoutRecordListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/* -------------------- setter -------------------- */

	/**
	 * inoutRecordListDaoを設定します。
	 * @param inoutRecordListDao inoutRecordListDao
	 */
	public void setInoutRecordListDao(
			final InoutRecordListDao inoutRecordListDao) {
		this.inoutRecordListDao = inoutRecordListDao;
	}

	/**
	 * inoutRecordListForReportDaoを設定します。
	 * @param inoutRecordListForReportDao inoutRecordListForReportDao
	 */
	public void setInoutRecordListForReportDao(
			final InoutRecordListForReportDao inoutRecordListForReportDao) {
		this.inoutRecordListForReportDao = inoutRecordListForReportDao;
	}
}
