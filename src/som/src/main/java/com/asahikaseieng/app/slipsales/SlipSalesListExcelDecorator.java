/*
 * Created on 2009/05/05
 
 * $copyright$
 *
 */
package com.asahikaseieng.app.slipsales;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 売上伝票ＥＸＣＥＬファイル作成用 interface.
 * @author t1344224
 */
public interface SlipSalesListExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param slipSalesNo 出荷伝票番号
	 * @param salesNo 出荷番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @param division division
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final ArrayList<String> slipSalesNo,
			final ArrayList<String> salesNo, final String tantoName,
			final Timestamp currentDate, final String ipAddress,
			final String division);

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param slipSalesNo 出荷伝票番号
	 * @param salesNo 出荷番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @param division division
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final ArrayList<String> slipSalesNo,
			final ArrayList<String> salesNo, final String tantoName,
			final Timestamp currentDate, final String ipAddress,
			final String division, BigDecimal seq);
}
