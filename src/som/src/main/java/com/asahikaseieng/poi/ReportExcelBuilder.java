/*
 * Created on 2007/04/17
 *
 * $copyright$
 *
 */
package com.asahikaseieng.poi;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.hssf.util.RangeAddress;
import org.apache.poi.hssf.util.Region;

import com.asahikaseieng.Constants;

/**
 * ReportExcelBuilderの基底クラス.
 * @author jbd
 */
public class ReportExcelBuilder {

	/* 列番号の最大値 */
	private static final short MAX_COL = 255;

	/* プリンタ定義ブック名 */
	private static final String SETTING_BOOK_NAME = "report_out_printer_setting";

	/* プリンタ定義データシート名 */
	private static final String SETTING_SHEET_NAME = "setting";

	/* プリンタ定義シート行番号の最大値 */
	private static final short MAX_SETTING_ROW = 10000;

	/* プリンタ定義シート列番号の最大値 */
	private static final short MAX_SETTING_COL = 50;

	/* EXCELの列を検索するためのリスト */
	private static final List<String> columnList;

	static {
		List<String> list = new ArrayList<String>();
		for (char x = 'A'; x <= 'Z'; x++) {
			list.add("" + x);
		}

		char before = 'A';
		for (int i = 0; i < 2; i++) {
			for (char x = 'A'; x <= 'Z'; x++) {
				list.add(before + "" + x);
			}
			before++;
		}
		columnList = list;
	}

	private static final ResourceBundle rb = Constants.RB_REPORT_PROPERTIES;

	private final int setByte = 8192;

	private HSSFWorkbook workbook;

	private HSSFSheet sheet;

	private URL workbookUrl;

	/* キーとなるテンプレートファイル名 */
	private String fileKey;

	/* デフォルトのセルスタイルが設定してある行番号 */
	private short defaultCellStyleRowNum;

	/* セルに対して設定するスタイル(種類ごとに作成…限界数がある為、セルごとにスタイルを作成するのは禁止！) */

	/* ↓セルのスタイルの種類ごとに増やす(createStyle1参照) */
	private HSSFCellStyle style1;

	/**
	 * コンストラクタ
	 */
	public ReportExcelBuilder() {
		super();
	}

	/**
	 * workbookUrlを取得します。
	 * @return workbookUrl
	 */
	public URL getWorkbookUrl() {
		return workbookUrl;
	}

	/**
	 * workbookUrlを設定します。
	 * @param workbookUrl workbookUrl
	 */
	public void setWorkbookUrl(final URL workbookUrl) {
		this.workbookUrl = workbookUrl;
	}

	/**
	 * fileKeyを取得します。
	 * @return fileKey
	 */
	private String getFileKey() {
		return fileKey;
	}

	/**
	 * fileKeyを設定します。
	 * @param fileKey fileKey
	 */
	private void setFileKey(final String fileKey) {
		this.fileKey = fileKey;
	}

	/**
	 * テンプレートからWorkbook作成.
	 * @throws IOException I/O例外
	 */
	protected void readTemplate() throws IOException {
		// テンプレートからWorkbook作成
		workbook = WorkbookCache.getInstance().getWorkbook(getWorkbookUrl());
	}

	/**
	 * Workbookを保存する.
	 * @param file ファイルパス
	 * @throws IOException I/O例外
	 */
	private void save(final File file) throws IOException {
		if (workbook == null || sheet == null) {
			throw new IllegalStateException();
		}

		FileOutputStream fileOut = null;
		try {
			fileOut = new FileOutputStream(file);
			workbook.write(fileOut);
		} finally {
			if (fileOut != null) {
				fileOut.close();
			}
			// workbookをnullにしておく
			workbook = null;
		}
	}

