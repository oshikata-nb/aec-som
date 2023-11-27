/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.slipshipping;

import com.asahikaseieng.dao.nonentity.slipshippinglistforreport.SlipShippingListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.出荷指図一覧
 * @author t1344224
 */
public interface SlipShippingListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final SlipShippingListConditionForReport condition);
}
