/*
 * Created on 2009/03/05
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.accept;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 受入入力詳細画面_売上データ登録用データ格納クラス.
 * 
 * @author tosco
 */
public class AcceptDetailSalesBase implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public AcceptDetailSalesBase() {
	}

	//
	// 定数
	//

	/** TABLEアノテーション. */
	public static final String TABLE = "SALES";

	/** COLUMNアノテーション salesDate */
	public static final String salesDate_COLUMN = "SALES_DATE";

	/** COLUMNアノテーション salesNo */
	public static final String salesNo_COLUMN = "SALES_NO";

	/** COLUMNアノテーション itemCd */
	public static final String itemCd_COLUMN = "ITEM_CD";

	/** COLUMNアノテーション venderCd */
	public static final String venderCd_COLUMN = "VENDER_CD";

	/** COLUMNアノテーション balanceCd */
	public static final String balanceCd_COLUMN = "BALANCE_CD";

	/** COLUMNアノテーション inputTantoCd */
	public static final String inputTantoCd_COLUMN = "INPUT_TANTO_CD";

	/** COLUMNアノテーション salesTantoCd */
	public static final String salesTantoCd_COLUMN = "SALES_TANTO_CD";

	/** COLUMNアノテーション productLotno */
	public static final String productLotno_COLUMN = "PRODUCT_LOTNO";

	/** COLUMNアノテーション orderNo */
	public static final String orderNo_COLUMN = "ORDER_NO";

	/** COLUMNアノテーション orderRowNo */
	public static final String orderRowNo_COLUMN = "ORDER_ROW_NO";

	/** COLUMNアノテーション salesQuantity */
	public static final String salesQuantity_COLUMN = "SALES_QUANTITY";

	/** COLUMNアノテーション salesUnitprice */
	public static final String salesUnitprice_COLUMN = "SALES_UNITPRICE";

	/** COLUMNアノテーション standardUnitprice */
	public static final String standardUnitprice_COLUMN = "STANDARD_UNITPRICE";

	/** COLUMNアノテーション standardDiscount */
	public static final String standardDiscount_COLUMN = "STANDARD_DISCOUNT";

	/** COLUMNアノテーション specialDiscount */
	public static final String specialDiscount_COLUMN = "SPECIAL_DISCOUNT";

	/** COLUMNアノテーション tmpUnitpriceFlg */
	public static final String tmpUnitpriceFlg_COLUMN = "TMP_UNITPRICE_FLG";

	/** COLUMNアノテーション remark */
	public static final String remark_COLUMN = "REMARK";

	/** COLUMNアノテーション invoiceUpdateDate */
	public static final String invoiceUpdateDate_COLUMN = "INVOICE_UPDATE_DATE";

	/** COLUMNアノテーション inputDivision */
	public static final String inputDivision_COLUMN = "INPUT_DIVISION";

	/** COLUMNアノテーション deliveryCd */
	public static final String deliveryCd_COLUMN = "DELIVERY_CD";

	/** COLUMNアノテーション taxDivision */
	public static final String taxDivision_COLUMN = "TAX_DIVISION";

	/** COLUMNアノテーション taxAmount */
	public static final String taxAmount_COLUMN = "TAX_AMOUNT";

	/** COLUMNアノテーション invoiceCd */
	public static final String invoiceCd_COLUMN = "INVOICE_CD";

	/** COLUMNアノテーション salesAmount */
	public static final String salesAmount_COLUMN = "SALES_AMOUNT";

	/** COLUMNアノテーション taxRatio */
	public static final String taxRatio_COLUMN = "TAX_RATIO";

	/** COLUMNアノテーション dataTotalDivision */
	public static final String dataTotalDivision_COLUMN = "DATA_TOTAL_DIVISION";

	/** COLUMNアノテーション categoryDivision */
	public static final String categoryDivision_COLUMN = "CATEGORY_DIVISION";

	/** COLUMNアノテーション accountYears */
	public static final String accountYears_COLUMN = "ACCOUNT_YEARS";

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

	/** COLUMNアノテーション keepFlag */
	public static final String keepFlag_COLUMN = "KEEP_FLAG";

	/** COLUMNアノテーション inputDate */
	public static final String inputDate_COLUMN = "INPUT_DATE";

	/** COLUMNアノテーション inputorCd */
	public static final String inputorCd_COLUMN = "INPUTOR_CD";

	/** COLUMNアノテーション updateDate */
	public static final String updateDate_COLUMN = "UPDATE_DATE";

	/** COLUMNアノテーション updatorCd */
	public static final String updatorCd_COLUMN = "UPDATOR_CD";

	//
	// インスタンスフィールド
	//

	/** 売上日付 */
	private Timestamp salesDate;

	/** 売上番号 */
	private String salesNo;

	/** 品目コード */
	private String itemCd;

	/** 得意先コード */
	private String venderCd;

	/** 帳合コード */
	private String balanceCd;

	/** 入力担当者コード */
	private String inputTantoCd;

	/** 営業担当者コード */
	private String salesTantoCd;

	/** 製品ロット番号 */
	private String productLotno;

	/** 受注番号 */
	private String orderNo;

	/** 受注行番号 */
	private BigDecimal orderRowNo;

	/** 売上数量 */
	private BigDecimal salesQuantity;

	/** 売上単価 */
	private BigDecimal salesUnitprice;

	/** 標準単価 */
	private BigDecimal standardUnitprice;

	/** 標準値引 */
	private BigDecimal standardDiscount;

	/** 特別値引 */
	private BigDecimal specialDiscount;

	/** 仮単価FLG */
	private BigDecimal tmpUnitpriceFlg;

	/** 摘要 */
	private String remark;

	/** 請求締め日 */
	private Timestamp invoiceUpdateDate;

	/** 入力区分 */
	private BigDecimal inputDivision;

	/** 納入先コード */
	private String deliveryCd;

	/** 消費税課税区分 */
	private BigDecimal taxDivision;

	/** 消費税額 */
	private BigDecimal taxAmount;

	/** 請求先コード */
	private String invoiceCd;

	/** 売上金額 */
	private BigDecimal salesAmount;

	/** 消費税率 */
	private BigDecimal taxRatio;

	/** ﾃﾞｰﾀ集計区分 */
	private BigDecimal dataTotalDivision;

	/** 分類コード */
	private String categoryDivision;

	/** 勘定年月 */
	private String accountYears;

	/** 会計部門借方コード */
	private String accountDebitSectionCd;

	/** 会計部門貸方コード */
	private String accountCreditSectionCd;

	/** 借方科目コード */
	private String debitTitleCd;

	/** 貸方科目コード */
	private String creditTitleCd;

	/** 担当部署 */
	private String chargeOrganizationCd;

	/** 預り品フラグ */
	private BigDecimal keepFlag;

	/** 登録日時 */
	private Timestamp inputDate;

	/** 登録者ID */
	private String inputorCd;

	/** 更新日時 */
	private Timestamp updateDate;

	/** 更新者ID */
	private String updatorCd;

	//
	// インスタンスメソッド
	//

	/**
	 * 売上日付取得
	 * @return Timestamp 売上日付
	 */
	public Timestamp getSalesDate() {
		return this.salesDate;
	}

	/**
	 * 売上日付設定
	 * @param salesDate 売上日付
	 */
	public void setSalesDate(final Timestamp salesDate) {
		this.salesDate = salesDate;
	}

	/**
	 * 売上番号取得
	 * @return String 売上番号
	 */
	public String getSalesNo() {
		return this.salesNo;
	}

	/**
	 * 売上番号設定
	 * @param salesNo 売上番号
	 */
	public void setSalesNo(final String salesNo) {
		this.salesNo = salesNo;
	}

	/**
	 * 品目コード取得
	 * @return String 品目コード
	 */
	public String getItemCd() {
		return this.itemCd;
	}

	/**
	 * 品目コード設定
	 * @param itemCd 品目コード
	 */
	public void setItemCd(final String itemCd) {
		this.itemCd = itemCd;
	}

	/**
	 * 得意先コード取得
	 * @return String 得意先コード
	 */
	public String getVenderCd() {
		return this.venderCd;
	}

	/**
	 * 得意先コード設定
	 * @param venderCd 得意先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 帳合コード取得
	 * @return String 帳合コード
	 */
	public String getBalanceCd() {
		return balanceCd;
	}

	/**
	 * 帳合コード設定
	 * @param balanceCd 帳合コード
	 */
	public void setBalanceCd(final String balanceCd) {
		this.balanceCd = balanceCd;
	}

	/**
	 * 入力担当者コード取得
	 * @return String 入力担当者コード
	 */
	public String getInputTantoCd() {
		return this.inputTantoCd;
	}

	/**
	 * 入力担当者コード設定
	 * @param inputTantoCd 入力担当者コード
	 */
	public void setInputTantoCd(final String inputTantoCd) {
		this.inputTantoCd = inputTantoCd;
	}

	/**
	 * 営業担当者コード取得
	 * @return String 営業担当者コード
	 */
	public String getSalesTantoCd() {
		return this.salesTantoCd;
	}

	/**
	 * 営業担当者コード設定
	 * @param salesTantoCd 営業担当者コード
	 */
	public void setSalesTantoCd(final String salesTantoCd) {
		this.salesTantoCd = salesTantoCd;
	}

	/**
	 * 製品ロット番号取得
	 * @return String 製品ロット番号
	 */
	public String getProductLotno() {
		return this.productLotno;
	}

	/**
	 * 製品ロット番号設定
	 * @param productLotno 製品ロット番号
	 */
	public void setProductLotno(final String productLotno) {
		this.productLotno = productLotno;
	}

	/**
	 * 受注番号取得
	 * @return String 受注番号
	 */
	public String getOrderNo() {
		return this.orderNo;
	}

	/**
	 * 受注番号設定
	 * @param orderNo 受注番号
	 */
	public void setOrderNo(final String orderNo) {
		this.orderNo = orderNo;
	}

	/**
	 * 受注行番号取得
	 * @return BigDecimal 受注行番号
	 */
	public BigDecimal getOrderRowNo() {
		return this.orderRowNo;
	}

	/**
	 * 受注行番号設定
	 * @param orderRowNo 受注行番号
	 */
	public void setOrderRowNo(final BigDecimal orderRowNo) {
		this.orderRowNo = orderRowNo;
	}

	/**
	 * 売上数量取得
	 * @return BigDecimal 売上数量
	 */
	public BigDecimal getSalesQuantity() {
		return this.salesQuantity;
	}

	/**
	 * 売上数量設定
	 * @param salesQuantity 売上数量
	 */
	public void setSalesQuantity(final BigDecimal salesQuantity) {
		this.salesQuantity = salesQuantity;
	}

	/**
	 * 売上単価取得
	 * @return BigDecimal 売上単価
	 */
	public BigDecimal getSalesUnitprice() {
		return this.salesUnitprice;
	}

	/**
	 * 売上単価設定
	 * @param salesUnitprice 売上単価
	 */
	public void setSalesUnitprice(final BigDecimal salesUnitprice) {
		this.salesUnitprice = salesUnitprice;
	}

	/**
	 * 標準単価取得
	 * @return BigDecimal 標準単価
	 */
	public BigDecimal getStandardUnitprice() {
		return standardUnitprice;
	}

	/**
	 * 標準単価設定
	 * @param standardUnitprice 標準単価
	 */
	public void setStandardUnitprice(final BigDecimal standardUnitprice) {
		this.standardUnitprice = standardUnitprice;
	}

	/**
	 * 標準値引取得
	 * @return BigDecimal 標準値引
	 */
	public BigDecimal getStandardDiscount() {
		return standardDiscount;
	}

	/**
	 * 標準値引設定
	 * @param standardDiscount 標準値引
	 */
	public void setStandardDiscount(final BigDecimal standardDiscount) {
		this.standardDiscount = standardDiscount;
	}

	/**
	 * 特別値引取得
	 * @return BigDecimal 特別値引
	 */
	public BigDecimal getSpecialDiscount() {
		return specialDiscount;
	}

	/**
	 * 特別値引設定
	 * @param specialDiscount 特別値引
	 */
	public void setSpecialDiscount(final BigDecimal specialDiscount) {
		this.specialDiscount = specialDiscount;
	}

	/**
	 * 仮単価FLG取得
	 * @return BigDecimal 仮単価FLG
	 */
	public BigDecimal getTmpUnitpriceFlg() {
		return this.tmpUnitpriceFlg;
	}

	/**
	 * 仮単価FLG設定
	 * @param tmpUnitpriceFlg 仮単価FLG
	 */
	public void setTmpUnitpriceFlg(final BigDecimal tmpUnitpriceFlg) {
		this.tmpUnitpriceFlg = tmpUnitpriceFlg;
	}

	/**
	 * 摘要取得
	 * @return String 摘要
	 */
	public String getRemark() {
		return this.remark;
	}

	/**
	 * 摘要設定
	 * @param remark 摘要
	 */
	public void setRemark(final String remark) {
		this.remark = remark;
	}

	/**
	 * 請求締め日取得
	 * @return Timestamp 請求締め日
	 */
	public Timestamp getInvoiceUpdateDate() {
		return this.invoiceUpdateDate;
	}

	/**
	 * 請求締め日設定
	 * @param invoiceUpdateDate 請求締め日
	 */
	public void setInvoiceUpdateDate(final Timestamp invoiceUpdateDate) {
		this.invoiceUpdateDate = invoiceUpdateDate;
	}

	/**
	 * 入力区分取得
	 * @return BigDecimal 入力区分
	 */
	public BigDecimal getInputDivision() {
		return this.inputDivision;
	}

	/**
	 * 入力区分設定
	 * @param inputDivision 入力区分
	 */
	public void setInputDivision(final BigDecimal inputDivision) {
		this.inputDivision = inputDivision;
	}

	/**
	 * 納入先コード取得
	 * @return String 納入先コード
	 */
	public String getDeliveryCd() {
		return this.deliveryCd;
	}

	/**
	 * 納入先コード設定
	 * @param deliveryCd 納入先コード
	 */
	public void setDeliveryCd(final String deliveryCd) {
		this.deliveryCd = deliveryCd;
	}

	/**
	 * 消費税課税区分取得
	 * @return BigDecimal 消費税課税区分
	 */
	public BigDecimal getTaxDivision() {
		return this.taxDivision;
	}

	/**
	 * 消費税課税区分設定
	 * @param taxDivision 消費税課税区分
	 */
	public void setTaxDivision(final BigDecimal taxDivision) {
		this.taxDivision = taxDivision;
	}

	/**
	 * 消費税額取得
	 * @return BigDecimal 消費税額
	 */
	public BigDecimal getTaxAmount() {
		return this.taxAmount;
	}

	/**
	 * 消費税額設定
	 * @param taxAmount 消費税額
	 */
	public void setTaxAmount(final BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * 請求先コード取得
	 * @return String 請求先コード
	 */
	public String getInvoiceCd() {
		return this.invoiceCd;
	}

	/**
	 * 請求先コード設定
	 * @param invoiceCd 請求先コード
	 */
	public void setInvoiceCd(final String invoiceCd) {
		this.invoiceCd = invoiceCd;
	}

	/**
	 * 売上金額取得
	 * @return BigDecimal 売上金額
	 */
	public BigDecimal getSalesAmount() {
		return this.salesAmount;
	}

	/**
	 * 売上金額設定
	 * @param salesAmount 売上金額
	 */
	public void setSalesAmount(final BigDecimal salesAmount) {
		this.salesAmount = salesAmount;
	}

	/**
	 * 消費税率取得
	 * @return BigDecimal 消費税率
	 */
	public BigDecimal getTaxRatio() {
		return this.taxRatio;
	}

	/**
	 * 消費税率設定
	 * @param taxRatio 消費税率
	 */
	public void setTaxRatio(final BigDecimal taxRatio) {
		this.taxRatio = taxRatio;
	}

	/**
	 * ﾃﾞｰﾀ集計区分取得
	 * @return BigDecimal ﾃﾞｰﾀ集計区分
	 */
	public BigDecimal getDataTotalDivision() {
		return this.dataTotalDivision;
	}

	/**
	 * ﾃﾞｰﾀ集計区分設定
	 * @param dataTotalDivision ﾃﾞｰﾀ集計区分
	 */
	public void setDataTotalDivision(final BigDecimal dataTotalDivision) {
		this.dataTotalDivision = dataTotalDivision;
	}

	/**
	 * 分類コード取得
	 * @return String 分類コード
	 */
	public String getCategoryDivision() {
		return this.categoryDivision;
	}

	/**
	 * 分類コード設定
	 * @param categoryDivision 分類コード
	 */
	public void setCategoryDivision(final String categoryDivision) {
		this.categoryDivision = categoryDivision;
	}

	/**
	 * 勘定年月取得
	 * @return String 勘定年月
	 */
	public String getAccountYears() {
		return this.accountYears;
	}

	/**
	 * 勘定年月設定
	 * @param accountYears 勘定年月
	 */
	public void setAccountYears(final String accountYears) {
		this.accountYears = accountYears;
	}

	/**
	 * 会計部門借方コード取得
	 * @return String 会計部門借方コード
	 */
	public String getAccountDebitSectionCd() {
		return this.accountDebitSectionCd;
	}

	/**
	 * 会計部門借方コード設定
	 * @param accountDebitSectionCd 会計部門借方コード
	 */
	public void setAccountDebitSectionCd(final String accountDebitSectionCd) {
		this.accountDebitSectionCd = accountDebitSectionCd;
	}

	/**
	 * 会計部門貸方コード取得
	 * @return String 会計部門貸方コード
	 */
	public String getAccountCreditSectionCd() {
		return this.accountCreditSectionCd;
	}

	/**
	 * 会計部門貸方コード設定
	 * @param accountCreditSectionCd 会計部門貸方コード
	 */
	public void setAccountCreditSectionCd(final String accountCreditSectionCd) {
		this.accountCreditSectionCd = accountCreditSectionCd;
	}

	/**
	 * 借方科目コード取得
	 * @return String 借方科目コード
	 */
	public String getDebitTitleCd() {
		return this.debitTitleCd;
	}

	/**
	 * 借方科目コード設定
	 * @param debitTitleCd 借方科目コード
	 */
	public void setDebitTitleCd(final String debitTitleCd) {
		this.debitTitleCd = debitTitleCd;
	}

	/**
	 * 貸方科目コード取得
	 * @return String 貸方科目コード
	 */
	public String getCreditTitleCd() {
		return this.creditTitleCd;
	}

	/**
	 * 貸方科目コード設定
	 * @param creditTitleCd 貸方科目コード
	 */
	public void setCreditTitleCd(final String creditTitleCd) {
		this.creditTitleCd = creditTitleCd;
	}

	/**
	 * 担当部署取得
	 * @return String 担当部署
	 */
	public String getChargeOrganizationCd() {
		return this.chargeOrganizationCd;
	}

	/**
	 * 担当部署設定
	 * @param chargeOrganizationCd 担当部署
	 */
	public void setChargeOrganizationCd(final String chargeOrganizationCd) {
		this.chargeOrganizationCd = chargeOrganizationCd;
	}

	/**
	 * 預り品フラグ取得
	 * @return BigDecimal 預り品フラグ
	 */
	public BigDecimal getKeepFlag() {
		return this.keepFlag;
	}

	/**
	 * 預り品フラグ設定
	 * @param keepFlag 預り品フラグ
	 */
	public void setKeepFlag(final BigDecimal keepFlag) {
		this.keepFlag = keepFlag;
	}

	/**
	 * 登録日時取得
	 * @return Timestamp 登録日時
	 */
	public Timestamp getInputDate() {
		return this.inputDate;
	}

	/**
	 * 登録日時設定
	 * @param inputDate 登録日時
	 */
	public void setInputDate(final Timestamp inputDate) {
		this.inputDate = inputDate;
	}

	/**
	 * 登録者ID取得
	 * @return String 登録者ID
	 */
	public String getInputorCd() {
		return this.inputorCd;
	}

	/**
	 * 登録者ID設定
	 * @param inputorCd 登録者ID
	 */
	public void setInputorCd(final String inputorCd) {
		this.inputorCd = inputorCd;
	}

	/**
	 * 更新日時取得
	 * @return Timestamp 更新日時
	 */
	public Timestamp getUpdateDate() {
		return this.updateDate;
	}

	/**
	 * 更新日時設定
	 * @param updateDate 更新日時
	 */
	public void setUpdateDate(final Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * 更新者ID取得
	 * @return String 更新者ID
	 */
	public String getUpdatorCd() {
		return this.updatorCd;
	}

	/**
	 * 更新者ID設定
	 * @param updatorCd 更新者ID
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
