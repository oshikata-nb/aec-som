/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.purchasedelivery;

import com.asahikaseieng.dao.nonentity.purchasedeliveryforreport.PurchaseDeliveryListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.納期回答一覧
 * @author t1344224
 */
public interface PurchaseDeliveryListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final PurchaseDeliveryListConditionForReport condition);
}
