/*
 * Created on 2020/10/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderfilelayoutchange;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * OrderImportHeadBaseクラス.
 * @author 
 */
public class OrderFileLayoutChangeBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderFileLayoutChangeBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ORDER_IMPORT_TEMP";

	/** COLUMNアノテーション venderGroupCd. */
	public static final String venderGroupCd_COLUMN = "VENDER_GROUP_CD";

	/** COLUMNアノテーション cellColumnNumber. */
	public static final String cellColumnNumber_COLUMN = "CELL_COLUMN_NUMBER";
	
	/** COLUMNアノテーション orderFileColumnName. */
	public static final String orderFileColumnName_COLUMN = "ORDER_FILE_COLUMN_NAME";
	
	/** COLUMNアノテーション apColumnName */
	public static final String cellData_COLUMN = "AP_COLUMN_NAME";
	
	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//
	
	private String venderGroupCd;
	
	private BigDecimal cellColumnNumber;
	
	private String orderFileColumnName;
	
	private String apColumnName;

	private Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * venderGroupCdを取得します。
	 * @return venderGroupCd
	 */
	public String getVenderGroupCd() {
		return venderGroupCd;
	}

	/**
	 * venderGroupCdを設定します。
	 * @param venderGroupCd venderGroupCd
	 */
	public void setVenderGroupCd(String venderGroupCd) {
		this.venderGroupCd = venderGroupCd;
	}

	/**
	 * cellColumnNumberを取得します。
	 * @return cellColumnNumber
	 */
	public BigDecimal getCellColumnNumber() {
		return cellColumnNumber;
	}

	/**
	 * cellColumnNumberを設定します。
	 * @param cellColumnNumber cellColumnNumber
	 */
	public void setCellColumnNumber(BigDecimal cellColumnNumber) {
		this.cellColumnNumber = cellColumnNumber;
	}

	/**
	 * orderFileColumnNameを取得します。
	 * @return orderFileColumnName
	 */
	public String getOrderFileColumnName() {
		return orderFileColumnName;
	}

	/**
	 * orderFileColumnNameを設定します。
	 * @param orderFileColumnName orderFileColumnName
	 */
	public void setOrderFileColumnName(String orderFileColumnName) {
		this.orderFileColumnName = orderFileColumnName;
	}

	/**
	 * apColumnNameを取得します。
	 * @return apColumnName
	 */
	public String getApColumnName() {
		return apColumnName;
	}

	/**
	 * apColumnNameを設定します。
	 * @param apColumnName apColumnName
	 */
	public void setApColumnName(String apColumnName) {
		this.apColumnName = apColumnName;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * updatorCdを取得します。
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return updatorCd;
	}

	/**
	 * updatorCdを設定します。
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(String updatorCd) {
		this.updatorCd = updatorCd;
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

}
