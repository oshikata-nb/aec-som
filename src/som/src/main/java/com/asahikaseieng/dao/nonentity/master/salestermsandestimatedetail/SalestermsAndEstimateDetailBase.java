
/*
 * Created on 2009/08/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsandestimatedetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SalestermsAndEstimateDetailListクラス.
 * @author t0011036
 */
public class SalestermsAndEstimateDetailBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalestermsAndEstimateDetailBase() {
	}

	//
	// 定数
	//
	
	/** COLUMNアノテーション pkNo */
	public static final String pkNo_COLUMN = "PK_NO";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";
	
	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";
	
	/** COLUMNアノテーション processDivision */
	public static final String processDivision_COLUMN = "PROCESS_DIVISION";
	
	/** COLUMNアノテーション itemCdFrom */
	public static final String itemCdFrom_COLUMN = "ITEM_CD_FROM";
	
	/** COLUMNアノテーション itemNameFrom */
	public static final String itemNameFrom_COLUMN = "ITEM_NAME_FROM";

	/** COLUMNアノテーション styleOfPackingFrom */
	public static final String styleOfPackingFrom_COLUMN = "STYLE_OF_PACKING_FROM";
	
	/** COLUMNアノテーション otherCompanyCd1From */
	public static final String otherCompanyCd1From_COLUMN = "OTHER_COMPANY_CD1_FROM";
		
	/** COLUMNアノテーション tantoName */
	public static final String tantoName_COLUMN = "TANTO_NM";
	
	/** COLUMNアノテーション status */
	public static final String status_COLUMN = "STATUS";	

	/** COLUMNアノテーション statusName */
	public static final String statusName_COLUMN = "STATUS_NAME";
	
	/** COLUMNアノテーション itemCdTo */
	public static final String itemCdTo_COLUMN = "ITEM_CD_TO";
	
	/** COLUMNアノテーション itemNameTo */
	public static final String itemNameTo_COLUMN = "ITEM_NAME_TO";
	
	/** COLUMNアノテーション styleOfPackingTo */
	public static final String styleOfPackingTo_COLUMN = "STYLE_OF_PACKING_TO";
	
	/** COLUMNアノテーション otherCompanyCd1To */
	public static final String otherCompanyCd1To_COLUMN = "OTHER_COMPANY_CD1_TO";

	/** COLUMNアノテーション dataCount */
	public static final String dataCount_COLUMN = "DATA_COUNT";

	//
	// インスタンスフィールド
	//

	private String pkNo;

	private Timestamp inputDate;
	
	private Timestamp updateDate;
	
	private String processDivision;
	
	private String itemCdFrom;
	
	private String itemNameFrom;
	
	private String styleOfPackingFrom;
	
	private String otherCompanyCd1From;
	
	private String tantoName;
	
	private String status;
	
	private String statusName;
	
	private String itemCdTo;
	
	private String itemNameTo;
	
	private String styleOfPackingTo;
	
	private String otherCompanyCd1To;
	
	private BigDecimal dataCount;

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
	public void setPkNo(final String pkNo) {
		this.pkNo = pkNo;
	}

	/**
	 * styleOfPackingFromを取得します。
	 * @return styleOfPackingFrom
	 */
	public String getStyleOfPackingFrom() {
		return styleOfPackingFrom;
	}

	/**
	 * styleOfPackingFromを設定します。
	 * @param styleOfPackingFrom styleOfPackingFrom
	 */
	public void setStyleOfPackingFrom(final String styleOfPackingFrom) {
		this.styleOfPackingFrom = styleOfPackingFrom;
	}

	/**
	 * statusNameを取得します。
	 * @return statusName
	 */
	public String getStatusName() {
		return statusName;
	}

	/**
	 * statusNameを設定します。
	 * @param statusName statusName
	 */
	public void setStatusName(final String statusName) {
		this.statusName = statusName;
	}

	/**
	 * styleOfPackingToを取得します。
	 * @return styleOfPackingTo
	 */
	public String getStyleOfPackingTo() {
		return styleOfPackingTo;
	}

	/**
	 * styleOfPackingToを設定します。
	 * @param styleOfPackingTo styleOfPackingTo
	 */
	public void setStyleOfPackingTo(final String styleOfPackingTo) {
		this.styleOfPackingTo = styleOfPackingTo;
	}

	/**
	 * processDivisionを取得します。
	 * @return processDivision
	 */
	public String getProcessDivision() {
		return processDivision;
	}

	/**
	 * processDivisionを設定します。
	 * @param processDivision processDivision
	 */
	public void setProcessDivision(final String processDivision) {
		this.processDivision = processDivision;
	}

	/**
	 * itemCdFromを取得します。
	 * @return itemCdFrom
	 */
	public String getItemCdFrom() {
		return itemCdFrom;
	}

	/**
	 * itemCdFromを設定します。
	 * @param itemCdFrom itemCdFrom
	 */
	public void setItemCdFrom(final String itemCdFrom) {
		this.itemCdFrom = itemCdFrom;
	}

	/**
	 * itemNameFromを取得します。
	 * @return itemNameFrom
	 */
	public String getItemNameFrom() {
		return itemNameFrom;
	}

	/**
	 * itemNameFromを設定します。
	 * @param itemNameFrom itemNameFrom
	 */
	public void setItemNameFrom(final String itemNameFrom) {
		this.itemNameFrom = itemNameFrom;
	}

	/**
	 * otherCompanyCd1Fromを取得します。
	 * @return otherCompanyCd1From
	 */
	public String getOtherCompanyCd1From() {
		return otherCompanyCd1From;
	}

	/**
	 * otherCompanyCd1Fromを設定します。
	 * @param otherCompanyCd1From otherCompanyCd1From
	 */
	public void setOtherCompanyCd1From(final String otherCompanyCd1From) {
		this.otherCompanyCd1From = otherCompanyCd1From;
	}

	/**
	 * tantoNameを取得します。
	 * @return tantoName
	 */
	public String getTantoName() {
		return tantoName;
	}

	/**
	 * tantoNameを設定します。
	 * @param tantoName tantoName
	 */
	public void setTantoName(final String tantoName) {
		this.tantoName = tantoName;
	}

	/**
	 * itemCdToを取得します。
	 * @return itemCdTo
	 */
	public String getItemCdTo() {
		return itemCdTo;
	}

	/**
	 * itemCdToを設定します。
	 * @param itemCdTo itemCdTo
	 */
	public void setItemCdTo(final String itemCdTo) {
		this.itemCdTo = itemCdTo;
	}

	/**
	 * itemNameToを取得します。
	 * @return itemNameTo
	 */
	public String getItemNameTo() {
		return itemNameTo;
	}

	/**
	 * itemNameToを設定します。
	 * @param itemNameTo itemNameTo
	 */
	public void setItemNameTo(final String itemNameTo) {
		this.itemNameTo = itemNameTo;
	}

	/**
	 * otherCompanyCd1Toを取得します。
	 * @return otherCompanyCd1To
	 */
	public String getOtherCompanyCd1To() {
		return otherCompanyCd1To;
	}

	/**
	 * otherCompanyCd1Toを設定します。
	 * @param otherCompanyCd1To otherCompanyCd1To
	 */
	public void setOtherCompanyCd1To(final String otherCompanyCd1To) {
		this.otherCompanyCd1To = otherCompanyCd1To;
	}

	/**
	 * inputDateを取得します。
	 * @return inputDate
	 */
	public Timestamp getInputDate() {
		return inputDate;
	}

	/**
	 * inputDateを設定します。
	 * @param inputDate inputDate
	 */
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * statusを取得します。
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * statusを設定します。
	 * @param status status
	 */
	public void setStatus(final String status) {
		this.status = status;
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
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * dataCountを取得します。
	 * @return dataCount
	 */
	public BigDecimal getDataCount() {
		return dataCount;
	}

	/**
	 * dataCountを設定します。
	 * @param dataCount dataCount
	 */
	public void setDataCount(BigDecimal dataCount) {
		this.dataCount = dataCount;
	}


}


