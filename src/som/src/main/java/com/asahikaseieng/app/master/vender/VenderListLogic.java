/*
 * Created on 2007/03/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.vender;

import java.util.List;

import com.asahikaseieng.dao.nonentity.master.venderlist.VenderList;
import com.asahikaseieng.dao.nonentity.master.venderlist.VenderListPagerCondition;
import com.asahikaseieng.dao.nonentity.master.venderlistforreport.VenderListConditionForReport;
import com.asahikaseieng.dao.nonentity.master.venderlistforreport.VenderListForReport;
import com.asahikaseieng.exception.NoDataException;

/**
 * 取引先一覧ロジック interface.
 * @author t0011036
 */
public interface VenderListLogic {
	/**
	 * 検索処理を行う.
	 * @param condition 検索条件
	 * @throws NoDataException NoDataException
	 * @return List<VenderList>
	 */
	List<VenderList> getList(final VenderListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition 検索条件
	 * @return List<VenderListForReport>
	 */
	List<VenderListForReport> getListForReport(
			final VenderListConditionForReport condition);

	/**
	 * 検索処理を行う.
	 * @throws NoDataException NoDataException
	 * @return List<VenderList>
	 */
	List<VenderList> getVenderList() throws NoDataException;

	/**
	 * 検索処理を行う.
	 * @param venderName 名称
	 * @throws NoDataException NoDataException
	 * @return List<VenderList>
	 */
	List<VenderList> getSearchNameList(final String venderName)
			throws NoDataException;

	/**
	 * 存在チェック処理を行う.
	 * @param venderDivi 取引先区分
	 * @param venderCd 取引先コード
	 * @throws NoDataException NoDataException
	 * @return List<VenderList>
	 */
	VenderList checkVenderCd(final String venderDivi, final String venderCd)
			throws NoDataException;
}
