/*
 * Created on 2007/04/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.seasar.extension.unit.S2TestCase;

/**
 * Excel側テストケースベース<br>
 * S2バージョン.
 * @author jbd
 */
public abstract class AbstractExcelS2TestCaseBase extends S2TestCase {

	/**
	 * コンストラクタ.
	 * @param name name
	 */
	public AbstractExcelS2TestCaseBase(final String name) {
		super(name);
	}

	/**
	 * テストデータのInputストリームを取得する<br>
	 * テストクラスと同じパッケージをルートとする。
	 * @param fileName テストデータファイル名
	 * @return Inputストリーム
	 */
	protected final InputStream getInputStream(final String fileName) {
		return this.getClass().getResourceAsStream(fileName);
	}

	/**
	 * Excelシートを取得する.
	 * @param inStream InputStream
	 * @param sheetName シート名
	 * @return Excelシート
	 * @throws IOException Exception
	 */
	protected final HSSFSheet getHSSFSheet(final InputStream inStream,
			final String sheetName) throws IOException {
		POIFSFileSystem fs = new POIFSFileSystem(inStream);
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		return workbook.getSheet(sheetName);
	}

	/**
	 * 生成したファイルのInputストリームを取得する.
	 * @param path ファイルパス
	 * @return Inputストリーム
	 * @throws IOException IOException
	 */
	protected final InputStream getReportStream(final String path)
			throws IOException {
		// 出力先ファイル読み込み
		return new FileInputStream(path);
	}

	/**
	 * ファイルを削除する.
	 * @param path ファイルパス
	 */
	protected final void rm(final String path) {
		File file = new File(path);
		file.deleteOnExit();
	}

}
