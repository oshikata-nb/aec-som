/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arledger;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.asahikaseieng.dao.nonentity.credit.arledgerlistforreport.RepArLedgerConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 売掛元帳ＥＸＣＥＬファイル作成用 interface.
 * @author t2712372
 */
public interface ArLedgerListExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition condition
	 * @param targetKbn 締め区分
	 * @param targetMonth 対象年月
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param request request
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final RepArLedgerConditionForReport condition,
			final String targetKbn, final String targetMonth,
			final String tantoName, final Timestamp currentDate,
			final HttpServletRequest request);

}
