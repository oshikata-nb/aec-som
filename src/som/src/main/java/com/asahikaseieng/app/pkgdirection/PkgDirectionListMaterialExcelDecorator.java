/*
 * Created on 2009/06/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.pkgdirection;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 原材料使用品リストＥＸＣＥＬファイル作成用 interface.
 * @author t2712372
 */
public interface PkgDirectionListMaterialExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param directionNoArray 製造指図番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final ArrayList<String> directionNoArray,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress);
}
