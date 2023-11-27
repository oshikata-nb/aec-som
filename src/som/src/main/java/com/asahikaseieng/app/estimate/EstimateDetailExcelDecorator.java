/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.estimate;

import java.sql.Timestamp;

import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 見積書ＥＸＣＥＬファイル作成用 interface.
 * @author t2712372
 */
public interface EstimateDetailExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param estimateNo 見積番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final String estimateNo,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress);
}
