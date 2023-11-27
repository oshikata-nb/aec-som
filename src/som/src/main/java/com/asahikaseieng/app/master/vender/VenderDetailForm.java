/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.vender;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 取引先詳細 Formクラス.
 * @author t0011036
 */
public final class VenderDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 取引先区分 */
	private String venderDivision;

	/* 取引先名称 */
	private String venderDivisionName;

	/* 請求(支払)先コード */
	private String paymentInvoiceCd;

	/* 請求(支払)先名称 */
	private String paymentInvoiceShortName;

	/* 請求(支払)先名称 */
	private String paymentInvoiceName;

	/* 取引先コード */
	private String venderCd;

	/* 担当部署コード */
	private String organizationCd;

	/* 担当部署名称 */
	private String organizationName;

	/* 取引先名称1 */
	private String venderName1;

	/* 取引先名称2 */
	private String venderName2;

	/* 取引先略称 */
	private String venderShortedName;

	/* 郵便番号 */
	private String zipcodeNo;

	/* 市町村コード */
	private String cityCd;

	/* 住所1 */
	private String address1;

	/* 住所2 */
	private String address2;

	/* 住所3 */
	private String address3;

	/* 電話番号 */
	private String telNo;

	/* FAX番号 */
	private String faxNo;

	/* 代表者役職 */
	private String representRole;

	/* 代表者氏名 */
	private String representPerson;

	/* 営業担当者コード */
	private String tantoCd;

	/* 営業担当者名 */
	private String tantoNm;

	/* 地区コード */
	private String areaCd;

	/* 地区名称 */
	private String areaName;

	/* 締日 */
	private BigDecimal closingDate;

	private String strClosingDate;

	/* 下請法該当 */
	private Boolean subcontractLaw;

	/* 与信限度 */
	private BigDecimal creditLimitPrice;

	private String strCreditLimitPrice;

	/* 前受金区分 */
	private BigDecimal advanceDivision;

	/* 会計部門コード */
	private String sectionCd;

	/* 会計部門名称 */
	private String sectionName;

	/* 勘定科目コード */
	private String accountsCd;

	/* 勘定科目名称 */
	private String accountsName;

	/* 区分1 */
	private BigDecimal creditDivision3;

	/* 区分2 */
	private BigDecimal creditDivision4;

	/* 区分3 */
	private BigDecimal creditDivision5;

	/* 手形サイト1 */
	private BigDecimal noteSight3;

	private String strNoteSight3;

	/* 手形サイト2 */
	private BigDecimal noteSight4;

	private String strNoteSight4;

	/* 手形サイト3 */
	private BigDecimal noteSight5;

	private String strNoteSight5;

	/* 境界額1 */
	private BigDecimal boundAmount3;

	private String strBoundAmount3;

	/* 境界額2 */
	private BigDecimal boundAmount4;

	private String strBoundAmount4;

	/* 支払月区分1 */
	private BigDecimal creditMonthDivision1;

	/* 支払月区分2 */
	private BigDecimal creditMonthDivision2;

	/* 支払月区分3 */
	private BigDecimal creditMonthDivision3;

	/* 入金(支払)予定1 */
	private BigDecimal creditScheduledDate1;

	private String strCreditScheduledDate1;

	/* 入金(支払)予定2 */
	private BigDecimal creditScheduledDate2;

	private String strCreditScheduledDate2;

	/* 入金(支払)予定3 */
	private BigDecimal creditScheduledDate3;

	private String strCreditScheduledDate3;

	/* 仕入割引日数1 */
	private BigDecimal purchaseDiscountDays1;

	private String strPurchaseDiscountDays1;

	/* 仕入割引日数2 */
	private BigDecimal purchaseDiscountDays2;

	private String strPurchaseDiscountDays2;

	/* 仕入割引日数3 */
	private BigDecimal purchaseDiscountDays3;

	private String strPurchaseDiscountDays3;

	/* 請求書発行区分 */
	private BigDecimal billPublish;

	/* 伝票発行区分 */
	private BigDecimal slipPublish;

	/* 休日入金(支払)指定 */
	private BigDecimal holidayFlg;

	/* カレンダーコード */
	private String calendarCd;

	/* カレンダー名称 */
	private String calName;

	/* 会計用取引先コード */
	private String accountCd;

	/* 自社銀行コード */
	private String bankMasterCd;

	private String[] bankMasterCdValues;

	private String[] bankMasterCdLabels;

	private String[] notCreditDivisionValues;

	private String[] notCreditDivisionLabels;

	private String[] creditDivisionValues;

	private String[] creditDivisionLabels;

	private String dispBankMasterCd;

	private String dispBankName;

	private String dispBranchName;

	/* 自社銀行口座区分 */
	private BigDecimal accountDivision;

	/* 送信区分 */
	private BigDecimal faxOutput;

	/* 自社銀行口座区分名称 */
	private String accountDivisionName;

	/* 自社銀行口座番号 */
	private String accountNo;

	/* 自社銀行口座名義人 */
	private String accountStockhold;

	/* 相手銀行コード */
	private String otherBankMasterCd;

	/* 相手銀行名称 */
	private String otherBankName;

	/* 相手銀行支店名称 */
	private String otherBranchName;

	/* 相手銀行口座区分 */
	private BigDecimal otherAccountDivision;

	/* 相手銀行口座番号 */
	private String otherAccountNo;

	/* 相手銀行口座名義人 */
	private String otherAccountStockhold;

	/* 消費税区分 */
	private BigDecimal taxDivision;

	/* 消費税算出区分 */
	private BigDecimal calcDivision;

	/* 消費税端数区分 */
	private BigDecimal taxRoundup;

	/* 消費税端数単位 */
	private BigDecimal taxRoundupUnit;

	/* 端数区分 */
	private BigDecimal roundup;

	/* 端数単位 */
	private BigDecimal roundupUnit;

	/* 売上(仕入)金額端数区分 */
	private BigDecimal salesPurchaseRoundup;

	/* 売上(仕入)金額端数単位 */
	private BigDecimal salesPurchaseRoundupUnit;

	/* 単価端数区分 */
	private BigDecimal unitpriceRoundup;

	/* 単価端数単位 */
	private BigDecimal unitpriceRoundupUnit;

	/* 消費税率 */
	private BigDecimal taxRatio;

	private String strTaxRatio;

	/* 振込手数料負担 */
	private BigDecimal transferCommissionLoad;

	/* 客先担当者1 */
	private String customerTantoName1;

	/* 客先担当者2 */
	private String customerTantoName2;

	/* 客先所感1 */
	private String customerImpression1;

	/* 客先所感2 */
	private String customerImpression2;

	/* 備考 */
	private String remarks;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* カーソル位置 */
	private String cursor;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/* 消費税算出区分初期値 4=自社マスタ */
	private static final BigDecimal DEFAULT_CALC_DIVISION = new BigDecimal("4");

	/* 消費税区分初期値 4=自社マスタ */
	private static final BigDecimal DEFAULT_TAX_DIVISION = new BigDecimal("4");

	/* 消費税端数区分初期値 4=自社マスタ */
	private static final BigDecimal DEFAULT_TAX_ROUNDUP = new BigDecimal("4");

	/* 消費税端数単位初期値 8=自社マスタ */
	private static final BigDecimal DEFAULT_TAX_ROUNDUP_UNIT = new BigDecimal(
			"8");

	/* 端数区分初期値 4=自社マスタ */
	private static final BigDecimal DEFAULT_ROUNDUP = new BigDecimal("4");

	/* 端数単位初期値 8=自社マスタ */
	private static final BigDecimal DEFAULT_ROUNDUP_UNIT = new BigDecimal("8");

	/* 売上金額端数区分初期値 4=自社マスタ */
	private static final BigDecimal DEFAULT_SALES_PURCHASE_ROUNDUP = new BigDecimal(
			"4");

	/* 売上金額端数単位初期値 8=自社マスタ */
	private static final BigDecimal DEFAULT_SALES_PURCHASE_ROUNDUP_UNIT = new BigDecimal(
			"8");

	/* 単価端数区分初期値 4=自社マスタ */
	private static final BigDecimal DEFAULT_UNITPRICE_ROUNDUP = new BigDecimal(
			"4");

	/* 単価端数単位初期値 8=自社マスタ */
	private static final BigDecimal DEFAULT_UNITPRICE_ROUNDUP_UNIT = new BigDecimal(
			"8");

	/* 仕入伝票用FAX番号 */
	private String slipFaxNo;

	/* 受注FAX番号 */
	private String orderFaxNo;

	/* 受注FAX送信区分 */
	private BigDecimal orderFaxOutput;

	/* 受注メールアドレス1 */
	private String orderMailAddress1;

	/* 受注メールアドレス2 */
	private String orderMailAddress2;

	/* 受注メールアドレス3 */
	private String orderMailAddress3;

	/* 受注メール送信区分 */
	private BigDecimal orderMailOutput;

	/* 売上FAX番号 */
	private String salesFaxNo;

	/* 売上FAX送信区分 */
	private BigDecimal salesFaxOutput;

	/* 売上メールアドレス1 */
	private String salesMailAddress1;

	/* 売上メールアドレス2 */
	private String salesMailAddress2;

	/* 売上メールアドレス3 */
	private String salesMailAddress3;

	/* 売上メール送信区分 */
	private BigDecimal salesMailOutput;

	/* メール送信元部署CD */
	private String mailOrganizationCd;

	/* メール送信元部署名称 */
	private String mailOrganizationName;


	/* 売上インボイスパターン */
	private String tsInvoicePtn;

	private String[] tsInvoicePtnValues;

	private String[] tsInvoicePtnLabels;

	/* 売上インボイスパターンリスト 活性フラグ*/
	private String tsInvoiceActiveFlg;

	/* 仕入インボイスパターン */
	private String siInvoicePtn;

	private String[] siInvoicePtnValues;

	private String[] siInvoicePtnLabels;

	/* インボイス登録番号 */
	private String invoiceNo;

	/**
	 * コンストラクタ.
	 */
	public VenderDetailForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {
		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			/* クリア処理 */
			clear();
		}

		if ("update".equals(getOp())) {
			setSubcontractLaw(Boolean.FALSE);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}

		return errors;
	}

	/**
	 * クリア処理.
	 */
	public void clear() {
		setVenderDivision(null);
		setVenderDivisionName(null);
		setPaymentInvoiceCd(null);
		setPaymentInvoiceName(null);
		setPaymentInvoiceShortName(null);
		setVenderCd(null);
		setOrganizationCd(null);
		setOrganizationName(null);
		setVenderName1(null);
		setVenderName2(null);
		setVenderShortedName(null);
		setZipcodeNo(null);
		setCityCd(null);
		setAddress1(null);
		setAddress2(null);
		setAddress3(null);
		setTelNo(null);
		setFaxNo(null);
		setOrderFaxNo(null);
		setOrderFaxOutput(null);
		setOrderMailOutput(null);
		setOrderMailAddress1(null);
		setOrderMailAddress2(null);
		setOrderMailAddress3(null);
		setSalesFaxNo(null);
		setSalesFaxOutput(null);
		setSalesMailOutput(null);
		setSalesMailAddress1(null);
		setSalesMailAddress2(null);
		setSalesMailAddress3(null);
		setRepresentRole(null);
		setRepresentPerson(null);
		setTantoCd(null);
		setTantoNm(null);
		setAreaCd(null);
		setAreaName(null);
		setClosingDate(null);
		setStrClosingDate(null);
		setCreditLimitPrice(null);
		setStrCreditLimitPrice(null);
		setAdvanceDivision(BigDecimal.ONE);
		setSubcontractLaw(Boolean.FALSE);
		setSectionCd(null);
		setSectionName(null);
		setAccountsCd(null);
		setAccountsName(null);
		setCreditDivision3(null);
		setCreditDivision4(null);
		setCreditDivision5(null);
		setNoteSight3(null);
		setStrNoteSight3(null);
		setNoteSight4(null);
		setStrNoteSight4(null);
		setNoteSight5(null);
		setStrNoteSight5(null);
		setBoundAmount3(null);
		setStrBoundAmount3(null);
		setBoundAmount4(null);
		setStrBoundAmount4(null);
		setCreditMonthDivision1(null);
		setCreditMonthDivision2(null);
		setCreditMonthDivision3(null);
		setCreditScheduledDate1(new BigDecimal("99"));
		setStrCreditScheduledDate1("99");
		setCreditScheduledDate2(new BigDecimal("99"));
		setStrCreditScheduledDate2("99");
		setCreditScheduledDate3(new BigDecimal("99"));
		setStrCreditScheduledDate3("99");
		setPurchaseDiscountDays1(null);
		setStrPurchaseDiscountDays1(null);
		setPurchaseDiscountDays2(null);
		setStrPurchaseDiscountDays2(null);
		setPurchaseDiscountDays3(null);
		setStrPurchaseDiscountDays3(null);
		setBillPublish(null);
		setSlipPublish(null);
		setHolidayFlg(null);
		setCalendarCd(null);
		setCalName(null);
		setAccountCd(null);
		setNotCreditDivisionValues(null);
		setNotCreditDivisionLabels(null);
		setCreditDivisionValues(null);
		setCreditDivisionLabels(null);
		setBankMasterCd(null);
		setBankMasterCdValues(null);
		setBankMasterCdLabels(null);
		setDispBankMasterCd(null);
		setDispBankName(null);
		setDispBranchName(null);
		setAccountDivision(null);
		setAccountDivisionName(null);
		setAccountNo(null);
		setAccountStockhold(null);
		setOtherBankMasterCd(null);
		setOtherBankName(null);
		setOtherBranchName(null);
		setOtherAccountDivision(null);
		setOtherAccountNo(null);
		setOtherAccountStockhold(null);
		setTaxDivision(DEFAULT_TAX_DIVISION);
		setCalcDivision(DEFAULT_CALC_DIVISION);
		setTaxRoundup(DEFAULT_TAX_ROUNDUP);
		setTaxRoundupUnit(DEFAULT_TAX_ROUNDUP_UNIT);
		setRoundup(DEFAULT_ROUNDUP);
		setRoundupUnit(DEFAULT_ROUNDUP_UNIT);
		setSalesPurchaseRoundup(DEFAULT_SALES_PURCHASE_ROUNDUP);
		setSalesPurchaseRoundupUnit(DEFAULT_SALES_PURCHASE_ROUNDUP_UNIT);
		setUnitpriceRoundup(DEFAULT_UNITPRICE_ROUNDUP);
		setUnitpriceRoundupUnit(DEFAULT_UNITPRICE_ROUNDUP_UNIT);
		setTaxRatio(null);
		setStrTaxRatio(null);
		setTransferCommissionLoad(null);
		setCustomerTantoName1(null);
		setCustomerTantoName2(null);
		setCustomerImpression1(null);
		setCustomerImpression2(null);
		setRemarks(null);
		setUpdateDate(null);
		setCursor(null);
		setDirtyFlg(null);
		setNewFlg(null);
		setFaxOutput(BigDecimal.ONE);
		setSlipFaxNo(null);
		setMailOrganizationCd(null);
		setMailOrganizationName(null);
		setTsInvoicePtn(null);
		setTsInvoicePtnLabels(null);
		setTsInvoicePtnValues(null);
		setSiInvoicePtn(null);
		setSiInvoicePtnLabels(null);
		setSiInvoicePtnValues(null);
		setInvoiceNo(null);
	}

	/**
	 * newFlgを取得します。
	 * @return newFlg
	 */
	public String getNewFlg() {
		return newFlg;
	}

	/**
	 * newFlgを設定します。
	 * @param newFlg newFlg
	 */
	public void setNewFlg(final String newFlg) {
		this.newFlg = newFlg;
	}

	/**
	 * updateDateを取得します。
	 * @return updateDate
	 */
	public java.sql.Timestamp getUpdateDate() {
		return updateDate;
	}

	/**
	 * updateDateを設定します。
	 * @param updateDate updateDate
	 */
	public void setUpdateDate(final java.sql.Timestamp updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * dirtyFlgを取得します。
	 * @return dirtyFlg
	 */
	public Boolean getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final Boolean dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
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
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * accountCdを取得します。
	 * @return accountCd
	 */
	public String getAccountCd() {
		return accountCd;
	}

	/**
	 * accountCdを設定します。
	 * @param accountCd accountCd
	 */
	public void setAccountCd(final String accountCd) {
		this.accountCd = accountCd;
	}

	/**
	 * accountDivisionを取得します。
	 * @return accountDivision
	 */
	public BigDecimal getAccountDivision() {
		return accountDivision;
	}

	/**
	 * accountDivisionを設定します。
	 * @param accountDivision accountDivision
	 */
	public void setAccountDivision(final BigDecimal accountDivision) {
		this.accountDivision = accountDivision;
	}

	/**
	 * accountNoを取得します。
	 * @return accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}

	/**
	 * accountNoを設定します。
	 * @param accountNo accountNo
	 */
	public void setAccountNo(final String accountNo) {
		this.accountNo = accountNo;
	}

	/**
	 * accountsCdを取得します。
	 * @return accountsCd
	 */
	public String getAccountsCd() {
		return accountsCd;
	}

	/**
	 * accountsCdを設定します。
	 * @param accountsCd accountsCd
	 */
	public void setAccountsCd(final String accountsCd) {
		this.accountsCd = accountsCd;
	}

	/**
	 * accountsNameを取得します。
	 * @return accountsName
	 */
	public String getAccountsName() {
		return accountsName;
	}

	/**
	 * accountsNameを設定します。
	 * @param accountsName accountsName
	 */
	public void setAccountsName(final String accountsName) {
		this.accountsName = accountsName;
	}

	/**
	 * accountStockholdを取得します。
	 * @return accountStockhold
	 */
	public String getAccountStockhold() {
		return accountStockhold;
	}

	/**
	 * accountStockholdを設定します。
	 * @param accountStockhold accountStockhold
	 */
	public void setAccountStockhold(final String accountStockhold) {
		this.accountStockhold = accountStockhold;
	}

	/**
	 * address1を取得します。
	 * @return address1
	 */
	public String getAddress1() {
		return address1;
	}

	/**
	 * address1を設定します。
	 * @param address1 address1
	 */
	public void setAddress1(final String address1) {
		this.address1 = address1;
	}

	/**
	 * address2を取得します。
	 * @return address2
	 */
	public String getAddress2() {
		return address2;
	}

	/**
	 * address2を設定します。
	 * @param address2 address2
	 */
	public void setAddress2(final String address2) {
		this.address2 = address2;
	}

	/**
	 * address3を取得します。
	 * @return address3
	 */
	public String getAddress3() {
		return address3;
	}

	/**
	 * address3を設定します。
	 * @param address3 address3
	 */
	public void setAddress3(final String address3) {
		this.address3 = address3;
	}

	/**
	 * advanceDivisionを取得します。
	 * @return advanceDivision
	 */
	public BigDecimal getAdvanceDivision() {
		return advanceDivision;
	}

	/**
	 * advanceDivisionを設定します。
	 * @param advanceDivision advanceDivision
	 */
	public void setAdvanceDivision(final BigDecimal advanceDivision) {
		this.advanceDivision = advanceDivision;
	}

	/**
	 * areaCdを取得します。
	 * @return areaCd
	 */
	public String getAreaCd() {
		return areaCd;
	}

	/**
	 * areaCdを設定します。
	 * @param areaCd areaCd
	 */
	public void setAreaCd(final String areaCd) {
		this.areaCd = areaCd;
	}

	/**
	 * areaNameを取得します。
	 * @return areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * areaNameを設定します。
	 * @param areaName areaName
	 */
	public void setAreaName(final String areaName) {
		this.areaName = areaName;
	}

	/**
	 * bankMasterCdを取得します。
	 * @return bankMasterCd
	 */
	public String getBankMasterCd() {
		return bankMasterCd;
	}

	/**
	 * bankMasterCdを設定します。
	 * @param bankMasterCd bankMasterCd
	 */
	public void setBankMasterCd(final String bankMasterCd) {
		this.bankMasterCd = bankMasterCd;
	}

	/**
	 * billPublishを取得します。
	 * @return billPublish
	 */
	public BigDecimal getBillPublish() {
		return billPublish;
	}

	/**
	 * billPublishを設定します。
	 * @param billPublish billPublish
	 */
	public void setBillPublish(final BigDecimal billPublish) {
		this.billPublish = billPublish;
	}

	/**
	 * boundAmount3を取得します。
	 * @return boundAmount3
	 */
	public BigDecimal getBoundAmount3() {
		return boundAmount3;
	}

	/**
	 * boundAmount3を設定します。
	 * @param boundAmount3 boundAmount3
	 */
	public void setBoundAmount3(final BigDecimal boundAmount3) {
		this.boundAmount3 = boundAmount3;
	}

	/**
	 * boundAmount4を取得します。
	 * @return boundAmount4
	 */
	public BigDecimal getBoundAmount4() {
		return boundAmount4;
	}

	/**
	 * boundAmount4を設定します。
	 * @param boundAmount4 boundAmount4
	 */
	public void setBoundAmount4(final BigDecimal boundAmount4) {
		this.boundAmount4 = boundAmount4;
	}

	/**
	 * calcDivisionを取得します。
	 * @return calcDivision
	 */
	public BigDecimal getCalcDivision() {
		return calcDivision;
	}

	/**
	 * calcDivisionを設定します。
	 * @param calcDivision calcDivision
	 */
	public void setCalcDivision(final BigDecimal calcDivision) {
		this.calcDivision = calcDivision;
	}

	/**
	 * calendarCdを取得します。
	 * @return calendarCd
	 */
	public String getCalendarCd() {
		return calendarCd;
	}

	/**
	 * calendarCdを設定します。
	 * @param calendarCd calendarCd
	 */
	public void setCalendarCd(final String calendarCd) {
		this.calendarCd = calendarCd;
	}

	/**
	 * calNameを取得します。
	 * @return calName
	 */
	public String getCalName() {
		return calName;
	}

	/**
	 * calNameを設定します。
	 * @param calName calName
	 */
	public void setCalName(final String calName) {
		this.calName = calName;
	}

	/**
	 * cityCdを取得します。
	 * @return cityCd
	 */
	public String getCityCd() {
		return cityCd;
	}

	/**
	 * cityCdを設定します。
	 * @param cityCd cityCd
	 */
	public void setCityCd(final String cityCd) {
		this.cityCd = cityCd;
	}

	/**
	 * closingDateを取得します。
	 * @return closingDate
	 */
	public BigDecimal getClosingDate() {
		return closingDate;
	}

	/**
	 * closingDateを設定します。
	 * @param closingDate closingDate
	 */
	public void setClosingDate(final BigDecimal closingDate) {
		this.closingDate = closingDate;
	}

	/**
	 * creditLimitPriceを取得します。
	 * @return creditLimitPrice
	 */
	public BigDecimal getCreditLimitPrice() {
		return creditLimitPrice;
	}

	/**
	 * creditLimitPriceを設定します。
	 * @param creditLimitPrice creditLimitPrice
	 */
	public void setCreditLimitPrice(final BigDecimal creditLimitPrice) {
		this.creditLimitPrice = creditLimitPrice;
	}

	/**
	 * creditMonthDivision1を取得します。
	 * @return creditMonthDivision1
	 */
	public BigDecimal getCreditMonthDivision1() {
		return creditMonthDivision1;
	}

	/**
	 * creditMonthDivision1を設定します。
	 * @param creditMonthDivision1 creditMonthDivision1
	 */
	public void setCreditMonthDivision1(final BigDecimal creditMonthDivision1) {
		this.creditMonthDivision1 = creditMonthDivision1;
	}

	/**
	 * creditMonthDivision2を取得します。
	 * @return creditMonthDivision2
	 */
	public BigDecimal getCreditMonthDivision2() {
		return creditMonthDivision2;
	}

	/**
	 * creditMonthDivision2を設定します。
	 * @param creditMonthDivision2 creditMonthDivision2
	 */
	public void setCreditMonthDivision2(final BigDecimal creditMonthDivision2) {
		this.creditMonthDivision2 = creditMonthDivision2;
	}

	/**
	 * creditMonthDivision3を取得します。
	 * @return creditMonthDivision3
	 */
	public BigDecimal getCreditMonthDivision3() {
		return creditMonthDivision3;
	}

	/**
	 * creditMonthDivision3を設定します。
	 * @param creditMonthDivision3 creditMonthDivision3
	 */
	public void setCreditMonthDivision3(final BigDecimal creditMonthDivision3) {
		this.creditMonthDivision3 = creditMonthDivision3;
	}

	/**
	 * creditScheduledDate1を取得します。
	 * @return creditScheduledDate1
	 */
	public BigDecimal getCreditScheduledDate1() {
		return creditScheduledDate1;
	}

	/**
	 * creditScheduledDate1を設定します。
	 * @param creditScheduledDate1 creditScheduledDate1
	 */
	public void setCreditScheduledDate1(final BigDecimal creditScheduledDate1) {
		this.creditScheduledDate1 = creditScheduledDate1;
	}

	/**
	 * creditScheduledDate2を取得します。
	 * @return creditScheduledDate2
	 */
	public BigDecimal getCreditScheduledDate2() {
		return creditScheduledDate2;
	}

	/**
	 * creditScheduledDate2を設定します。
	 * @param creditScheduledDate2 creditScheduledDate2
	 */
	public void setCreditScheduledDate2(final BigDecimal creditScheduledDate2) {
		this.creditScheduledDate2 = creditScheduledDate2;
	}

	/**
	 * creditScheduledDate3を取得します。
	 * @return creditScheduledDate3
	 */
	public BigDecimal getCreditScheduledDate3() {
		return creditScheduledDate3;
	}

	/**
	 * creditScheduledDate3を設定します。
	 * @param creditScheduledDate3 creditScheduledDate3
	 */
	public void setCreditScheduledDate3(final BigDecimal creditScheduledDate3) {
		this.creditScheduledDate3 = creditScheduledDate3;
	}

	/**
	 * customerImpression1を取得します。
	 * @return customerImpression1
	 */
	public String getCustomerImpression1() {
		return customerImpression1;
	}

	/**
	 * customerImpression1を設定します。
	 * @param customerImpression1 customerImpression1
	 */
	public void setCustomerImpression1(final String customerImpression1) {
		this.customerImpression1 = customerImpression1;
	}

	/**
	 * customerImpression2を取得します。
	 * @return customerImpression2
	 */
	public String getCustomerImpression2() {
		return customerImpression2;
	}

	/**
	 * customerImpression2を設定します。
	 * @param customerImpression2 customerImpression2
	 */
	public void setCustomerImpression2(final String customerImpression2) {
		this.customerImpression2 = customerImpression2;
	}

	/**
	 * customerTantoName1を取得します。
	 * @return customerTantoName1
	 */
	public String getCustomerTantoName1() {
		return customerTantoName1;
	}

	/**
	 * customerTantoName1を設定します。
	 * @param customerTantoName1 customerTantoName1
	 */
	public void setCustomerTantoName1(final String customerTantoName1) {
		this.customerTantoName1 = customerTantoName1;
	}

	/**
	 * customerTantoName2を取得します。
	 * @return customerTantoName2
	 */
	public String getCustomerTantoName2() {
		return customerTantoName2;
	}

	/**
	 * customerTantoName2を設定します。
	 * @param customerTantoName2 customerTantoName2
	 */
	public void setCustomerTantoName2(final String customerTantoName2) {
		this.customerTantoName2 = customerTantoName2;
	}

	/**
	 * faxNoを取得します。
	 * @return faxNo
	 */
	public String getFaxNo() {
		return faxNo;
	}

	/**
	 * faxNoを設定します。
	 * @param faxNo faxNo
	 */
	public void setFaxNo(final String faxNo) {
		this.faxNo = faxNo;
	}

	/**
	 * holidayFlgを取得します。
	 * @return holidayFlg
	 */
	public BigDecimal getHolidayFlg() {
		return holidayFlg;
	}

	/**
	 * holidayFlgを設定します。
	 * @param holidayFlg holidayFlg
	 */
	public void setHolidayFlg(final BigDecimal holidayFlg) {
		this.holidayFlg = holidayFlg;
	}

	/**
	 * noteSight3を取得します。
	 * @return noteSight3
	 */
	public BigDecimal getNoteSight3() {
		return noteSight3;
	}

	/**
	 * noteSight3を設定します。
	 * @param noteSight3 noteSight3
	 */
	public void setNoteSight3(final BigDecimal noteSight3) {
		this.noteSight3 = noteSight3;
	}

	/**
	 * noteSight4を取得します。
	 * @return noteSight4
	 */
	public BigDecimal getNoteSight4() {
		return noteSight4;
	}

	/**
	 * noteSight4を設定します。
	 * @param noteSight4 noteSight4
	 */
	public void setNoteSight4(final BigDecimal noteSight4) {
		this.noteSight4 = noteSight4;
	}

	/**
	 * organizationCdを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * organizationCdを設定します。
	 * @param organizationCd organizationCd
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * organizationNameを取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * organizationNameを設定します。
	 * @param organizationName organizationName
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * otherAccountDivisionを取得します。
	 * @return otherAccountDivision
	 */
	public BigDecimal getOtherAccountDivision() {
		return otherAccountDivision;
	}

	/**
	 * otherAccountDivisionを設定します。
	 * @param otherAccountDivision otherAccountDivision
	 */
	public void setOtherAccountDivision(final BigDecimal otherAccountDivision) {
		this.otherAccountDivision = otherAccountDivision;
	}

	/**
	 * otherAccountNoを取得します。
	 * @return otherAccountNo
	 */
	public String getOtherAccountNo() {
		return otherAccountNo;
	}

	/**
	 * otherAccountNoを設定します。
	 * @param otherAccountNo otherAccountNo
	 */
	public void setOtherAccountNo(final String otherAccountNo) {
		this.otherAccountNo = otherAccountNo;
	}

	/**
	 * otherAccountStockholdを取得します。
	 * @return otherAccountStockhold
	 */
	public String getOtherAccountStockhold() {
		return otherAccountStockhold;
	}

	/**
	 * otherAccountStockholdを設定します。
	 * @param otherAccountStockhold otherAccountStockhold
	 */
	public void setOtherAccountStockhold(final String otherAccountStockhold) {
		this.otherAccountStockhold = otherAccountStockhold;
	}

	/**
	 * otherBankMasterCdを取得します。
	 * @return otherBankMasterCd
	 */
	public String getOtherBankMasterCd() {
		return otherBankMasterCd;
	}

	/**
	 * otherBankMasterCdを設定します。
	 * @param otherBankMasterCd otherBankMasterCd
	 */
	public void setOtherBankMasterCd(final String otherBankMasterCd) {
		this.otherBankMasterCd = otherBankMasterCd;
	}

	/**
	 * otherBankNameを取得します。
	 * @return otherBankName
	 */
	public String getOtherBankName() {
		return otherBankName;
	}

	/**
	 * otherBankNameを設定します。
	 * @param otherBankName otherBankName
	 */
	public void setOtherBankName(final String otherBankName) {
		this.otherBankName = otherBankName;
	}

	/**
	 * otherBranchNameを取得します。
	 * @return otherBranchName
	 */
	public String getOtherBranchName() {
		return otherBranchName;
	}

	/**
	 * otherBranchNameを設定します。
	 * @param otherBranchName otherBranchName
	 */
	public void setOtherBranchName(final String otherBranchName) {
		this.otherBranchName = otherBranchName;
	}

	/**
	 * paymentInvoiceCdを取得します。
	 * @return paymentInvoiceCd
	 */
	public String getPaymentInvoiceCd() {
		return paymentInvoiceCd;
	}

	/**
	 * paymentInvoiceCdを設定します。
	 * @param paymentInvoiceCd paymentInvoiceCd
	 */
	public void setPaymentInvoiceCd(final String paymentInvoiceCd) {
		this.paymentInvoiceCd = paymentInvoiceCd;
	}

	/**
	 * paymentInvoiceShortNameを取得します。
	 * @return paymentInvoiceShortName
	 */
	public String getPaymentInvoiceShortName() {
		return paymentInvoiceShortName;
	}

	/**
	 * paymentInvoiceShortNameを設定します。
	 * @param paymentInvoiceShortName paymentInvoiceShortName
	 */
	public void setPaymentInvoiceShortName(final String paymentInvoiceShortName) {
		this.paymentInvoiceShortName = paymentInvoiceShortName;
	}

	/**
	 * purchaseDiscountDays1を取得します。
	 * @return purchaseDiscountDays1
	 */
	public BigDecimal getPurchaseDiscountDays1() {
		return purchaseDiscountDays1;
	}

	/**
	 * purchaseDiscountDays1を設定します。
	 * @param purchaseDiscountDays1 purchaseDiscountDays1
	 */
	public void setPurchaseDiscountDays1(final BigDecimal purchaseDiscountDays1) {
		this.purchaseDiscountDays1 = purchaseDiscountDays1;
	}

	/**
	 * purchaseDiscountDays2を取得します。
	 * @return purchaseDiscountDays2
	 */
	public BigDecimal getPurchaseDiscountDays2() {
		return purchaseDiscountDays2;
	}

	/**
	 * purchaseDiscountDays2を設定します。
	 * @param purchaseDiscountDays2 purchaseDiscountDays2
	 */
	public void setPurchaseDiscountDays2(final BigDecimal purchaseDiscountDays2) {
		this.purchaseDiscountDays2 = purchaseDiscountDays2;
	}

	/**
	 * purchaseDiscountDays3を取得します。
	 * @return purchaseDiscountDays3
	 */
	public BigDecimal getPurchaseDiscountDays3() {
		return purchaseDiscountDays3;
	}

	/**
	 * purchaseDiscountDays3を設定します。
	 * @param purchaseDiscountDays3 purchaseDiscountDays3
	 */
	public void setPurchaseDiscountDays3(final BigDecimal purchaseDiscountDays3) {
		this.purchaseDiscountDays3 = purchaseDiscountDays3;
	}

	/**
	 * remarksを取得します。
	 * @return remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * remarksを設定します。
	 * @param remarks remarks
	 */
	public void setRemarks(final String remarks) {
		this.remarks = remarks;
	}

	/**
	 * representPersonを取得します。
	 * @return representPerson
	 */
	public String getRepresentPerson() {
		return representPerson;
	}

	/**
	 * representPersonを設定します。
	 * @param representPerson representPerson
	 */
	public void setRepresentPerson(final String representPerson) {
		this.representPerson = representPerson;
	}

	/**
	 * representRoleを取得します。
	 * @return representRole
	 */
	public String getRepresentRole() {
		return representRole;
	}

	/**
	 * representRoleを設定します。
	 * @param representRole representRole
	 */
	public void setRepresentRole(final String representRole) {
		this.representRole = representRole;
	}

	/**
	 * roundupを取得します。
	 * @return roundup
	 */
	public BigDecimal getRoundup() {
		return roundup;
	}

	/**
	 * roundupを設定します。
	 * @param roundup roundup
	 */
	public void setRoundup(final BigDecimal roundup) {
		this.roundup = roundup;
	}

	/**
	 * roundupUnitを取得します。
	 * @return roundupUnit
	 */
	public BigDecimal getRoundupUnit() {
		return roundupUnit;
	}

	/**
	 * roundupUnitを設定します。
	 * @param roundupUnit roundupUnit
	 */
	public void setRoundupUnit(final BigDecimal roundupUnit) {
		this.roundupUnit = roundupUnit;
	}

	/**
	 * salesPurchaseRoundupを取得します。
	 * @return salesPurchaseRoundup
	 */
	public BigDecimal getSalesPurchaseRoundup() {
		return salesPurchaseRoundup;
	}

	/**
	 * salesPurchaseRoundupを設定します。
	 * @param salesPurchaseRoundup salesPurchaseRoundup
	 */
	public void setSalesPurchaseRoundup(final BigDecimal salesPurchaseRoundup) {
		this.salesPurchaseRoundup = salesPurchaseRoundup;
	}

	/**
	 * salesPurchaseRoundupUnitを取得します。
	 * @return salesPurchaseRoundupUnit
	 */
	public BigDecimal getSalesPurchaseRoundupUnit() {
		return salesPurchaseRoundupUnit;
	}

	/**
	 * salesPurchaseRoundupUnitを設定します。
	 * @param salesPurchaseRoundupUnit salesPurchaseRoundupUnit
	 */
	public void setSalesPurchaseRoundupUnit(
			final BigDecimal salesPurchaseRoundupUnit) {
		this.salesPurchaseRoundupUnit = salesPurchaseRoundupUnit;
	}

	/**
	 * sectionCdを取得します。
	 * @return sectionCd
	 */
	public String getSectionCd() {
		return sectionCd;
	}

	/**
	 * sectionCdを設定します。
	 * @param sectionCd sectionCd
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * sectionNameを取得します。
	 * @return sectionName
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * sectionNameを設定します。
	 * @param sectionName sectionName
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
	}

	/**
	 * slipPublishを取得します。
	 * @return slipPublish
	 */
	public BigDecimal getSlipPublish() {
		return slipPublish;
	}

	/**
	 * slipPublishを設定します。
	 * @param slipPublish slipPublish
	 */
	public void setSlipPublish(final BigDecimal slipPublish) {
		this.slipPublish = slipPublish;
	}

	/**
	 * strBoundAmount3を取得します。
	 * @return strBoundAmount3
	 */
	public String getStrBoundAmount3() {
		return strBoundAmount3;
	}

	/**
	 * strBoundAmount3を設定します。
	 * @param strBoundAmount3 strBoundAmount3
	 */
	public void setStrBoundAmount3(final String strBoundAmount3) {
		this.strBoundAmount3 = strBoundAmount3;
	}

	/**
	 * strBoundAmount4を取得します。
	 * @return strBoundAmount4
	 */
	public String getStrBoundAmount4() {
		return strBoundAmount4;
	}

	/**
	 * strBoundAmount4を設定します。
	 * @param strBoundAmount4 strBoundAmount4
	 */
	public void setStrBoundAmount4(final String strBoundAmount4) {
		this.strBoundAmount4 = strBoundAmount4;
	}

	/**
	 * strClosingDateを取得します。
	 * @return strClosingDate
	 */
	public String getStrClosingDate() {
		return strClosingDate;
	}

	/**
	 * strClosingDateを設定します。
	 * @param strClosingDate strClosingDate
	 */
	public void setStrClosingDate(final String strClosingDate) {
		this.strClosingDate = strClosingDate;
	}

	/**
	 * strCreditLimitPriceを取得します。
	 * @return strCreditLimitPrice
	 */
	public String getStrCreditLimitPrice() {
		return strCreditLimitPrice;
	}

	/**
	 * strCreditLimitPriceを設定します。
	 * @param strCreditLimitPrice strCreditLimitPrice
	 */
	public void setStrCreditLimitPrice(final String strCreditLimitPrice) {
		this.strCreditLimitPrice = strCreditLimitPrice;
	}

	/**
	 * strCreditScheduledDate1を取得します。
	 * @return strCreditScheduledDate1
	 */
	public String getStrCreditScheduledDate1() {
		return strCreditScheduledDate1;
	}

	/**
	 * strCreditScheduledDate1を設定します。
	 * @param strCreditScheduledDate1 strCreditScheduledDate1
	 */
	public void setStrCreditScheduledDate1(final String strCreditScheduledDate1) {
		this.strCreditScheduledDate1 = strCreditScheduledDate1;
	}

	/**
	 * strCreditScheduledDate2を取得します。
	 * @return strCreditScheduledDate2
	 */
	public String getStrCreditScheduledDate2() {
		return strCreditScheduledDate2;
	}

	/**
	 * strCreditScheduledDate2を設定します。
	 * @param strCreditScheduledDate2 strCreditScheduledDate2
	 */
	public void setStrCreditScheduledDate2(final String strCreditScheduledDate2) {
		this.strCreditScheduledDate2 = strCreditScheduledDate2;
	}

	/**
	 * strCreditScheduledDate3を取得します。
	 * @return strCreditScheduledDate3
	 */
	public String getStrCreditScheduledDate3() {
		return strCreditScheduledDate3;
	}

	/**
	 * strCreditScheduledDate3を設定します。
	 * @param strCreditScheduledDate3 strCreditScheduledDate3
	 */
	public void setStrCreditScheduledDate3(final String strCreditScheduledDate3) {
		this.strCreditScheduledDate3 = strCreditScheduledDate3;
	}

	/**
	 * strNoteSight3を取得します。
	 * @return strNoteSight3
	 */
	public String getStrNoteSight3() {
		return strNoteSight3;
	}

	/**
	 * strNoteSight3を設定します。
	 * @param strNoteSight3 strNoteSight3
	 */
	public void setStrNoteSight3(final String strNoteSight3) {
		this.strNoteSight3 = strNoteSight3;
	}

	/**
	 * strNoteSight4を取得します。
	 * @return strNoteSight4
	 */
	public String getStrNoteSight4() {
		return strNoteSight4;
	}

	/**
	 * strNoteSight4を設定します。
	 * @param strNoteSight4 strNoteSight4
	 */
	public void setStrNoteSight4(final String strNoteSight4) {
		this.strNoteSight4 = strNoteSight4;
	}

	/**
	 * strPurchaseDiscountDays1を取得します。
	 * @return strPurchaseDiscountDays1
	 */
	public String getStrPurchaseDiscountDays1() {
		return strPurchaseDiscountDays1;
	}

	/**
	 * strPurchaseDiscountDays1を設定します。
	 * @param strPurchaseDiscountDays1 strPurchaseDiscountDays1
	 */
	public void setStrPurchaseDiscountDays1(
			final String strPurchaseDiscountDays1) {
		this.strPurchaseDiscountDays1 = strPurchaseDiscountDays1;
	}

	/**
	 * strPurchaseDiscountDays2を取得します。
	 * @return strPurchaseDiscountDays2
	 */
	public String getStrPurchaseDiscountDays2() {
		return strPurchaseDiscountDays2;
	}

	/**
	 * strPurchaseDiscountDays2を設定します。
	 * @param strPurchaseDiscountDays2 strPurchaseDiscountDays2
	 */
	public void setStrPurchaseDiscountDays2(
			final String strPurchaseDiscountDays2) {
		this.strPurchaseDiscountDays2 = strPurchaseDiscountDays2;
	}

	/**
	 * strPurchaseDiscountDays3を取得します。
	 * @return strPurchaseDiscountDays3
	 */
	public String getStrPurchaseDiscountDays3() {
		return strPurchaseDiscountDays3;
	}

	/**
	 * strPurchaseDiscountDays3を設定します。
	 * @param strPurchaseDiscountDays3 strPurchaseDiscountDays3
	 */
	public void setStrPurchaseDiscountDays3(
			final String strPurchaseDiscountDays3) {
		this.strPurchaseDiscountDays3 = strPurchaseDiscountDays3;
	}

	/**
	 * subcontractLawを取得します。
	 * @return subcontractLaw
	 */
	public Boolean getSubcontractLaw() {
		return subcontractLaw;
	}

	/**
	 * subcontractLawを設定します。
	 * @param subcontractLaw subcontractLaw
	 */
	public void setSubcontractLaw(final Boolean subcontractLaw) {
		this.subcontractLaw = subcontractLaw;
	}

	/**
	 * tantoCdを取得します。
	 * @return tantoCd
	 */
	public String getTantoCd() {
		return tantoCd;
	}

	/**
	 * tantoCdを設定します。
	 * @param tantoCd tantoCd
	 */
	public void setTantoCd(final String tantoCd) {
		this.tantoCd = tantoCd;
	}

	/**
	 * taxDivisionを取得します。
	 * @return taxDivision
	 */
	public BigDecimal getTaxDivision() {
		return taxDivision;
	}

	/**
	 * taxDivisionを設定します。
	 * @param taxDivision taxDivision
	 */
	public void setTaxDivision(final BigDecimal taxDivision) {
		this.taxDivision = taxDivision;
	}

	/**
	 * taxRatioを取得します。
	 * @return taxRatio
	 */
	public BigDecimal getTaxRatio() {
		return taxRatio;
	}

	/**
	 * taxRatioを設定します。
	 * @param taxRatio taxRatio
	 */
	public void setTaxRatio(final BigDecimal taxRatio) {
		this.taxRatio = taxRatio;
	}

	/**
	 * taxRoundupを取得します。
	 * @return taxRoundup
	 */
	public BigDecimal getTaxRoundup() {
		return taxRoundup;
	}

	/**
	 * taxRoundupを設定します。
	 * @param taxRoundup taxRoundup
	 */
	public void setTaxRoundup(final BigDecimal taxRoundup) {
		this.taxRoundup = taxRoundup;
	}

	/**
	 * taxRoundupUnitを取得します。
	 * @return taxRoundupUnit
	 */
	public BigDecimal getTaxRoundupUnit() {
		return taxRoundupUnit;
	}

	/**
	 * taxRoundupUnitを設定します。
	 * @param taxRoundupUnit taxRoundupUnit
	 */
	public void setTaxRoundupUnit(final BigDecimal taxRoundupUnit) {
		this.taxRoundupUnit = taxRoundupUnit;
	}

	/**
	 * telNoを取得します。
	 * @return telNo
	 */
	public String getTelNo() {
		return telNo;
	}

	/**
	 * telNoを設定します。
	 * @param telNo telNo
	 */
	public void setTelNo(final String telNo) {
		this.telNo = telNo;
	}

	/**
	 * transferCommissionLoadを取得します。
	 * @return transferCommissionLoad
	 */
	public BigDecimal getTransferCommissionLoad() {
		return transferCommissionLoad;
	}

	/**
	 * transferCommissionLoadを設定します。
	 * @param transferCommissionLoad transferCommissionLoad
	 */
	public void setTransferCommissionLoad(
			final BigDecimal transferCommissionLoad) {
		this.transferCommissionLoad = transferCommissionLoad;
	}

	/**
	 * unitpriceRoundupを取得します。
	 * @return unitpriceRoundup
	 */
	public BigDecimal getUnitpriceRoundup() {
		return unitpriceRoundup;
	}

	/**
	 * unitpriceRoundupを設定します。
	 * @param unitpriceRoundup unitpriceRoundup
	 */
	public void setUnitpriceRoundup(final BigDecimal unitpriceRoundup) {
		this.unitpriceRoundup = unitpriceRoundup;
	}

	/**
	 * unitpriceRoundupUnitを取得します。
	 * @return unitpriceRoundupUnit
	 */
	public BigDecimal getUnitpriceRoundupUnit() {
		return unitpriceRoundupUnit;
	}

	/**
	 * unitpriceRoundupUnitを設定します。
	 * @param unitpriceRoundupUnit unitpriceRoundupUnit
	 */
	public void setUnitpriceRoundupUnit(final BigDecimal unitpriceRoundupUnit) {
		this.unitpriceRoundupUnit = unitpriceRoundupUnit;
	}

	/**
	 * venderDivisionを取得します。
	 * @return venderDivision
	 */
	public String getVenderDivision() {
		return venderDivision;
	}

	/**
	 * venderDivisionを設定します。
	 * @param venderDivision venderDivision
	 */
	public void setVenderDivision(final String venderDivision) {
		this.venderDivision = venderDivision;
	}

	/**
	 * venderDivisionNameを取得します。
	 * @return venderDivisionName
	 */
	public String getVenderDivisionName() {
		return venderDivisionName;
	}

	/**
	 * venderDivisionNameを設定します。
	 * @param venderDivisionName venderDivisionName
	 */
	public void setVenderDivisionName(final String venderDivisionName) {
		this.venderDivisionName = venderDivisionName;
	}

	/**
	 * venderName1を取得します。
	 * @return venderName1
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * venderName1を設定します。
	 * @param venderName1 venderName1
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
	}

	/**
	 * venderName2を取得します。
	 * @return venderName2
	 */
	public String getVenderName2() {
		return venderName2;
	}

	/**
	 * venderName2を設定します。
	 * @param venderName2 venderName2
	 */
	public void setVenderName2(final String venderName2) {
		this.venderName2 = venderName2;
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
	public void setVenderShortedName(final String venderShortedName) {
		this.venderShortedName = venderShortedName;
	}

	/**
	 * zipcodeNoを取得します。
	 * @return zipcodeNo
	 */
	public String getZipcodeNo() {
		return zipcodeNo;
	}

	/**
	 * zipcodeNoを設定します。
	 * @param zipcodeNo zipcodeNo
	 */
	public void setZipcodeNo(final String zipcodeNo) {
		this.zipcodeNo = zipcodeNo;
	}

	/**
	 * tantoNmを取得します。
	 * @return tantoNm
	 */
	public String getTantoNm() {
		return tantoNm;
	}

	/**
	 * tantoNmを設定します。
	 * @param tantoNm tantoNm
	 */
	public void setTantoNm(final String tantoNm) {
		this.tantoNm = tantoNm;
	}

	/**
	 * noteSight5を取得します。
	 * @return noteSight5
	 */
	public BigDecimal getNoteSight5() {
		return noteSight5;
	}

	/**
	 * noteSight5を設定します。
	 * @param noteSight5 noteSight5
	 */
	public void setNoteSight5(final BigDecimal noteSight5) {
		this.noteSight5 = noteSight5;
	}

	/**
	 * strNoteSight5を取得します。
	 * @return strNoteSight5
	 */
	public String getStrNoteSight5() {
		return strNoteSight5;
	}

	/**
	 * strNoteSight5を設定します。
	 * @param strNoteSight5 strNoteSight5
	 */
	public void setStrNoteSight5(final String strNoteSight5) {
		this.strNoteSight5 = strNoteSight5;
	}

	/**
	 * strTaxRatioを設定します。
	 * @param strTaxRatio strTaxRatio
	 */
	public void setStrTaxRatio(final String strTaxRatio) {
		this.strTaxRatio = strTaxRatio;
	}

	/**
	 * strTaxRatioを取得します。
	 * @return strTaxRatio
	 */
	public String getStrTaxRatio() {
		return strTaxRatio;
	}

	/**
	 * bankMasterCdLabelsを取得します。
	 * @return bankMasterCdLabels
	 */
	public String[] getBankMasterCdLabels() {
		return bankMasterCdLabels;
	}

	/**
	 * bankMasterCdLabelsを設定します。
	 * @param bankMasterCdLabels bankMasterCdLabels
	 */
	public void setBankMasterCdLabels(final String[] bankMasterCdLabels) {
		this.bankMasterCdLabels = bankMasterCdLabels;
	}

	/**
	 * bankMasterCdValuesを取得します。
	 * @return bankMasterCdValues
	 */
	public String[] getBankMasterCdValues() {
		return bankMasterCdValues;
	}

	/**
	 * bankMasterCdValuesを設定します。
	 * @param bankMasterCdValues bankMasterCdValues
	 */
	public void setBankMasterCdValues(final String[] bankMasterCdValues) {
		this.bankMasterCdValues = bankMasterCdValues;
	}

	/**
	 * paymentInvoiceNameを取得します。
	 * @return paymentInvoiceName
	 */
	public String getPaymentInvoiceName() {
		return paymentInvoiceName;
	}

	/**
	 * paymentInvoiceNameを設定します。
	 * @param paymentInvoiceName paymentInvoiceName
	 */
	public void setPaymentInvoiceName(final String paymentInvoiceName) {
		this.paymentInvoiceName = paymentInvoiceName;
	}

	/**
	 * dispBankMasterCdを取得します。
	 * @return dispBankMasterCd
	 */
	public String getDispBankMasterCd() {
		return dispBankMasterCd;
	}

	/**
	 * dispBankMasterCdを設定します。
	 * @param dispBankMasterCd dispBankMasterCd
	 */
	public void setDispBankMasterCd(final String dispBankMasterCd) {
		this.dispBankMasterCd = dispBankMasterCd;
	}

	/**
	 * dispBankNameを取得します。
	 * @return dispBankName
	 */
	public String getDispBankName() {
		return dispBankName;
	}

	/**
	 * dispBankNameを設定します。
	 * @param dispBankName dispBankName
	 */
	public void setDispBankName(final String dispBankName) {
		this.dispBankName = dispBankName;
	}

	/**
	 * dispBranchNameを取得します。
	 * @return dispBranchName
	 */
	public String getDispBranchName() {
		return dispBranchName;
	}

	/**
	 * dispBranchNameを設定します。
	 * @param dispBranchName dispBranchName
	 */
	public void setDispBranchName(final String dispBranchName) {
		this.dispBranchName = dispBranchName;
	}

	/**
	 * creditDivision3を取得します。
	 * @return creditDivision3
	 */
	public BigDecimal getCreditDivision3() {
		return creditDivision3;
	}

	/**
	 * creditDivision3を設定します。
	 * @param creditDivision3 creditDivision3
	 */
	public void setCreditDivision3(final BigDecimal creditDivision3) {
		this.creditDivision3 = creditDivision3;
	}

	/**
	 * creditDivision4を取得します。
	 * @return creditDivision4
	 */
	public BigDecimal getCreditDivision4() {
		return creditDivision4;
	}

	/**
	 * creditDivision4を設定します。
	 * @param creditDivision4 creditDivision4
	 */
	public void setCreditDivision4(final BigDecimal creditDivision4) {
		this.creditDivision4 = creditDivision4;
	}

	/**
	 * creditDivision5を取得します。
	 * @return creditDivision5
	 */
	public BigDecimal getCreditDivision5() {
		return creditDivision5;
	}

	/**
	 * creditDivision5を設定します。
	 * @param creditDivision5 creditDivision5
	 */
	public void setCreditDivision5(final BigDecimal creditDivision5) {
		this.creditDivision5 = creditDivision5;
	}

	/**
	 * cursorを取得します。
	 * @return cursor
	 */
	public String getCursor() {
		return cursor;
	}

	/**
	 * cursorを設定します。
	 * @param cursor cursor
	 */
	public void setCursor(final String cursor) {
		this.cursor = cursor;
	}

	/**
	 * creditDivisionLabelsを取得します。
	 * @return creditDivisionLabels
	 */
	public String[] getCreditDivisionLabels() {
		return creditDivisionLabels;
	}

	/**
	 * creditDivisionLabelsを設定します。
	 * @param creditDivisionLabels creditDivisionLabels
	 */
	public void setCreditDivisionLabels(final String[] creditDivisionLabels) {
		this.creditDivisionLabels = creditDivisionLabels;
	}

	/**
	 * creditDivisionValuesを取得します。
	 * @return creditDivisionValues
	 */
	public String[] getCreditDivisionValues() {
		return creditDivisionValues;
	}

	/**
	 * creditDivisionValuesを設定します。
	 * @param creditDivisionValues creditDivisionValues
	 */
	public void setCreditDivisionValues(final String[] creditDivisionValues) {
		this.creditDivisionValues = creditDivisionValues;
	}

	/**
	 * accountDivisionNameを取得します。
	 * @return accountDivisionName
	 */
	public String getAccountDivisionName() {
		return accountDivisionName;
	}

	/**
	 * accountDivisionNameを設定します。
	 * @param accountDivisionName accountDivisionName
	 */
	public void setAccountDivisionName(final String accountDivisionName) {
		this.accountDivisionName = accountDivisionName;
	}

	/**
	 * notCreditDivisionLabelsを取得します。
	 * @return notCreditDivisionLabels
	 */
	public String[] getNotCreditDivisionLabels() {
		return notCreditDivisionLabels;
	}

	/**
	 * notCreditDivisionLabelsを設定します。
	 * @param notCreditDivisionLabels notCreditDivisionLabels
	 */
	public void setNotCreditDivisionLabels(
			final String[] notCreditDivisionLabels) {
		this.notCreditDivisionLabels = notCreditDivisionLabels;
	}

	/**
	 * notCreditDivisionValuesを取得します。
	 * @return notCreditDivisionValues
	 */
	public String[] getNotCreditDivisionValues() {
		return notCreditDivisionValues;
	}

	/**
	 * notCreditDivisionValuesを設定します。
	 * @param notCreditDivisionValues notCreditDivisionValues
	 */
	public void setNotCreditDivisionValues(
			final String[] notCreditDivisionValues) {
		this.notCreditDivisionValues = notCreditDivisionValues;
	}

	/**
	 * faxOutputを取得します。
	 * @return faxOutput
	 */
	public BigDecimal getFaxOutput() {
		return faxOutput;
	}

	/**
	 * faxOutputを設定します。
	 * @param faxOutput faxOutput
	 */
	public void setFaxOutput(final BigDecimal faxOutput) {
		this.faxOutput = faxOutput;
	}

	/**
	 * slipFaxNoを取得します。
	 * @return slipFaxNo
	 */
	public String getSlipFaxNo() {
		return slipFaxNo;
	}

	/**
	 * slipFaxNoを設定します。
	 * @param slipFaxNo slipFaxNo
	 */
	public void setSlipFaxNo(String slipFaxNo) {
		this.slipFaxNo = slipFaxNo;
	}

	/**
	 * orderFaxNoを取得します。
	 * @return orderFaxNo
	 */
	public String getOrderFaxNo() {
		return orderFaxNo;
	}

	/**
	 * orderFaxNoを設定します。
	 * @param orderFaxNo orderFaxNo
	 */
	public void setOrderFaxNo(String orderFaxNo) {
		this.orderFaxNo = orderFaxNo;
	}

	/**
	 * orderFaxOutputを取得します。
	 * @return orderFaxOutput
	 */
	public BigDecimal getOrderFaxOutput() {
		return orderFaxOutput;
	}

	/**
	 * orderFaxOutputを設定します。
	 * @param orderFaxOutput orderFaxOutput
	 */
	public void setOrderFaxOutput(BigDecimal orderFaxOutput) {
		this.orderFaxOutput = orderFaxOutput;
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
	 * orderMailOutputを取得します。
	 * @return orderMailOutput
	 */
	public BigDecimal getOrderMailOutput() {
		return orderMailOutput;
	}

	/**
	 * orderMailOutputを設定します。
	 * @param orderMailOutput orderMailOutput
	 */
	public void setOrderMailOutput(BigDecimal orderMailOutput) {
		this.orderMailOutput = orderMailOutput;
	}

	/**
	 * salesFaxNoを取得します。
	 * @return salesFaxNo
	 */
	public String getSalesFaxNo() {
		return salesFaxNo;
	}

	/**
	 * salesFaxNoを設定します。
	 * @param salesFaxNo salesFaxNo
	 */
	public void setSalesFaxNo(String salesFaxNo) {
		this.salesFaxNo = salesFaxNo;
	}

	/**
	 * salesFaxOutputを取得します。
	 * @return salesFaxOutput
	 */
	public BigDecimal getSalesFaxOutput() {
		return salesFaxOutput;
	}

	/**
	 * salesFaxOutputを設定します。
	 * @param salesFaxOutput salesFaxOutput
	 */
	public void setSalesFaxOutput(BigDecimal salesFaxOutput) {
		this.salesFaxOutput = salesFaxOutput;
	}

	/**
	 * salesMailAddress1を取得します。
	 * @return salesMailAddress1
	 */
	public String getSalesMailAddress1() {
		return salesMailAddress1;
	}

	/**
	 * salesMailAddress1を設定します。
	 * @param salesMailAddress1 salesMailAddress1
	 */
	public void setSalesMailAddress1(String salesMailAddress1) {
		this.salesMailAddress1 = salesMailAddress1;
	}

	/**
	 * salesMailAddress2を取得します。
	 * @return salesMailAddress2
	 */
	public String getSalesMailAddress2() {
		return salesMailAddress2;
	}

	/**
	 * salesMailAddress2を設定します。
	 * @param salesMailAddress2 salesMailAddress2
	 */
	public void setSalesMailAddress2(String salesMailAddress2) {
		this.salesMailAddress2 = salesMailAddress2;
	}

	/**
	 * salesMailAddress3を取得します。
	 * @return salesMailAddress3
	 */
	public String getSalesMailAddress3() {
		return salesMailAddress3;
	}

	/**
	 * salesMailAddress3を設定します。
	 * @param salesMailAddress3 salesMailAddress3
	 */
	public void setSalesMailAddress3(String salesMailAddress3) {
		this.salesMailAddress3 = salesMailAddress3;
	}

	/**
	 * salesMailOutputを取得します。
	 * @return salesMailOutput
	 */
	public BigDecimal getSalesMailOutput() {
		return salesMailOutput;
	}

	/**
	 * salesMailOutputを設定します。
	 * @param salesMailOutput salesMailOutput
	 */
	public void setSalesMailOutput(BigDecimal salesMailOutput) {
		this.salesMailOutput = salesMailOutput;
	}

	/**
	 * mailOrganizationCdを取得します。
	 * @return mailOrganizationCd
	 */
	public String getMailOrganizationCd() {
		return mailOrganizationCd;
	}

	/**
	 * mailOrganizationCdを設定します。
	 * @param mailOrganizationCd mailOrganizationCd
	 */
	public void setMailOrganizationCd(final String mailOrganizationCd) {
		this.mailOrganizationCd = mailOrganizationCd;
	}

	/**
	 * mailOrganizationNameを取得します。
	 * @return mailOrganizationName
	 */
	public String getMailOrganizationName() {
		return mailOrganizationName;
	}

	/**
	 * mailOrganizationNameを設定します。
	 * @param mailOrganizationName mailOrganizationName
	 */
	public void setMailOrganizationName(final String mailOrganizationName) {
		this.mailOrganizationName = mailOrganizationName;
	}

	/**
	 * tsInvoicePtnを取得します。
	 * @return tsInvoicePtn
	 */
	public String getTsInvoicePtn() {
		return tsInvoicePtn;
	}

	/**
	 * tsInvoicePtnを設定します。
	 * @param tsInvoicePtn tsInvoicePtn
	 */
	public void setTsInvoicePtn(String tsInvoicePtn) {
		this.tsInvoicePtn = tsInvoicePtn;
	}

	/**
	 * tsInvoicePtnValuesを取得します。
	 * @return tsInvoicePtnValues
	 */
	public String[] getTsInvoicePtnValues() {
		return tsInvoicePtnValues;
	}

	/**
	 * tsInvoicePtnValuesを設定します。
	 * @param tsInvoicePtnValues tsInvoicePtnValues
	 */
	public void setTsInvoicePtnValues(String[] tsInvoicePtnValues) {
		this.tsInvoicePtnValues = tsInvoicePtnValues;
	}

	/**
	 * tsInvoicePtnLabelsを取得します。
	 * @return tsInvoicePtnLabels
	 */
	public String[] getTsInvoicePtnLabels() {
		return tsInvoicePtnLabels;
	}

	/**
	 * tsInvoicePtnLabelsを設定します。
	 * @param tsInvoicePtnLabels tsInvoicePtnLabels
	 */
	public void setTsInvoicePtnLabels(String[] tsInvoicePtnLabels) {
		this.tsInvoicePtnLabels = tsInvoicePtnLabels;
	}

	/**
	 * tsInvoiceActiveFlgを取得します。
	 * @return tsInvoiceActiveFlg
	 */
	public String getTsInvoiceActiveFlg() {
		return tsInvoiceActiveFlg;
	}

	/**
	 * tsInvoiceActiveFlgを設定します。
	 * @param tsInvoiceActiveFlg tsInvoiceActiveFlg
	 */
	public void setTsInvoiceActiveFlg(String tsInvoiceActiveFlg) {
		this.tsInvoiceActiveFlg = tsInvoiceActiveFlg;
	}

	/**
	 * siInvoicePtnを取得します。
	 * @return siInvoicePtn
	 */
	public String getSiInvoicePtn() {
		return siInvoicePtn;
	}

	/**
	 * siInvoicePtnを設定します。
	 * @param siInvoicePtn siInvoicePtn
	 */
	public void setSiInvoicePtn(String siInvoicePtn) {
		this.siInvoicePtn = siInvoicePtn;
	}

	/**
	 * siInvoicePtnValuesを取得します。
	 * @return siInvoicePtnValues
	 */
	public String[] getSiInvoicePtnValues() {
		return siInvoicePtnValues;
	}

	/**
	 * siInvoicePtnValuesを設定します。
	 * @param siInvoicePtnValues siInvoicePtnValues
	 */
	public void setSiInvoicePtnValues(String[] siInvoicePtnValues) {
		this.siInvoicePtnValues = siInvoicePtnValues;
	}

	/**
	 * siInvoicePtnLabelsを取得します。
	 * @return siInvoicePtnLabels
	 */
	public String[] getSiInvoicePtnLabels() {
		return siInvoicePtnLabels;
	}

	/**
	 * siInvoicePtnLabelsを設定します。
	 * @param siInvoicePtnLabels siInvoicePtnLabels
	 */
	public void setSiInvoicePtnLabels(String[] siInvoicePtnLabels) {
		this.siInvoicePtnLabels = siInvoicePtnLabels;
	}

	/**
	 * invoiceNoを取得します。
	 * @return invoiceNo
	 */
	public String getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * invoiceNoを設定します。
	 * @param invoiceNo invoiceNo
	 */
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

}
