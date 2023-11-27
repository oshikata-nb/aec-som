/*
 * Created on 2008/10/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inout;

import java.util.List;

import com.asahikaseieng.dao.nonentity.inoutrecordlistforreport.InoutRecordListForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorインターフェイス.ロケーション別受払照会
 * @author tosco
 */
public interface InoutListExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param list list
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final List<InoutRecordListForReport> list);
}
