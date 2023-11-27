/*
 * Created on 2008/10/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.sales;

import java.util.List;

import com.asahikaseieng.dao.nonentity.saleslistforreport.SalesInoutListForReport;
import com.asahikaseieng.dao.nonentity.saleslistforreport.SalesListForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorインターフェイス.売上
 * @author tosco
 */
public interface SalesListExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param list list
	 * @param listInout listInout
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final List<SalesListForReport> list,
			final List<SalesInoutListForReport> listInout);
}
