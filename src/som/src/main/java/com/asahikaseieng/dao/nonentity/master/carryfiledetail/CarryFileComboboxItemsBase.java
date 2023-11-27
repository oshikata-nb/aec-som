/*
 * Created on 2009/02/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carryfiledetail;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * CarryDetailクラス.
 * @author t0011036
 */
public class CarryFileComboboxItemsBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CarryFileComboboxItemsBase() {
	}

	//
	// 定数
	//

	//
	// 定数
	//

	/** COLUMNアノテーション tableName */
	public static final String tableName_COLUMN = "TABLE_NAME";

	/** COLUMNアノテーション tableComments */
	public static final String tableComments_COLUMN = "TABLE_COMMENTS";

	/** COLUMNアノテーション column */
	public static final String column_COLUMN = "COLUMN_NAME";

	/** COLUMNアノテーション colComments */
	public static final String colComments_COLUMN = "COLUMNS_COMMENTS";


	//
	// インスタンスフィールド
	//

	// チェックボックス
	private String tableName;

	private String tableComments;

	private String columnName;

	private String colComments;


	//
	// インスタンスメソッド
	//
	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean equals(final Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	/**
	 * tableNameを取得します。
	 * @return tableName
	 */
	public String getTableName() {
		return tableName;
	}

	/**
	 * tableNameを設定します。
	 * @param tableName tableName
	 */
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	/**
	 * tableCommentsを取得します。
	 * @return tableComments
	 */
	public String getTableComments() {
		return tableComments;
	}

	/**
	 * tableCommentsを設定します。
	 * @param tableComments tableComments
	 */
	public void setTableComments(String tableComments) {
		this.tableComments = tableComments;
	}

	/**
	 * columnNameを取得します。
	 * @return columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * columnNameを設定します。
	 * @param columnName columnName
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	/**
	 * colCommentsを取得します。
	 * @return colComments
	 */
	public String getColComments() {
		return colComments;
	}

	/**
	 * colCommentsを設定します。
	 * @param colComments colComments
	 */
	public void setColComments(String colComments) {
		this.colComments = colComments;
	}


}

