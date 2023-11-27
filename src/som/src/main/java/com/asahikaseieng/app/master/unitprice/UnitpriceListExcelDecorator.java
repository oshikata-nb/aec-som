/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.unitprice;

import com.asahikaseieng.dao.nonentity.master.unitpriceiistforreport.UnitpriceListConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.仕入先別単価マスタ一覧
 * @author t0011036
 */
public interface UnitpriceListExcelDecorator {
	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(
			final UnitpriceListConditionForReport condition);
}
