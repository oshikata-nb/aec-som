/*
 * Created on 2009/07/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.payment;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.asahikaseieng.dao.nonentity.payment.paymentlistforreport.PaymentListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ＥＸＣＥＬファイル作成用 interface.
 * @author t2712372
 */
public interface PaymentCsmListExcelDecorator {

	/**
	 * 仕訳伝票（支払伝票） 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param slipNo 伝票番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final ArrayList<String> slipNo,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress);

	/**
	 * 手形振出指示書 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param slipNo 伝票番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReportTegata(final ArrayList<String> slipNo,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress);

	/**
	 * お支払通知書 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param slipNo 伝票番号
	 * @param tantoName 出力担当者
	 * @param loginUserOrganizationName ログイン者部署
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReportPaymentInform(final ArrayList<String> slipNo,
			final String tantoName, final String loginUserOrganizationName,
			final Timestamp currentDate, final String ipAddress);

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final PaymentListConditionForReport condition);
}
