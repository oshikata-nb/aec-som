/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.payment.paymentbalance;

import com.asahikaseieng.dao.nonentity.payment.paymentbalancelistforreport.PaymentBalanceListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.支払残高一覧
 * @author t0011036
 */
public interface PaymentBalanceListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final PaymentBalanceListConditionForReport condition);
}
