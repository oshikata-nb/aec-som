/*
 * Created on 2009/08/14
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SalesTermsListForReportクラス.
 * @author t0011036
 */
public class SalestermsAndEstimateListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalestermsAndEstimateListForReportBase() {
	}

	//
	// 定数
	//
	/** COLUMNアノテーション pkNo */
	public static final String pkNo_COLUMN = "PK_NO";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";
	
	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";
	
	/** COLUMNアノテーション inputorName */
	public static final String inputorName_COLUMN = "INPUTOR_NAME";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";
	
	/** COLUMNアノテーション updatorName */
	public static final String updatorName_COLUMN = "UPDATOR_NAME";
	
	/** COLUMNアノテーション processDivision */
	public static final String processDivision_COLUMN = "PROCESS_DIVISION";
	
	/** COLUMNアノテーション processName */
	public static final String processName_COLUMN = "PROCESS_NAME";
	
	/** COLUMNアノテーション itemCdFrom */
	public static final String itemCdFrom_COLUMN = "ITEM_CD_FROM";
	
	/** COLUMNアノテーション itemNameFrom */
	public static final String itemNameFrom_COLUMN = "ITEM_NAME_FROM";

	/** COLUMNアノテーション styleOfPackingFrom */
	public static final String styleOfPackingFrom_COLUMN = "STYLE_OF_PACKING_FROM";
	
	/** COLUMNアノテーション otherCompanyCd1From */
	public static final String otherCompanyCd1From_COLUMN = "OTHER_COMPANY_CD1_FROM";
	
	/** COLUMNアノテーション activateFrom */
	public static final String activateFrom_COLUMN = "ACTIVATE_FROM";
	
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
	
	/** COLUMNアノテーション activateTo */
	public static final String activateTo_COLUMN = "ACTIVATE_TO";
	
	//
	// インスタンスフィールド
	//

	private String pkNo;

	private Timestamp inputDate;
	
	private String inputorCd;
	
	private String inputorName;
	
	private Timestamp updateDate;
	
	private String updatorCd;
	
	private String updatorName;
	
	private String processDivision;
	
	private String processName;
	
	private String itemCdFrom;
	
	private String itemNameFrom;
	
	private String styleOfPackingFrom;
	
	private String otherCompanyCd1From;
	
	private String activateFrom;
	
	private String status;
	
	private String statusName;
	
	private String itemCdTo;
	
	private String itemNameTo;
	
	private String styleOfPackingTo;
	
	private String otherCompanyCd1To;
	
	private String activateTo;

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
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * inputorNameを取得します。
	 * @return inputorName
	 */
	public String getInputorName() {
		return inputorName;
	}

	/**
	 * inputorNameを設定します。
	 * @param inputorName inputorName
	 */
	public void setInputorName(final String inputorName) {
		this.inputorName = inputorName;
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
	public void setUpdatorCd(final String updatorCd) {
		this.updatorCd = updatorCd;
	}

	/**
	 * updatorNameを取得します。
	 * @return updatorName
	 */
	public String getUpdatorName() {
		return updatorName;
	}

	/**
	 * updatorNameを設定します。
	 * @param updatorName updatorName
	 */
	public void setUpdatorName(final String updatorName) {
		this.updatorName = updatorName;
	}

	/**
	 * processNameを取得します。
	 * @return processName
	 */
	public String getProcessName() {
		return processName;
	}

	/**
	 * processNameを設定します。
	 * @param processName processName
	 */
	public void setProcessName(final String processName) {
		this.processName = processName;
	}

	/**
	 * activateFromを取得します。
	 * @return activateFrom
	 */
	public String getActivateFrom() {
		return activateFrom;
	}

	/**
	 * activateFromを設定します。
	 * @param activateFrom activateFrom
	 */
	public void setActivateFrom(String activateFrom) {
		this.activateFrom = activateFrom;
	}

	/**
	 * activateToを取得します。
	 * @return activateTo
	 */
	public String getActivateTo() {
		return activateTo;
	}

	/**
	 * activateToを設定します。
	 * @param activateTo activateTo
	 */
	public void setActivateTo(String activateTo) {
		this.activateTo = activateTo;
	}
	
}
