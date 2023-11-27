/*
 * Created on 2007/04/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.vender;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.venderlist.VenderList;
import com.asahikaseieng.dao.nonentity.master.venderlist.VenderListDao;
import com.asahikaseieng.dao.nonentity.master.venderlist.VenderListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.venderlistforreport.VenderListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.venderlistforreport.VenderListForReport;
import com.asahikaseieng.dao.nonentity.master.venderlistforreport.VenderListForReportDao;
import com.asahikaseieng.exception.NoDataException;

/**
 * 取引先一覧ロジック 実装クラス.
 * @author t0011036
 */
public class VenderListLogicImpl implements VenderListLogic {

	private VenderListDao venderListDao;

	private VenderListForReportDao venderListForReportDao;

	/**
	 * コンストラクタ.
	 */
	public VenderListLogicImpl() {
	}

	/**
	 * 取引先一覧検索
	 * @param condition 検索条件
	 * @return List<VenderList>
	 * @throws NoDataException NoDataException
	 */
	public List<VenderList> getList(final VenderListPagerCondition condition)
			throws NoDataException {
		/* パラメータチェック */
		checkParams(condition);

		List<VenderList> list = venderListDao.getList(condition);

		if (list.isEmpty()) {
			throw new NoDataException();
		}

		return list;
	}

	/**
	 * パラメータチェック.
	 * @param condition 検索条件
	 */
	private void checkParams(final VenderListPagerCondition condition) {
		if (condition == null) {
			throw new IllegalArgumentException(
					" Paramater is illegal (getList)");
		}
	}

	/**
	 * 取引先一覧検索（帳票用）
	 * @param condition 検索条件
	 * @return List<VenderListForReport>
	 */
	public List<VenderListForReport> getListForReport(
			final VenderListConditionForReport condition) {
		List<VenderListForReport> list = venderListForReportDao
				.getListForReport(condition);

		if (list.isEmpty()) {
			return null;
		}

		return list;
	}

	/**
	 * 検索処理を行う.すべて
	 * @throws NoDataException NoDataException
	 * @return List<VenderList>
	 */
	public List<VenderList> getVenderList() throws NoDataException {

		List<VenderList> list = venderListDao.getVenderList();

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 検索処理を行う.条件：名称
	 * @param venderName 名称
	 * @throws NoDataException NoDataException
	 * @return List<VenderList>
	 */
	public List<VenderList> getSearchNameList(final String venderName)
			throws NoDataException {

		List<VenderList> list = venderListDao.getSearchNameList(venderName);

		if (list.isEmpty()) {
			throw new NoDataException();
		}
		return list;
	}

	/**
	 * 存在チェック処理を行う.
	 * @param venderDivi 取引先区分
	 * @param venderCd 取引先名称
	 * @throws NoDataException NoDataException
	 * @return List<VenderList>
	 */
	public VenderList checkVenderCd(final String venderDivi,
			final String venderCd) throws NoDataException {

		VenderList bean = venderListDao.checkVenderCd(venderDivi, venderCd);

		if (bean == null) {
			throw new NoDataException();
		}
		return bean;
	}

	/* -------------------- setter -------------------- */

	/**
	 * venderListDaoを設定します。
	 * @param venderListDao venderListDao
	 */
	public void setVenderListDao(final VenderListDao venderListDao) {
		this.venderListDao = venderListDao;
	}

	/**
	 * venderListForReportDaoを設定します。
	 * @param venderListForReportDao venderListForReportDao
	 */
	public void setVenderListForReportDao(
			final VenderListForReportDao venderListForReportDao) {
		this.venderListForReportDao = venderListForReportDao;
	}
}
