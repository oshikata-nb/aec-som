/*
 * Created on 2020/10/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.orderimportsamelist;

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
public class OrderImportSameListBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OrderImportSameListBase() {
	}

	public String frstOrderNo;
	public BigDecimal frstOrderRowNo;
	public String orderImpNo;
	public String orderNo;
	public BigDecimal rowNo;
	public String shippingNo;
	public String itemCd;
	public BigDecimal orderQty;
	public BigDecimal orderUnitprice;
	public BigDecimal standardUnitprice;
	public BigDecimal standardDiscount;
	public BigDecimal specialDiscount;
	public BigDecimal tmpUnitpriceFlg;
	public BigDecimal matss;
	public BigDecimal estimateStandardAmount;
	public BigDecimal estimateMatss;
	public BigDecimal inputDivision;
	public BigDecimal weight;
	public String customerOrderDetailNo;
	public Timestamp inputApprovalDate;
	public String inputApproverCd;
	public Timestamp delDateSendDate;
	public String delDateSenderCd;
	public Timestamp deleteDate;
	public BigDecimal delFlg;
	public BigDecimal cancelQty;
	public String cancelDate;
	public BigDecimal cancelFlg;
	public BigDecimal errorFlg;
	public Timestamp inputDate;
	public String inputorCd;
	public Timestamp updateDate;
	public String updatorCd;


	// インスタンスメソッド
	//

	/**
	 * frstOrderNoを取得します。
	 * @return frstOrderNo
	 */
	public String getFrstOrderNo() {
		return frstOrderNo;
	}

	/**
	 * frstOrderNoを設定します。
	 * @param frstOrderNo frstOrderNo
	 */
	public void setFrstOrderNo(String frstOrderNo) {
		this.frstOrderNo = frstOrderNo;
	}

	/**
	 * frstOrderRowNoを取得します。
	 * @return frstOrderRowNo
	 */
	public BigDecimal getFrstOrderRowNo() {
		return frstOrderRowNo;
	}

	/**
	 * frstOrderRowNoを設定します。
	 * @param frstOrderRowNo frstOrderRowNo
	 */
	public void setFrstOrderRowNo(BigDecimal frstOrderRowNo) {
		this.frstOrderRowNo = frstOrderRowNo;
	}

	/**
	 * orderImpNoを取得します。
	 * @return orderImpNo
	 */
	public String getOrderImpNo() {
		return orderImpNo;
	}

	/**
	 * orderImpNoを設定します。
	 * @param orderImpNo orderImpNo
	 */
	public void setOrderImpNo(String orderImpNo) {
		this.orderImpNo = orderImpNo;
	}

	/**
	 * orderNoを取得します。
	 * @return orderNo
	 */
	public String getOrderNo() {
		return orderNo;
	}

	/**
	 * orderNoを設定します。
	 * @param orderNo orderNo
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * rowNoを取得します。
	 * @return rowNo
	 */
	public BigDecimal getRowNo() {
		return rowNo;
	}

	/**
	 * rowNoを設定します。
	 * @param rowNo rowNo
	 */
	public void setRowNo(BigDecimal rowNo) {
		this.rowNo = rowNo;
	}

	/**
	 * shippingNoを取得します。
	 * @return shippingNo
	 */
	public String getShippingNo() {
		return shippingNo;
	}

	/**
	 * shippingNoを設定します。
	 * @param shippingNo shippingNo
	 */
	public void setShippingNo(String shippingNo) {
		this.shippingNo = shippingNo;
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
	 * orderUnitpriceを取得します。
	 * @return orderUnitprice
	 */
	public BigDecimal getOrderUnitprice() {
		return orderUnitprice;
	}

	/**
	 * orderUnitpriceを設定します。
	 * @param orderUnitprice orderUnitprice
	 */
	public void setOrderUnitprice(BigDecimal orderUnitprice) {
		this.orderUnitprice = orderUnitprice;
	}

	/**
	 * standardUnitpriceを取得します。
	 * @return standardUnitprice
	 */
	public BigDecimal getStandardUnitprice() {
		return standardUnitprice;
	}

	/**
	 * standardUnitpriceを設定します。
	 * @param standardUnitprice standardUnitprice
	 */
	public void setStandardUnitprice(BigDecimal standardUnitprice) {
		this.standardUnitprice = standardUnitprice;
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
	 * tmpUnitpriceFlgを取得します。
	 * @return tmpUnitpriceFlg
	 */
	public BigDecimal getTmpUnitpriceFlg() {
		return tmpUnitpriceFlg;
	}

	/**
	 * tmpUnitpriceFlgを設定します。
	 * @param tmpUnitpriceFlg tmpUnitpriceFlg
	 */
	public void setTmpUnitpriceFlg(BigDecimal tmpUnitpriceFlg) {
		this.tmpUnitpriceFlg = tmpUnitpriceFlg;
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
	 * estimateStandardAmountを取得します。
	 * @return estimateStandardAmount
	 */
	public BigDecimal getEstimateStandardAmount() {
		return estimateStandardAmount;
	}

	/**
	 * estimateStandardAmountを設定します。
	 * @param estimateStandardAmount estimateStandardAmount
	 */
	public void setEstimateStandardAmount(BigDecimal estimateStandardAmount) {
		this.estimateStandardAmount = estimateStandardAmount;
	}

	/**
	 * estimateMatssを取得します。
	 * @return estimateMatss
	 */
	public BigDecimal getEstimateMatss() {
		return estimateMatss;
	}

	/**
	 * estimateMatssを設定します。
	 * @param estimateMatss estimateMatss
	 */
	public void setEstimateMatss(BigDecimal estimateMatss) {
		this.estimateMatss = estimateMatss;
	}

	/**
	 * inputDivisionを取得します。
	 * @return inputDivision
	 */
	public BigDecimal getInputDivision() {
		return inputDivision;
	}

	/**
	 * inputDivisionを設定します。
	 * @param inputDivision inputDivision
	 */
	public void setInputDivision(BigDecimal inputDivision) {
		this.inputDivision = inputDivision;
	}

	/**
	 * weightを取得します。
	 * @return weight
	 */
	public BigDecimal getWeight() {
		return weight;
	}

	/**
	 * weightを設定します。
	 * @param weight weight
	 */
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	/**
	 * customerOrderDetailNoを取得します。
	 * @return customerOrderDetailNo
	 */
	public String getCustomerOrderDetailNo() {
		return customerOrderDetailNo;
	}

	/**
	 * customerOrderDetailNoを設定します。
	 * @param customerOrderDetailNo customerOrderDetailNo
	 */
	public void setCustomerOrderDetailNo(String customerOrderDetailNo) {
		this.customerOrderDetailNo = customerOrderDetailNo;
	}

	/**
	 * inputApprovalDateを取得します。
	 * @return inputApprovalDate
	 */
	public Timestamp getInputApprovalDate() {
		return inputApprovalDate;
	}

	/**
	 * inputApprovalDateを設定します。
	 * @param inputApprovalDate inputApprovalDate
	 */
	public void setInputApprovalDate(Timestamp inputApprovalDate) {
		this.inputApprovalDate = inputApprovalDate;
	}

	/**
	 * inputApproverCdを取得します。
	 * @return inputApproverCd
	 */
	public String getInputApproverCd() {
		return inputApproverCd;
	}

	/**
	 * inputApproverCdを設定します。
	 * @param inputApproverCd inputApproverCd
	 */
	public void setInputApproverCd(String inputApproverCd) {
		this.inputApproverCd = inputApproverCd;
	}

	/**
	 * delDateSendDateを取得します。
	 * @return delDateSendDate
	 */
	public Timestamp getDelDateSendDate() {
		return delDateSendDate;
	}

	/**
	 * delDateSendDateを設定します。
	 * @param delDateSendDate delDateSendDate
	 */
	public void setDelDateSendDate(Timestamp delDateSendDate) {
		this.delDateSendDate = delDateSendDate;
	}

	/**
	 * delDateSenderCdを取得します。
	 * @return delDateSenderCd
	 */
	public String getDelDateSenderCd() {
		return delDateSenderCd;
	}

	/**
	 * delDateSenderCdを設定します。
	 * @param delDateSenderCd delDateSenderCd
	 */
	public void setDelDateSenderCd(String delDateSenderCd) {
		this.delDateSenderCd = delDateSenderCd;
	}

	/**
	 * deleteDateを取得します。
	 * @return deleteDate
	 */
	public Timestamp getDeleteDate() {
		return deleteDate;
	}

	/**
	 * deleteDateを設定します。
	 * @param deleteDate deleteDate
	 */
	public void setDeleteDate(Timestamp deleteDate) {
		this.deleteDate = deleteDate;
	}

	/**
	 * delFlgを取得します。
	 * @return delFlg
	 */
	public BigDecimal getDelFlg() {
		return delFlg;
	}

	/**
	 * delFlgを設定します。
	 * @param delFlg delFlg
	 */
	public void setDelFlg(BigDecimal delFlg) {
		this.delFlg = delFlg;
	}

	/**
	 * cancelQtyを取得します。
	 * @return cancelQty
	 */
	public BigDecimal getCancelQty() {
		return cancelQty;
	}

	/**
	 * cancelQtyを設定します。
	 * @param cancelQty cancelQty
	 */
	public void setCancelQty(BigDecimal cancelQty) {
		this.cancelQty = cancelQty;
	}

	/**
	 * cancelDateを取得します。
	 * @return cancelDate
	 */
	public String getCancelDate() {
		return cancelDate;
	}

	/**
	 * cancelDateを設定します。
	 * @param cancelDate cancelDate
	 */
	public void setCancelDate(String cancelDate) {
		this.cancelDate = cancelDate;
	}

	/**
	 * cancelFlgを取得します。
	 * @return cancelFlg
	 */
	public BigDecimal getCancelFlg() {
		return cancelFlg;
	}

	/**
	 * cancelFlgを設定します。
	 * @param cancelFlg cancelFlg
	 */
	public void setCancelFlg(BigDecimal cancelFlg) {
		this.cancelFlg = cancelFlg;
	}

	/**
	 * errorFlgを取得します。
	 * @return errorFlg
	 */
	public BigDecimal getErrorFlg() {
		return errorFlg;
	}

	/**
	 * errorFlgを設定します。
	 * @param errorFlg errorFlg
	 */
	public void setErrorFlg(BigDecimal errorFlg) {
		this.errorFlg = errorFlg;
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
