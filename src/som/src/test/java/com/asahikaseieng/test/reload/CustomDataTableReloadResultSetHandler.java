/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.test.reload;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.seasar.extension.dataset.DataRow;
import org.seasar.extension.dataset.DataTable;
import org.seasar.extension.dataset.states.RowStates;
import org.seasar.extension.jdbc.PropertyType;
import org.seasar.extension.jdbc.ResultSetHandler;
import org.seasar.extension.jdbc.impl.PropertyTypeUtil;

/**
 * DataRowReloadResultSetHandler(カスタマイズ).
 * @author jbd
 */
public class CustomDataTableReloadResultSetHandler implements ResultSetHandler {
	private DataTable newTable;

	/**
	 * コンストラクタ.
	 * @param newTable DataTable
	 */
	public CustomDataTableReloadResultSetHandler(final DataTable newTable) {
		this.newTable = newTable;
	}

	/**
	 * newTableを取得します。
	 * @return newTable
	 */
	public DataTable getNewTable() {
		return newTable;
	}

	/**
	 * {@inheritDoc}
	 */
	public Object handle(final ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		PropertyType[] propertyTypes = PropertyTypeUtil
				.createPropertyTypes(rsmd);
		while (rs.next()) {
			reload(rs, propertyTypes, newTable.addRow());
		}
		return getNewTable();
	}

	private void reload(final ResultSet rs, final PropertyType[] propertyTypes,
			final DataRow newRow) throws SQLException {
		for (int i = 0; i < propertyTypes.length; ++i) {
			Object value = propertyTypes[i].getValueType().getValue(rs, i + 1);
			newRow.setValue(propertyTypes[i].getPropertyName(), value);
		}
		newRow.setState(RowStates.UNCHANGED);
	}
}
