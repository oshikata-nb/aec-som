/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.paymentplan;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.asahikaseieng.dao.nonentity.payment.paymentplanlistforreport.PaymentPlanListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.支払予定一覧
 * @author t0011036
 */
public interface PaymentPlanListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final PaymentPlanListConditionForReport condition);

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

}
