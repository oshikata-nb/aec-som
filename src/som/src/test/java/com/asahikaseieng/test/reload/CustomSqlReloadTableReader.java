/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.test.reload;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.seasar.extension.dataset.DataColumn;
import org.seasar.extension.dataset.DataRow;
import org.seasar.extension.dataset.DataTable;
import org.seasar.extension.dataset.TableReader;
import org.seasar.extension.dataset.impl.DataTableImpl;
import org.seasar.extension.jdbc.SelectHandler;
import org.seasar.extension.jdbc.impl.BasicSelectHandler;
import org.seasar.extension.jdbc.util.ConnectionUtil;
import org.seasar.extension.jdbc.util.DataSourceUtil;

/**
 * SqlReloadTableReader(カスタマイズ).
 * @author jbd
 */
public class CustomSqlReloadTableReader implements TableReader {
	private DataSource dataSource;

	private DataTable table;

	private String sql;

	private String[] primaryKeys;

	/**
	 * コンストラクタ.
	 * @param dataSource DataSource
	 * @param table DataTable
	 */
	public CustomSqlReloadTableReader(final DataSource dataSource,
			final DataTable table) {
		this.dataSource = dataSource;
		this.table = table;
		Connection con = DataSourceUtil.getConnection(dataSource);
		try {
			DatabaseMetaData dbMetaData = ConnectionUtil.getMetaData(con);
			this.table.setupMetaData(dbMetaData);
		} finally {
			ConnectionUtil.close(con);
		}
		setup();
	}

	private void setup() {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT ");
		StringBuffer whereBuf = new StringBuffer();
		whereBuf.append(" WHERE");
		List<String[]> primaryKeyList = new ArrayList<String[]>();
		for (int i = 0; i < table.getColumnSize(); ++i) {
			DataColumn column = table.getColumn(i);
			buf.append(column.getColumnName());
			buf.append(", ");
			/*
			 * WHERE句はつけない if (column.isPrimaryKey()) { whereBuf.append(" ");
			 * whereBuf.append(column.getColumnName()); whereBuf.append(" = ?
			 * AND"); primaryKeyList.add(column.getColumnName()); }
			 */
		}
		buf.setLength(buf.length() - 2);
		whereBuf.setLength(whereBuf.length() - (2 + 2));
		buf.append(" FROM ");
		buf.append(table.getTableName());
		buf.append(whereBuf);
		this.sql = buf.toString();
		primaryKeys = primaryKeyList.toArray(new String[primaryKeyList.size()]);
	}

	/**
	 * getDataSource.
	 * @return DataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * getTable.
	 * @return DataTable
	 */
	public DataTable getTable() {
		return table;
	}

	/**
	 * {@inheritDoc}
	 */
	public DataTable read() {
		DataTable newTable = new DataTableImpl(table.getTableName());
		for (int i = 0; i < table.getColumnSize(); ++i) {
			DataColumn column = table.getColumn(i);
			newTable.addColumn(column.getColumnName(), column.getColumnType());
		}
		/*
		 * 1テーブルで1回のSQLのみ実行 for (int i = 0; i < table.getRowSize(); ++i) {
		 * DataRow row = table.getRow(i); if (!reload(row, newTable)) { break; } }
		 */
		reload(null, newTable);
		return newTable;
	}

	/**
	 * reload.
	 * @param row 行データ
	 * @param newTable 新規テーブルデータ
	 * @return boolean
	 */
	protected boolean reload(final DataRow row, final DataTable newTable) {
		SelectHandler selectHandler = new BasicSelectHandler(this.dataSource,
				this.sql, new CustomDataTableReloadResultSetHandler(newTable));
		Object[] args = new Object[primaryKeys.length];
		boolean flg = false;
		/*
		 * WHERE句はつけないので削除 for (int i = 0; i < this.primaryKeys.length; ++i) {
		 * args[i] = row.getValue(this.primaryKeys[i]); flg = true; }
		 */
		selectHandler.execute(args);
		return flg;
	}
}
