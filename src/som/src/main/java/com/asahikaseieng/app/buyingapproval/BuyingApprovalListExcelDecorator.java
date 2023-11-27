/*
 * Created on 2009/08/21
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.buyingapproval;

import com.asahikaseieng.dao.nonentity.buyingapprovalforreport.BuyingApprovalListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.入荷一覧
 * @author t1344224
 */
public interface BuyingApprovalListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final BuyingApprovalListConditionForReport condition);
}
