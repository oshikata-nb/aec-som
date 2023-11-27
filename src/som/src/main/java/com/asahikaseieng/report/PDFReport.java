/*
 * Created on 2007/04/19
 *
 * $copyright$
 *
 */
package com.asahikaseieng.report;

import java.sql.Connection;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import com.asahikaseieng.Constants;
import com.asahikaseieng.exception.ReportCreateException;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.commons.lang.SystemUtils;
import org.seasar.extension.jdbc.util.ConnectionUtil;
import org.seasar.extension.jdbc.util.DataSourceUtil;

/**
 * PDF用Report.
 * @author jbd
 */
public class PDFReport {

	private JasperPrint print;

	private ResourceBundle rb = Constants.RB_REPORT_PROPERTIES;

	/**
	 * コンストラクタ.
	 * 
	 */
	public PDFReport() {
	}

	/**
	 * ページを追加する.
	 * @param template テンプレート
	 * @param parameters パラメータ
	 * @throws ReportCreateException レポート作成例外
	 */
	@SuppressWarnings("unchecked")
	public final void addPage(final TemplateBuilder template,
			final Map parameters) throws ReportCreateException {

		try {
			JasperPrint jasperPrint = template.createJasperPrint(parameters);

			if (print == null) {
				// 初回は
				print = jasperPrint;
			} else {
				// ページを追加する
				List<JRPrintPage> pageList = (List<JRPrintPage>) jasperPrint
						.getPages();

				for (JRPrintPage jrpp : pageList) {
					print.addPage(jrpp);
				}
			}
		} catch (JRException e) {
			throw new ReportCreateException(e);
		}
	}

	/**
	 * pdfを作成しファイルパスを返す.
	 * @return ファイルパス
	 * @throws ReportCreateException レポート作成例外
	 */
	public final String create() throws ReportCreateException {

		try {
			// 一回ファイルに出力する
			String name = generateTemporaryFileName();

			// PDFファイル出力(いったんテンポラリディレクトリに出力する)
			JasperExportManager.exportReportToPdfFile(print, name);

			return name;

		} catch (JRException e) {
			throw new ReportCreateException(e);
		}
	}

	/**
	 * テンポラリーに出力ファイル名を返す.
	 * @return 一時ファイルの絶対パス
	 */
	private String generateTemporaryFileName() {
		return SystemUtils.getJavaIoTmpDir().getAbsolutePath()
				+ SystemUtils.FILE_SEPARATOR
				+ this.hashCode() + System.currentTimeMillis()
				+ rb.getString("pdf.extension");
	}

	/**
	 * pdfを作成しファイルパスを返す.
	 * @param ds データソース
	 * @param parameters パラメータ
	 * @param key jasperファイルのキー
	 * @return ファイルパス
	 * @throws ReportCreateException レポート作成例外
	 */
	public final String create(final DataSource ds, final Map parameters,
			final String key) throws ReportCreateException {

		Connection con = null;
		try {
			con = DataSourceUtil.getConnection(ds);

			String path = this.generateTemporaryFileName();
			String jasperFilePath = this.getClass().getResource(
				rb.getString("report.template.path")
						+ Constants.RB_REPORT_PROPERTIES.getString(key))
					.getPath();

			JasperRunManager.runReportToPdfFile(jasperFilePath, path,
				parameters, con);

			return path;

		} catch (JRException e) {
			throw new ReportCreateException(e);
		} finally {
			ConnectionUtil.close(con);
		}
	}
}
