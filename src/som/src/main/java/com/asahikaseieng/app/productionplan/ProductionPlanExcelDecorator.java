/*
 * Created on 2007/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.productionplan;

import java.util.List;

import com.asahikaseieng.dao.nonentity.repproductionplanforreport.RepProductionPlanCalendar;
import com.asahikaseieng.dao.nonentity.repproductionplanforreport.RepProductionPlanForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 生産計画Excelファイル作成用 interface.
 * @author jbd
 */
public interface ProductionPlanExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param tantoCd tantoCd
	 * @param listAll listAll
	 * @param listKao listKao
	 * @param calendarList calendarList
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final String tantoCd,
			final List<RepProductionPlanForReport> listAll,
			final List<RepProductionPlanForReport> listKao,
			final List<RepProductionPlanCalendar> calendarList,
			final String ipAddress);
}
