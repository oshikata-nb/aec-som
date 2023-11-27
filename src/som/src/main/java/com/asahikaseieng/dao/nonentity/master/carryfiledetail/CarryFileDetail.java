/*
 * Created on 2009/01/16
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.carryfiledetail;

/**
 * CarryDetailクラス.
 * @author t0011036
 */
public class CarryFileDetail extends CarryFileDetailBase {

	private static final long serialVersionUID = 1L;

	private String[] dataClsValues = { null , "00" , "01" , "02"};

	private String[] dataClsLabels = { "未選択" , "データベース" , "フリー入力" , " 当日日付"};

	private String[] tableNameValues = {null};

	private String[] tableNameLabels = {"データ区分を選択してください"};

	private String[] columnNameValues= {null};

	private String[] columnNameLabels= {"データ区分を選択してください"};

	private boolean inputReadOnly = true;
	
	/**
	 * コンストラクタ.
	 */
	public CarryFileDetail() {
		super();
	}
	
	/**
	 * 
	 * テーブル名とカラム名を初期化
	 */
	public void ClearCombobox(){
		this.tableNameValues = new String[] {null};
		this.tableNameLabels = new String[] {"データ区分を選択してください"};
		this.columnNameValues= new String[] {null};
		this.columnNameLabels= new String[] {"テーブルを選択してください"};
	}


	/**
	 * dataClsValuesを取得します。
	 * @return dataClsValues
	 */
	public String[] getDataClsValues() {
		return dataClsValues;
	}


	/**
	 * dataClsValuesを設定します。
	 * @param dataClsValues dataClsValues
	 */
	public void setDataClsValues(String[] dataClsValues) {
		this.dataClsValues = dataClsValues;
	}


	/**
	 * dataClsLabelsを取得します。
	 * @return dataClsLabels
	 */
	public String[] getDataClsLabels() {
		return dataClsLabels;
	}


	/**
	 * dataClsLabelsを設定します。
	 * @param dataClsLabels dataClsLabels
	 */
	public void setDataClsLabels(String[] dataClsLabels) {
		this.dataClsLabels = dataClsLabels;
	}


	/**
	 * tableNameValuesを取得します。
	 * @return tableNameValues
	 */
	public String[] getTableNameValues() {
		return tableNameValues;
	}


	/**
	 * tableNameValuesを設定します。
	 * @param tableNameValues tableNameValues
	 */
	public void setTableNameValues(String[] tableNameValues) {
		this.tableNameValues = tableNameValues;
	}


	/**
	 * tableNameLabelsを取得します。
	 * @return tableNameLabels
	 */
	public String[] getTableNameLabels() {
		return tableNameLabels;
	}


	/**
	 * tableNameLabelsを設定します。
	 * @param tableNameLabels tableNameLabels
	 */
	public void setTableNameLabels(String[] tableNameLabels) {
		this.tableNameLabels = tableNameLabels;
	}


	/**
	 * columnNameValuesを取得します。
	 * @return columnNameValues
	 */
	public String[] getColumnNameValues() {
		return columnNameValues;
	}


	/**
	 * columnNameValuesを設定します。
	 * @param columnNameValues columnNameValues
	 */
	public void setColumnNameValues(String[] columnNameValues) {
		this.columnNameValues = columnNameValues;
	}


	/**
	 * columnNameLabelsを取得します。
	 * @return columnNameLabels
	 */
	public String[] getColumnNameLabels() {
		return columnNameLabels;
	}


	/**
	 * columnNameLabelsを設定します。
	 * @param columnNameLabels columnNameLabels
	 */
	public void setColumnNameLabels(String[] columnNameLabels) {
		this.columnNameLabels = columnNameLabels;
	}

	/**
	 * inputReadOnlyを取得します。
	 * @return inputReadOnly
	 */
	public boolean isInputReadOnly() {
		return inputReadOnly;
	}

	/**
	 * inputReadOnlyを設定します。
	 * @param inputReadOnly inputReadOnly
	 */
	public void setInputReadOnly(boolean inputReadOnly) {
		this.inputReadOnly = inputReadOnly;
	}

}

