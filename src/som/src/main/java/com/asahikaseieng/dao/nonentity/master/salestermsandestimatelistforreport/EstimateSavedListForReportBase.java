package com.asahikaseieng.dao.nonentity.master.salestermsandestimatelistforreport;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SalesTermsListForReportクラス.
 */
public class EstimateSavedListForReportBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public EstimateSavedListForReportBase() {
	}

	//
	// 定数
	//
	public static final String pkNo_COLUMN = "PK_NO";
	public static final String procType_COLUMN = "PROC_TYPE";
	public static final String estimateNo_COLUMN = "ESTIMATE_NO";
	public static final String estimateInputDate_COLUMN = "ESTIMATE_INPUT_DATE";
	public static final String balanceCd_COLUMN = "BALANCE_CD";
	public static final String venderCd_COLUMN = "VENDER_CD";
	public static final String venderName_COLUMN = "VENDER_NAME";
	public static final String itemCd_COLUMN = "ITEM_CD";
	public static final String itemName_COLUMN = "ITEM_NAME";
	public static final String consecutiveNo_COLUMN = "CONSECUTIVE_NO";
	public static final String standard_unitPrice_COLUMN = "STANDARD_UNIT_PRICE";
	public static final String standardDiscount_COLUMN = "STANDARD_DISCOUNT";
	public static final String specialDiscount_COLUMN = "SPECIAL_DISCOUNT";
	public static final String unitprice_COLUMN = "UNITPRICE";
	public static final String standardAmount_COLUMN = "STANDARD_AMOUNT";
	public static final String matss_COLUMN = "MATSS";
	public static final String estimateValidDateFrom_COLUMN = "ESTIMATE_VALID_DATE_FROM";
	public static final String estimateValidDateTo_COLUMN = "ESTIMATE_VALID_DATE_TO";
	public static final String remark_COLUMN = "REMARK";
	public static final String inputDate_COLUMN = "INPUT_DATE";
	public static final String inputorCd_COLUMN = "INPUTOR_CD";
	public static final String inputorName_COLUMN = "INPUTOR_NAME";
	public static final String updateDate_COLUMN = "UPDATE_DATE";
	public static final String updatorCd_COLUMN = "UPDATOR_CD";
	public static final String updatorName_COLUMN = "UPDATOR_NAME";
	public static final String estimateStatus_COLUMN = "ESTIMATE_STATUS";
	
	//
	// インスタンスフィールド
	//
	private String pkNo;
	private String procType;
	private String estimateNo;
	private Timestamp estimateInputDate;
	private String balanceCd;
	private String venderCd;
	private String venderName;
	private String itemCd;
	private String itemName;
	private String consecutiveNo;
	private BigDecimal standard_unitPrice;
	private BigDecimal standardDiscount;
	private BigDecimal specialDiscount;
	private BigDecimal unitprice;
	private BigDecimal standardAmount;
	private BigDecimal matss;
	private Timestamp estimateValidDateFrom;
	private Timestamp estimateValidDateTo;
	private String remark;
	private Timestamp inputDate;
	private String inputorCd;
	private String inputorName;
	private Timestamp updateDate;
	private String updatorCd;
	private String updatorName;
	private String estimateStatus;
	
	

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
	public void setPkNo(String pkNo) {
		this.pkNo = pkNo;
	}

	/**
	 * procTypeを取得します。
	 * @return procType
	 */
	public String getProcType() {
		return procType;
	}

	/**
	 * procTypeを設定します。
	 * @param procType procType
	 */
	public void setProcType(String procType) {
		this.procType = procType;
	}

	/**
	 * estimateNoを取得します。
	 * @return estimateNo
	 */
	public String getEstimateNo() {
		return estimateNo;
	}

	/**
	 * estimateNoを設定します。
	 * @param estimateNo estimateNo
	 */
	public void setEstimateNo(String estimateNo) {
		this.estimateNo = estimateNo;
	}

	/**
	 * estimateInputDateを取得します。
	 * @return estimateInputDate
	 */
	public Timestamp getEstimateInputDate() {
		return estimateInputDate;
	}

	/**
	 * estimateInputDateを設定します。
	 * @param estimateInputDate estimateInputDate
	 */
	public void setEstimateInputDate(Timestamp estimateInputDate) {
		this.estimateInputDate = estimateInputDate;
	}

	/**
	 * balanceCdを取得します。
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return balanceCd;
	}

	/**
	 * balanceCdを設定します。
	 * @param balanceCd balanceCd
	 */
	public void setBalanceCd(String balanceCd) {
		this.balanceCd = balanceCd;
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
	 * venderNameを取得します。
	 * @return venderName
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * venderNameを設定します。
	 * @param venderName venderName
	 */
	public void setVenderName(String venderName) {
		this.venderName = venderName;
	}

	/**
	 * itemCdを取得します。
	 * @return itemCd
	 */
	public String getItemCd() {
		return itemCd;
	}

	/**
	 * itemCdを設定します。
	 * @param itemCd itemCd
	 */
	public void setItemCd(String itemCd) {
		this.itemCd = itemCd;
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
	 * consecutiveNoを取得します。
	 * @return consecutiveNo
	 */
	public String getConsecutiveNo() {
		return consecutiveNo;
	}

	/**
	 * consecutiveNoを設定します。
	 * @param consecutiveNo consecutiveNo
	 */
	public void setConsecutiveNo(String consecutiveNo) {
		this.consecutiveNo = consecutiveNo;
	}

	/**
	 * standard_unitPriceを取得します。
	 * @return standard_unitPrice
	 */
	public BigDecimal getStandard_unitPrice() {
		return standard_unitPrice;
	}

	/**
	 * standard_unitPriceを設定します。
	 * @param standard_unitPrice standard_unitPrice
	 */
	public void setStandard_unitPrice(BigDecimal standard_unitPrice) {
		this.standard_unitPrice = standard_unitPrice;
	}

	/**
	 * standardDiscountを取得します。
	 * @return standardDiscount
	 */
	public BigDecimal getStandardDiscount() {
		return standardDiscount;
	}

	/**
	 * standardDiscountを設定します。
	 * @param standardDiscount standardDiscount
	 */
	public void setStandardDiscount(BigDecimal standardDiscount) {
		this.standardDiscount = standardDiscount;
	}

	/**
	 * specialDiscountを取得します。
	 * @return specialDiscount
	 */
	public BigDecimal getSpecialDiscount() {
		return specialDiscount;
	}

	/**
	 * specialDiscountを設定します。
	 * @param specialDiscount specialDiscount
	 */
	public void setSpecialDiscount(BigDecimal specialDiscount) {
		this.specialDiscount = specialDiscount;
	}

	/**
	 * unitpriceを取得します。
	 * @return unitprice
	 */
	public BigDecimal getUnitprice() {
		return unitprice;
	}

	/**
	 * unitpriceを設定します。
	 * @param unitprice unitprice
	 */
	public void setUnitprice(BigDecimal unitprice) {
		this.unitprice = unitprice;
	}

	/**
	 * standardAmountを取得します。
	 * @return standardAmount
	 */
	public BigDecimal getStandardAmount() {
		return standardAmount;
	}

	/**
	 * standardAmountを設定します。
	 * @param standardAmount standardAmount
	 */
	public void setStandardAmount(BigDecimal standardAmount) {
		this.standardAmount = standardAmount;
	}

	/**
	 * matssを取得します。
	 * @return matss
	 */
	public BigDecimal getMatss() {
		return matss;
	}

	/**
	 * matssを設定します。
	 * @param matss matss
	 */
	public void setMatss(BigDecimal matss) {
		this.matss = matss;
	}

	/**
	 * estimateValidDateFromを取得します。
	 * @return estimateValidDateFrom
	 */
	public Timestamp getEstimateValidDateFrom() {
		return estimateValidDateFrom;
	}

	/**
	 * estimateValidDateFromを設定します。
	 * @param estimateValidDateFrom estimateValidDateFrom
	 */
	public void setEstimateValidDateFrom(Timestamp estimateValidDateFrom) {
		this.estimateValidDateFrom = estimateValidDateFrom;
	}

	/**
	 * estimateValidDateToを取得します。
	 * @return estimateValidDateTo
	 */
	public Timestamp getEstimateValidDateTo() {
		return estimateValidDateTo;
	}

	/**
	 * estimateValidDateToを設定します。
	 * @param estimateValidDateTo estimateValidDateTo
	 */
	public void setEstimateValidDateTo(Timestamp estimateValidDateTo) {
		this.estimateValidDateTo = estimateValidDateTo;
	}

	/**
	 * remarkを取得します。
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * remarkを設定します。
	 * @param remark remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	public void setInputDate(Timestamp inputDate) {
		this.inputDate = inputDate;
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
	public void setInputorName(String inputorName) {
		this.inputorName = inputorName;
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
	public void setUpdatorName(String updatorName) {
		this.updatorName = updatorName;
	}

	/**
	 * estimateStatusを取得します。
	 * @return estimateStatus
	 */
	public String getEstimateStatus() {
		return estimateStatus;
	}

	/**
	 * estimateStatusを設定します。
	 * @param estimateStatus estimateStatus
	 */
	public void setEstimateStatus(String estimateStatus) {
		this.estimateStatus = estimateStatus;
	}

	
}

