/*
 * Created on 2009/02/27
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.slipshipping;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 出荷帳票_検索結果表示用データ格納クラス.
 * 
 * @author tosco
 */
public class CarryFileBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CarryFileBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション dataCls. */
	public static final String dataCls_COLUMN = "DATA_CLS";

	/** COLUMNアノテーション rowNum. */
	public static final String rowNum_COLUMN = "ROW_NUM";

	/** COLUMNアノテーション csvData. */
	public static final String csvData_COLUMN = "CSV_DATA";

	//
	// インスタンスフィールド
	//

	private String inputorCd;

	private String inputDate;

	private String dataCls;

	private String rowNum;

	private String csvData;

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
	 * inputorCdを取得します。
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return inputorCd;
	}

	/**
	 * inputorCdを設定します。
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * inputDateを取得します。
	 * @return inputDate
	 */
	public String getInputDate() {
		return inputDate;
	}

	/**
	 * inputDateを設定します。
	 * @param inputDate inputDate
	 */
	public void setInputDate(String inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * dataClsを取得します。
	 * @return dataCls
	 */
	public String getDataCls() {
		return dataCls;
	}

	/**
	 * dataClsを設定します。
	 * @param dataCls dataCls
	 */
	public void setDataCls(String dataCls) {
		this.dataCls = dataCls;
	}

	/**
	 * rowNumを取得します。
	 * @return rowNum
	 */
	public String getRowNum() {
		return rowNum;
	}

	/**
	 * rowNumを設定します。
	 * @param rowNum rowNum
	 */
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}

	/**
	 * csvDataを取得します。
	 * @return csvData
	 */
	public String getCsvData() {
		return csvData;
	}

	/**
	 * csvDataを設定します。
	 * @param csvData csvData
	 */
	public void setCsvData(String csvData) {
		this.csvData = csvData;
	}

}
