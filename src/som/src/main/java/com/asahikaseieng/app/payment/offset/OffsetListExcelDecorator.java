/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.offset;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.asahikaseieng.dao.nonentity.payment.offsetlistforreport.OffsetListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 仕訳伝票（相殺伝票）ＥＸＣＥＬファイル作成用 interface.
 * @author t2712372
 */
public interface OffsetListExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param offsetNo 相殺番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final ArrayList<String> offsetNo,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress);

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final OffsetListConditionForReport condition);
}
