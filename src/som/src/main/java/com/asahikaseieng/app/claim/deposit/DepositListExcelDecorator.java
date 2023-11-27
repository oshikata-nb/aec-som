/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.deposit;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.asahikaseieng.dao.nonentity.claim.depositlistforreport.DepositCreditListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 仕訳伝票（入金伝票）ＥＸＣＥＬファイル作成用 interface.
 * @author t2712372
 */
public interface DepositListExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param creditNo 請求番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final ArrayList<String> creditNo,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress);

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final DepositCreditListConditionForReport condition);
}
