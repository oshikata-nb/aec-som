/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.claim.billissue;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.asahikaseieng.dao.nonentity.claim.billissuelistforreport.BillIssueListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 請求書ＥＸＣＥＬファイル作成用 interface.
 * @author t2712372
 */
public interface BillIssueListExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param claimNo 請求番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final ArrayList<String> claimNo,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress, final BigDecimal srhNormalTemp);

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final BillIssueListConditionForReport condition);
}
