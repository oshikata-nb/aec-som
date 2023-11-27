/*
 * Created on 2009/07/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.accept;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * AcceptDetailSalesCarryクラス.
 * @author kanri-user
 */
public class AcceptDetailSalesCarryBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AcceptDetailSalesCarryBase() {
	}

	//
	// 定数
	//

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション orderRowNo */
	public static final String orderRowNo_COLUMN = "ORDER_ROW_NO";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション balanceCd */
	public static final String balanceCd_COLUMN = "BALANCE_CD";

	/** COLUMNアノテーション salesTantoCd */
	public static final String salesTantoCd_COLUMN = "SALES_TANTO_CD";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション taxDivision */
	public static final String taxDivision_COLUMN = "TAX_DIVISION";

	/** COLUMNアノテーション calcDivision */
	public static final String calcDivision_COLUMN = "CALC_DIVISION";

	/** COLUMNアノテーション compCalcDivision */
	public static final String compCalcDivision_COLUMN = "COMP_CALC_DIVISION";

	/** COLUMNアノテーション invoiceCd */
	public static final String invoiceCd_COLUMN = "INVOICE_CD";

	/** COLUMNアノテーション taxRatio */
	public static final String taxRatio_COLUMN = "TAX_RATIO";

	/** COLUMNアノテーション accountYears */
	public static final String accountYears_COLUMN = "ACCOUNT_YEARS";

	/** COLUMNアノテーション dataTotalDivision */
	public static final String dataTotalDivision_COLUMN = "DATA_TOTAL_DIVISION";

	/** COLUMNアノテーション accountDebitSectionCd */
	public static final String accountDebitSectionCd_COLUMN = "ACCOUNT_DEBIT_SECTION_CD";

	/** COLUMNアノテーション accountCreditSectionCd */
	public static final String accountCreditSectionCd_COLUMN = "ACCOUNT_CREDIT_SECTION_CD";

	/** COLUMNアノテーション debitTitleCd */
	public static final String debitTitleCd_COLUMN = "DEBIT_TITLE_CD";

	/** COLUMNアノテーション creditTitleCd */
	public static final String creditTitleCd_COLUMN = "CREDIT_TITLE_CD";

	/** COLUMNアノテーション chargeOrganizationCd */
	public static final String chargeOrganizationCd_COLUMN = "CHARGE_ORGANIZATION_CD";

	/** COLUMNアノテーション unitDiv */
	public static final String unitDiv_COLUMN = "UNIT_DIV";

	//
	// インスタンスフィールド
	//

	private String orderNo;

	private java.math.BigDecimal orderRowNo;

	private String itemCd;

	private String venderCd;

	private String balanceCd;

	private String salesTantoCd;

	private String remark;

	private String deliveryCd;

	private java.math.BigDecimal taxDivision;

	private java.math.BigDecimal calcDivision;

	private java.math.BigDecimal compCalcDivision;

	private String invoiceCd;

	private java.math.BigDecimal taxRatio;

	private String accountYears;

	private java.math.BigDecimal dataTotalDivision;

	private String accountDebitSectionCd;

	private String accountCreditSectionCd;

	private String debitTitleCd;

	private String creditTitleCd;

	private String chargeOrganizationCd;

	private String unitDiv;

	//
	// インスタンスメソッド
	//

	/**
	 * orderNo取得.
	 * @return orderNo
	 */
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * orderNo設定.
	 * @param orderNo orderNo
	 */
	public void setOrderNo(final String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * orderRowNo取得.
	 * @return orderRowNo
	 */
	public java.math.BigDecimal getOrderRowNo() {
		return this.orderRowNo;
	}

	/**
	 * orderRowNo設定.
	 * @param orderRowNo orderRowNo
	 */
	public void setOrderRowNo(final java.math.BigDecimal orderRowNo) {
		this.orderRowNo = orderRowNo;
	}

	/**
	 * itemCd取得.
	 * @return itemCd
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * itemCd設定.
	 * @param itemCd itemCd
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * venderCd取得.
	 * @return venderCd
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * venderCd設定.
	 * @param venderCd venderCd
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * balanceCd取得.
	 * @return balanceCd
	 */
	public String getBalanceCd() {
		return this.balanceCd;
	}

	/**
	 * balanceCd設定.
	 * @param balanceCd balanceCd
	 */
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * salesTantoCd取得.
	 * @return salesTantoCd
	 */
	public String getSalesTantoCd() {
		return this.salesTantoCd;
	}

	/**
	 * salesTantoCd設定.
	 * @param salesTantoCd salesTantoCd
	 */
	public void setSalesTantoCd(final String salesTantoCd) {
		this.salesTantoCd = salesTantoCd;
	}

	/**
	 * remark取得.
	 * @return remark
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * remark設定.
	 * @param remark remark
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * deliveryCd取得.
	 * @return deliveryCd
	 */
	public String getDeliveryCd() {
		return this.deliveryCd;
	}

	/**
	 * deliveryCd設定.
	 * @param deliveryCd deliveryCd
	 */
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * taxDivision取得.
	 * @return taxDivision
	 */
	public java.math.BigDecimal getTaxDivision() {
		return this.taxDivision;
	}

	/**
	 * taxDivision設定.
	 * @param taxDivision taxDivision
	 */
	public void setTaxDivision(final java.math.BigDecimal taxDivision) {
		this.taxDivision = taxDivision;
	}

	/**
	 * calcDivision取得.
	 * @return calcDivision
	 */
	public java.math.BigDecimal getCalcDivision() {
		return this.calcDivision;
	}

	/**
	 * calcDivision設定.
	 * @param calcDivision calcDivision
	 */
	public void setCalcDivision(final java.math.BigDecimal calcDivision) {
		this.calcDivision = calcDivision;
	}

	/**
	 * compCalcDivision取得.
	 * @return compCalcDivision
	 */
	public java.math.BigDecimal getCompCalcDivision() {
		return this.compCalcDivision;
	}

	/**
	 * compCalcDivision設定.
	 * @param compCalcDivision compCalcDivision
	 */
	public void setCompCalcDivision(final java.math.BigDecimal compCalcDivision) {
		this.compCalcDivision = compCalcDivision;
	}

	/**
	 * invoiceCd取得.
	 * @return invoiceCd
	 */
	public String getInvoiceCd() {
		return this.invoiceCd;
	}

	/**
	 * invoiceCd設定.
	 * @param invoiceCd invoiceCd
	 */
	public void setInvoiceCd(final String invoiceCd) {
		this.invoiceCd = invoiceCd;
	}

	/**
	 * taxRatio取得.
	 * @return taxRatio
	 */
	public java.math.BigDecimal getTaxRatio() {
		return this.taxRatio;
	}

	/**
	 * taxRatio設定.
	 * @param taxRatio taxRatio
	 */
	public void setTaxRatio(final java.math.BigDecimal taxRatio) {
		this.taxRatio = taxRatio;
	}

	/**
	 * accountYears取得.
	 * @return accountYears
	 */
	public String getAccountYears() {
		return this.accountYears;
	}

	/**
	 * accountYears設定.
	 * @param accountYears accountYears
	 */
	public void setAccountYears(final String accountYears) {
		this.accountYears = accountYears;
	}

	/**
	 * dataTotalDivision取得.
	 * @return dataTotalDivision
	 */
	public java.math.BigDecimal getDataTotalDivision() {
		return this.dataTotalDivision;
	}

	/**
	 * dataTotalDivision設定.
	 * @param dataTotalDivision dataTotalDivision
	 */
	public void setDataTotalDivision(final java.math.BigDecimal dataTotalDivision) {
		this.dataTotalDivision = dataTotalDivision;
	}

	/**
	 * accountDebitSectionCd取得.
	 * @return accountDebitSectionCd
	 */
	public String getAccountDebitSectionCd() {
		return this.accountDebitSectionCd;
	}

	/**
	 * accountDebitSectionCd設定.
	 * @param accountDebitSectionCd accountDebitSectionCd
	 */
	public void setAccountDebitSectionCd(final String accountDebitSectionCd) {
		this.accountDebitSectionCd = accountDebitSectionCd;
	}

	/**
	 * accountCreditSectionCd取得.
	 * @return accountCreditSectionCd
	 */
	public String getAccountCreditSectionCd() {
		return this.accountCreditSectionCd;
	}

	/**
	 * accountCreditSectionCd設定.
	 * @param accountCreditSectionCd accountCreditSectionCd
	 */
	public void setAccountCreditSectionCd(final String accountCreditSectionCd) {
		this.accountCreditSectionCd = accountCreditSectionCd;
	}

	/**
	 * debitTitleCd取得.
	 * @return debitTitleCd
	 */
	public String getDebitTitleCd() {
		return this.debitTitleCd;
	}

	/**
	 * debitTitleCd設定.
	 * @param debitTitleCd debitTitleCd
	 */
	public void setDebitTitleCd(final String debitTitleCd) {
		this.debitTitleCd = debitTitleCd;
	}

	/**
	 * creditTitleCd取得.
	 * @return creditTitleCd
	 */
	public String getCreditTitleCd() {
		return this.creditTitleCd;
	}

	/**
	 * creditTitleCd設定.
	 * @param creditTitleCd creditTitleCd
	 */
	public void setCreditTitleCd(final String creditTitleCd) {
		this.creditTitleCd = creditTitleCd;
	}

	/**
	 * chargeOrganizationCd取得.
	 * @return chargeOrganizationCd
	 */
	public String getChargeOrganizationCd() {
		return this.chargeOrganizationCd;
	}

	/**
	 * chargeOrganizationCd設定.
	 * @param chargeOrganizationCd chargeOrganizationCd
	 */
	public void setChargeOrganizationCd(final String chargeOrganizationCd) {
		this.chargeOrganizationCd = chargeOrganizationCd;
	}

	/**
	 * unitDiv取得.
	 * @return unitDiv
	 */
	public String getUnitDiv() {
		return this.unitDiv;
	}

	/**
	 * unitDiv設定.
	 * @param unitDiv unitDiv
	 */
	public void setUnitDiv(final String unitDiv) {
		this.unitDiv = unitDiv;
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

