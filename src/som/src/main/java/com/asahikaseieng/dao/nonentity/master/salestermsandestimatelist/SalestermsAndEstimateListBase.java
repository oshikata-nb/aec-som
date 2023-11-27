package com.asahikaseieng.dao.nonentity.master.salestermsandestimatelist;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SalesTermsListクラス.
 * @author t0011036
 */
public class SalestermsAndEstimateListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalestermsAndEstimateListBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション pkNo */
	public static final String pkNo_COLUMN = "PK_NO";
	
	/** COLUMNアノテーション srhInputDate */
	public static final String srhInputDate_COLUMN = "INPUT_DATE";
	
	/** COLUMNアノテーション processDivision */
	public static final String processDivision_COLUMN = "PROCESS_DIVISION";

	/** COLUMNアノテーション processName */
	public static final String processName_COLUMN = "PROCESS_NAME";
	
	/** COLUMNアノテーション srhItemCdFrom */
	public static final String srhItemCdFrom_COLUMN = "ITEM_CD_FROM";
	
	/** COLUMNアノテーション srhItemNameFrom */
	public static final String srhItemNameFrom_COLUMN = "ITEM_NAME_FROM";

	/** COLUMNアノテーション styleOfPackingFrom */
	public static final String styleOfPackingFrom_COLUMN = "STYLE_OF_PACKING_FROM";
	
	/** COLUMNアノテーション srhOtherCompanyCd1From */
	public static final String srhOtherCompanyCd1From_COLUMN = "OTHER_COMPANY_CD1_FROM";
	
	/** COLUMNアノテーション activateFrom */
	public static final String activateFrom_COLUMN = "ACTIVATE_FROM";
	
	/** COLUMNアノテーション srhTantoName */
	public static final String srhTantoName_COLUMN = "TANTO_NM";
	
	/** COLUMNアノテーション status */
	public static final String status_COLUMN = "STATUS";

	/** COLUMNアノテーション statusName */
	public static final String statusName_COLUMN = "STATUS_NAME";
	
	/** COLUMNアノテーション srhItemCdTo */
	public static final String srhItemCdTo_COLUMN = "ITEM_CD_TO";
	
	/** COLUMNアノテーション srhItemNameTo */
	public static final String srhItemNameTo_COLUMN = "ITEM_NAME_TO";
	
	/** COLUMNアノテーション styleOfPackingTo */
	public static final String styleOfPackingTo_COLUMN = "STYLE_OF_PACKING_TO";
	
	/** COLUMNアノテーション srhOtherCompanyCd1To */
	public static final String srhOtherCompanyCd1To_COLUMN = "OTHER_COMPANY_CD1_TO";
	
	/** COLUMNアノテーション activateTo */
	public static final String activateTo_COLUMN = "ACTIVATE_TO";
	
	//
	// インスタンスフィールド
	//
	private String pkNo;

	private Timestamp srhInputDate;
	
	private String processDivision;
	
	private String processName;
	
	private String srhItemCdFrom;
	
	private String srhItemNameFrom;
	
	private String styleOfPackingFrom;
	
	private String srhOtherCompanyCd1From;
	
	private String activateFrom;
	
	private String srhTantoName;
	
	private String status;
	
	private String statusName;
	
	private String srhItemCdTo;
	
	private String srhItemNameTo;
	
	private String styleOfPackingTo;
	
	private String srhOtherCompanyCd1To;
	
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
	 * srhOtherCompanyCd1Fromを取得します。
	 * @return srhOtherCompanyCd1From
	 */
	public String getSrhOtherCompanyCd1From() {
		return srhOtherCompanyCd1From;
	}

	/**
	 * srhOtherCompanyCd1Fromを設定します。
	 * @param srhOtherCompanyCd1From srhOtherCompanyCd1From
	 */
	public void setSrhOtherCompanyCd1From(final String srhOtherCompanyCd1From) {
		this.srhOtherCompanyCd1From = srhOtherCompanyCd1From;
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
	public void setActivateFrom(final String activateFrom) {
		this.activateFrom = activateFrom;
	}

	/**
	 * srhTantoNameを取得します。
	 * @return srhTantoName
	 */
	public String getSrhTantoName() {
		return srhTantoName;
	}

	/**
	 * srhTantoNameを設定します。
	 * @param srhTantoName srhTantoName
	 */
	public void setSrhTantoName(final String srhTantoName) {
		this.srhTantoName = srhTantoName;
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
	 * srhOtherCompanyCd1Toを取得します。
	 * @return srhOtherCompanyCd1To
	 */
	public String getSrhOtherCompanyCd1To() {
		return srhOtherCompanyCd1To;
	}

	/**
	 * srhOtherCompanyCd1Toを設定します。
	 * @param srhOtherCompanyCd1To srhOtherCompanyCd1To
	 */
	public void setSrhOtherCompanyCd1To(final String srhOtherCompanyCd1To) {
		this.srhOtherCompanyCd1To = srhOtherCompanyCd1To;
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
	public void setActivateTo(final String activateTo) {
		this.activateTo = activateTo;
	}

	/**
	 * srhItemCdFromを取得します。
	 * @return srhItemCdFrom
	 */
	public String getSrhItemCdFrom() {
		return srhItemCdFrom;
	}

	/**
	 * srhItemCdFromを設定します。
	 * @param srhItemCdFrom srhItemCdFrom
	 */
	public void setSrhItemCdFrom(final String srhItemCdFrom) {
		this.srhItemCdFrom = srhItemCdFrom;
	}

	/**
	 * srhItemNameFromを取得します。
	 * @return srhItemNameFrom
	 */
	public String getSrhItemNameFrom() {
		return srhItemNameFrom;
	}

	/**
	 * srhItemNameFromを設定します。
	 * @param srhItemNameFrom srhItemNameFrom
	 */
	public void setSrhItemNameFrom(final String srhItemNameFrom) {
		this.srhItemNameFrom = srhItemNameFrom;
	}

	/**
	 * srhItemCdToを取得します。
	 * @return srhItemCdTo
	 */
	public String getSrhItemCdTo() {
		return srhItemCdTo;
	}

	/**
	 * srhItemCdToを設定します。
	 * @param srhItemCdTo srhItemCdTo
	 */
	public void setSrhItemCdTo(final String srhItemCdTo) {
		this.srhItemCdTo = srhItemCdTo;
	}

	/**
	 * srhItemNameToを取得します。
	 * @return srhItemNameTo
	 */
	public String getSrhItemNameTo() {
		return srhItemNameTo;
	}

	/**
	 * srhItemNameToを設定します。
	 * @param srhItemNameTo srhItemNameTo
	 */
	public void setSrhItemNameTo(final String srhItemNameTo) {
		this.srhItemNameTo = srhItemNameTo;
	}

	/**
	 * srhInputDateを取得します。
	 * @return srhInputDate
	 */
	public Timestamp getSrhInputDate() {
		return this.srhInputDate;
	}

	/**
	 * srhInputDateを設定します。
	 * @param srhInputDate srhInputDate
	 */
	public void setSrhInputDate(final Timestamp srhInputDate) {
		this.srhInputDate = srhInputDate;
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
}