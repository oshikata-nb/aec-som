/* 注意：table2daoで作成されました。
 *　     編集しないでください。table2daoで上書されます。
 * Created on Mon Jun 15 11:34:44 JST 2009
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.temporarydepositsales;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * TemporaryDepositSalesBaseクラス.
 * @author a7710658
 */
public class TemporaryDepositSalesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public TemporaryDepositSalesBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "TEMPORARY_DEPOSIT_SALES";


//	/** IDアノテーション salesNo. */
//	public static final String salesNo_ID = "assigned";

	/** COLUMNアノテーション salesDate. */
	public static final String salesDate_COLUMN = "SALES_DATE";

	/** COLUMNアノテーション salesNo. */
	public static final String salesNo_COLUMN = "SALES_NO";

	/** COLUMNアノテーション cancelSalesNo. */
	public static final String cancelSalesNo_COLUMN = "CANCEL_SALES_NO";

	/** COLUMNアノテーション itemCd. */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション organizationCd. */
	public static final String organizationCd_COLUMN = "ORGANIZATION_CD";

	/** COLUMNアノテーション venderCd. */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション balanceCd. */
	public static final String balanceCd_COLUMN = "BALANCE_CD";

	/** COLUMNアノテーション inputTantoCd. */
	public static final String inputTantoCd_COLUMN = "INPUT_TANTO_CD";

	/** COLUMNアノテーション salesTantoCd. */
	public static final String salesTantoCd_COLUMN = "SALES_TANTO_CD";

	/** COLUMNアノテーション productLotno. */
	public static final String productLotno_COLUMN = "PRODUCT_LOTNO";

	/** COLUMNアノテーション orderNo. */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション orderRowNo. */
	public static final String orderRowNo_COLUMN = "ORDER_ROW_NO";

	/** COLUMNアノテーション salesQuantity. */
	public static final String salesQuantity_COLUMN = "SALES_QUANTITY";

	/** COLUMNアノテーション salesUnitprice. */
	public static final String salesUnitprice_COLUMN = "SALES_UNITPRICE";

	/** COLUMNアノテーション standardUnitprice. */
	public static final String standardUnitprice_COLUMN = "STANDARD_UNITPRICE";

	/** COLUMNアノテーション standardDiscount. */
	public static final String standardDiscount_COLUMN = "STANDARD_DISCOUNT";

	/** COLUMNアノテーション specialDiscount. */
	public static final String specialDiscount_COLUMN = "SPECIAL_DISCOUNT";

	/** COLUMNアノテーション tmpUnitpriceFlg. */
	public static final String tmpUnitpriceFlg_COLUMN = "TMP_UNITPRICE_FLG";

	/** COLUMNアノテーション remark. */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション disbursementLocationCd. */
	public static final String disbursementLocationCd_COLUMN = "DISBURSEMENT_LOCATION_CD";

	/** COLUMNアノテーション deliveryComp. */
	public static final String deliveryComp_COLUMN = "DELIVERY_COMP";

	/** COLUMNアノテーション deliveryUpdateDate. */
	public static final String deliveryUpdateDate_COLUMN = "DELIVERY_UPDATE_DATE";

	/** COLUMNアノテーション invoiceUpdateDate. */
	public static final String invoiceUpdateDate_COLUMN = "INVOICE_UPDATE_DATE";

	/** COLUMNアノテーション inputDivision. */
	public static final String inputDivision_COLUMN = "INPUT_DIVISION";

	/** COLUMNアノテーション deliveryCd. */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション taxDivision. */
	public static final String taxDivision_COLUMN = "TAX_DIVISION";

	/** COLUMNアノテーション taxAmount. */
	public static final String taxAmount_COLUMN = "TAX_AMOUNT";

	/** COLUMNアノテーション invoiceCd. */
	public static final String invoiceCd_COLUMN = "INVOICE_CD";

	/** COLUMNアノテーション salesAmount. */
	public static final String salesAmount_COLUMN = "SALES_AMOUNT";

	/** COLUMNアノテーション salesBasic. */
	public static final String salesBasic_COLUMN = "SALES_BASIC";

	/** COLUMNアノテーション taxRatio. */
	public static final String taxRatio_COLUMN = "TAX_RATIO";

	/** COLUMNアノテーション updateFlg. */
	public static final String updateFlg_COLUMN = "UPDATE_FLG";

	/** COLUMNアノテーション configExpRatio. */
	public static final String configExpRatio_COLUMN = "CONFIG_EXP_RATIO";

	/** COLUMNアノテーション ratioNotapplySalesUnitprice. */
	public static final String ratioNotapplySalesUnitprice_COLUMN = "RATIO_NOTAPPLY_SALES_UNITPRICE";

	/** COLUMNアノテーション shippingNo. */
	public static final String shippingNo_COLUMN = "SHIPPING_NO";

	/** COLUMNアノテーション salesSlipNo. */
	public static final String salesSlipNo_COLUMN = "SALES_SLIP_NO";

	/** COLUMNアノテーション salesSlipRowNo. */
	public static final String salesSlipRowNo_COLUMN = "SALES_SLIP_ROW_NO";

	/** COLUMNアノテーション slipPublishComp. */
	public static final String slipPublishComp_COLUMN = "SLIP_PUBLISH_COMP";

	/** COLUMNアノテーション slipPublishDate. */
	public static final String slipPublishDate_COLUMN = "SLIP_PUBLISH_DATE";

	/** COLUMNアノテーション transmissionDate. */
	public static final String transmissionDate_COLUMN = "TRANSMISSION_DATE";

	/** COLUMNアノテーション ledgerPostDate. */
	public static final String ledgerPostDate_COLUMN = "LEDGER_POST_DATE";

	/** COLUMNアノテーション ledgerPostNo. */
	public static final String ledgerPostNo_COLUMN = "LEDGER_POST_NO";

	/** COLUMNアノテーション salesConvertQuantity. */
	public static final String salesConvertQuantity_COLUMN = "SALES_CONVERT_QUANTITY";

	/** COLUMNアノテーション acceptConvertQuantity. */
	public static final String acceptConvertQuantity_COLUMN = "ACCEPT_CONVERT_QUANTITY";

	/** COLUMNアノテーション dataType. */
	public static final String dataType_COLUMN = "DATA_TYPE";

	/** COLUMNアノテーション dataTotalDivision. */
	public static final String dataTotalDivision_COLUMN = "DATA_TOTAL_DIVISION";

	/** COLUMNアノテーション categoryDivision. */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション accountYears. */
	public static final String accountYears_COLUMN = "ACCOUNT_YEARS";

	/** COLUMNアノテーション accountDebitSectionCd. */
	public static final String accountDebitSectionCd_COLUMN = "ACCOUNT_DEBIT_SECTION_CD";

	/** COLUMNアノテーション accountCreditSectionCd. */
	public static final String accountCreditSectionCd_COLUMN = "ACCOUNT_CREDIT_SECTION_CD";

	/** COLUMNアノテーション debitTitleCd. */
	public static final String debitTitleCd_COLUMN = "DEBIT_TITLE_CD";

	/** COLUMNアノテーション debitSubTitleCd. */
	public static final String debitSubTitleCd_COLUMN = "DEBIT_SUB_TITLE_CD";

	/** COLUMNアノテーション creditTitleCd. */
	public static final String creditTitleCd_COLUMN = "CREDIT_TITLE_CD";

	/** COLUMNアノテーション creditSubTitleCd. */
	public static final String creditSubTitleCd_COLUMN = "CREDIT_SUB_TITLE_CD";

	/** COLUMNアノテーション depositTargetDivision. */
	public static final String depositTargetDivision_COLUMN = "DEPOSIT_TARGET_DIVISION";

	/** COLUMNアノテーション claimTargetDivision. */
	public static final String claimTargetDivision_COLUMN = "CLAIM_TARGET_DIVISION";

	/** COLUMNアノテーション summaryCd. */
	public static final String summaryCd_COLUMN = "SUMMARY_CD";

	/** COLUMNアノテーション summary. */
	public static final String summary_COLUMN = "SUMMARY";

	/** COLUMNアノテーション depositUpdateDivision. */
	public static final String depositUpdateDivision_COLUMN = "DEPOSIT_UPDATE_DIVISION";

	/** COLUMNアノテーション depositNo. */
	public static final String depositNo_COLUMN = "DEPOSIT_NO";

	/** COLUMNアノテーション claimUpdateDivision. */
	public static final String claimUpdateDivision_COLUMN = "CLAIM_UPDATE_DIVISION";

	/** COLUMNアノテーション claimNo. */
	public static final String claimNo_COLUMN = "CLAIM_NO";

	/** COLUMNアノテーション eraserCompleteDivision. */
	public static final String eraserCompleteDivision_COLUMN = "ERASER_COMPLETE_DIVISION";

	/** COLUMNアノテーション eraserCompleteDate. */
	public static final String eraserCompleteDate_COLUMN = "ERASER_COMPLETE_DATE";

	/** COLUMNアノテーション approvalStatus. */
	public static final String approvalStatus_COLUMN = "APPROVAL_STATUS";

	/** COLUMNアノテーション approvedby. */
	public static final String approvedby_COLUMN = "APPROVEDBY";

	/** COLUMNアノテーション approvaldate. */
	public static final String approvaldate_COLUMN = "APPROVALDATE";

	/** COLUMNアノテーション chargeOrganizationCd. */
	public static final String chargeOrganizationCd_COLUMN = "CHARGE_ORGANIZATION_CD";

	/** COLUMNアノテーション keepFlag. */
	public static final String keepFlag_COLUMN = "KEEP_FLAG";

	/** COLUMNアノテーション ryCd. */
	public static final String ryCd_COLUMN = "RY_CD";

	/** COLUMNアノテーション housingLocationCd. */
	public static final String housingLocationCd_COLUMN = "HOUSING_LOCATION_CD";

	/** COLUMNアノテーション packageDirectionNo. */
	public static final String packageDirectionNo_COLUMN = "PACKAGE_DIRECTION_NO";

	/** COLUMNアノテーション inputDate. */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd. */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate. */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd. */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	private java.sql.Timestamp salesDate;

	private String salesNo;

	private String cancelSalesNo;

	private String itemCd;

	private String organizationCd;

	private String venderCd;

	private String balanceCd;

	private String inputTantoCd;

	private String salesTantoCd;

	private String productLotno;

	private String orderNo;

	private java.math.BigDecimal orderRowNo;

	private java.math.BigDecimal salesQuantity;

	private java.math.BigDecimal salesUnitprice;

	private java.math.BigDecimal standardUnitprice;

	private java.math.BigDecimal standardDiscount;

	private java.math.BigDecimal specialDiscount;

	private java.math.BigDecimal tmpUnitpriceFlg;

	private String remark;

	private String disbursementLocationCd;

	private java.math.BigDecimal deliveryComp;

	private java.sql.Timestamp deliveryUpdateDate;

	private java.sql.Timestamp invoiceUpdateDate;

	private java.math.BigDecimal inputDivision;

	private String deliveryCd;

	private java.math.BigDecimal taxDivision;

	private java.math.BigDecimal taxAmount;

	private String invoiceCd;

	private java.math.BigDecimal salesAmount;

	private java.math.BigDecimal salesBasic;

	private java.math.BigDecimal taxRatio;

	private java.math.BigDecimal updateFlg;

	private java.math.BigDecimal configExpRatio;

	private java.math.BigDecimal ratioNotapplySalesUnitprice;

	private String shippingNo;

	private String salesSlipNo;

	private java.math.BigDecimal salesSlipRowNo;

	private java.math.BigDecimal slipPublishComp;

	private java.sql.Timestamp slipPublishDate;

	private java.sql.Timestamp transmissionDate;

	private java.sql.Timestamp ledgerPostDate;

	private String ledgerPostNo;

	private java.math.BigDecimal salesConvertQuantity;

	private java.math.BigDecimal acceptConvertQuantity;

	private java.math.BigDecimal dataType;

	private java.math.BigDecimal dataTotalDivision;

	private String categoryDivision;

	private String accountYears;

	private String accountDebitSectionCd;

	private String accountCreditSectionCd;

	private String debitTitleCd;

	private String debitSubTitleCd;

	private String creditTitleCd;

	private String creditSubTitleCd;

	private java.math.BigDecimal depositTargetDivision;

	private java.math.BigDecimal claimTargetDivision;

	private String summaryCd;

	private String summary;

	private java.math.BigDecimal depositUpdateDivision;

	private String depositNo;

	private java.math.BigDecimal claimUpdateDivision;

	private String claimNo;

	private java.math.BigDecimal eraserCompleteDivision;

	private java.sql.Timestamp eraserCompleteDate;

	private java.math.BigDecimal approvalStatus;

	private String approvedby;

	private java.sql.Timestamp approvaldate;

	private String chargeOrganizationCd;

	private java.math.BigDecimal keepFlag;

	private String ryCd;

	private String housingLocationCd;

	private String packageDirectionNo;

	private java.sql.Timestamp inputDate;

	private String inputorCd;

	private java.sql.Timestamp updateDate;

	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * salesDate取得.
	 * @return salesDate
	 */
	public java.sql.Timestamp getSalesDate() {
		return this.salesDate;
	}

	/**
	 * salesDate設定.
	 * @param salesDate salesDate
	 */
	public void setSalesDate(final java.sql.Timestamp salesDate) {
		this.salesDate = salesDate;
	}

	/**
	 * salesNo取得.
	 * @return salesNo
	 */
	public String getSalesNo() {
		return this.salesNo;
	}

	/**
	 * salesNo設定.
	 * @param salesNo salesNo
	 */
	public void setSalesNo(final String salesNo) {
		this.salesNo = salesNo;
	}

	/**
	 * cancelSalesNo取得.
	 * @return cancelSalesNo
	 */
	public String getCancelSalesNo() {
		return this.cancelSalesNo;
	}

	/**
	 * cancelSalesNo設定.
	 * @param cancelSalesNo cancelSalesNo
	 */
	public void setCancelSalesNo(final String cancelSalesNo) {
		this.cancelSalesNo = cancelSalesNo;
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
	 * organizationCd取得.
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return this.organizationCd;
	}

	/**
	 * organizationCd設定.
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
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
	 * inputTantoCd取得.
	 * @return inputTantoCd
	 */
	public String getInputTantoCd() {
		return this.inputTantoCd;
	}

	/**
	 * inputTantoCd設定.
	 * @param inputTantoCd inputTantoCd
	 */
	public void setInputTantoCd(final String inputTantoCd) {
		this.inputTantoCd = inputTantoCd;
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
	 * productLotno取得.
	 * @return productLotno
	 */
	public String getProductLotno() {
		return this.productLotno;
	}

	/**
	 * productLotno設定.
	 * @param productLotno productLotno
	 */
	public void setProductLotno(final String productLotno) {
		this.productLotno = productLotno;
	}

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
	 * salesQuantity取得.
	 * @return salesQuantity
	 */
	public java.math.BigDecimal getSalesQuantity() {
		return this.salesQuantity;
	}

	/**
	 * salesQuantity設定.
	 * @param salesQuantity salesQuantity
	 */
	public void setSalesQuantity(final java.math.BigDecimal salesQuantity) {
		this.salesQuantity = salesQuantity;
	}

	/**
	 * salesUnitprice取得.
	 * @return salesUnitprice
	 */
	public java.math.BigDecimal getSalesUnitprice() {
		return this.salesUnitprice;
	}

	/**
	 * salesUnitprice設定.
	 * @param salesUnitprice salesUnitprice
	 */
	public void setSalesUnitprice(final java.math.BigDecimal salesUnitprice) {
		this.salesUnitprice = salesUnitprice;
	}

	/**
	 * standardUnitprice取得.
	 * @return standardUnitprice
	 */
	public java.math.BigDecimal getStandardUnitprice() {
		return this.standardUnitprice;
	}

	/**
	 * standardUnitprice設定.
	 * @param standardUnitprice standardUnitprice
	 */
	public void setStandardUnitprice(final java.math.BigDecimal standardUnitprice) {
		this.standardUnitprice = standardUnitprice;
	}

	/**
	 * standardDiscount取得.
	 * @return standardDiscount
	 */
	public java.math.BigDecimal getStandardDiscount() {
		return this.standardDiscount;
	}

	/**
	 * standardDiscount設定.
	 * @param standardDiscount standardDiscount
	 */
	public void setStandardDiscount(final java.math.BigDecimal standardDiscount) {
		this.standardDiscount = standardDiscount;
	}

	/**
	 * specialDiscount取得.
	 * @return specialDiscount
	 */
	public java.math.BigDecimal getSpecialDiscount() {
		return this.specialDiscount;
	}

	/**
	 * specialDiscount設定.
	 * @param specialDiscount specialDiscount
	 */
	public void setSpecialDiscount(final java.math.BigDecimal specialDiscount) {
		this.specialDiscount = specialDiscount;
	}

	/**
	 * tmpUnitpriceFlg取得.
	 * @return tmpUnitpriceFlg
	 */
	public java.math.BigDecimal getTmpUnitpriceFlg() {
		return this.tmpUnitpriceFlg;
	}

	/**
	 * tmpUnitpriceFlg設定.
	 * @param tmpUnitpriceFlg tmpUnitpriceFlg
	 */
	public void setTmpUnitpriceFlg(final java.math.BigDecimal tmpUnitpriceFlg) {
		this.tmpUnitpriceFlg = tmpUnitpriceFlg;
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
	 * disbursementLocationCd取得.
	 * @return disbursementLocationCd
	 */
	public String getDisbursementLocationCd() {
		return this.disbursementLocationCd;
	}

	/**
	 * disbursementLocationCd設定.
	 * @param disbursementLocationCd disbursementLocationCd
	 */
	public void setDisbursementLocationCd(final String disbursementLocationCd) {
		this.disbursementLocationCd = disbursementLocationCd;
	}

	/**
	 * deliveryComp取得.
	 * @return deliveryComp
	 */
	public java.math.BigDecimal getDeliveryComp() {
		return this.deliveryComp;
	}

	/**
	 * deliveryComp設定.
	 * @param deliveryComp deliveryComp
	 */
	public void setDeliveryComp(final java.math.BigDecimal deliveryComp) {
		this.deliveryComp = deliveryComp;
	}

	/**
	 * deliveryUpdateDate取得.
	 * @return deliveryUpdateDate
	 */
	public java.sql.Timestamp getDeliveryUpdateDate() {
		return this.deliveryUpdateDate;
	}

	/**
	 * deliveryUpdateDate設定.
	 * @param deliveryUpdateDate deliveryUpdateDate
	 */
	public void setDeliveryUpdateDate(final java.sql.Timestamp deliveryUpdateDate) {
		this.deliveryUpdateDate = deliveryUpdateDate;
	}

	/**
	 * invoiceUpdateDate取得.
	 * @return invoiceUpdateDate
	 */
	public java.sql.Timestamp getInvoiceUpdateDate() {
		return this.invoiceUpdateDate;
	}

	/**
	 * invoiceUpdateDate設定.
	 * @param invoiceUpdateDate invoiceUpdateDate
	 */
	public void setInvoiceUpdateDate(final java.sql.Timestamp invoiceUpdateDate) {
		this.invoiceUpdateDate = invoiceUpdateDate;
	}

	/**
	 * inputDivision取得.
	 * @return inputDivision
	 */
	public java.math.BigDecimal getInputDivision() {
		return this.inputDivision;
	}

	/**
	 * inputDivision設定.
	 * @param inputDivision inputDivision
	 */
	public void setInputDivision(final java.math.BigDecimal inputDivision) {
		this.inputDivision = inputDivision;
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
	 * taxAmount取得.
	 * @return taxAmount
	 */
	public java.math.BigDecimal getTaxAmount() {
		return this.taxAmount;
	}

	/**
	 * taxAmount設定.
	 * @param taxAmount taxAmount
	 */
	public void setTaxAmount(final java.math.BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
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
	 * salesAmount取得.
	 * @return salesAmount
	 */
	public java.math.BigDecimal getSalesAmount() {
		return this.salesAmount;
	}

	/**
	 * salesAmount設定.
	 * @param salesAmount salesAmount
	 */
	public void setSalesAmount(final java.math.BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}

	/**
	 * salesBasic取得.
	 * @return salesBasic
	 */
	public java.math.BigDecimal getSalesBasic() {
		return this.salesBasic;
	}

	/**
	 * salesBasic設定.
	 * @param salesBasic salesBasic
	 */
	public void setSalesBasic(final java.math.BigDecimal salesBasic) {
		this.salesBasic = salesBasic;
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
	 * updateFlg取得.
	 * @return updateFlg
	 */
	public java.math.BigDecimal getUpdateFlg() {
		return this.updateFlg;
	}

	/**
	 * updateFlg設定.
	 * @param updateFlg updateFlg
	 */
	public void setUpdateFlg(final java.math.BigDecimal updateFlg) {
		this.updateFlg = updateFlg;
	}

	/**
	 * configExpRatio取得.
	 * @return configExpRatio
	 */
	public java.math.BigDecimal getConfigExpRatio() {
		return this.configExpRatio;
	}

	/**
	 * configExpRatio設定.
	 * @param configExpRatio configExpRatio
	 */
	public void setConfigExpRatio(final java.math.BigDecimal configExpRatio) {
		this.configExpRatio = configExpRatio;
	}

	/**
	 * ratioNotapplySalesUnitprice取得.
	 * @return ratioNotapplySalesUnitprice
	 */
	public java.math.BigDecimal getRatioNotapplySalesUnitprice() {
		return this.ratioNotapplySalesUnitprice;
	}

	/**
	 * ratioNotapplySalesUnitprice設定.
	 * @param ratioNotapplySalesUnitprice ratioNotapplySalesUnitprice
	 */
	public void setRatioNotapplySalesUnitprice(final java.math.BigDecimal ratioNotapplySalesUnitprice) {
		this.ratioNotapplySalesUnitprice = ratioNotapplySalesUnitprice;
	}

	/**
	 * shippingNo取得.
	 * @return shippingNo
	 */
	public String getShippingNo() {
		return this.shippingNo;
	}

	/**
	 * shippingNo設定.
	 * @param shippingNo shippingNo
	 */
	public void setShippingNo(final String shippingNo) {
		this.shippingNo = shippingNo;
	}

	/**
	 * salesSlipNo取得.
	 * @return salesSlipNo
	 */
	public String getSalesSlipNo() {
		return this.salesSlipNo;
	}

	/**
	 * salesSlipNo設定.
	 * @param salesSlipNo salesSlipNo
	 */
	public void setSalesSlipNo(final String salesSlipNo) {
		this.salesSlipNo = salesSlipNo;
	}

	/**
	 * salesSlipRowNo取得.
	 * @return salesSlipRowNo
	 */
	public java.math.BigDecimal getSalesSlipRowNo() {
		return this.salesSlipRowNo;
	}

	/**
	 * salesSlipRowNo設定.
	 * @param salesSlipRowNo salesSlipRowNo
	 */
	public void setSalesSlipRowNo(final java.math.BigDecimal salesSlipRowNo) {
		this.salesSlipRowNo = salesSlipRowNo;
	}

	/**
	 * slipPublishComp取得.
	 * @return slipPublishComp
	 */
	public java.math.BigDecimal getSlipPublishComp() {
		return this.slipPublishComp;
	}

	/**
	 * slipPublishComp設定.
	 * @param slipPublishComp slipPublishComp
	 */
	public void setSlipPublishComp(final java.math.BigDecimal slipPublishComp) {
		this.slipPublishComp = slipPublishComp;
	}

	/**
	 * slipPublishDate取得.
	 * @return slipPublishDate
	 */
	public java.sql.Timestamp getSlipPublishDate() {
		return this.slipPublishDate;
	}

	/**
	 * slipPublishDate設定.
	 * @param slipPublishDate slipPublishDate
	 */
	public void setSlipPublishDate(final java.sql.Timestamp slipPublishDate) {
		this.slipPublishDate = slipPublishDate;
	}

	/**
	 * transmissionDate取得.
	 * @return transmissionDate
	 */
	public java.sql.Timestamp getTransmissionDate() {
		return this.transmissionDate;
	}

	/**
	 * transmissionDate設定.
	 * @param transmissionDate transmissionDate
	 */
	public void setTransmissionDate(final java.sql.Timestamp transmissionDate) {
		this.transmissionDate = transmissionDate;
	}

	/**
	 * ledgerPostDate取得.
	 * @return ledgerPostDate
	 */
	public java.sql.Timestamp getLedgerPostDate() {
		return this.ledgerPostDate;
	}

	/**
	 * ledgerPostDate設定.
	 * @param ledgerPostDate ledgerPostDate
	 */
	public void setLedgerPostDate(final java.sql.Timestamp ledgerPostDate) {
		this.ledgerPostDate = ledgerPostDate;
	}

	/**
	 * ledgerPostNo取得.
	 * @return ledgerPostNo
	 */
	public String getLedgerPostNo() {
		return this.ledgerPostNo;
	}

	/**
	 * ledgerPostNo設定.
	 * @param ledgerPostNo ledgerPostNo
	 */
	public void setLedgerPostNo(final String ledgerPostNo) {
		this.ledgerPostNo = ledgerPostNo;
	}

	/**
	 * salesConvertQuantity取得.
	 * @return salesConvertQuantity
	 */
	public java.math.BigDecimal getSalesConvertQuantity() {
		return this.salesConvertQuantity;
	}

	/**
	 * salesConvertQuantity設定.
	 * @param salesConvertQuantity salesConvertQuantity
	 */
	public void setSalesConvertQuantity(final java.math.BigDecimal salesConvertQuantity) {
		this.salesConvertQuantity = salesConvertQuantity;
	}

	/**
	 * acceptConvertQuantity取得.
	 * @return acceptConvertQuantity
	 */
	public java.math.BigDecimal getAcceptConvertQuantity() {
		return this.acceptConvertQuantity;
	}

	/**
	 * acceptConvertQuantity設定.
	 * @param acceptConvertQuantity acceptConvertQuantity
	 */
	public void setAcceptConvertQuantity(final java.math.BigDecimal acceptConvertQuantity) {
		this.acceptConvertQuantity = acceptConvertQuantity;
	}

	/**
	 * dataType取得.
	 * @return dataType
	 */
	public java.math.BigDecimal getDataType() {
		return this.dataType;
	}

	/**
	 * dataType設定.
	 * @param dataType dataType
	 */
	public void setDataType(final java.math.BigDecimal dataType) {
		this.dataType = dataType;
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
	 * categoryDivision取得.
	 * @return categoryDivision
	 */
	public String getCategoryDivision() {
		return this.categoryDivision;
	}

	/**
	 * categoryDivision設定.
	 * @param categoryDivision categoryDivision
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
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
	 * debitSubTitleCd取得.
	 * @return debitSubTitleCd
	 */
	public String getDebitSubTitleCd() {
		return this.debitSubTitleCd;
	}

	/**
	 * debitSubTitleCd設定.
	 * @param debitSubTitleCd debitSubTitleCd
	 */
	public void setDebitSubTitleCd(final String debitSubTitleCd) {
		this.debitSubTitleCd = debitSubTitleCd;
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
	 * creditSubTitleCd取得.
	 * @return creditSubTitleCd
	 */
	public String getCreditSubTitleCd() {
		return this.creditSubTitleCd;
	}

	/**
	 * creditSubTitleCd設定.
	 * @param creditSubTitleCd creditSubTitleCd
	 */
	public void setCreditSubTitleCd(final String creditSubTitleCd) {
		this.creditSubTitleCd = creditSubTitleCd;
	}

	/**
	 * depositTargetDivision取得.
	 * @return depositTargetDivision
	 */
	public java.math.BigDecimal getDepositTargetDivision() {
		return this.depositTargetDivision;
	}

	/**
	 * depositTargetDivision設定.
	 * @param depositTargetDivision depositTargetDivision
	 */
	public void setDepositTargetDivision(final java.math.BigDecimal depositTargetDivision) {
		this.depositTargetDivision = depositTargetDivision;
	}

	/**
	 * claimTargetDivision取得.
	 * @return claimTargetDivision
	 */
	public java.math.BigDecimal getClaimTargetDivision() {
		return this.claimTargetDivision;
	}

	/**
	 * claimTargetDivision設定.
	 * @param claimTargetDivision claimTargetDivision
	 */
	public void setClaimTargetDivision(final java.math.BigDecimal claimTargetDivision) {
		this.claimTargetDivision = claimTargetDivision;
	}

	/**
	 * summaryCd取得.
	 * @return summaryCd
	 */
	public String getSummaryCd() {
		return this.summaryCd;
	}

	/**
	 * summaryCd設定.
	 * @param summaryCd summaryCd
	 */
	public void setSummaryCd(final String summaryCd) {
		this.summaryCd = summaryCd;
	}

	/**
	 * summary取得.
	 * @return summary
	 */
	public String getSummary() {
		return this.summary;
	}

	/**
	 * summary設定.
	 * @param summary summary
	 */
	public void setSummary(final String summary) {
		this.summary = summary;
	}

	/**
	 * depositUpdateDivision取得.
	 * @return depositUpdateDivision
	 */
	public java.math.BigDecimal getDepositUpdateDivision() {
		return this.depositUpdateDivision;
	}

	/**
	 * depositUpdateDivision設定.
	 * @param depositUpdateDivision depositUpdateDivision
	 */
	public void setDepositUpdateDivision(final java.math.BigDecimal depositUpdateDivision) {
		this.depositUpdateDivision = depositUpdateDivision;
	}

	/**
	 * depositNo取得.
	 * @return depositNo
	 */
	public String getDepositNo() {
		return this.depositNo;
	}

	/**
	 * depositNo設定.
	 * @param depositNo depositNo
	 */
	public void setDepositNo(final String depositNo) {
		this.depositNo = depositNo;
	}

	/**
	 * claimUpdateDivision取得.
	 * @return claimUpdateDivision
	 */
	public java.math.BigDecimal getClaimUpdateDivision() {
		return this.claimUpdateDivision;
	}

	/**
	 * claimUpdateDivision設定.
	 * @param claimUpdateDivision claimUpdateDivision
	 */
	public void setClaimUpdateDivision(final java.math.BigDecimal claimUpdateDivision) {
		this.claimUpdateDivision = claimUpdateDivision;
	}

	/**
	 * claimNo取得.
	 * @return claimNo
	 */
	public String getClaimNo() {
		return this.claimNo;
	}

	/**
	 * claimNo設定.
	 * @param claimNo claimNo
	 */
	public void setClaimNo(final String claimNo) {
		this.claimNo = claimNo;
	}

	/**
	 * eraserCompleteDivision取得.
	 * @return eraserCompleteDivision
	 */
	public java.math.BigDecimal getEraserCompleteDivision() {
		return this.eraserCompleteDivision;
	}

	/**
	 * eraserCompleteDivision設定.
	 * @param eraserCompleteDivision eraserCompleteDivision
	 */
	public void setEraserCompleteDivision(final java.math.BigDecimal eraserCompleteDivision) {
		this.eraserCompleteDivision = eraserCompleteDivision;
	}

	/**
	 * eraserCompleteDate取得.
	 * @return eraserCompleteDate
	 */
	public java.sql.Timestamp getEraserCompleteDate() {
		return this.eraserCompleteDate;
	}

	/**
	 * eraserCompleteDate設定.
	 * @param eraserCompleteDate eraserCompleteDate
	 */
	public void setEraserCompleteDate(final java.sql.Timestamp eraserCompleteDate) {
		this.eraserCompleteDate = eraserCompleteDate;
	}

	/**
	 * approvalStatus取得.
	 * @return approvalStatus
	 */
	public java.math.BigDecimal getApprovalStatus() {
		return this.approvalStatus;
	}

	/**
	 * approvalStatus設定.
	 * @param approvalStatus approvalStatus
	 */
	public void setApprovalStatus(final java.math.BigDecimal approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	/**
	 * approvedby取得.
	 * @return approvedby
	 */
	public String getApprovedby() {
		return this.approvedby;
	}

	/**
	 * approvedby設定.
	 * @param approvedby approvedby
	 */
	public void setApprovedby(final String approvedby) {
		this.approvedby = approvedby;
	}

	/**
	 * approvaldate取得.
	 * @return approvaldate
	 */
	public java.sql.Timestamp getApprovaldate() {
		return this.approvaldate;
	}

	/**
	 * approvaldate設定.
	 * @param approvaldate approvaldate
	 */
	public void setApprovaldate(final java.sql.Timestamp approvaldate) {
		this.approvaldate = approvaldate;
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
	 * keepFlag取得.
	 * @return keepFlag
	 */
	public java.math.BigDecimal getKeepFlag() {
		return this.keepFlag;
	}

	/**
	 * keepFlag設定.
	 * @param keepFlag keepFlag
	 */
	public void setKeepFlag(final java.math.BigDecimal keepFlag) {
		this.keepFlag = keepFlag;
	}

	/**
	 * ryCd取得.
	 * @return ryCd
	 */
	public String getRyCd() {
		return this.ryCd;
	}

	/**
	 * ryCd設定.
	 * @param ryCd ryCd
	 */
	public void setRyCd(final String ryCd) {
		this.ryCd = ryCd;
	}

	/**
	 * housingLocationCd取得.
	 * @return housingLocationCd
	 */
	public String getHousingLocationCd() {
		return this.housingLocationCd;
	}

	/**
	 * housingLocationCd設定.
	 * @param housingLocationCd housingLocationCd
	 */
	public void setHousingLocationCd(final String housingLocationCd) {
		this.housingLocationCd = housingLocationCd;
	}

	/**
	 * packageDirectionNo取得.
	 * @return packageDirectionNo
	 */
	public String getPackageDirectionNo() {
		return this.packageDirectionNo;
	}

	/**
	 * packageDirectionNo設定.
	 * @param packageDirectionNo packageDirectionNo
	 */
	public void setPackageDirectionNo(final String packageDirectionNo) {
		this.packageDirectionNo = packageDirectionNo;
	}

	/**
	 * inputDate取得.
	 * @return inputDate
	 */
	public java.sql.Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * inputDate設定.
	 * @param inputDate inputDate
	 */
	public void setInputDate(final java.sql.Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * inputorCd取得.
	 * @return inputorCd
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * inputorCd設定.
	 * @param inputorCd inputorCd
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
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
	 * updatorCd取得.
	 * @return updatorCd
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * updatorCd設定.
	 * @param updatorCd updatorCd
	 */
	public void setUpdatorCd(final String updatorCd) {
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
