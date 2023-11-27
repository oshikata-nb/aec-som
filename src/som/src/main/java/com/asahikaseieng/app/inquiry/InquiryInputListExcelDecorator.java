/*
 * Created on 2009/03/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inquiry;

import java.sql.Timestamp;
import java.util.List;

import com.asahikaseieng.dao.nonentity.inquiryinputlistforreport.InquiryInputListForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.棚卸入力一覧
 * @author t0011036
 */
public interface InquiryInputListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param list list
	 * @param tantoNm tantoNm
	 * @param currentDate currentDate
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final List<InquiryInputListForReport> list,
			final String tantoNm, final Timestamp currentDate);
}
