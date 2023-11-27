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
public class CarryFileDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public CarryFileDetailBase() {
	}

	//
	// 定数
	//

	//
	// 定数
	//

	/** COLUMNアノテーション carryCd */
	public static final String carryCd_COLUMN = "CARRY_CD";

	/** COLUMNアノテーション carryName */
	public static final String carryName_COLUMN = "CARRY_NAME";

	/** COLUMNアノテーション seq */
	public static final String seq_COLUMN = "SEQ";

	/** COLUMNアノテーション headerFlg */
	public static final String headerFlg_COLUMN = "HEADER_FLG";

	/** COLUMNアノテーション dataCls */
	public static final String dataCls_COLUMN = "DATA_CLS";

	/** COLUMNアノテーション tableName */
	public static final String tableName_COLUMN = "TABLE_NAME";

	/** COLUMNアノテーション tableComments */
	public static final String tableComments_COLUMN = "TABLE_COMMENTS";

	/** COLUMNアノテーション columnName */
	public static final String columnName_COLUMN = "COLUMN_NAME";

	/** COLUMNアノテーション colComments */
	public static final String colComments_COLUMN = "COL_COMMENTS";

	/** COLUMNアノテーション headerName */
	public static final String headerName_COLUMN = "HEADER_NAME";

	/** COLUMNアノテーション linkFlg */
	public static final String linkFlg_COLUMN = "LINK_FLG";

	/** COLUMNアノテーション description */
	public static final String description_COLUMN = "DESCRIPTION";

	/** COLUMNアノテーション sumFlg */
	public static final String sumFlg_COLUMN = "SUM_FLG";

	
	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";


	//
	// インスタンスフィールド
	//

	// チェックボックス
	private Boolean checkline = false;

	private String carryCd = "";

	private String carryName = "";

	private java.math.BigDecimal seq;

	private java.math.BigDecimal headerFlg = BigDecimal.ZERO;
	
	private String dataCls = "";

	private String tableName = "";

	private String tableComments = "";

	private String columnName = "";

	private String colComments = "";

	private String headerName = "";

	private java.math.BigDecimal linkFlg = BigDecimal.ZERO;

	private String description = "";

	private java.math.BigDecimal sumFlg = BigDecimal.ZERO;
	
	private java.sql.Timestamp updateDate;

	private String inputorCd;

	private java.sql.Timestamp inputDate;

	
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
	 * carryNameを取得します。
	 * @return carryName
	 */
	public String getCarryName() {
		return carryName;
	}

	/**
	 * carryNameを設定します。
	 * @param carryName carryName
	 */
	public void setCarryName(String carryName) {
		this.carryName = carryName;
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
	 * linkFlgを取得します。(チェックボックス表示用ラッパー)
	 * @return linkFlg
	 */
	public boolean getLinkFlgCheck() {
		if( linkFlg != null && linkFlg.equals(BigDecimal.ONE ) ){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * linkFlgを設定します。(チェックボックス表示用ラッパー)
	 * @param linkFlg linkFlg
	 */
	public void setLinkFlgCheck(boolean linkFlg) {
		if( linkFlg ){
			this.linkFlg = BigDecimal.ONE;
		}else{
			this.linkFlg = BigDecimal.ZERO;
		}
	}
	
	/**
	 * headerFlgを取得します。(チェックボックス表示用ラッパー)
	 * @return linkFlg
	 */
	public boolean getHeaderFlgCheck() {
		if( headerFlg != null && headerFlg.equals(BigDecimal.ONE ) ){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * headerFlgを設定します。(チェックボックス表示用ラッパー)
	 * @param headerFlg headerFlg
	 */
	public void setHeaderFlgCheck(boolean linkFlg) {
		if( linkFlg ){
			this.headerFlg = BigDecimal.ONE;
		}else{
			this.headerFlg = BigDecimal.ZERO;
		}
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
	 * checklineを取得します。
	 * @return checkline
	 */
	public Boolean getCheckline() {
		return checkline;
	}

	/**
	 * checklineを設定します。
	 * @param checkline checkline
	 */
	public void setCheckline(Boolean checkline) {
		this.checkline = checkline;
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
	public java.sql.Timestamp getInputDate() {
		return inputDate;
	}

	/**
	 * inputDateを設定します。
	 * @param inputDate inputDate
	 */
	public void setInputDate(java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
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

	/**
	 * headerFlgを取得します。(チェックボックス表示用ラッパー)
	 * @return linkFlg
	 */
	public boolean getSumFlgCheck() {
		if( sumFlg != null && sumFlg.equals(BigDecimal.ONE ) ){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * headerFlgを設定します。(チェックボックス表示用ラッパー)
	 * @param headerFlg headerFlg
	 */
	public void setSumFlgCheck(boolean sumFlg) {
		if( sumFlg ){
			this.sumFlg = BigDecimal.ONE;
		}else{
			this.sumFlg = BigDecimal.ZERO;
		}
	}
	

}

