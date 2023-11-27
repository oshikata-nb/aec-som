/*
 * Created on 2009/06/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.buying;

import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 仕入伝票ＥＸＣＥＬファイル作成用 interface.
 * @author t2712372
 */
public interface BuyingListExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param buyingNo buyingNo
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param stockingDateFrom 仕入日From
	 * @param stockingDateTo 仕入日To
	 * @param request request
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final ArrayList<String> buyingNo,
			final String tantoName, final Timestamp currentDate,
			final String stockingDateFrom, final String stockingDateTo,
			final HttpServletRequest request);
}
