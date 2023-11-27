/*
 * Created on 2007/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.bumon;

import com.asahikaseieng.dao.nonentity.master.bumonlistforreport.BumonListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 会計部門マスタ一覧ＥＸＣＥＬファイル作成用 interface.
 * @author t0011036
 */
public interface BumonListExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final BumonListConditionForReport condition);
}
