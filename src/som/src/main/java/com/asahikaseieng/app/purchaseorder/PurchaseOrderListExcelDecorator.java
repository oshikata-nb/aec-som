/*
 * Created on 2009/05/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.purchaseorder;

import java.sql.Timestamp;
import java.util.ArrayList;

import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * 発注書（注文書）ＥＸＣＥＬファイル作成用 interface.
 * @author t2712372
 */
public interface PurchaseOrderListExcelDecorator {

	/**
	 * 作成したEXCEL情報を格納したFileDownloadInfoを作成.
	 * @param orderSheetNo 発注書No
	 * @param buySubcontractOrderNoArray 発注番号
	 * @param tantoName 出力担当者
	 * @param currentDate 出力日
	 * @param ipAddress ipAddress
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final String[] orderSheetNo,
			final ArrayList<String> buySubcontractOrderNoArray,
			final String tantoName, final Timestamp currentDate,
			final String ipAddress);
}
