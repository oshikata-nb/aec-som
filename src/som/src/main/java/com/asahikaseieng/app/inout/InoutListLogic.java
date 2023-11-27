/*
 * Created on 2008/03/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inout;

import java.util.List;

import com.asahikaseieng.dao.nonentity.inoutrecordlist.InoutRecordList;
import com.asahikaseieng.dao.nonentity.inoutrecordlist.InoutRecordListPagerCondition;
import com.asahikaseieng.dao.nonentity.inoutrecordlistforreport.InoutRecordListForReport;
import com.asahikaseieng.dao.nonentity.inoutrecordlistforreport.InoutRecordReportCondition;
import com.asahikaseieng.exception.NoDataException;

/**
 * 受払照会 ロジック interface.
 * @author FPC
 */
public interface InoutListLogic {
	/**
	 * Listメソッド
	 * 
	 * @param condition condition
	 * @return List<InoutRecordList>
	 * @throws NoDataException NoDataException
	 */
	List<InoutRecordList> getList(final InoutRecordListPagerCondition condition)
			throws NoDataException;

	/**
	 * 帳票用検索処理を行う.
	 * @param condition condition
	 * @throws NoDataException NoDataException
	 * @return List<InoutRecordListForReport>
	 */
	List<InoutRecordListForReport> getListForReport(
			final InoutRecordReportCondition condition) throws NoDataException;
}
