/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.eraser;

import com.asahikaseieng.dao.nonentity.claim.eraserlistforreport.ClaimEraserCsmListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.入金消込一覧
 * @author t0011036
 */
public interface EraserCsmListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final ClaimEraserCsmListConditionForReport condition);
}
