/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.shippingresult;

import com.asahikaseieng.dao.nonentity.shippingresultforreport.ShippingResultListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.出荷実績一覧
 * @author t1344224
 */
public interface ShippingResultListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final ShippingResultListConditionForReport condition);
}
