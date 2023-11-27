/*
 * Created on 2007/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.company;

import com.asahikaseieng.dao.nonentity.master.companylistforreport.CompanyListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 自社マスタ一覧ＥＸＣＥＬファイル作成用 interface.
 * @author t0011036
 */
public interface CompanyListExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final CompanyListConditionForReport condition);
}
