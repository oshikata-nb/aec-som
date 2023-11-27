/*
 * Created on 2009/02/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.master.company;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.struts.AbstractForm;

/**
 * 自社詳細 Formクラス.
 * @author t0011036
 */
public final class CompanyDetailForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	/* 自社名称1 */
	private String homeName1;

	/* 郵便番号 */
	private String zipcodeNo;

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

	/* 代表役職名 */
	private String representRole;

	/* 代表者名 */
	private String representPerson;

	/* インボイス登録番号 */
	private String invoiceNo;

	/* 決算月 */
	private BigDecimal settlement;

	private String strSettlement;

	/* 締日 */
	private BigDecimal closingDay;

	private String strClosingDay;

	/* 消費税課税区分 */
	private BigDecimal taxDivision;

	/* 消費税算出区分 */
	private BigDecimal calcDivision;

	/* 消費税端数区分 */
	private BigDecimal taxRoundup;

	/* 消費税端数単位 */
	private BigDecimal taxRoundupUnit;

	/* 消費税率 */
	private BigDecimal taxRatio;

	private String strTaxRatio;

	/* 在庫割引率 */
	private BigDecimal stockDiscountRate;

	private String strStockDiscountRate;

	/* 仕入割引率 */
	private BigDecimal purchaseDiscountRate;

	private String strPurchaseDiscountRate;

	/* 端数区分 */
	private BigDecimal roundup;

	/* 端数単位 */
	private BigDecimal roundupUnit;

	/* 売上金額端数区分 */
	private BigDecimal salesRoundup;

	/* 売上金額端数単位 */
	private BigDecimal salesRoundupUnit;

	/* 仕入金額端数区分 */
	private BigDecimal purchaseRoundup;

	/* 仕入金額端数単位 */
	private BigDecimal purchaseRoundupUnit;

	/* 単価端数区分 */
	private BigDecimal unitpriceRoundup;

	/* 単価端数単位 */
	private BigDecimal unitpriceRoundupUnit;

	/* 配合量端数区分 */
	private BigDecimal blendQtyRoundup;

	/* 配合量端数単位 */
	private BigDecimal blendQtyRoundupUnit;

	/* 配合率端数区分 */
	private BigDecimal mixRateRoundup;

	/* 配合率端数単位 */
	private BigDecimal mixRateRoundupUnit;

	/* 配合調整端数区分 */
	private BigDecimal adjRoundup;

	/* 配合調整端数単位 */
	private BigDecimal adjRoundupUnit;

	/* 支払更新区分 */
	private BigDecimal paymentUpdate;

	/* 自社コード */
	private String companyCd;

	/* 入金銀行マスタコード1 */
	private String bankMasterCd1;

	/* 入金銀行マスタ名称1 */
	private String bankMasterName1;

	/* 口座略称1 */
	private String accountAbbreviation1;

	/* 口座区分1 */
	private BigDecimal accountDivision1;

	/* 口座番号1 */
	private String accountNo1;

	/* 口座名義人1 */
	private String accountStockhold1;

	/* 入金銀行マスタコード2 */
	private String bankMasterCd2;

	/* 入金銀行マスタ名称2 */
	private String bankMasterName2;

	/* 口座略称2 */
	private String accountAbbreviation2;

	/* 口座区分2 */
	private BigDecimal accountDivision2;

	/* 口座番号2 */
	private String accountNo2;

	/* 口座名義人2 */
	private String accountStockhold2;

	/* 入金銀行マスタコード3 */
	private String bankMasterCd3;

	/* 入金銀行マスタ名称3 */
	private String bankMasterName3;

	/* 口座略称3 */
	private String accountAbbreviation3;

	/* 口座区分3 */
	private BigDecimal accountDivision3;

	/* 口座番号3 */
	private String accountNo3;

	/* 口座名義人3 */
	private String accountStockhold3;

	/* 入金銀行マスタコード4 */
	private String bankMasterCd4;

	/* 入金銀行マスタ名称4 */
	private String bankMasterName4;

	/* 口座略称4 */
	private String accountAbbreviation4;

	/* 口座区分4 */
	private BigDecimal accountDivision4;

	/* 口座番号4 */
	private String accountNo4;

	/* 口座名義人4 */
	private String accountStockhold4;

	/* 支払銀行マスタコード */
	private String bankMasterCd;

	/* 支払銀行マスタ名称 */
	private String bankMasterName;

	/* 口座区分 */
	private BigDecimal accountDivision;

	/* 口座番号 */
	private String accountNo;

	/* 振込依頼人コード */
	private String transferClientCd;

	/* 振込依頼人名 */
	private String transferClientName;

	/* パスワード有効期限 */
	private BigDecimal passwordValidTerm;

	private String strPasswordValidTerm;

	/* 開始パスワード有効桁数 */
	private BigDecimal passwordKetaLowerLimit;

	private String strPasswordKetaLowerLimit;

	/* 終了パスワード有効桁数 */
	private BigDecimal passwordKetaUpperLimit;

	private String strPasswordKetaUpperLimit;

	/* 短プラ+金利 */
	private BigDecimal prime;

	private String strPrime;

	/* 更新日時 */
	private java.sql.Timestamp updateDate;

	/* 変更フラグ */
	private Boolean dirtyFlg;

	/* 新規フラグ */
	private String newFlg;

	/* 新消費税率 */
	private BigDecimal newTaxRatio;

	/* 新消費税率文字 */
	private String strNewTaxRatio;

	/* 新消費税率開始日 */
	private java.sql.Timestamp newTaxApllyDate;

	/* 新消費税率開始日文字 */
	private String strNewTaxApllyDate;

	/**
	 * コンストラクタ.
	 */
	public CompanyDetailForm() {
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
		setHomeName1(null);
		setZipcodeNo(null);
		setAddress1(null);
		setAddress2(null);
		setAddress3(null);
		setTelNo(null);
		setFaxNo(null);
		setRepresentRole(null);
		setRepresentPerson(null);
		setInvoiceNo(null);
		setSettlement(null);
		setStrSettlement(null);
		setClosingDay(null);
		setStrClosingDay(null);
		setTaxDivision(null);
		setCalcDivision(null);
		setTaxRoundup(null);
		setTaxRoundupUnit(null);
		setTaxRatio(null);
		setStrTaxRatio(null);
		setStockDiscountRate(null);
		setStrStockDiscountRate(null);
		setPurchaseDiscountRate(null);
		setStrPurchaseDiscountRate(null);
		setRoundup(null);
		setRoundupUnit(null);
		setSalesRoundup(null);
		setSalesRoundupUnit(null);
		setPurchaseRoundup(null);
		setPurchaseRoundupUnit(null);
		setUnitpriceRoundup(null);
		setUnitpriceRoundupUnit(null);
		setBlendQtyRoundup(null);
		setBlendQtyRoundupUnit(null);
		setMixRateRoundup(null);
		setMixRateRoundupUnit(null);
		setAdjRoundup(null);
		setAdjRoundupUnit(null);
		setPaymentUpdate(null);
		setCompanyCd(null);
		setBankMasterCd1(null);
		setBankMasterName1(null);
		setAccountAbbreviation1(null);
		setAccountDivision1(null);
		setAccountNo1(null);
		setAccountStockhold1(null);
		setBankMasterCd2(null);
		setBankMasterName2(null);
		setAccountAbbreviation2(null);
		setAccountDivision2(null);
		setAccountNo2(null);
		setAccountStockhold2(null);
		setBankMasterCd3(null);
		setBankMasterName3(null);
		setAccountAbbreviation3(null);
		setAccountDivision3(null);
		setAccountNo3(null);
		setAccountStockhold3(null);
		setBankMasterCd4(null);
		setBankMasterName4(null);
		setAccountAbbreviation4(null);
		setAccountDivision4(null);
		setAccountNo4(null);
		setAccountStockhold4(null);
		setBankMasterCd(null);
		setBankMasterName(null);
		setAccountDivision(null);
		setAccountNo(null);
		setTransferClientCd(null);
		setTransferClientName(null);
		setPasswordValidTerm(null);
		setStrPasswordValidTerm(null);
		setPasswordKetaLowerLimit(null);
		setStrPasswordKetaLowerLimit(null);
		setPasswordKetaUpperLimit(null);
		setStrPasswordKetaUpperLimit(null);
		setPrime(null);
		setStrPrime(null);
		setUpdateDate(null);
		setDirtyFlg(null);
		setNewFlg(null);

		/* 新消費税率 */
		setNewTaxRatio(null);

		/* 新消費税率文字 */
		setStrNewTaxRatio(null);

		/* 新消費税率開始日 */
		setNewTaxApllyDate(null);

		/* 新消費税率開始日文字 */
		setStrNewTaxApllyDate(null);
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
	 * companyCdを取得します。
	 * @return companyCd
	 */
	public String getCompanyCd() {
		return companyCd;
	}

	/**
	 * companyCdを設定します。
	 * @param companyCd companyCd
	 */
	public void setCompanyCd(final String companyCd) {
		this.companyCd = companyCd;
	}

	/**
	 * accountAbbreviation1を取得します。
	 * @return accountAbbreviation1
	 */
	public String getAccountAbbreviation1() {
		return accountAbbreviation1;
	}

	/**
	 * accountAbbreviation1を設定します。
	 * @param accountAbbreviation1 accountAbbreviation1
	 */
	public void setAccountAbbreviation1(final String accountAbbreviation1) {
		this.accountAbbreviation1 = accountAbbreviation1;
	}

	/**
	 * accountAbbreviation2を取得します。
	 * @return accountAbbreviation2
	 */
	public String getAccountAbbreviation2() {
		return accountAbbreviation2;
	}

	/**
	 * accountAbbreviation2を設定します。
	 * @param accountAbbreviation2 accountAbbreviation2
	 */
	public void setAccountAbbreviation2(final String accountAbbreviation2) {
		this.accountAbbreviation2 = accountAbbreviation2;
	}

	/**
	 * accountAbbreviation3を取得します。
	 * @return accountAbbreviation3
	 */
	public String getAccountAbbreviation3() {
		return accountAbbreviation3;
	}

	/**
	 * accountAbbreviation3を設定します。
	 * @param accountAbbreviation3 accountAbbreviation3
	 */
	public void setAccountAbbreviation3(final String accountAbbreviation3) {
		this.accountAbbreviation3 = accountAbbreviation3;
	}

	/**
	 * accountAbbreviation4を取得します。
	 * @return accountAbbreviation4
	 */
	public String getAccountAbbreviation4() {
		return accountAbbreviation4;
	}

	/**
	 * accountAbbreviation4を設定します。
	 * @param accountAbbreviation4 accountAbbreviation4
	 */
	public void setAccountAbbreviation4(final String accountAbbreviation4) {
		this.accountAbbreviation4 = accountAbbreviation4;
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
	 * accountNo1を取得します。
	 * @return accountNo1
	 */
	public String getAccountNo1() {
		return accountNo1;
	}

	/**
	 * accountNo1を設定します。
	 * @param accountNo1 accountNo1
	 */
	public void setAccountNo1(final String accountNo1) {
		this.accountNo1 = accountNo1;
	}

	/**
	 * accountNo2を取得します。
	 * @return accountNo2
	 */
	public String getAccountNo2() {
		return accountNo2;
	}

	/**
	 * accountNo2を設定します。
	 * @param accountNo2 accountNo2
	 */
	public void setAccountNo2(final String accountNo2) {
		this.accountNo2 = accountNo2;
	}

	/**
	 * accountNo3を取得します。
	 * @return accountNo3
	 */
	public String getAccountNo3() {
		return accountNo3;
	}

	/**
	 * accountNo3を設定します。
	 * @param accountNo3 accountNo3
	 */
	public void setAccountNo3(final String accountNo3) {
		this.accountNo3 = accountNo3;
	}

	/**
	 * accountNo4を取得します。
	 * @return accountNo4
	 */
	public String getAccountNo4() {
		return accountNo4;
	}

	/**
	 * accountNo4を設定します。
	 * @param accountNo4 accountNo4
	 */
	public void setAccountNo4(final String accountNo4) {
		this.accountNo4 = accountNo4;
	}

	/**
	 * accountStockhold1を取得します。
	 * @return accountStockhold1
	 */
	public String getAccountStockhold1() {
		return accountStockhold1;
	}

	/**
	 * accountStockhold1を設定します。
	 * @param accountStockhold1 accountStockhold1
	 */
	public void setAccountStockhold1(final String accountStockhold1) {
		this.accountStockhold1 = accountStockhold1;
	}

	/**
	 * accountStockhold2を取得します。
	 * @return accountStockhold2
	 */
	public String getAccountStockhold2() {
		return accountStockhold2;
	}

	/**
	 * accountStockhold2を設定します。
	 * @param accountStockhold2 accountStockhold2
	 */
	public void setAccountStockhold2(final String accountStockhold2) {
		this.accountStockhold2 = accountStockhold2;
	}

	/**
	 * accountStockhold3を取得します。
	 * @return accountStockhold3
	 */
	public String getAccountStockhold3() {
		return accountStockhold3;
	}

	/**
	 * accountStockhold3を設定します。
	 * @param accountStockhold3 accountStockhold3
	 */
	public void setAccountStockhold3(final String accountStockhold3) {
		this.accountStockhold3 = accountStockhold3;
	}

	/**
	 * accountStockhold4を取得します。
	 * @return accountStockhold4
	 */
	public String getAccountStockhold4() {
		return accountStockhold4;
	}

	/**
	 * accountStockhold4を設定します。
	 * @param accountStockhold4 accountStockhold4
	 */
	public void setAccountStockhold4(final String accountStockhold4) {
		this.accountStockhold4 = accountStockhold4;
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
	 * adjRoundupを取得します。
	 * @return adjRoundup
	 */
	public BigDecimal getAdjRoundup() {
		return adjRoundup;
	}

	/**
	 * adjRoundupを設定します。
	 * @param adjRoundup adjRoundup
	 */
	public void setAdjRoundup(final BigDecimal adjRoundup) {
		this.adjRoundup = adjRoundup;
	}

	/**
	 * adjRoundupUnitを取得します。
	 * @return adjRoundupUnit
	 */
	public BigDecimal getAdjRoundupUnit() {
		return adjRoundupUnit;
	}

	/**
	 * adjRoundupUnitを設定します。
	 * @param adjRoundupUnit adjRoundupUnit
	 */
	public void setAdjRoundupUnit(final BigDecimal adjRoundupUnit) {
		this.adjRoundupUnit = adjRoundupUnit;
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
	 * bankMasterCd1を取得します。
	 * @return bankMasterCd1
	 */
	public String getBankMasterCd1() {
		return bankMasterCd1;
	}

	/**
	 * bankMasterCd1を設定します。
	 * @param bankMasterCd1 bankMasterCd1
	 */
	public void setBankMasterCd1(final String bankMasterCd1) {
		this.bankMasterCd1 = bankMasterCd1;
	}

	/**
	 * bankMasterCd2を取得します。
	 * @return bankMasterCd2
	 */
	public String getBankMasterCd2() {
		return bankMasterCd2;
	}

	/**
	 * bankMasterCd2を設定します。
	 * @param bankMasterCd2 bankMasterCd2
	 */
	public void setBankMasterCd2(final String bankMasterCd2) {
		this.bankMasterCd2 = bankMasterCd2;
	}

	/**
	 * bankMasterCd3を取得します。
	 * @return bankMasterCd3
	 */
	public String getBankMasterCd3() {
		return bankMasterCd3;
	}

	/**
	 * bankMasterCd3を設定します。
	 * @param bankMasterCd3 bankMasterCd3
	 */
	public void setBankMasterCd3(final String bankMasterCd3) {
		this.bankMasterCd3 = bankMasterCd3;
	}

	/**
	 * bankMasterCd4を取得します。
	 * @return bankMasterCd4
	 */
	public String getBankMasterCd4() {
		return bankMasterCd4;
	}

	/**
	 * bankMasterCd4を設定します。
	 * @param bankMasterCd4 bankMasterCd4
	 */
	public void setBankMasterCd4(final String bankMasterCd4) {
		this.bankMasterCd4 = bankMasterCd4;
	}

	/**
	 * bankMasterName1を取得します。
	 * @return bankMasterName1
	 */
	public String getBankMasterName1() {
		return bankMasterName1;
	}

	/**
	 * bankMasterName1を設定します。
	 * @param bankMasterName1 bankMasterName1
	 */
	public void setBankMasterName1(final String bankMasterName1) {
		this.bankMasterName1 = bankMasterName1;
	}

	/**
	 * bankMasterName2を取得します。
	 * @return bankMasterName2
	 */
	public String getBankMasterName2() {
		return bankMasterName2;
	}

	/**
	 * bankMasterName2を設定します。
	 * @param bankMasterName2 bankMasterName2
	 */
	public void setBankMasterName2(final String bankMasterName2) {
		this.bankMasterName2 = bankMasterName2;
	}

	/**
	 * bankMasterName3を取得します。
	 * @return bankMasterName3
	 */
	public String getBankMasterName3() {
		return bankMasterName3;
	}

	/**
	 * bankMasterName3を設定します。
	 * @param bankMasterName3 bankMasterName3
	 */
	public void setBankMasterName3(final String bankMasterName3) {
		this.bankMasterName3 = bankMasterName3;
	}

	/**
	 * bankMasterName4を取得します。
	 * @return bankMasterName4
	 */
	public String getBankMasterName4() {
		return bankMasterName4;
	}

	/**
	 * bankMasterName4を設定します。
	 * @param bankMasterName4 bankMasterName4
	 */
	public void setBankMasterName4(final String bankMasterName4) {
		this.bankMasterName4 = bankMasterName4;
	}

	/**
	 * blendQtyRoundupを取得します。
	 * @return blendQtyRoundup
	 */
	public BigDecimal getBlendQtyRoundup() {
		return blendQtyRoundup;
	}

	/**
	 * blendQtyRoundupを設定します。
	 * @param blendQtyRoundup blendQtyRoundup
	 */
	public void setBlendQtyRoundup(final BigDecimal blendQtyRoundup) {
		this.blendQtyRoundup = blendQtyRoundup;
	}

	/**
	 * blendQtyRoundupUnitを取得します。
	 * @return blendQtyRoundupUnit
	 */
	public BigDecimal getBlendQtyRoundupUnit() {
		return blendQtyRoundupUnit;
	}

	/**
	 * blendQtyRoundupUnitを設定します。
	 * @param blendQtyRoundupUnit blendQtyRoundupUnit
	 */
	public void setBlendQtyRoundupUnit(final BigDecimal blendQtyRoundupUnit) {
		this.blendQtyRoundupUnit = blendQtyRoundupUnit;
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
	 * closingDayを取得します。
	 * @return closingDay
	 */
	public BigDecimal getClosingDay() {
		return closingDay;
	}

	/**
	 * closingDayを設定します。
	 * @param closingDay closingDay
	 */
	public void setClosingDay(final BigDecimal closingDay) {
		this.closingDay = closingDay;
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
	 * homeName1を取得します。
	 * @return homeName1
	 */
	public String getHomeName1() {
		return homeName1;
	}

	/**
	 * homeName1を設定します。
	 * @param homeName1 homeName1
	 */
	public void setHomeName1(final String homeName1) {
		this.homeName1 = homeName1;
	}

	/**
	 * mixRateRoundupを取得します。
	 * @return mixRateRoundup
	 */
	public BigDecimal getMixRateRoundup() {
		return mixRateRoundup;
	}

	/**
	 * mixRateRoundupを設定します。
	 * @param mixRateRoundup mixRateRoundup
	 */
	public void setMixRateRoundup(final BigDecimal mixRateRoundup) {
		this.mixRateRoundup = mixRateRoundup;
	}

	/**
	 * mixRateRoundupUnitを取得します。
	 * @return mixRateRoundupUnit
	 */
	public BigDecimal getMixRateRoundupUnit() {
		return mixRateRoundupUnit;
	}

	/**
	 * mixRateRoundupUnitを設定します。
	 * @param mixRateRoundupUnit mixRateRoundupUnit
	 */
	public void setMixRateRoundupUnit(final BigDecimal mixRateRoundupUnit) {
		this.mixRateRoundupUnit = mixRateRoundupUnit;
	}

	/**
	 * passwordKetaLowerLimitを取得します。
	 * @return passwordKetaLowerLimit
	 */
	public BigDecimal getPasswordKetaLowerLimit() {
		return passwordKetaLowerLimit;
	}

	/**
	 * passwordKetaLowerLimitを設定します。
	 * @param passwordKetaLowerLimit passwordKetaLowerLimit
	 */
	public void setPasswordKetaLowerLimit(
			final BigDecimal passwordKetaLowerLimit) {
		this.passwordKetaLowerLimit = passwordKetaLowerLimit;
	}

	/**
	 * passwordKetaUpperLimitを取得します。
	 * @return passwordKetaUpperLimit
	 */
	public BigDecimal getPasswordKetaUpperLimit() {
		return passwordKetaUpperLimit;
	}

	/**
	 * passwordKetaUpperLimitを設定します。
	 * @param passwordKetaUpperLimit passwordKetaUpperLimit
	 */
	public void setPasswordKetaUpperLimit(
			final BigDecimal passwordKetaUpperLimit) {
		this.passwordKetaUpperLimit = passwordKetaUpperLimit;
	}

	/**
	 * passwordValidTermを取得します。
	 * @return passwordValidTerm
	 */
	public BigDecimal getPasswordValidTerm() {
		return passwordValidTerm;
	}

	/**
	 * passwordValidTermを設定します。
	 * @param passwordValidTerm passwordValidTerm
	 */
	public void setPasswordValidTerm(final BigDecimal passwordValidTerm) {
		this.passwordValidTerm = passwordValidTerm;
	}

	/**
	 * paymentUpdateを取得します。
	 * @return paymentUpdate
	 */
	public BigDecimal getPaymentUpdate() {
		return paymentUpdate;
	}

	/**
	 * paymentUpdateを設定します。
	 * @param paymentUpdate paymentUpdate
	 */
	public void setPaymentUpdate(final BigDecimal paymentUpdate) {
		this.paymentUpdate = paymentUpdate;
	}

	/**
	 * primeを取得します。
	 * @return prime
	 */
	public BigDecimal getPrime() {
		return prime;
	}

	/**
	 * primeを設定します。
	 * @param prime prime
	 */
	public void setPrime(final BigDecimal prime) {
		this.prime = prime;
	}

	/**
	 * purchaseDiscountRateを取得します。
	 * @return purchaseDiscountRate
	 */
	public BigDecimal getPurchaseDiscountRate() {
		return purchaseDiscountRate;
	}

	/**
	 * purchaseDiscountRateを設定します。
	 * @param purchaseDiscountRate purchaseDiscountRate
	 */
	public void setPurchaseDiscountRate(final BigDecimal purchaseDiscountRate) {
		this.purchaseDiscountRate = purchaseDiscountRate;
	}

	/**
	 * purchaseRoundupを取得します。
	 * @return purchaseRoundup
	 */
	public BigDecimal getPurchaseRoundup() {
		return purchaseRoundup;
	}

	/**
	 * purchaseRoundupを設定します。
	 * @param purchaseRoundup purchaseRoundup
	 */
	public void setPurchaseRoundup(final BigDecimal purchaseRoundup) {
		this.purchaseRoundup = purchaseRoundup;
	}

	/**
	 * purchaseRoundupUnitを取得します。
	 * @return purchaseRoundupUnit
	 */
	public BigDecimal getPurchaseRoundupUnit() {
		return purchaseRoundupUnit;
	}

	/**
	 * purchaseRoundupUnitを設定します。
	 * @param purchaseRoundupUnit purchaseRoundupUnit
	 */
	public void setPurchaseRoundupUnit(final BigDecimal purchaseRoundupUnit) {
		this.purchaseRoundupUnit = purchaseRoundupUnit;
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
	 * salesRoundupを取得します。
	 * @return salesRoundup
	 */
	public BigDecimal getSalesRoundup() {
		return salesRoundup;
	}

	/**
	 * salesRoundupを設定します。
	 * @param salesRoundup salesRoundup
	 */
	public void setSalesRoundup(final BigDecimal salesRoundup) {
		this.salesRoundup = salesRoundup;
	}

	/**
	 * salesRoundupUnitを取得します。
	 * @return salesRoundupUnit
	 */
	public BigDecimal getSalesRoundupUnit() {
		return salesRoundupUnit;
	}

	/**
	 * salesRoundupUnitを設定します。
	 * @param salesRoundupUnit salesRoundupUnit
	 */
	public void setSalesRoundupUnit(final BigDecimal salesRoundupUnit) {
		this.salesRoundupUnit = salesRoundupUnit;
	}

	/**
	 * settlementを取得します。
	 * @return settlement
	 */
	public BigDecimal getSettlement() {
		return settlement;
	}

	/**
	 * settlementを設定します。
	 * @param settlement settlement
	 */
	public void setSettlement(final BigDecimal settlement) {
		this.settlement = settlement;
	}

	/**
	 * stockDiscountRateを取得します。
	 * @return stockDiscountRate
	 */
	public BigDecimal getStockDiscountRate() {
		return stockDiscountRate;
	}

	/**
	 * stockDiscountRateを設定します。
	 * @param stockDiscountRate stockDiscountRate
	 */
	public void setStockDiscountRate(final BigDecimal stockDiscountRate) {
		this.stockDiscountRate = stockDiscountRate;
	}

	/**
	 * strClosingDayを取得します。
	 * @return strClosingDay
	 */
	public String getStrClosingDay() {
		return strClosingDay;
	}

	/**
	 * strClosingDayを設定します。
	 * @param strClosingDay strClosingDay
	 */
	public void setStrClosingDay(final String strClosingDay) {
		this.strClosingDay = strClosingDay;
	}

	/**
	 * strPasswordKetaLowerLimitを取得します。
	 * @return strPasswordKetaLowerLimit
	 */
	public String getStrPasswordKetaLowerLimit() {
		return strPasswordKetaLowerLimit;
	}

	/**
	 * strPasswordKetaLowerLimitを設定します。
	 * @param strPasswordKetaLowerLimit strPasswordKetaLowerLimit
	 */
	public void setStrPasswordKetaLowerLimit(
			final String strPasswordKetaLowerLimit) {
		this.strPasswordKetaLowerLimit = strPasswordKetaLowerLimit;
	}

	/**
	 * strPasswordKetaUpperLimitを取得します。
	 * @return strPasswordKetaUpperLimit
	 */
	public String getStrPasswordKetaUpperLimit() {
		return strPasswordKetaUpperLimit;
	}

	/**
	 * strPasswordKetaUpperLimitを設定します。
	 * @param strPasswordKetaUpperLimit strPasswordKetaUpperLimit
	 */
	public void setStrPasswordKetaUpperLimit(
			final String strPasswordKetaUpperLimit) {
		this.strPasswordKetaUpperLimit = strPasswordKetaUpperLimit;
	}

	/**
	 * strPasswordValidTermを取得します。
	 * @return strPasswordValidTerm
	 */
	public String getStrPasswordValidTerm() {
		return strPasswordValidTerm;
	}

	/**
	 * strPasswordValidTermを設定します。
	 * @param strPasswordValidTerm strPasswordValidTerm
	 */
	public void setStrPasswordValidTerm(final String strPasswordValidTerm) {
		this.strPasswordValidTerm = strPasswordValidTerm;
	}

	/**
	 * strPrimeを取得します。
	 * @return strPrime
	 */
	public String getStrPrime() {
		return strPrime;
	}

	/**
	 * strPrimeを設定します。
	 * @param strPrime strPrime
	 */
	public void setStrPrime(final String strPrime) {
		this.strPrime = strPrime;
	}

	/**
	 * strPurchaseDiscountRateを取得します。
	 * @return strPurchaseDiscountRate
	 */
	public String getStrPurchaseDiscountRate() {
		return strPurchaseDiscountRate;
	}

	/**
	 * strPurchaseDiscountRateを設定します。
	 * @param strPurchaseDiscountRate strPurchaseDiscountRate
	 */
	public void setStrPurchaseDiscountRate(final String strPurchaseDiscountRate) {
		this.strPurchaseDiscountRate = strPurchaseDiscountRate;
	}

	/**
	 * strSettlementを取得します。
	 * @return strSettlement
	 */
	public String getStrSettlement() {
		return strSettlement;
	}

	/**
	 * strSettlementを設定します。
	 * @param strSettlement strSettlement
	 */
	public void setStrSettlement(final String strSettlement) {
		this.strSettlement = strSettlement;
	}

	/**
	 * strStockDiscountRateを取得します。
	 * @return strStockDiscountRate
	 */
	public String getStrStockDiscountRate() {
		return strStockDiscountRate;
	}

	/**
	 * strStockDiscountRateを設定します。
	 * @param strStockDiscountRate strStockDiscountRate
	 */
	public void setStrStockDiscountRate(final String strStockDiscountRate) {
		this.strStockDiscountRate = strStockDiscountRate;
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
	 * transferClientCdを取得します。
	 * @return transferClientCd
	 */
	public String getTransferClientCd() {
		return transferClientCd;
	}

	/**
	 * transferClientCdを設定します。
	 * @param transferClientCd transferClientCd
	 */
	public void setTransferClientCd(final String transferClientCd) {
		this.transferClientCd = transferClientCd;
	}

	/**
	 * transferClientNameを取得します。
	 * @return transferClientName
	 */
	public String getTransferClientName() {
		return transferClientName;
	}

	/**
	 * transferClientNameを設定します。
	 * @param transferClientName transferClientName
	 */
	public void setTransferClientName(final String transferClientName) {
		this.transferClientName = transferClientName;
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
	 * accountDivision1を取得します。
	 * @return accountDivision1
	 */
	public BigDecimal getAccountDivision1() {
		return accountDivision1;
	}

	/**
	 * accountDivision1を設定します。
	 * @param accountDivision1 accountDivision1
	 */
	public void setAccountDivision1(final BigDecimal accountDivision1) {
		this.accountDivision1 = accountDivision1;
	}

	/**
	 * accountDivision2を取得します。
	 * @return accountDivision2
	 */
	public BigDecimal getAccountDivision2() {
		return accountDivision2;
	}

	/**
	 * accountDivision2を設定します。
	 * @param accountDivision2 accountDivision2
	 */
	public void setAccountDivision2(final BigDecimal accountDivision2) {
		this.accountDivision2 = accountDivision2;
	}

	/**
	 * accountDivision3を取得します。
	 * @return accountDivision3
	 */
	public BigDecimal getAccountDivision3() {
		return accountDivision3;
	}

	/**
	 * accountDivision3を設定します。
	 * @param accountDivision3 accountDivision3
	 */
	public void setAccountDivision3(final BigDecimal accountDivision3) {
		this.accountDivision3 = accountDivision3;
	}

	/**
	 * accountDivision4を取得します。
	 * @return accountDivision4
	 */
	public BigDecimal getAccountDivision4() {
		return accountDivision4;
	}

	/**
	 * accountDivision4を設定します。
	 * @param accountDivision4 accountDivision4
	 */
	public void setAccountDivision4(final BigDecimal accountDivision4) {
		this.accountDivision4 = accountDivision4;
	}

	/**
	 * strTaxRatioを取得します。
	 * @return strTaxRatio
	 */
	public String getStrTaxRatio() {
		return strTaxRatio;
	}

	/**
	 * strTaxRatioを設定します。
	 * @param strTaxRatio strTaxRatio
	 */
	public void setStrTaxRatio(final String strTaxRatio) {
		this.strTaxRatio = strTaxRatio;
	}

	/**
	 * bankMasterNameを取得します。
	 * @return bankMasterName
	 */
	public String getBankMasterName() {
		return bankMasterName;
	}

	/**
	 * bankMasterNameを設定します。
	 * @param bankMasterName bankMasterName
	 */
	public void setBankMasterName(final String bankMasterName) {
		this.bankMasterName = bankMasterName;
	}

	/**
	 * bankMasterNameを取得します。
	 * @return bankMasterName
	 */
	public java.sql.Timestamp getNewTaxApllyDate() {
		return newTaxApllyDate;
	}

	/**
	 * newTaxApllyDateを設定します。
	 * @param newTaxApllyDate newTaxApllyDate
	 */
	public void setNewTaxApllyDate(final java.sql.Timestamp newTaxApllyDate) {
		this.newTaxApllyDate = newTaxApllyDate;
	}

	/**
	 * newTaxRatioを取得します。
	 * @return newTaxRatio
	 */
	public BigDecimal getNewTaxRatio() {
		return newTaxRatio;
	}

	/**
	 * newTaxRatioを設定します。
	 * @param newTaxRatio newTaxRatio
	 */
	public void setNewTaxRatio(final BigDecimal newTaxRatio) {
		this.newTaxRatio = newTaxRatio;
	}

	/**
	 * strNewTaxApllyDateを取得します。
	 * @return strNewTaxApllyDate
	 */
	public String getStrNewTaxApllyDate() {
		return strNewTaxApllyDate;
	}

	/**
	 * strNewTaxApllyDateを設定します。
	 * @param strNewTaxApllyDate strNewTaxApllyDate
	 */
	public void setStrNewTaxApllyDate(final String strNewTaxApllyDate) {
		this.strNewTaxApllyDate = strNewTaxApllyDate;
	}

	/**
	 * strNewTaxRatioを取得します。
	 * @return strNewTaxRatio
	 */
	public String getStrNewTaxRatio() {
		return strNewTaxRatio;
	}

	/**
	 * strNewTaxRatioを設定します。
	 * @param strNewTaxRatio strNewTaxRatio
	 */
	public void setStrNewTaxRatio(final String strNewTaxRatio) {
		this.strNewTaxRatio = strNewTaxRatio;
	}
}
