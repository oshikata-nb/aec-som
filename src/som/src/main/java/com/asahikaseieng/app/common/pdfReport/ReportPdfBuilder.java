/*
 * Created on 2017/11/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.common.pdfReport;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.asahikaseieng.Constants;
import com.asahikaseieng.app.claim.deposit.DepositUtil;
import com.asahikaseieng.app.common.SendMailConstants;
import com.asahikaseieng.servlet.FileDownloadInfo;
import com.asahikaseieng.utils.AecDateUtils;

/**
 * ReportPdfBuilder
 * @author sh_shibata
 *
 */
public class ReportPdfBuilder {

	protected ResourceBundle rb = Constants.RB_REPORT_PROPERTIES;

	/**
	 * テンプレートファイル名のキー
	 */
	private String fileKey;

	/**
	 * ダウンロード時ファイル名のキー
	 */
	private String downloadKey;

	/**
	 * 個別ロジックのパラメータリスト
	 */
	protected List<String> params;

	/**
	 * コンストラクタ
	 */
	public ReportPdfBuilder() {
		super();
	}
	
	/**
	 * 一次ファイルを作成し、そのファイルパスを返却する.
	 * @param tanto
	 * @return
	 */
	public String create(final String tanto) {
		try {
			rb = ResourceBundle.getBundle("report");
			return createTemporaryFile(tanto);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * マクロ込の帳票をPDFに変換する
	 * @param tanto
	 * @return
	 */
	public String pdfConvert(final String tanto, final FileDownloadInfo info, BigDecimal seq) {
		try {
			rb = ResourceBundle.getBundle("report");
			return createTemporaryFile(tanto, info, seq);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * ダウンロード表示するファイル名を取得する.
	 * @return 表示するファイル名
	 */
	public String getResponseFileName(String tanto, Timestamp datetime) {
		rb = ResourceBundle.getBundle("report");

		return AecDateUtils.dateFormat(datetime, "yyyyMMddHHmmssS") + "_" + tanto + "_"
				+ rb.getString(StringUtils.replace(rb.getString("pdf.filename.template"), "key", getFileKey()))
				+ rb.getString("pdf.extension");
	}

	/**
	 * テンプレートのファイル名を取得する.
	 * @return デフォルトファイル名
	 */
	protected String getTemplateFileName() {
		return rb.getString(StringUtils.replace(rb
				.getString("pdf.filename.template"), "key", getFileKey())) + rb.getString("excel.extension");
	}

	/**
	 * 帳票作成アプリのEXE名を取得する
	 * @return
	 */
	protected String getCreatorExeName() {
		//テンプレートファイル名と同じ
		return rb.getString(StringUtils.replace(rb
				.getString("pdf.filename.template"), "key", getFileKey())) + ".exe";
	}

	/**
	 * PDF変換アプリのEXE名を取得する
	 * @return
	 */
	protected String getPdfConverterName() {
		return rb.getString("pdf.converter") + ".exe";
	}

	/**
	 * 一時ファイルを作成する.
	 * @return 一時ファイルパス
	 * @throws IOException I/O例外
	 * @throws InterruptedException
	 */
	protected String createTemporaryFile(final String tanto) throws IOException {

		/* 出力ファイル */
		File tempDir = SystemUtils.getJavaIoTmpDir();
		String path = tempDir + SystemUtils.FILE_SEPARATOR
				+ System.currentTimeMillis() + rb.getString("pdf.extension");

		/* 入力ファイル（テンプレート） */
		URL url = this.getClass().getResource(
				rb.getString("pdf.template.path") + getTemplateFileName());
		if (url == null) {
			throw new RuntimeException("テンプレートファイルがありません。" + getTemplateFileName());
		}
		File ifile = new File(url.getPath());

		/* 帳票アプリ */
		URL urlexe = this.getClass().getResource(
				rb.getString("pdf.reportcreator.path") + getCreatorExeName());
		if (urlexe == null) {
			throw new RuntimeException("帳票作成アプリの実行ファイルがありません。" + getCreatorExeName());
		}
		File exefile = new File(urlexe.getPath());

		try {
			callReportCreator(exefile.getPath(), tanto, ifile.getPath(), path);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return path;
	}
	
	/**
	 * XLSファイルからPDFファイルを作成する.
	 * @param tanto
	 * @param info　作成前のXLSファイルパス
	 * @return 作成されるPDFのファイルパス
	 * @throws IOException
	 */
	protected String createTemporaryFile(final String tanto, final FileDownloadInfo info, BigDecimal seq) throws IOException {

		/* 出力先にディレクトリがなければ作成 */
		File savDir = new File(rb.getString("pdf.template.path.mail"));
		if(!savDir.exists()){
			savDir.mkdirs();
		}
		
		/* 出力ファイル */
		String tempDir = rb.getString("pdf.template.path.mail");
		String path = tempDir + SystemUtils.FILE_SEPARATOR
				+ System.currentTimeMillis() + "_" + seq.toString() + rb.getString("pdf.extension");

		/* 入力ファイル（データ埋込済ファイル） */
		File ifile = new File(info.getPath());
		if (!ifile.exists()) {
			throw new RuntimeException("作成された帳票ファイルがありません。" + getTemplateFileName());
		}

		/* PDF変換アプリ */
		URL urlexe = this.getClass().getResource(
				rb.getString("pdf.reportcreator.path") + getPdfConverterName());
		if (urlexe == null) {
			throw new RuntimeException("PDF変換アプリの実行ファイルがありません。" + getPdfConverterName());
		}
		File exefile = new File(urlexe.getPath());

		try {
			callReportCreator(exefile.getPath(), tanto, ifile.getPath(), path);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return path;
	}

	/**
	 * 帳票作成アプリを呼び出して、一時ファイルを作成する
	 * @param exePath
	 * @param tanto
	 * @param iPath
	 * @param oPath
	 * @throws IOException
	 * @throws InterruptedException
	 */
	protected void callReportCreator(final String exePath
									, final String tanto
									, final String iPath
									, final String oPath) throws IOException, InterruptedException {

		//パラメータ作成
		List<String> args = new ArrayList<String>();
		args.add(exePath);
		args.add(tanto);
		args.add("ja");
		args.add(iPath);
		args.add(oPath);
		args.add("PDF");

		//　帳票取得条件
		if (this.params != null && this.params.size() > 0) {
			args.addAll(this.params);
		}

		if (getLog().isDebugEnabled()) {
			getLog().debug("exePath:" + exePath);
			getLog().debug("tanto:" + tanto);
			getLog().debug("iPath:" + iPath);
			getLog().debug("oPath:" + oPath);
		}

		//プロセス実行
		ProcessBuilder pb = new ProcessBuilder(args);
		Process p = pb.start();
		p.waitFor();

	}

	/**
	 * @return fileKey
	 */
	public String getFileKey() {
		return fileKey;
	}

	/**
	 * @param fileKey セットする fileKey
	 */
	public void setFileKey(String fileKey) {
		this.fileKey = fileKey;
	}

	/**
	 * @return params
	 */
	public List<String> getParams() {
		return params;
	}

	/**
	 * @param params セットする params
	 */
	public void setParams(List<String> params) {
		this.params = params;
	}

	/**
	 * Logオブジェクトを返す.
	 * @return Logオブジェクト
	 */
	private static Log getLog() {
		return LogFactory.getLog(DepositUtil.class);
	}
}
