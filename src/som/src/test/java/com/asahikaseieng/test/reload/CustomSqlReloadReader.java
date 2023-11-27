/*
 * Created on 2007/04/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.test.reload;

import javax.sql.DataSource;

import org.seasar.extension.dataset.DataSet;
import org.seasar.extension.dataset.TableReader;
import org.seasar.extension.dataset.impl.DataSetImpl;
import org.seasar.extension.dataset.impl.SqlReloadReader;

/**
 * SqlReloadReader(カスタマイズ).
 * @author jbd
 */
public class CustomSqlReloadReader extends SqlReloadReader {

	/**
	 * コンストラクタ.
	 * @param dataSource DataSource
	 * @param dataSet DataSet
	 */
	public CustomSqlReloadReader(final DataSource dataSource,
			final DataSet dataSet) {
		super(dataSource, dataSet);
	}

	/**
	 * {@inheritDoc}
	 */
	public DataSet read() {
		DataSet newDataSet = new DataSetImpl();
		for (int i = 0; i < getDataSet().getTableSize(); ++i) {
			TableReader reader = new CustomSqlReloadTableReader(
					getDataSource(), getDataSet().getTable(i));
			newDataSet.addTable(reader.read());
		}
		return newDataSet;
	}
}
