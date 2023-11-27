/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.purchaseorder;

import com.asahikaseieng.dao.nonentity.purchaseorderforreport.PurchaseOrderListConditionForRepor;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.発注書一覧
 * @author t1344224
 */
public interface PurchaseOrderReportListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final PurchaseOrderListConditionForRepor condition);
}
