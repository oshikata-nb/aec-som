/*
 * Created on 2009/06/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.inventory;

import java.sql.Timestamp;

import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 製品ラベルＥＸＣＥＬファイル作成用 interface.
 * @author t2712372
 */
public interface InventoryDetailExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param frm 登録データ
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final InventoryDetailForm frm,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress);
}
