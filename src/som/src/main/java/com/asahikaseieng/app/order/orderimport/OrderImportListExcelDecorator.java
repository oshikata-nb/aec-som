/*
 * Created on 2020/09/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.order.orderimport;

import java.math.BigDecimal;
import java.util.List;

import com.asahikaseieng.dao.nonentity.orderimportlist.OrderImportList;
import com.asahikaseieng.dao.nonentity.orderimportlistforreport.OrderImportListConditionForReport;
import com.asahikaseieng.dao.nonentity.orderimportslipforreport.RepOrderImportSlipConditionForReport;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ExcelDecoratorクラス.受注取込一覧
 * @author 
 */
public interface OrderImportListExcelDecorator {
	/**
	 * 帳票EXCEL情報を格納したFileDownloadInfoを作成.
	 * @param condition 検索条件
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createReport(final OrderImportListConditionForReport condition);

	/**
	 * 納期連絡表情報を格納したFileDownloadInfoを作成
	 * @param pkNoList List<String>
	 * @param pkRowList List<BigDecimal>
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createDeliveryDateContact(final List<OrderImportList> checkedList , final String tantoCd , List<String> checkedFrstOrderNoList);
	
	/**　20220530
	 * 納期連絡表情報を格納したFileDownloadInfoを作成 PDF専用
	 * @param pkNoList List<String>
	 * @param pkRowList List<BigDecimal>
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createDeliveryDateContact(final List<OrderImportList> checkedList , final String tantoCd , List<String> checkedFrstOrderNoList,BigDecimal seq);

	/**
	 * 受注一覧票情報を格納したFileDownloadInfoを作成
	 * @param condition
	 * @return FileDownloadInfo
	 */
	FileDownloadInfo createOrderReport(final RepOrderImportSlipConditionForReport condition);
	
	public void updateDelDateSender(final String tantoCd,
			List<String> checkedFrstOrderNoList);

}