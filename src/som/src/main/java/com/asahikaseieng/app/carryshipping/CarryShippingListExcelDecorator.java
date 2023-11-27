/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.carryshipping;

import com.asahikaseieng.dao.nonentity.carryshippingforreport.CarryShippingListConditionForReport;
import com.asahikaseieng.dao.nonentity.repcarryshippingforreport.RepCarryShippingForReportCondition;
import com.asahikaseieng.exception.NoDataException;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.出荷指図一覧
 * @author t1344224
 */
public interface CarryShippingListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final CarryShippingListConditionForReport condition);

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 * @throws NoDataException データが存在しない例外
	 */
	FileDownloadInfo createReportCarryShipping(
			final RepCarryShippingForReportCondition condition)
			throws NoDataException;
}
