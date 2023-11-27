/*
 * Created on 2007/03/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.test;

import org.seasar.extension.dataset.DataSet;
import org.seasar.extension.dataset.DataTable;
import org.seasar.extension.dataset.impl.DataSetImpl;
import org.seasar.extension.unit.S2TestCase;

/**
 * S2TestCaseのスーパークラス.
 * @author jbd
 */
public abstract class AbstractS2TestCase extends S2TestCase {

	/**
	 * コンストラクタ.
	 * 
	 * @param name name
	 */
	public AbstractS2TestCase(final String name) {
		super(name);
	}

	/**
	 * Excelから指定シートのDataSetを取得する.
	 * 
	 * @param filePath ファイルパス
	 * @param sheetNo シート番号
	 * @return DataSet
	 */
	protected final DataSet readXls(final String filePath, final int sheetNo) {
		return readXls(filePath, sheetNo, null);
	}

	/**
	 * Excelから指定シートのDataSetを取得する.
	 * 
	 * @param filePath ファイルパス
	 * @param sheetNo シート番号
	 * @param tableName テーブル名
	 * @return DataSet
	 */
	protected final DataSet readXls(final String filePath, final int sheetNo,
			final String tableName) {
		DataSet dataSet = readXls(filePath);
		return getDataSetImpl(dataSet.getTable(sheetNo), tableName);
	}

	/**
	 * Excelから指定シートのDataSetを取得する.
	 * 
	 * @param filePath ファイルパス
	 * @param sheetName シート名
	 * @return DataSet
	 */
	protected final DataSet readXls(final String filePath,
			final String sheetName) {
		return readXls(filePath, sheetName, null);
	}

	/**
	 * Excelから指定シートのDataSetを取得する.
	 * 
	 * @param filePath ファイルパス
	 * @param sheetName シート名
	 * @param tableName テーブル名
	 * @return DataSet
	 */
	protected final DataSet readXls(final String filePath,
			final String sheetName, final String tableName) {
		DataSet dataSet = readXls(filePath);
		return getDataSetImpl(dataSet.getTable(sheetName), tableName);
	}

	private DataSetImpl getDataSetImpl(final DataTable dataTable,
			final String tableName) {
		if (tableName != null) {
			dataTable.setTableName(tableName);
		}
		DataSetImpl setImpl = new DataSetImpl();
		setImpl.addTable(dataTable);
		return setImpl;
	}
}
