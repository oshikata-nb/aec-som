/*
 * Created on 2009/06/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.arrival;

import java.sql.Timestamp;
import java.util.TreeMap;

import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 予備溶解ラベルＥＸＣＥＬファイル作成用 interface.
 * @author t2712372
 */
public interface ArrivalListExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param tMap 包装指図番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final TreeMap<String, String> tMap,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress);
}
