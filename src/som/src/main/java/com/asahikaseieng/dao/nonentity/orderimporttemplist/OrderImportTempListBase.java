/*
 * Created on 2009/01/15
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimporttemplist;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * AreaListクラス.
 * @author t0011036
 */
public class OrderImportTempListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderImportTempListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション tempNo. */
	public static final String pkNo_COLUMN = "PK_NO";
	
	/** COLUMNアノテーション pkRow. */
	public static final String pkRow_COLUMN = "PK_ROW";
	

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
	
	/** COLUMNアノテーション deliveryExpectedDate. */
	public static final String deliveryExpectedDate_COLUMN = "DELIVERY_EXPECTED_DATE";
	
	/** COLUMNアノテーション orderQty. */
	public static final String orderQty_COLUMN = "ORDER_QTY";
	
	/** COLUMNアノテーション carryFare. */
	public static final String carryFare_COLUMN = "CARRY_FARE";
	
	/** COLUMNアノテーション carryGroup. */
	public static final String carryGroup_COLUMN = "CARRY_GROUP";
	
	/** COLUMNアノテーション errorStatus. */
	public static final String errorStatus_COLUMN = "ERROR_STATUS";
		
	/** COLUMNアノテーション importStatus. */
	public static final String importStatus_COLUMN = "IMPORT_STATUS";
	
	/** COLUMNアノテーション importStatus. */
	public static final String check01_COLUMN = "CHECK01";
	
	/** COLUMNアノテーション importStatus. */
	public static final String check02_COLUMN = "CHECK02";
	/** COLUMNアノテーション importStatus. */
	public static final String check03_COLUMN = "CHECK03";
	/** COLUMNアノテーション importStatus. */
	public static final String check04_COLUMN = "CHECK04";
	/** COLUMNアノテーション importStatus. */
	public static final String check05_COLUMN = "CHECK05";
	/** COLUMNアノテーション importStatus. */
	public static final String check06_COLUMN = "CHECK06";
	/** COLUMNアノテーション importStatus. */
	public static final String check07_COLUMN = "CHECK07";
	/** COLUMNアノテーション importStatus. */
	public static final String check08_COLUMN = "CHECK08";
	/** COLUMNアノテーション importStatus. */
	public static final String check09_COLUMN = "CHECK09";
	/** COLUMNアノテーション importStatus. */
	public static final String check10_COLUMN = "CHECK10";
	
	/** COLUMNアノテーション checkAll. */
	public static final String checkAll_COLUMN = "CHECK_ALL";
	
	/** COLUMNアノテーション screenRow. */
	public static final String screenRow_COLUMN = "SCREEN_ROW";
	
	/** COLUMNアノテーション deliveryName. */
	public static final String deliveryName_COLUMN = "DELIVERY_NAME";
	
	/** COLUMNアノテーション itemName. */
	public static final String itemName_COLUMN = "ITEM_NAME";
	
	/** COLUMNアノテーション importDataNum. */
	public static final String importDataNum_COLUMN = "IMPORT_DATA_NUM";
	
	/** COLUMNアノテーション venderCd. */
	public static final String venderCd_COLUMN = "VENDER_CD";
	
	/** COLUMNアノテーション venderCd. */
	public static final String venderShortedName_COLUMN = "VENDER_SHORTED_NAME";
	
	
	/** COLUMNアノテーション orderMailAddress1. */
	public static final String orderMailAddress1_COLUMN = "ORDER_MAIL_ADDRESS1";
	
	/** COLUMNアノテーション orderMailAddress2. */
	public static final String orderMailAddress2_COLUMN = "ORDER_MAIL_ADDRESS2";
	
	/** COLUMNアノテーション orderMailAddress3. */
	public static final String orderMailAddress3_COLUMN = "ORDER_MAIL_ADDRESS3";
	
	/** COLUMNアノテーション orderMailOutput. */
	public static final String orderMailOutput_COLUMN = "ORDER_MAIL_OUTPUT";
	
	//
	// インスタンスフィールド
	//
	private String pkNo;
	
	private BigDecimal pkRow;
	
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

	private String deliveryExpectedDate;
	
	private BigDecimal orderQty;
	
	private BigDecimal carryFare;
	
	private String carryGroup;
	
	private String errorStatus;
	
	private String importStatus;
	
	private String check01;
	private String check02;
	private String check03;
	private String check04;
	private String check05;
	private String check06;
	private String check07;
	private String check08;
	private String check09;
	private String check10;
	
	private String checkAll;
	
	private String checkFlag;

	private BigDecimal screenRow;
	
	private String deliveryName;
	
	private String itemName;
	
	private BigDecimal importDataNum;
	
	private String venderCd;
	
	private String orderMailOutput;
	
	private String venderShortedName;
	
	private String orderMailAddress1;
	
	private String orderMailAddress2;

	private String orderMailAddress3;

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
	 * deliveryExpectedDateを取得します。
	 * @return deliveryExpectedDate
	 */
	public String getDeliveryExpectedDate() {
		return deliveryExpectedDate;
	}

	/**
	 * deliveryExpectedDateを設定します。
	 * @param deliveryExpectedDate deliveryExpectedDate
	 */
	public void setDeliveryExpectedDate(String deliveryExpectedDate) {
		this.deliveryExpectedDate = deliveryExpectedDate;
	}

	/**
	 * orderQtyを取得します。
	 * @return orderQty
	 */
	public BigDecimal getOrderQty() {
		return orderQty;
	}

	/**
	 * orderQtyを設定します。
	 * @param orderQty orderQty
	 */
	public void setOrderQty(BigDecimal orderQty) {
		this.orderQty = orderQty;
	}

	/**
	 * carryFareを取得します。
	 * @return carryFare
	 */
	public BigDecimal getCarryFare() {
		return carryFare;
	}

	/**
	 * carryFareを設定します。
	 * @param carryFare carryFare
	 */
	public void setCarryFare(BigDecimal carryFare) {
		this.carryFare = carryFare;
	}

	/**
	 * carryGroupを取得します。
	 * @return carryGroup
	 */
	public String getCarryGroup() {
		return carryGroup;
	}

	/**
	 * carryGroupを設定します。
	 * @param carryGroup carryGroup
	 */
	public void setCarryGroup(String carryGroup) {
		this.carryGroup = carryGroup;
	}

	/**
	 * errorStatusを取得します。
	 * @return errorStatus
	 */
	public String getErrorStatus() {
		return errorStatus;
	}

	/**
	 * errorStatusを設定します。
	 * @param errorStatus errorStatus
	 */
	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}

	/**
	 * importStatusを取得します。
	 * @return importStatus
	 */
	public String getImportStatus() {
		return importStatus;
	}

	/**
	 * importStatusを設定します。
	 * @param importStatus importStatus
	 */
	public void setImportStatus(String importStatus) {
		this.importStatus = importStatus;
	}

	/**
	 * check01を取得します。
	 * @return check01
	 */
	public String getCheck01() {
		return check01;
	}

	/**
	 * check01を設定します。
	 * @param check01 check01
	 */
	public void setCheck01(String check01) {
		this.check01 = check01;
	}

	/**
	 * check02を取得します。
	 * @return check02
	 */
	public String getCheck02() {
		return check02;
	}

	/**
	 * check02を設定します。
	 * @param check02 check02
	 */
	public void setCheck02(String check02) {
		this.check02 = check02;
	}

	/**
	 * check03を取得します。
	 * @return check03
	 */
	public String getCheck03() {
		return check03;
	}

	/**
	 * check03を設定します。
	 * @param check03 check03
	 */
	public void setCheck03(String check03) {
		this.check03 = check03;
	}

	/**
	 * check04を取得します。
	 * @return check04
	 */
	public String getCheck04() {
		return check04;
	}

	/**
	 * check04を設定します。
	 * @param check04 check04
	 */
	public void setCheck04(String check04) {
		this.check04 = check04;
	}

	/**
	 * check05を取得します。
	 * @return check05
	 */
	public String getCheck05() {
		return check05;
	}

	/**
	 * check05を設定します。
	 * @param check05 check05
	 */
	public void setCheck05(String check05) {
		this.check05 = check05;
	}

	/**
	 * check06を取得します。
	 * @return check06
	 */
	public String getCheck06() {
		return check06;
	}

	/**
	 * check06を設定します。
	 * @param check06 check06
	 */
	public void setCheck06(String check06) {
		this.check06 = check06;
	}

	/**
	 * check07を取得します。
	 * @return check07
	 */
	public String getCheck07() {
		return check07;
	}

	/**
	 * check07を設定します。
	 * @param check07 check07
	 */
	public void setCheck07(String check07) {
		this.check07 = check07;
	}

	/**
	 * check08を取得します。
	 * @return check08
	 */
	public String getCheck08() {
		return check08;
	}

	/**
	 * check08を設定します。
	 * @param check08 check08
	 */
	public void setCheck08(String check08) {
		this.check08 = check08;
	}

	/**
	 * check09を取得します。
	 * @return check09
	 */
	public String getCheck09() {
		return check09;
	}

	/**
	 * check09を設定します。
	 * @param check09 check09
	 */
	public void setCheck09(String check09) {
		this.check09 = check09;
	}

	/**
	 * check10を取得します。
	 * @return check10
	 */
	public String getCheck10() {
		return check10;
	}

	/**
	 * check10を設定します。
	 * @param check10 check10
	 */
	public void setCheck10(String check10) {
		this.check10 = check10;
	}

	/**
	 * checkAllを取得します。
	 * @return checkAll
	 */
	public String getCheckAll() {
		return checkAll;
	}

	/**
	 * checkAllを設定します。
	 * @param checkAll checkAll
	 */
	public void setCheckAll(String checkAll) {
		this.checkAll = checkAll;
	}

	/**
	 * checkFlagを取得します。
	 * @return checkFlag
	 */
	public String getCheckFlag() {
		return checkFlag;
	}

	/**
	 * checkFlagを設定します。
	 * @param checkFlag checkFlag
	 */
	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	/**
	 * pkNoを取得します。
	 * @return pkNo
	 */
	public String getPkNo() {
		return pkNo;
	}

	/**
	 * pkNoを設定します。
	 * @param pkNo pkNo
	 */
	public void setPkNo(String pkNo) {
		this.pkNo = pkNo;
	}

	/**
	 * pkRowを取得します。
	 * @return pkRow
	 */
	public BigDecimal getPkRow() {
		return pkRow;
	}

	/**
	 * pkRowを設定します。
	 * @param pkRow pkRow
	 */
	public void setPkRow(BigDecimal pkRow) {
		this.pkRow = pkRow;
	}

	/**
	 * screenRowを取得します。
	 * @return screenRow
	 */
	public BigDecimal getScreenRow() {
		return screenRow;
	}

	/**
	 * screenRowを設定します。
	 * @param screenRow screenRow
	 */
	public void setScreenRow(BigDecimal screenRow) {
		this.screenRow = screenRow;
	}

	/**
	 * deliveryNameを取得します。
	 * @return deliveryName
	 */
	public String getDeliveryName() {
		return deliveryName;
	}

	/**
	 * deliveryNameを設定します。
	 * @param deliveryName deliveryName
	 */
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	/**
	 * itemNameを取得します。
	 * @return itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * itemNameを設定します。
	 * @param itemName itemName
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * importDataNumを取得します。
	 * @return importDataNum
	 */
	public BigDecimal getImportDataNum() {
		return importDataNum;
	}

	/**
	 * importDataNumを設定します。
	 * @param importDataNum importDataNum
	 */
	public void setImportDataNum(BigDecimal importDataNum) {
		this.importDataNum = importDataNum;
	}

	/**
	 * venderCdを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * venderCdを設定します。
	 * @param venderCd venderCd
	 */
	public void setVenderCd(String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * orderMailAddress1を取得します。
	 * @return orderMailAddress1
	 */
	public String getOrderMailAddress1() {
		return orderMailAddress1;
	}

	/**
	 * orderMailAddress1を設定します。
	 * @param orderMailAddress1 orderMailAddress1
	 */
	public void setOrderMailAddress1(String orderMailAddress1) {
		this.orderMailAddress1 = orderMailAddress1;
	}

	/**
	 * orderMailAddress2を取得します。
	 * @return orderMailAddress2
	 */
	public String getOrderMailAddress2() {
		return orderMailAddress2;
	}

	/**
	 * orderMailAddress2を設定します。
	 * @param orderMailAddress2 orderMailAddress2
	 */
	public void setOrderMailAddress2(String orderMailAddress2) {
		this.orderMailAddress2 = orderMailAddress2;
	}

	/**
	 * orderMailAddress3を取得します。
	 * @return orderMailAddress3
	 */
	public String getOrderMailAddress3() {
		return orderMailAddress3;
	}

	/**
	 * orderMailAddress3を設定します。
	 * @param orderMailAddress3 orderMailAddress3
	 */
	public void setOrderMailAddress3(String orderMailAddress3) {
		this.orderMailAddress3 = orderMailAddress3;
	}

	/**
	 * venderShortedNameを取得します。
	 * @return venderShortedName
	 */
	public String getVenderShortedName() {
		return venderShortedName;
	}

	/**
	 * venderShortedNameを設定します。
	 * @param venderShortedName venderShortedName
	 */
	public void setVenderShortedName(String venderShortedName) {
		this.venderShortedName = venderShortedName;
	}

	/**
	 * orderMailOutputを取得します。
	 * @return orderMailOutput
	 */
	public String getOrderMailOutput() {
		return orderMailOutput;
	}

	/**
	 * orderMailOutputを設定します。
	 * @param orderMailOutput orderMailOutput
	 */
	public void setOrderMailOutput(String orderMailOutput) {
		this.orderMailOutput = orderMailOutput;
	}

}
