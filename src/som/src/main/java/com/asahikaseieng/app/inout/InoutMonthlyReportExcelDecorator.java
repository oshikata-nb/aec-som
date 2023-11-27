/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inout;

import java.sql.Timestamp;

import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 受払月報ＥＸＣＥＬファイル作成用 interface.
 * @author t2712372
 */
public interface InoutMonthlyReportExcelDecorator {

	/**
	 * 受払月報ステータスセット
	 * @param dateFrom 開始月
	 * @param dateTo 終了月
	 * @param tantoCd 出力担当者コード
	 * @param status ステータス
	 */
	void setStatus(final String dateFrom, final String dateTo,
			final String tantoCd, final String status);

	/**
	 * 受払月報Temp作成
	 * @param dateFrom 開始月
	 * @param dateTo 終了月
	 * @param tantoCd 出力担当者コード
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 */
	void createTemp(final String dateFrom, final String dateTo,
			final String tantoCd, final String tantoName,
			final Timestamp currentDate);

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param dateFrom 開始月
	 * @param dateTo 終了月
	 * @param tantoCd 出力担当者コード
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final String dateFrom, final String dateTo,
			final String tantoCd, final String tantoName,
			final Timestamp currentDate);

}
