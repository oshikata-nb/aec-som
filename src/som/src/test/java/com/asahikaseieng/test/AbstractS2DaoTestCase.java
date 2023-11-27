/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.test;

import com.asahikaseieng.test.reload.CustomSqlReloadReader;
import com.asahikaseieng.test.reload.CustomSqlReloadTableReader;

import org.seasar.dao.unit.S2DaoTestCase;
import org.seasar.extension.dataset.DataSet;
import org.seasar.extension.dataset.DataTable;
import org.seasar.extension.dataset.impl.DataSetImpl;


/**
 * S2DaoTestCaseのスーパークラス.
 * @author jbd
 */
public abstract class AbstractS2DaoTestCase extends S2DaoTestCase {

	/**
	 * コンストラクタ.
	 * 
	 * @param name name
	 */
	public AbstractS2DaoTestCase(final String name) {
		super(name);
	}

	/**
	 * {@inheritDoc}
	 */
	protected final void setUp() throws Exception {
		super.setUp();

		include("alldao.dicon");

		setUpImpl();
	}

	/**
	 * 独自の初期化内容を記述する(必要があれば継承先でカスタマイズして下さい).
	 * 
	 * @throws Exception 例外
	 */
	protected void setUpImpl() throws Exception {
	}

	/**
	 * Excelから指定シートのDataSetを取得する.
	 * 
	 * @param filePath ファイルパス
	 * @param sheetNo シート番号
	 * @return DataSet
	 */
	protected DataSet readXls(final String filePath, final int sheetNo) {
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
	protected DataSet readXls(final String filePath, final int sheetNo,
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
	protected DataSet readXls(final String filePath, final String sheetName) {
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
	protected DataSet readXls(final String filePath, final String sheetName,
			final String tableName) {
		DataSet dataSet = readXls(filePath);
		return getDataSetImpl(dataSet.getTable(sheetName), tableName);
	}

	/**
	 * SQLでテーブルからデータを取得する。.
	 * 
	 * @param sql SQL
	 * @return DataSet
	 */
	protected DataSet readDbBySql(final String sql) {
		return getDataSetImpl(readDbBySql(sql, "result"), null);
	}

	/**
	 * テーブルからデータを取得する。.
	 * 
	 * @param table テーブル名
	 * @return DataSet
	 */
	protected DataSet readDbByTb(final String table) {
		return getDataSetImpl(readDbByTable(table), table);
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

	/**
	 * {@inheritDoc}
	 */
	public DataSet reload(final DataSet dataSet) {
		// reloadはカスタマイズしたReloadReaderを呼ぶ
		return new CustomSqlReloadReader(getDataSource(), dataSet).read();
	}

	/**
	 * {@inheritDoc}
	 */
	public DataTable reload(final DataTable table) {
		// reloadはカスタマイズしたReloadReaderを呼ぶ
		return new CustomSqlReloadTableReader(getDataSource(), table).read();
	}

}
