/*
 * Created on 2017/11/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common.pdfReport;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Locale;

import com.asahikaseieng.dao.entity.master.mailtemplate.MailTemplate;
import com.asahikaseieng.servlet.FileDownloadInfo;

/**
 * ReportPdfBuilderクラス.
 * 
 * @author ssv_ogata
 */
public interface PdfReportDecorator {
	
	/**
	 * XLS -> PDF変換
	 * 
	 * @param frm
	 *            　出力データ
	 * @param locale
	 * @param tantoCd
	 *            　担当者コード
	 * 
	 */
	public FileDownloadInfo convertPdfForExcel(final String fileNameKey, 
			final String tantoCd, final String fileName,  final FileDownloadInfo info, final MailTemplate mailTemplate, BigDecimal seq) throws IOException;
}
