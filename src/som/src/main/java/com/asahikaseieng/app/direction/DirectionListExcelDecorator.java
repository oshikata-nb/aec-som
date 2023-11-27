/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.direction;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 製造指図一覧ＥＸＣＥＬファイル作成用 interface.
 * @author t2712372
 */
public interface DirectionListExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param directionNo 製造指図番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param request HttpServletRequest
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final String[] directionNo,
			final String tantoName, final Timestamp currentDate,
			final HttpServletRequest request);
}
