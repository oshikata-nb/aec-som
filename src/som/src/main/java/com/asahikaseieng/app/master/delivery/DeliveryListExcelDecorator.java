/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.delivery;

import com.asahikaseieng.dao.nonentity.master.deliverylistforreport.DeliveryListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.納入先マスタ一覧
 * @author t0011036
 */
public interface DeliveryListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final DeliveryListConditionForReport condition);
}