	/**
	 * ファイルパスを取得する.
	 * @return ファイルパス
	 */
	public String create() {
		try {
			return createTemporaryFile();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**　20220530　S.FujimakiAdd
	 * PDF専用ファイルパスを取得する.
	 * @return ファイルパス
	 */
	public String createPdfExcel(BigDecimal seq) {
		try {
			return createTemporaryFilePdfExcel(seq);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * デフォルトのファイル名を取得する.
	 * @return デフォルトファイル名
	 */
	public String getDefaultFileName() {
		return rb.getString(StringUtils.replace(rb
				.getString("excel.filename.template"), "key", getFileKey()))
				+ rb.getString("excel.extension");
	}

	/**
	 * デフォルトのファイル名を取得する.
	 * @return デフォルトファイル名
	 */
	public String getDefaultFileNameAddName(final String fileName) {
		return rb.getString(StringUtils.replace(rb
				.getString("excel.filename.template"), "key", getFileKey())) + "_" + fileName + "_"
				+ rb.getString("excel.extension");
	}

	/**
	 * バイナリデータを取得する.
	 * @return byte
	 */
	public byte[] getBytes() {
		File file = null;
		try {
			file = new File(createTemporaryFile());

			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(file));
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			int n;
			byte[] buf = new byte[setByte];

			while ((n = in.read(buf)) != -1) {
				out.write(buf, 0, n);
			}
			out.flush();
			byte[] bytes = out.toByteArray();

			out.close();
			in.close();

			return bytes;

		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (file != null) {
				file.deleteOnExit();
			}
		}
	}

	/**
	 * 一時ファイルを作成する.
	 * @return 一時ファイルパス
	 * @throws IOException I/O例外
	 */
	protected String createTemporaryFile() throws IOException {
		if (workbook == null || sheet == null) {
			throw new IllegalStateException();
		}

		// 一回ファイルに出力する
		File tempDir = SystemUtils.getJavaIoTmpDir();
		String path = tempDir + SystemUtils.FILE_SEPARATOR
				+ System.currentTimeMillis() + rb.getString("excel.extension");

		File file = new File(path);

		save(file);
		return path;
	}
	
	/** 20220530 S.Fujimaki add
	 * PDF専用一時ファイルを作成する.
	 * @return 一時ファイルパス
	 * @throws IOException I/O例外
	 */
	protected String createTemporaryFilePdfExcel(BigDecimal seq) throws IOException {
		if (workbook == null || sheet == null) {
			throw new IllegalStateException();
		}

		// 一回ファイルに出力する
		File tempDir = SystemUtils.getJavaIoTmpDir();
		String path = tempDir + SystemUtils.FILE_SEPARATOR
				+ System.currentTimeMillis() + "_" + seq + rb.getString("excel.extension");

		File file = new File(path);

		save(file);
		return path;
	}

	/**
	 * シートを設定する.
	 * @param sheetName シート名
	 */
	public void setSheet(final String sheetName) {
		if (workbook == null) {
			try {
				readTemplate();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		sheet = workbook.getSheet(sheetName);
	}

	/**
	 * シートを設定する.
	 * @param index index番号
	 */
	public void setSheet(final int index) {
		if (workbook == null) {
			try {
				readTemplate();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		sheet = workbook.getSheetAt(index);
	}

	/**
	 * 値を取得する.
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @return 値(String)
	 */
	public String getStringCellValue(final short rowNum, final short columnNum) {
		if (sheet == null) {
			throw new IllegalStateException();
		}
		HSSFCell cell = sheet.getRow(rowNum).getCell(columnNum);
		return cell.getStringCellValue();
	}

	/**
	 * 値を取得する.
	 * @param namedArea 名前付エリア
	 * @return 値(String)
	 */
	public String getStringCellValue(final String namedArea) {
		CellReference fromCell = getCellReferenceByName(namedArea);
		return getStringCellValue((short) fromCell.getRow(), fromCell.getCol());
	}

	/**
	 * 名前付エリアをセル参照に変換する. 注意：sheetが書き換わります.
	 * @param namedArea 名前付エリア
	 * @return CellReference セル参照
	 */
	private CellReference getCellReferenceByName(final String namedArea) {
		if (workbook == null) {
			throw new IllegalStateException();
		}
		int nameIndex = workbook.getNameIndex(namedArea);
		if (nameIndex < 0) {
			throw new IllegalStateException();
		}
		HSSFName hssfName = workbook.getNameAt(nameIndex);
		setSheet(hssfName.getSheetName());
		RangeAddress address = new RangeAddress(hssfName.getReference());
		CellReference fromCell = new CellReference(address.getFromCell());
		// CellReference toCell = new CellReference(address.getToCell());
		return fromCell;
	}

	/**
	 * 値を取得する.
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @return 値(double)
	 */
	public double getNumericCellValue(final short rowNum, final short columnNum) {
		if (sheet == null) {
			throw new IllegalStateException();
		}
		HSSFCell cell = sheet.getRow(rowNum).getCell(columnNum);
		return cell.getNumericCellValue();
	}

	/**
	 * 値を取得する.
	 * @param namedArea 名前付エリア
	 * @return 値(double)
	 */
	public double getNumericCellValue(final String namedArea) {
		CellReference fromCell = getCellReferenceByName(namedArea);
		return getNumericCellValue((short) fromCell.getRow(), fromCell.getCol());
	}

	/**
	 * 値を取得する.
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @return 値(Date)
	 */
	public Date getDateCellValue(final short rowNum, final short columnNum) {
		if (sheet == null) {
			throw new IllegalStateException();
		}
		HSSFCell cell = sheet.getRow(rowNum).getCell(columnNum);
		return cell.getDateCellValue();
	}

	/**
	 * 値を取得する.
	 * @param namedArea 名前付エリア
	 * @return 値(Date)
	 */
	public Date getDateCellValue(final String namedArea) {
		CellReference fromCell = getCellReferenceByName(namedArea);
		return getDateCellValue((short) fromCell.getRow(), fromCell.getCol());
	}

	/**
	 * 値を設定する.
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @param value 設定値(String)
	 */
	public void setCellValue(final short rowNum, final short columnNum,
			final String value) {
		if (sheet == null) {
			throw new IllegalStateException();
		}
		HSSFCell cell = gainCellCompulsorily(rowNum, columnNum);
		HSSFRichTextString richString = new HSSFRichTextString(value);
		cell.setCellValue(richString);

	}

	/**
	 * 値を設定する.
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @param value 設定値(String)
	 */
	public void setCellValueNumeric(final short rowNum, final short columnNum,
			final BigDecimal value) {
		if (sheet == null) {
			throw new IllegalStateException();
		}
		HSSFCell cell = gainCellCompulsorily(rowNum, columnNum);
		cell.setCellValue(value.doubleValue());

	}

	/**
	 * 値を設定する.
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @param value 設定値(String)
	 */
	public void setCellValueTimestamp(final short rowNum,
			final short columnNum, final Date value) {
		if (sheet == null) {
			throw new IllegalStateException();
		}
		HSSFCell cell = gainCellCompulsorily(rowNum, columnNum);
		cell.setCellValue(value);

	}

	/**
	 * 行、列を指定してExcelシートにデータを貼り付け(文字列用）
	 * @param sRow 行
	 * @param sCol 列
	 * @param value 値
	 */
	public void setExcelDataString(final short sRow, final short sCol,
			final String value) {

		// nullでは無い場合のみデータをセット
		if (value != null) {
			// データをセット
			this.setCellValue(sRow, sCol, value);
		}
	}

	/**
	 * 行、列を指定してExcelシートにデータを貼り付け(数値用）
	 * @param sRow 行
	 * @param sCol 列
	 * @param value 値
	 */
	public void setExcelDataBigDecimal(final short sRow, final short sCol,
			final BigDecimal value) {

		// nullでは無い場合のみデータをセット
		if (value != null) {
			// データをセット
			this.setCellValueNumeric(sRow, sCol, value);
		}
	}

	/**
	 * 行、列を指定してExcelシートにデータを貼り付け(数値用）
	 * @param sRow 行
	 * @param sCol 列
	 * @param value 値
	 */
	public void setExcelDataTimestamp(final short sRow, final short sCol,
			final Timestamp value) {

		// nullでは無い場合のみデータをセット
		if (value != null) {
			// データをセット
			this.setCellValueTimestamp(sRow, sCol, value);
		}
	}

	/**
	 * 値を設定する.
	 * @param namedArea 名前付エリア
	 * @param value 設定値(String)
	 */
	public void setCellValue(final String namedArea, final String value) {
		CellReference fromCell = getCellReferenceByName(namedArea);
		setCellValue((short) fromCell.getRow(), fromCell.getCol(), value);
	}

	/**
	 * 値を設定する.
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @param value 設定値(double)
	 */
	public void setCellValue(final short rowNum, final short columnNum,
			final double value) {
		if (sheet == null) {
			throw new IllegalStateException();
		}
		HSSFCell cell = gainCellCompulsorily(rowNum, columnNum);
		cell.setCellValue(value);
	}

	/**
	 * 値を設定する.
	 * @param namedArea 名前付エリア
	 * @param value 設定値(double)
	 */
	public void setCellValue(final String namedArea, final double value) {
		CellReference fromCell = getCellReferenceByName(namedArea);
		setCellValue((short) fromCell.getRow(), fromCell.getCol(), value);
	}

	/**
	 * 値を設定する.
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @param value 設定値(Date)
	 */
	public void setCellValue(final short rowNum, final short columnNum,
			final Date value) {
		if (sheet == null) {
			throw new IllegalStateException();
		}
		HSSFCell cell = gainCellCompulsorily(rowNum, columnNum);
		cell.setCellValue(value);
	}

	/**
	 * 値を設定する.
	 * @param namedArea 名前付エリア
	 * @param value 設定値(Date)
	 */
	public void setCellValue(final String namedArea, final Date value) {
		CellReference fromCell = getCellReferenceByName(namedArea);
		setCellValue((short) fromCell.getRow(), fromCell.getCol(), value);
	}

	/**
	 * 値を設定する.
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @param value 設定値(Calendar)
	 */
	public void setCellValue(final short rowNum, final short columnNum,
			final Calendar value) {
		if (sheet == null) {
			throw new IllegalStateException();
		}
		HSSFCell cell = gainCellCompulsorily(rowNum, columnNum);
		cell.setCellValue(value);
	}

	/**
	 * 値を設定する.
	 * @param namedArea 名前付エリア
	 * @param value 設定値(Calendar)
	 */
	public void setCellValue(final String namedArea, final Calendar value) {
		CellReference fromCell = getCellReferenceByName(namedArea);
		setCellValue((short) fromCell.getRow(), fromCell.getCol(), value);
	}

	/**
	 * Cellが無ければ作って返します。
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @return セル
	 */
	private HSSFCell gainCellCompulsorily(final short rowNum,
			final short columnNum) {

		HSSFCell cell = null;
		HSSFRow row = null;
		if (sheet.getRow(rowNum) == null) {
			row = sheet.createRow(rowNum);
		} else {
			row = sheet.getRow(rowNum);
		}

		if (row.getCell(columnNum) == null) {
			cell = row.createCell(columnNum);
		} else {
			cell = row.getCell(columnNum);
		}
		cell.setEncoding(HSSFCell.ENCODING_UTF_16);
		return cell;

	}

	/**
	 * 最終行を取得します。
	 * @return int 最終行番号
	 */
	public int getLastRow() {
		if (sheet == null) {
			throw new IllegalStateException();
		}
		return sheet.getPhysicalNumberOfRows();

	}

	/**
	 * 最終列を取得します。
	 * @param row 行番号
	 * @return int 最終列番号
	 */
	public int getLastCell(final int row) {
		if (sheet == null) {
			throw new IllegalStateException();
		}
		return sheet.getRow(row).getPhysicalNumberOfCells();

	}

	/**
	 * CellType判定し、文字列で値を取得する.
	 * @param rowNum 行番号
	 * @param columNum 列番号
	 * @return String
	 */
	public String getStringValue(final short rowNum, final short columNum) {
		if (sheet == null) {
			throw new IllegalStateException();
		}

		HSSFRow row = sheet.getRow(rowNum);
		HSSFCell cell = row.getCell(columNum);
		if (cell == null) {
			return "";
		}

		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC: // 数値
			return new DecimalFormat("############0.##")
					.format(getNumericCellValue(rowNum, columNum));
		case HSSFCell.CELL_TYPE_BLANK: // ブランク
		case HSSFCell.CELL_TYPE_STRING: // 文字列
		default:
			return getStringCellValue(rowNum, columNum);
		}

	}

	/**
	 * セルを結合する.
	 * @param region 結合範囲
	 */
	public void execCellRegion(final Region region) {

		if (sheet == null) {
			throw new IllegalStateException();
		}

		// セルを結合する
		sheet.addMergedRegion(region);
	}

	/**
	 * セル幅を設定する。
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @param columnSize 列幅
	 */
	public void setCellWidth(final short rowNum, final short columnNum,
			final short columnSize) {

		if (sheet == null) {
			throw new IllegalStateException();
		}

		// 列幅設定
		if (columnSize != 0) {
			sheet.setColumnWidth(columnNum, columnSize);
		}
	}

	/**
	 * セルの形式を設定。
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @param cellType 形式
	 */
	public void setCellType(final short rowNum, final short columnNum,
			final String cellType) {

		if (sheet == null) {
			throw new IllegalStateException();
		}

		// セルが無ければ作成
		HSSFCell cell = gainCellCompulsorily(rowNum, columnNum);

		// セルに形式を設定する
		if (StringUtils.isNotEmpty(cellType)) {
			if (cellType.equals(Constants.TYPE_NUMERIC)) {
				cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
			} else if (cellType.equals(Constants.TYPE_STRING)) {
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
			}
		}
	}

	/**
	 * 行の高さを設定する.
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @param rowHight 行の高さ
	 */
	public void setRowHeight(final short rowNum, final short columnNum,
			final short rowHight) {

		if (sheet == null) {
			throw new IllegalStateException();
		}

		HSSFRow row = sheet.getRow(rowNum);

		// 行の高さを設定する
		row.setHeight(rowHight);
	}

	/**
	 * セルの文字の折り返しを設定。
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @param bol 設定・解除
	 */
	public void setCellWrapText(final short rowNum, final short columnNum,
			final boolean bol) {

		if (sheet == null) {
			throw new IllegalStateException();
		}
		if (workbook == null) {
			throw new IllegalStateException();
		}

		// セルが無ければ作成
		HSSFCell cell = gainCellCompulsorily(rowNum, columnNum);

		HSSFCellStyle style = cell.getCellStyle();

		style.setWrapText(bol);

		// 折り返しを設定する
		cell.setCellStyle(style);
	}

	/**
	 * スタイルを設定します パターン１：横左詰・縦上詰、文字の折り返し.
	 */
	public void createStyle1() {

		if (workbook == null) {
			throw new IllegalStateException();
		}

		// 共通スタイル作成
		style1 = createStyleCommon();

		// 文字位置
		style1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
		style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_TOP);

		// 文字の折り返し
		style1.setWrapText(true);
	}

	/**
	 * 共通スタイルを作成します 共通スタイル：フォント＝ＭＳゴシップ、フォントサイズ＝９、枠線有り.
	 * @return HSSFCellStyle
	 */
	public HSSFCellStyle createStyleCommon() {

		// String fontName = rb.getString("excel.font");
		final short fontSize = 9;

		if (workbook == null) {
			throw new IllegalStateException();
		}

		HSSFCellStyle style = workbook.createCellStyle();

		// フォント設定
		HSSFFont font = workbook.createFont();
		// Windows98 Office2000でフォントを指定すると文字化けする
		// ため、フォント指定を削除する。
		// if (StringUtils.isNotEmpty(fontName)) {
		// font.setFontName(fontName);
		// }
		if (fontSize != 0) {
			font.setFontHeightInPoints(fontSize);
		}
		style.setFont(font);

		// 枠線設定
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);

		return style;
	}

	/**
	 * セルにスタイルを設定します.
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 */
	public void setStyle1(final short rowNum, final short columnNum) {

		// セルが無ければ作成
		HSSFCell cell = gainCellCompulsorily(rowNum, columnNum);

		cell.setCellStyle(style1);
	}

	/**
	 * defaultCellStyleRowNumを取得します。
	 * @return defaultCellStyleRowNum
	 */
	public short getDefaultCellStyleRowNum() {
		return defaultCellStyleRowNum;
	}

	/**
	 * defaultCellStyleRowNumを設定します。
	 * @param defaultCellStyleRowNum defaultCellStyleRowNum
	 */
	public void setDefaultCellStyleRowNum(final short defaultCellStyleRowNum) {
		this.defaultCellStyleRowNum = defaultCellStyleRowNum;
	}

	/**
	 * 値を取得する.
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @return 値(Date)
	 */
	public Date getDefaultStyleDateCellValue(final short rowNum,
			final short columnNum) {
		if (sheet == null) {
			throw new IllegalStateException();
		}
		HSSFCell cell = sheet.getRow(rowNum).getCell(columnNum);
		return cell.getDateCellValue();
	}

	/**
	 * 値を設定する.
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @param value 設定値(String)
	 */
	public void setDefaultStyleCellValue(final short rowNum,
			final short columnNum, final String value) {
		if (workbook == null || sheet == null) {
			throw new IllegalStateException();
		}
		HSSFCell cell = gainCellCompulsorily(rowNum, columnNum);
		cell.setCellStyle(getDefaultCellStyle(columnNum));
		HSSFRichTextString richString = new HSSFRichTextString(value);
		cell.setCellValue(richString);
	}

	/**
	 * 値を設定する.
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @param value 設定値(double)
	 */
	public void setDefaultStyleCellValue(final short rowNum,
			final short columnNum, final double value) {
		if (sheet == null) {
			throw new IllegalStateException();
		}
		HSSFCell cell = gainCellCompulsorily(rowNum, columnNum);
		cell.setCellStyle(getDefaultCellStyle(columnNum));
		cell.setCellValue(value);
	}

	/**
	 * 値を設定する.
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @param value 設定値(Date)
	 */
	public void setDefaultStyleCellValue(final short rowNum,
			final short columnNum, final Date value) {
		if (sheet == null) {
			throw new IllegalStateException();
		}
		HSSFCell cell = gainCellCompulsorily(rowNum, columnNum);
		cell.setCellStyle(getDefaultCellStyle(columnNum));
		cell.setCellValue(value);
	}

	/**
	 * 値を設定する.
	 * @param rowNum 行番号
	 * @param columnNum 列番号
	 * @param value 設定値(Calendar)
	 */
	public void setDefaultStyleCellValue(final short rowNum,
			final short columnNum, final Calendar value) {
		if (sheet == null) {
			throw new IllegalStateException();
		}
		HSSFCell cell = gainCellCompulsorily(rowNum, columnNum);
		cell.setCellStyle(getDefaultCellStyle(columnNum));
		cell.setCellValue(value);
	}

	/**
	 * デフォルトセルスタイルを取得する.
	 * @param column 列番号
	 * @return セルスタイル
	 */
	private HSSFCellStyle getDefaultCellStyle(final short column) {
		HSSFCell defaultCell = gainCellCompulsorily(
			getDefaultCellStyleRowNum(), column);
		return defaultCell.getCellStyle();
	}

	/**
	 * シートのコピーを行う.
	 * @param index コピーするシートのindex
	 * @param sheetName シート名
	 */
	public void cloneSheetAt(final int index, final String sheetName) {
		if (workbook == null) {
			try {
				readTemplate();
			} catch (IOException e) {
				throw new IllegalStateException();
			}
		}
		workbook.cloneSheet(index);
		int clonedSheetIndex = workbook.getNumberOfSheets() - 1;
		workbook.setSheetName(clonedSheetIndex, sheetName,
			HSSFWorkbook.ENCODING_UTF_16);
		setSheet(clonedSheetIndex);
	}

	/**
	 * シートを複製する.
	 * @param orgSheetName コピー元のシート名
	 * @param sheetName シート名
	 */
	public void cloneSheet(final String orgSheetName, final String sheetName) {
		if (workbook == null) {
			try {
				readTemplate();
			} catch (IOException e) {
				throw new IllegalStateException();
			}
		}
		cloneSheetAt(workbook.getSheetIndex(orgSheetName), sheetName);
	}

	/**
	 * シートを削除する(indexから).
	 * @param index index
	 */
	public void removeSheetAt(final int index) {
		if (workbook == null) {
			try {
				readTemplate();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		workbook.removeSheetAt(index);
	}

	/**
	 * シートを削除する(シート名から).
	 * @param sheetName シート名
	 */
	public void removeSheet(final String sheetName) {
		if (workbook == null) {
			try {
				readTemplate();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		removeSheetAt(workbook.getSheetIndex(sheetName));
	}

	/**
	 * 指定行以下を指定行数分下にシフトする。
	 * 
	 * @param rowNum 行番号
	 * @param shiftCnt シフトする行数
	 */
	public void shiftRows(final short rowNum, final short shiftCnt) {
		if (sheet == null) {
			throw new IllegalStateException();
		}
		if (rowNum > sheet.getLastRowNum()) {
			throw new IllegalArgumentException();
		}
		// 行をシフトする
		short[] rowHeights = new short[sheet.getLastRowNum() - rowNum + 1];
		for (short i = rowNum; i < sheet.getLastRowNum(); i++) {
			rowHeights[i - rowNum] = sheet.getRow(i).getHeight();
		}
		for (short i = rowNum; i < sheet.getLastRowNum(); i++) {
			sheet.getRow(i + shiftCnt).setHeight(rowHeights[i - rowNum]);
		}
		sheet.shiftRows(rowNum, sheet.getLastRowNum(), shiftCnt);
	}

	/**
	 * 指定行以下を指定行数分下にシフトする。 製造指図書(ピッキング・配合)出力用
	 * @param rowNum 行番号
	 * @param shiftCnt シフトする行数
	 */
	public void shiftRows2(final short rowNum, final short shiftCnt) {
		sheet.shiftRows(rowNum, sheet.getLastRowNum(), shiftCnt);
	}

	/**
	 * 
	 * セルのスタイルをコピーする。 製造指図書(ピッキング・配合)出力用
	 * @param rowNum コピー先行番号
	 * @param colNum 最終列番号(０起点)＋１
	 */
	public void copyStyle(final short rowNum, final short colNum) {
		HSSFRow row = sheet.getRow(rowNum - 2);
		HSSFRow copyRow = sheet.getRow(rowNum);
		for (short s = 0; s < colNum; s++) {
			HSSFCell cell = row.getCell(s);
			HSSFCell copyCell = copyRow.createCell(s);
			copyCell.setCellStyle(cell.getCellStyle());
		}
	}

	/**
	 * 
	 * セルのスタイルをコピーする。 製造指図書(ピッキング・配合)出力用
	 * @param rowNum コピー先行番号
	 * @param colNum 最終列番号(０起点)＋１
	 */
	public void copyStyleOrg(final short rowNum, final short colNum) {
		HSSFRow row = sheet.getRow(rowNum - 1);
		HSSFRow copyRow = sheet.getRow(rowNum);
		copyRow.setHeight(row.getHeight());
		for (short s = 0; s < colNum; s++) {
			HSSFCell cell = row.getCell(s);
			HSSFCell copyCell = copyRow.createCell(s);
			if (cell != null) {
				copyCell.setCellStyle(cell.getCellStyle());
			}
		}
	}

	/**
	 * セルのスタイルをコピーします.
	 * @param rowNum 行番号
	 * @param colNum 列番号
	 * @param rowGain 行の増分
	 * @param colGain 列の増分
	 * @return HSSFCellStyle HSSFCellStyle
	 */
	private HSSFCellStyle copyStyle(final short rowNum, final short colNum,
			final short rowGain, final short colGain) {

		if (workbook == null) {
			throw new IllegalStateException();
		}

		// スタイルを設定する
		sheet.setColumnWidth((short) (colNum + colGain), sheet
				.getColumnWidth(colNum));
		sheet.getRow(rowNum + rowGain).setHeight(
			sheet.getRow(rowNum).getHeight());
		HSSFCell fromCell = gainCellCompulsorily(rowNum, colNum);
		HSSFCellStyle style = null;
		style = fromCell.getCellStyle();
		HSSFCell cell = gainCellCompulsorily((short) (rowNum + rowGain),
			(short) (colNum + colGain));
		cell.setCellStyle(style);

		return style;
	}

	/**
	 * 指定範囲のスタイル指定範囲にをコピーします(範囲の重複不可）.
	 * @param rowFrom 行番号(FROM)
	 * @param colFrom 列番号(FROM)
	 * @param rowTo 行番号(TO)
	 * @param colTo 列番号(TO)
	 * @param rowGain 行の増分
	 * @param colGain 列の増分
	 */
	public void copyStyle(final short rowFrom, final short colFrom,
			final short rowTo, final short colTo, final short rowGain,
			final short colGain) {

		if (workbook == null) {
			throw new IllegalStateException();
		}
		if (rowTo < rowFrom) {
			throw new IllegalArgumentException("rowTo < rowFrom");
		}
		if (colTo < colFrom) {
			throw new IllegalArgumentException("colTo < colFrom");
		}
		if (Math.abs(rowTo - rowFrom) > Math.abs(rowGain)
				&& Math.abs(colTo - colFrom) > Math.abs(colGain)) {
			throw new IllegalArgumentException("重複領域があります");
		}

		// 結合とスタイル
		for (short x = colFrom; x <= colTo; x++) {
			for (short y = rowFrom; y <= rowTo; y++) {

				// セルの結合
				for (int i = 0; i < sheet.getNumMergedRegions(); i++) {
					Region mergedRegion = sheet.getMergedRegionAt(i);
					if (x == mergedRegion.getColumnFrom()
							&& y == mergedRegion.getRowFrom()) {
						Region region = new Region((short) (y + rowGain),
								(short) (x + colGain), (short) (mergedRegion
										.getRowTo() + rowGain),
								(short) (mergedRegion.getColumnTo() + colGain));
						sheet.addMergedRegion(region);
					}
				}

				// スタイルを設定する
				copyStyle(y, x, rowGain, colGain);
			}
		}

		// 外枠の罫線を設定する
		for (short x = colFrom; x <= colTo; x++) {
			if ((x + colGain >= colFrom) && (x + colGain <= colTo)) {
				if ((rowFrom + rowGain == rowTo + 1)
						&& (rowFrom + rowGain - 1 >= 0)) {
					short border = gainCellCompulsorily(
						(short) (rowFrom + rowGain), (short) (x + colGain))
							.getCellStyle().getBorderTop();
					gainCellCompulsorily((short) (rowFrom + rowGain - 1),
						(short) (x + colGain)).getCellStyle().setBorderBottom(
						border);
				}
				if (rowTo + rowGain == rowFrom - 1) {
					short border = gainCellCompulsorily(
						(short) (rowTo + rowGain), (short) (x + colGain))
							.getCellStyle().getBorderBottom();
					gainCellCompulsorily((short) (rowTo + rowGain + 1),
						(short) (x + colGain)).getCellStyle().setBorderTop(
						border);
				}
			}
		}
		for (short y = rowFrom; y <= rowTo; y++) {
			if ((y + rowGain >= rowFrom) && (y + rowGain <= rowTo)) {
				if ((colFrom + colGain == colTo + 1)
						&& (colFrom + colGain - 1 >= 0)) {
					short border = gainCellCompulsorily((short) (y + rowGain),
						(short) (colFrom + colGain)).getCellStyle()
							.getBorderLeft();
					gainCellCompulsorily((short) (y + rowGain),
						(short) (colFrom + colGain - 1)).getCellStyle()
							.setBorderRight(border);
				}
				if ((colTo + colGain == colFrom - 1)
						&& (colTo + colGain + 1 <= MAX_COL)) {
					short border = gainCellCompulsorily((short) (y + rowGain),
						(short) (colTo + colGain)).getCellStyle()
							.getBorderRight();
					gainCellCompulsorily((short) (y + rowGain),
						(short) (colTo + colGain + 1)).getCellStyle()
							.setBorderLeft(border);
				}
			}
		}

		// 印刷範囲の設定
		setPrintArea((short) Math.max(rowTo, rowTo + rowGain), (short) Math
				.max(colTo, colTo + colGain));
	}

	/**
	 * 印刷範囲を設定する。
	 * 
	 * @param rowNum 行番号
	 * @param colNum 列番号
	 */
	public void setPrintArea(final short rowNum, final short colNum) {
		if (workbook == null) {
			throw new IllegalStateException();
		}
		if (sheet == null) {
			throw new IllegalStateException();
		}

		short index = 0;
		for (index = 0; index < workbook.getNumberOfSheets(); index++) {
			if (workbook.getSheetAt(index).equals(sheet)) {
				break;
			}
		}
		workbook.setPrintArea(index, 0, colNum, 0, rowNum);
	}

	/**
	 * ワークブックを追加する.
	 * @param workbookUrl ワークブックのURL
	 */
	public void setWorkbookUrl(final String workbookUrl) {

		setFileKey(workbookUrl);

		ResourceBundle rb = Constants.RB_REPORT_PROPERTIES;
		URL url = this.getClass().getResource(
			rb.getString("excel.template.path") + workbookUrl
					+ rb.getString("excel.extension"));

		setWorkbookUrl(url);

		try {
			readTemplate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ワークブックを追加する.TANAKA
	 * @param workbookUrl ワークブックのURL
	 */
	public void setDynamicWorkbookUrl(final String workbookUrl) {

		setFileKey(workbookUrl);

		// ResourceBundle rb = Constants.RB_REPORT_PROPERTIES;
		// URL url = this.getClass().getResource(
		// rb.getString("excel.template.path") + workbookUrl
		// + rb.getString("excel.extension"));
		//
		// setWorkbookUrl(url);
		//
		// try {
		// readTemplate();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
	}

	/**
	 * 行番号を取得.
	 * @param count String
	 * @return shortの行番号
	 */
	public short getRow(final String count) {

		if (StringUtils.isEmpty(count)) {
			throw new IllegalArgumentException("Paramater is empty.");
		}

		int rowNum = 0;
		try {
			rowNum = Integer.parseInt(count);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Numeric error.");
		}

		return (short) (rowNum - 1);
	}

	/**
	 * 列番号を取得.
	 * @param column カラム名
	 * @return shortの列番号
	 */
	public short getColumn(final String column) {

		if (StringUtils.isEmpty(column)) {
			throw new IllegalArgumentException("Paramater is empty.");
		}

		int count = columnList.indexOf(column);
		if (count == -1) {
			throw new IllegalArgumentException("column is not found.");
		}
		return (short) count;
	}

	/**
	 * CellType判定し、文字列で値を取得する.（NULL値対応）
	 * @param rowNum 行番号
	 * @param columNum 列番号
	 * @return String
	 */
	public String getStringValue2(final short rowNum, final short columNum) {
		if (sheet == null) {
			throw new IllegalStateException();
		}

		HSSFRow row = sheet.getRow(rowNum);
		if (row == null) {
			return "";
		}
		HSSFCell cell = row.getCell(columNum);
		if (cell == null) {
			return "";
		}

		switch (cell.getCellType()) {
		case HSSFCell.CELL_TYPE_NUMERIC: // 数値
			return new DecimalFormat("############0.##")
					.format(getNumericCellValue(rowNum, columNum));
		case HSSFCell.CELL_TYPE_BLANK: // ブランク
		case HSSFCell.CELL_TYPE_STRING: // 文字列
		default:
			return getStringCellValue(rowNum, columNum);
		}

	}

	/**
	 * プリンタ設定シートをコピー
	 */
	public void setPrtSettingSheet() {

		ReportExcelBuilder builderPrintSetting = new ReportExcelBuilder();

		// 定義ファイルをセット
		builderPrintSetting.setWorkbookUrl(SETTING_BOOK_NAME);

		// 設定シートをセット
		builderPrintSetting.setSheet(SETTING_SHEET_NAME);

		// コピー先のシートをセット
		setSheet("printer_setting");

		// セルを順にコピー
		for (short sRow = 0; sRow < MAX_SETTING_ROW; sRow++) {

			for (short sCol = 0; sCol < MAX_SETTING_COL; sCol++) {

				String tmpSrt = builderPrintSetting.getStringValue2(sRow, sCol);

				if (tmpSrt != null) {
					setCellValue(sRow, sCol, tmpSrt);

					// EOFが現れたら終了
					if (tmpSrt.equals("EOF")) {
						return;
					}

				}

			}
		}

	}

}
