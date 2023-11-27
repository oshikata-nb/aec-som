/*
 * Created on 2007/04/26
 *
 * $copyright$
 *
 */
package com.asahikaseieng.report;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.asahikaseieng.exception.ReportCreateException;
import com.asahikaseieng.test.AbstractReportS2TestCaseBase;

/**
 * PDFBuilderTestクラス.
 * @author jbd
 */
public class PDFReportTest extends AbstractReportS2TestCaseBase {

	private PDFReport report;

	private DataSource ds;

	/**
	 * コンストラクタ.
	 * @param testname testname
	 */
	public PDFReportTest(final String testname) {
		super(testname);
	}

	/**
	 * dsを設定します。
	 * @param ds ds
	 */
	public void setDs(final DataSource ds) {
		this.ds = ds;
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUp() throws Exception {
		include("app.dicon");
		report = new PDFReport();
	}

	/**
	 * testAddPage.
	 * @throws Exception Exception
	 */
	public void testAddPage() throws Exception {

		File file = null;
		/* 正常に作成される */
		try {
			/* パラメータを作成 */
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("TEST", "test");

			/* ページを作成 */
//			report.addPage(new ShippingListTemplateBuilder(), parameters);
//			report.addPage(new ShippingListTemplateBuilder(), parameters);

		} finally {
			if (file != null) {
				file.deleteOnExit();
			}
		}

		/* ページ追加時にエラー：パラメータ異常 */
		try {
			/* パラメータを作成 */
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("TEST", new ArrayList<String>());

			/* PDFを作成 */
//			report.addPage(new ShippingListTemplateBuilder(), null);

			fail("raise ReportCreateException");

//		} catch (ReportCreateException e) {
//			;
		} finally {
			if (file != null) {
				file.deleteOnExit();
			}
		}
	}

	/**
	 * testCreate.
	 * @throws Exception Exception
	 */
	public void testCreate() throws Exception {

		File file = null;
		/* 正常に作成される */
		try {
			/* パラメータを作成 */
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("TEST", "test");

			/* PDFを作成 */
//			report.addPage(new ShippingListTemplateBuilder(), parameters);
			String path = report.create();

			/** * ファイルが出来ているかを確認 */
			file = new File(path);
			assertEquals(true, file.exists());

		} finally {
			if (file != null) {
				file.deleteOnExit();
			}
		}

		/* レポート作成時にエラー：PDFReportのprintに異常あり */
		try {
			/* パラメータを作成 */
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("TEST", new ArrayList<String>());

			PDFReport rep = new PDFReport();
			rep.create();

			fail("raise ReportCreateException");

		} catch (ReportCreateException e) {
			;
		} finally {
			if (file != null) {
				file.deleteOnExit();
			}
		}
	}

	/**
	 * testCreate2.
	 * @throws Exception Exception
	 */
	public void testCreate2() throws Exception {

		File file = null;
		try {
			/* パラメータを作成 */
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("TEST", "test");

			/* PDFを作成 */
			String path = report.create(ds, parameters, "template.test");

			/** * ファイルが出来ているかを確認 */
			file = new File(path);
			assertEquals(true, file.exists());

		} finally {
			if (file != null) {
				file.deleteOnExit();
			}
		}

		/* レポート作成時にエラー：パラメータ異常 */
		try {
			/* パラメータを作成 */
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("TEST", new ArrayList<String>());

			/* PDFを作成 */
			report.create(ds, parameters, "template.test");

			fail("raise ReportCreateException");

		} catch (ReportCreateException e) {
			;
		} finally {
			if (file != null) {
				file.deleteOnExit();
			}
		}

	}
}
