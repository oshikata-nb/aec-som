/*
 * Created on 2007/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import com.asahikaseieng.poi.WorkbookCache;
import junit.framework.TestCase;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * WorkbookCacheテスト.
 * @author jbd
 */
public class WorkbookCacheTest extends TestCase {

	private static WorkbookCache wc;

	private URL url;

	/**
	 * Constructor for WorkbookCacheTest.
	 * @param arg0 arg0
	 */
	public WorkbookCacheTest(final String arg0) {
		super(arg0);
		// 初期化
		wc = WorkbookCache.getInstance();
		url = null;
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUp() throws Exception {
		super.setUp();
		wc.clear();
		url = this.getClass().getResource("/template/test.xls");
	}

	/**
	 * {@inheritDoc}
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * ファイルWrite.
	 * @param url URL
	 * @param value 値
	 */
	private void writeFile(final URL url, final String value) {
		// ワークブックとシートを作る
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("Sheet1");

		try {
			// Cell(1,1)に書き込み
			HSSFCell cell = sheet.createRow((short) 0).createCell((short) 0);
			cell.setEncoding(HSSFCell.ENCODING_UTF_16);
			HSSFRichTextString richString = new HSSFRichTextString(value);
			cell.setCellValue(richString);

			FileOutputStream fileOut = new FileOutputStream(url.getPath());
			workbook.write(fileOut);
			fileOut.close();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	/**
	 * Cell値取得.
	 * @param workbook Workbook
	 * @return 文字列
	 */
	protected String getCellValue(final HSSFWorkbook workbook) {
		HSSFSheet sheet = workbook.getSheet("Sheet1");
		HSSFCell cell = sheet.getRow((short) 0).getCell((short) 0);
		return cell.getStringCellValue();
	}

	/**
	 * testClear.
	 * @throws Exception 例外
	 */
	public void testClear() throws Exception {
		wc.clear();
	}

	/**
	 * testGetPOIFSFileSystem01.
	 */
	public void testGetPOIFSFileSystem01() {
		/** * 正常テスト(取得確認) */
		try {
			writeFile(url, "testGetPOIFSFileSystem01");
			HSSFWorkbook book = wc.getWorkbook(url);
			assertNotNull(book);

			assertEquals("testGetPOIFSFileSystem01", getCellValue(book));
		} catch (IOException e) {
			e.printStackTrace();
			fail("IOException!!");
		}
	}

	/**
	 * testGetPOIFSFileSystem02.
	 * @throws Exception 例外
	 */
	public void testGetPOIFSFileSystem02() throws Exception {
		/** * 正常系テスト(キャッシュ確認) */

		writeFile(url, "testGetPOIFSFileSystem02");
		HSSFWorkbook book = wc.getWorkbook(url);
		assertNotNull(book);
		assertEquals("testGetPOIFSFileSystem02", getCellValue(book));

		Thread.sleep(1000);

		wc.getWorkbook(url);
		assertNotNull(book);
		assertEquals("testGetPOIFSFileSystem02", getCellValue(book));

	}

	/**
	 * testGetPOIFSFileSystem03.
	 * @throws Exception 例外
	 */
	public void testGetPOIFSFileSystem03() throws Exception {
		/** * 正常系テスト(変化取得確認) */
		writeFile(url, "testGetPOIFSFileSystem03");
		HSSFWorkbook book = wc.getWorkbook(url);
		assertNotNull(book);
		assertEquals("testGetPOIFSFileSystem03", getCellValue(book));

		Thread.sleep(1000);

		writeFile(url, "modify_testGetPOIFSFileSystem03");
		book = wc.getWorkbook(url);
		assertNotNull(book);
		assertEquals("modify_testGetPOIFSFileSystem03", getCellValue(book));

	}

	/**
	 * testGetPOIFSFileSystem04.
	 */
	public void testGetPOIFSFileSystem04() {
		/** * 異常系テスト(ファイル取得不可) */
		try {
			url = this.getClass().getResource("hoge.xls");
			wc.getWorkbook(url);
			fail("Should raise a Exception");
		} catch (IOException e) {
			;
		}
	}

	/**
	 * testGetPOIFSFileSystem05.
	 */
	public void testGetPOIFSFileSystem05() {
		/** * 異常系テスト(拡張子異常) */
		try {
			url = this.getClass().getResource("WorkbookCacheTest.class");
			wc.getWorkbook(url);
			fail("Should raise a Exception");
		} catch (IOException e) {
			;
		}
	}
}
