/*
 * Created on Fri Jan 16 16:23:20 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.carryfilelayout;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * CarryBaseクラス.
 * @author t0011036
 */
public class CarryFileLayoutBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CarryFileLayoutBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "CARRY_FILE_LAYOUT";

	// /** IDアノテーション carryCd. */
	// public static final String carryCd_ID = "assigned";
	// /** IDアノテーション seq. */
	// public static final String seq_ID = "assigned";

	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション headerFlg */
	public static final String headerFlg_COLUMN = "HEADER_FLG";

	/** COLUMNアノテーション dataCls */
	public static final String dataCls_COLUMN = "DATA_CLS";

	/** COLUMNアノテーション tableName */
	public static final String tableName_COLUMN = "TABLE_NAME";

	/** COLUMNアノテーション columnName */
	public static final String columnName_COLUMN = "COLUMN_NAME";

	/** COLUMNアノテーション headerName */
	public static final String headerName_COLUMN = "HEADER_NAME";

	/** COLUMNアノテーション linkFlg */
	public static final String linkFlg_COLUMN = "LINK_FLG";

	/** COLUMNアノテーション description */
	public static final String description_COLUMN = "DESCRIPTION";

	/** COLUMNアノテーション sumFlg */
	public static final String sumFlg_COLUMN = "SUM_FLG";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	//
	// インスタンスフィールド
	//

	private String carryCd;


	private java.math.BigDecimal seq;

	private java.math.BigDecimal headerFlg;
	
	private String dataCls;

	private String tableName;

	private String columnName;

	private String headerName;

	private java.math.BigDecimal linkFlg;

	private String description ;

	private java.math.BigDecimal sumFlg;

	
	private String inputorCd;

	private String updatorCd;

	private java.sql.Timestamp inputDate;

	private java.sql.Timestamp updateDate;

	
	//
	// インスタンスメソッド
	//

	/**
	 * carryCd取得.
	 * @return carryCd
	 */
	public String getCarryCd() {
		return this.carryCd;
	}

	/**
	 * carryCd設定.
	 * @param carryCd carryCd
	 */
	public void setCarryCd(final String carryCd) {
		this.carryCd = carryCd;
	}
	
	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * updateDate取得.
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * updateDate設定.
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

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
	 * seqを取得します。
	 * @return seq
	 */
	public java.math.BigDecimal getSeq() {
		return seq;
	}

	/**
	 * seqを設定します。
	 * @param seq seq
	 */
	public void setSeq(java.math.BigDecimal seq) {
		this.seq = seq;
	}

	/**
	 * headerFlgを取得します。
	 * @return headerFlg
	 */
	public java.math.BigDecimal getHeaderFlg() {
		return headerFlg;
	}

	/**
	 * headerFlgを設定します。
	 * @param headerFlg headerFlg
	 */
	public void setHeaderFlg(java.math.BigDecimal headerFlg) {
		this.headerFlg = headerFlg;
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
	 * headerNameを取得します。
	 * @return headerName
	 */
	public String getHeaderName() {
		return headerName;
	}

	/**
	 * headerNameを設定します。
	 * @param headerName headerName
	 */
	public void setHeaderName(String headerName) {
		this.headerName = headerName;
	}

	/**
	 * linkFlgを取得します。
	 * @return linkFlg
	 */
	public java.math.BigDecimal getLinkFlg() {
		return linkFlg;
	}

	/**
	 * linkFlgを設定します。
	 * @param linkFlg linkFlg
	 */
	public void setLinkFlg(java.math.BigDecimal linkFlg) {
		this.linkFlg = linkFlg;
	}

	/**
	 * descriptionを取得します。
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * descriptionを設定します。
	 * @param description description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * sumFlgを取得します。
	 * @return sumFlg
	 */
	public java.math.BigDecimal getSumFlg() {
		return sumFlg;
	}

	/**
	 * sumFlgを設定します。
	 * @param sumFlg sumFlg
	 */
	public void setSumFlg(java.math.BigDecimal sumFlg) {
		this.sumFlg = sumFlg;
	}

}
