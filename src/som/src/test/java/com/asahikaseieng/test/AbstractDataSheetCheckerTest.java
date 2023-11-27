/*
 * Created on 2007/04/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.test;

import java.util.Calendar;

import junit.framework.TestCase;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * AbstractDataSheetCheckerテスト.
 * @author jbd
 */
public final class AbstractDataSheetCheckerTest extends TestCase {

	private HSSFWorkbook workbook;

	private HSSFSheet sheet1;

	private HSSFSheet sheet2;

	private HSSFCell cell1;

	private HSSFCell cell2;

	/**
	 * Constructor for AbstractDataSheetCheckerTest.
	 * @param arg0 arg0
	 */
	public AbstractDataSheetCheckerTest(final String arg0) {
		super(arg0);
		// 初期化
		workbook = null;
		sheet1 = null;
		sheet2 = null;
		cell1 = null;
		cell2 = null;
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setUp() throws Exception {
		super.setUp();

		// ワークブックとシートを作る
		workbook = new HSSFWorkbook();
		sheet1 = workbook.createSheet("sheet1");
		sheet2 = workbook.createSheet("sheet2");

		// Cell(1,1)に書き込み
		cell1 = sheet1.createRow((short) 0).createCell((short) 0);
		cell2 = sheet2.createRow((short) 0).createCell((short) 0);
	}

	/**
	 * {@inheritDoc}
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * testCheckSheet01.
	 */
	public void testCheckSheet01() {
		/** *正常系テスト(文字列) */
		HSSFRichTextString richString;

		richString = new HSSFRichTextString("a");
		cell1.setCellValue(richString);
		cell2.setCellValue(richString);
		assertEquals(true, AbstractDataSheetChecker.checkSheet(sheet1, sheet2));
	}

	/**
	 * testCheckSheet02.
	 */
	public void testCheckSheet02() {
		/** *正常系テスト(数値) */
		cell1.setCellValue(12345.67);
		cell2.setCellValue(12345.67);

		assertEquals(true, AbstractDataSheetChecker.checkSheet(sheet1, sheet2));
	}

	/**
	 * testCheckSheet03.
	 */
	public void testCheckSheet03() {
		/** *正常系テスト(ブランク) */
		HSSFRichTextString richString;

		richString = new HSSFRichTextString("");
		cell1.setCellValue(richString);
		cell2.setCellValue(richString);

		assertEquals(true, AbstractDataSheetChecker.checkSheet(sheet1, sheet2));
	}

	/**
	 * testCheckSheet04.
	 */
	public void testCheckSheet04() {
		/** *正常系テスト(計算式) */
		cell1.setCellFormula("SUM(B1:C1)");
		cell2.setCellFormula("SUM(B1:C1)");

		assertEquals(true, AbstractDataSheetChecker.checkSheet(sheet1, sheet2));
	}

	/**
	 * testCheckSheet05.
	 */
	public void testCheckSheet05() {
		/** *正常系テスト(日付) */
		HSSFCellStyle style = workbook.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
		Calendar cal = Calendar.getInstance();
		cell1.setCellStyle(style);
		cell2.setCellStyle(style);
		cell1.setCellValue(cal);
		cell2.setCellValue(cal);

		assertEquals(true, AbstractDataSheetChecker.checkSheet(sheet1, sheet2));
	}

	/**
	 * testCheckSheet06.
	 */
	public void testCheckSheet06() {
		/** *異常系テスト(文字列) */
		HSSFRichTextString richString;

		richString = new HSSFRichTextString("a");
		cell1.setCellValue(richString);
		richString = new HSSFRichTextString("b");
		cell2.setCellValue(richString);

		assertEquals(false, AbstractDataSheetChecker.checkSheet(sheet1, sheet2));
	}

	/**
	 * testCheckSheet07.
	 */
	public void testCheckSheet07() {
		/** *異常系テスト(数値) */
		cell1.setCellValue(12345.67);
		cell2.setCellValue(12345.68);

		assertEquals(false, AbstractDataSheetChecker.checkSheet(sheet1, sheet2));
	}

	/**
	 * testCheckSheet08.
	 */
	public void testCheckSheet08() {
		/** *異常系テスト(ブランク) */
		HSSFRichTextString richString;
		richString = new HSSFRichTextString("");
		cell1.setCellValue(richString);
		richString = new HSSFRichTextString("a");
		cell2.setCellValue(richString);

		assertEquals(false, AbstractDataSheetChecker.checkSheet(sheet1, sheet2));
	}

	/**
	 * testCheckSheet09.
	 */
	public void testCheckSheet09() {
		/** *異常系テスト(計算式) */
		cell1.setCellFormula("SUM(B1:C1)");
		cell2.setCellFormula("SUM(B1:C2)");

		assertEquals(false, AbstractDataSheetChecker.checkSheet(sheet1, sheet2));
	}

	/**
	 * testCheckSheet10.
	 */
	public void testCheckSheet10() {
		/** *異常系テスト(日付) */
		Calendar cal = Calendar.getInstance();

		HSSFCellStyle style = workbook.createCellStyle();
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
		cell1.setCellStyle(style);
		cell2.setCellStyle(style);
		cell1.setCellValue(cal);
		// 日付を1日進める。
		cal.add(Calendar.DATE, 1);
		cell2.setCellValue(cal);

		assertEquals(false, AbstractDataSheetChecker.checkSheet(sheet1, sheet2));
	}

	/**
	 * testCheckSheet11.
	 */
	public void testCheckSheet11() {
		/** *異常系テスト(行数異常) */
		HSSFRichTextString richString;

		richString = new HSSFRichTextString("a");
		cell1.setCellValue(richString);
		cell2.setCellValue(richString);

		richString = new HSSFRichTextString("c");
		HSSFCell cell = sheet1.createRow((short) 1).createCell((short) 0);
		cell.setCellValue(richString);

		assertEquals(false, AbstractDataSheetChecker.checkSheet(sheet1, sheet2));
	}

	/**
	 * testCheckSheet12.
	 */
	public void testCheckSheet12() {
		/** *異常系テスト(Cell数異常) */
		HSSFRichTextString richString;

		richString = new HSSFRichTextString("a");
		cell1.setCellValue(richString);
		cell2.setCellValue(richString);

		richString = new HSSFRichTextString("c");
		HSSFCell cell = sheet1.getRow(0).createCell((short) 1);
		cell.setCellValue(richString);

		assertEquals(false, AbstractDataSheetChecker.checkSheet(sheet1, sheet2));
	}

	/**
	 * testCheckSheet13.
	 */
	public void testCheckSheet13() {
		/** *異常系テスト(Cell位置異常) */
		HSSFRichTextString richString;

		richString = new HSSFRichTextString("a");
		cell1.setCellValue(richString);
		cell2.setCellValue(richString);

		richString = new HSSFRichTextString("c");
		HSSFCell cell3 = sheet1.getRow(0).createCell((short) 1);
		cell3.setCellValue(richString);
		HSSFCell cell4 = sheet2.getRow(0).createCell((short) 2);
		cell4.setCellValue(richString);

		assertEquals(false, AbstractDataSheetChecker.checkSheet(sheet1, sheet2));
	}

	/**
	 * testCheckSheet14.
	 */
	public void testCheckSheet14() {
		/** *異常系テスト(CellType異常) */
		HSSFRichTextString richString;
		richString = new HSSFRichTextString("a");
		cell1.setCellValue(richString);
		cell2.setCellValue(1);

		assertEquals(false, AbstractDataSheetChecker.checkSheet(sheet1, sheet2));
	}
}
