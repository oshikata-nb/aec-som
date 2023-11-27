/*
 * Created on 2007/04/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

/**
 * Excelシートの内容をチェックする.
 * @author jbd
 */
public final class AbstractDataSheetChecker {

	private static Log log = LogFactory.getLog(AbstractDataSheetChecker.class);

	private AbstractDataSheetChecker() {
	}

	/**
	 * Excelシート比較.
	 * @param verifySheet 検証シート
	 * @param targetSheet 比較対象シート
	 * @return boolean true:正常 false:異常
	 */
	public static boolean checkSheet(final HSSFSheet verifySheet,
			final HSSFSheet targetSheet) {
		// 行数チェック
		if (verifySheet.getPhysicalNumberOfRows() != targetSheet
				.getPhysicalNumberOfRows()) {
			log.error(">>>>>The number of lines do not match. veriryLines = "
					+ verifySheet.getPhysicalNumberOfRows()
					+ ", targetLines = "
					+ targetSheet.getPhysicalNumberOfRows());
			return false;
		}

		for (int i = 0; i <= verifySheet.getLastRowNum(); i++) {
			HSSFRow verifyRow = verifySheet.getRow(i);
			HSSFRow targetRow = targetSheet.getRow(i);
			// 値の入っているCellがない場合は次の行へ
			if (verifyRow == null && targetRow == null) {
				continue;
			}
			// Cell数チェック
			if (verifyRow.getPhysicalNumberOfCells() != targetRow
					.getPhysicalNumberOfCells()) {
				log.error(">>>>>The number of cells do not match. verifyRow = "
						+ verifyRow.getPhysicalNumberOfCells()
						+ ", targetRow = "
						+ targetRow.getPhysicalNumberOfCells());
				return false;
			}

			for (int j = 0; j <= verifyRow.getLastCellNum(); j++) {
				HSSFCell verifyCell = verifyRow.getCell((short) j);
				HSSFCell targetCell = targetRow.getCell((short) j);

				// Cell位置チェック
				if ((verifyCell == null) ^ (targetCell == null)) {
					log.error(">>>>>The cell position do not match. line = "
							+ i + ", row = " + j + ", verifyCell = "
							+ verifyCell + ", targetCell = " + targetCell);
					return false;
				}
				// skip
				if (verifyCell == null) {
					continue;
				}

				// CellTypeチェック
				if (verifyCell.getCellType() != targetCell.getCellType()) {
					log.error(">>>>>The cell-type do not match. line = " + i
							+ ", row = " + j + ", verifyCellType = "
							+ verifyCell.getCellType() + ", targetCellType = "
							+ targetCell.getCellType());
					return false;
				}

				String verifyValue = null;
				String targetValue = null;
				switch (verifyCell.getCellType()) {
				case HSSFCell.CELL_TYPE_FORMULA: // 関数
					verifyValue = verifyCell.getCellFormula();
					targetValue = targetCell.getCellFormula();
					break;
				case HSSFCell.CELL_TYPE_NUMERIC: // 数値
					// 日付か？
					if (HSSFDateUtil.isCellDateFormatted(verifyCell)) {
						verifyValue = verifyCell.getDateCellValue().toString();
						targetValue = targetCell.getDateCellValue().toString();
					} else {
						verifyValue = "" + verifyCell.getNumericCellValue();
						targetValue = "" + targetCell.getNumericCellValue();
					}
					break;
				case HSSFCell.CELL_TYPE_BLANK: // ブランク
				case HSSFCell.CELL_TYPE_STRING: // 文字列
				default:
					verifyValue = verifyCell.getStringCellValue().trim();
					targetValue = targetCell.getStringCellValue().trim();
					break;
				}
				if (!verifyValue.equals(targetValue)) {
					log.error(">>>>>two values do not match.(x=" + i + ", y="
							+ j + ", verify=[" + verifyValue + "], target=["
							+ targetValue + "]");
					return false;
				}

			}
		}
		return true;
	}
}
