/*
 * Created on 2009/05/25
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.rdirection;

import java.sql.Timestamp;

import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 製造記録ＥＸＣＥＬファイル作成用 interface.
 * @author t2712372
 */
public interface RdirectionListExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param directionNo 製造指図番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final String[] directionNo,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress);
}
