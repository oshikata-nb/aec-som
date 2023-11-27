/*
 * Created on 2020/10/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.orderimporttemp;

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
public class OrderImportTempBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderImportTempBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "ORDER_IMPORT_TEMP";
	
	/** COLUMNアノテーション tempNo. */
	public static final String tempNo_COLUMN = "TEMP_NO";

	/** COLUMNアノテーション venderGroupCd. */
	public static final String venderGroupCd_COLUMN = "VENDER_GROUP_CD";
	
	/** COLUMNアノテーション orderFileName. */
	public static final String orderFileName_COLUMN = "ORDER_FILE_NAME";
	
	/** COLUMNアノテーション md5Checksum. */
	public static final String md5Checksum_COLUMN = "MD5_CHECKSUM";
	
	/** COLUMNアノテーション importDate. */
	public static final String importDate_COLUMN = "IMPORT_DATE";
	
	/** COLUMNアノテーション importTantoCd. */
	public static final String importTantoCd_COLUMN = "IMPORT_TANTO_CD";

	/** COLUMNアノテーション cellRowNumber. */
	public static final String cellRowNumber_COLUMN = "CELL_ROW_NUMBER";

	/** COLUMNアノテーション cellColumnNumber. */
	public static final String cellColumnNumber_COLUMN = "CELL_COLUMN_NUMBER";
	
	/** COLUMNアノテーション orderFileColumnName. */
	public static final String orderFileColumnName_COLUMN = "ORDER_FILE_COLUMN_NAME";
	
	/** COLUMNアノテーション cellData */
	public static final String cellData_COLUMN = "CELL_DATA";
	
	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//
	
	private String tempNo;
	
	private String venderGroupCd;
	
	private String orderFileName;
	
	private String md5Checksum;
	
	private Timestamp importDate;
	
	private String importTantoCd;
	
	private BigDecimal cellRowNumber;
	
	private BigDecimal cellColumnNumber;
	
	private String orderFileColumnName;
	
	private String cellData;

	private Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * tempNoを取得します。
	 * @return tempNo
	 */
	public String getTempNo() {
		return tempNo;
	}

	/**
	 * tempNoを設定します。
	 * @param tempNo tempNo
	 */
	public void setTempNo(String tempNo) {
		this.tempNo = tempNo;
	}

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
	 * orderFileNameを取得します。
	 * @return orderFileName
	 */
	public String getOrderFileName() {
		return orderFileName;
	}

	/**
	 * orderFileNameを設定します。
	 * @param orderFileName orderFileName
	 */
	public void setOrderFileName(String orderFileName) {
		this.orderFileName = orderFileName;
	}

	/**
	 * md5Checksumを取得します。
	 * @return md5Checksum
	 */
	public String getMd5Checksum() {
		return md5Checksum;
	}

	/**
	 * md5Checksumを設定します。
	 * @param md5Checksum md5Checksum
	 */
	public void setMd5Checksum(String md5Checksum) {
		this.md5Checksum = md5Checksum;
	}

	/**
	 * importDateを取得します。
	 * @return importDate
	 */
	public Timestamp getImportDate() {
		return importDate;
	}

	/**
	 * importDateを設定します。
	 * @param importDate importDate
	 */
	public void setImportDate(Timestamp importDate) {
		this.importDate = importDate;
	}

	/**
	 * importTantoCdを取得します。
	 * @return importTantoCd
	 */
	public String getImportTantoCd() {
		return importTantoCd;
	}

	/**
	 * importTantoCdを設定します。
	 * @param importTantoCd importTantoCd
	 */
	public void setImportTantoCd(String importTantoCd) {
		this.importTantoCd = importTantoCd;
	}

	/**
	 * cellRowNumberを取得します。
	 * @return cellRowNumber
	 */
	public BigDecimal getCellRowNumber() {
		return cellRowNumber;
	}

	/**
	 * cellRowNumberを設定します。
	 * @param cellRowNumber cellRowNumber
	 */
	public void setCellRowNumber(BigDecimal cellRowNumber) {
		this.cellRowNumber = cellRowNumber;
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
	 * cellDataを取得します。
	 * @return cellData
	 */
	public String getCellData() {
		return cellData;
	}

	/**
	 * cellDataを設定します。
	 * @param cellData cellData
	 */
	public void setCellData(String cellData) {
		this.cellData = cellData;
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
