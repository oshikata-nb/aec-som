/*
 * Created on 2008/07/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.debt.apledger;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerDetail;
import com.asahikaseieng.dao.nonentity.debt.apledger.ApLedgerDetailPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 買掛元帳 Formクラス
 * @author tosco
 */
public final class ApLedgerDetailForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	/* ページの明細行数 */
	private static final int PAGE_ROW;

	/* 最大データ数 */
	private static final int DATA_ROW;

	static {
		ResourceBundle rb = ResourceBundle
				.getBundle(Constants.SYSTEM_PROPERTIES);

		PAGE_ROW = Integer.parseInt(rb.getString("linage.common"));
		DATA_ROW = Integer.parseInt(rb.getString("threshold.common"));
	}

	//
	// インスタンスフィールド
	//

	/** 対象区分(0:通常 1:仮締め 2:両方) */
	private String targetKbn;

	/** 買掛番号 */
	private String payableNo;

	/** 部署コード */
	private String organizationCd;

	/** 部署名称 */
	private String organizationName;

	/** 支払先コード */
	private String venderCd;

	/** 支払先名称 */
	private String venderName1;

	/** 支払先略称 */
	private String venderShortedName;

	/** 前月繰越 */
	private String strBalanceForward;

	/** 支払金額 */
	private String strPaymentAmount;

	/** その他支払金額 */
	private String strOtherPaymentAmount;

	/** 仕入高 */
	private String strStockingAmount;

	/** 返品金額 */
	private String strReturnedAmount;

	/** 値引金額 */
	private String strDiscountAmount;

	/** その他仕入 */
	private String strOtherStockingAmount;

	/** 相殺金額 */
	private String strOffsetAmount;

	/** 消費税額 */
	private String strTaxAmount;

	/** 買掛残高 */
	private String strPayableAmount;

	/** 買掛金(内訳) */
	private String strAccountPayableBreakdown;

	/** 未払金(内訳) */
	private String strArrearageBreakdown;

	/** 以外(内訳) */
	private String strExceptBreakdown;

	/** 買掛明細データリスト */
	private List<ApLedgerDetail> detailList;

	/**
	 * コンストラクタ.
	 */
	public ApLedgerDetailForm() {
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getLimit() {
		return PAGE_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected int getThreshold() {
		return DATA_ROW;
	}

	/**
	 * {@inheritDoc}
	 */
	protected Class getPagerConditionClass() {
		return ApLedgerDetailPagerCondition.class;
	}

	/**
	 * {@inheritDoc}
	 */
	public void reset(final ActionMapping mapping,
			final HttpServletRequest request) {

		super.reset(mapping, request);

		if ("init".equals(getOp())) {
			clear();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public ActionErrors validate(final ActionMapping mapping,
			final HttpServletRequest request) {
		ActionErrors errors = null;

		if ("search".equals(getOp())) {
			/* 詳細データリスト */
			setDetailList(null);
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		return errors;
	}

	/**
	 * クリア処理.
	 */
	private void clear() {
		// 対象区分
		setTargetKbn(null);
		// 買掛番号
		setPayableNo(null);
		// 支払先コード
		setVenderCd(null);
		// 支払先名称
		setVenderName1(null);
		// 支払先略称
		setVenderShortedName(null);
		// 前月繰越
		setStrBalanceForward(null);
		// 支払額
		setStrPaymentAmount(null);
		// その他
		setStrOtherPaymentAmount(null);
		// 仕入高
		setStrStockingAmount(null);
		// 値引
		setStrDiscountAmount(null);
		// 返品
		setStrReturnedAmount(null);
		// その他
		setStrOtherStockingAmount(null);
		// 相殺
		setStrOffsetAmount(null);
		// 消費税
		setStrTaxAmount(null);
		// 買掛残高
		setStrPayableAmount(null);
		// 買掛金(内訳)
		setStrAccountPayableBreakdown(null);
		// 未払金(内訳)
		setStrArrearageBreakdown(null);
		// 以外(内訳)
		setStrExceptBreakdown(null);

		// 詳細データリスト
		setDetailList(null);

	}

	//	
	// インスタンスメソッド
	//		

	/**
	 * 買掛番号を取得します。
	 * @return payableNo
	 */
	public String getPayableNo() {
		return payableNo;
	}

	/**
	 * 買掛番号を設定します。
	 * @param payableNo 買掛番号
	 */
	public void setPayableNo(final String payableNo) {
		this.payableNo = payableNo;
	}

	/**
	 * 部署コードを取得します。
	 * @return organizationCd
	 */
	public String getOrganizationCd() {
		return organizationCd;
	}

	/**
	 * 部署コードを設定します。
	 * @param organizationCd 部署コード
	 */
	public void setOrganizationCd(final String organizationCd) {
		this.organizationCd = organizationCd;
	}

	/**
	 * 部署名称を取得します。
	 * @return organizationName
	 */
	public String getOrganizationName() {
		return organizationName;
	}

	/**
	 * 部署名称を設定します。
	 * @param organizationName 部署名称
	 */
	public void setOrganizationName(final String organizationName) {
		this.organizationName = organizationName;
	}

	/**
	 * 前月繰越を取得します。
	 * @return strBalanceForward
	 */
	public String getStrBalanceForward() {
		return strBalanceForward;
	}

	/**
	 * 前月繰越を設定します。
	 * @param strBalanceForward 前月繰越
	 */
	public void setStrBalanceForward(final String strBalanceForward) {
		this.strBalanceForward = strBalanceForward;
	}

	/**
	 * 値引金額を取得します。
	 * @return strDiscountAmount
	 */
	public String getStrDiscountAmount() {
		return strDiscountAmount;
	}

	/**
	 * 値引金額を設定します。
	 * @param strDiscountAmount 値引金額
	 */
	public void setStrDiscountAmount(final String strDiscountAmount) {
		this.strDiscountAmount = strDiscountAmount;
	}

	/**
	 * 相殺金額を取得します。
	 * @return strOffsetAmount
	 */
	public String getStrOffsetAmount() {
		return strOffsetAmount;
	}

	/**
	 * 相殺金額を設定します。
	 * @param strOffsetAmount 相殺金額
	 */
	public void setStrOffsetAmount(final String strOffsetAmount) {
		this.strOffsetAmount = strOffsetAmount;
	}

	/**
	 * その他支払金額を取得します。
	 * @return strOtherPaymentAmount
	 */
	public String getStrOtherPaymentAmount() {
		return strOtherPaymentAmount;
	}

	/**
	 * その他支払金額を設定します。
	 * @param strOtherPaymentAmount その他支払金額
	 */
	public void setStrOtherPaymentAmount(final String strOtherPaymentAmount) {
		this.strOtherPaymentAmount = strOtherPaymentAmount;
	}

	/**
	 * その他仕入を取得します。
	 * @return strOtherStockingAmount
	 */
	public String getStrOtherStockingAmount() {
		return strOtherStockingAmount;
	}

	/**
	 * その他仕入を設定します。
	 * @param strOtherStockingAmount その他仕入
	 */
	public void setStrOtherStockingAmount(final String strOtherStockingAmount) {
		this.strOtherStockingAmount = strOtherStockingAmount;
	}

	/**
	 * 買掛残高を取得します。
	 * @return strPayableAmount
	 */
	public String getStrPayableAmount() {
		return strPayableAmount;
	}

	/**
	 * 買掛残高を設定します。
	 * @param strPayableAmount 買掛残高
	 */
	public void setStrPayableAmount(final String strPayableAmount) {
		this.strPayableAmount = strPayableAmount;
	}

	/**
	 * 買掛金(内訳)を取得します。
	 * @return strAccountPayableBreakdown
	 */
	public String getStrAccountPayableBreakdown() {
		return strAccountPayableBreakdown;
	}

	/**
	 * 買掛金(内訳)を設定します。
	 * @param strAccountPayableBreakdown 買掛金(内訳)
	 */
	public void setStrAccountPayableBreakdown(
			final String strAccountPayableBreakdown) {
		this.strAccountPayableBreakdown = strAccountPayableBreakdown;
	}

	/**
	 * 未払金(内訳)を取得します。
	 * @return strArrearageBreakdown
	 */
	public String getStrArrearageBreakdown() {
		return strArrearageBreakdown;
	}

	/**
	 * 未払金(内訳)を設定します。
	 * @param strArrearageBreakdown 未払金(内訳)
	 */
	public void setStrArrearageBreakdown(final String strArrearageBreakdown) {
		this.strArrearageBreakdown = strArrearageBreakdown;
	}

	/**
	 * 以外(内訳)を取得します。
	 * @return strExceptBreakdown
	 */
	public String getStrExceptBreakdown() {
		return strExceptBreakdown;
	}

	/**
	 * 以外(内訳)を設定します。
	 * @param strExceptBreakdown 以外(内訳)
	 */
	public void setStrExceptBreakdown(final String strExceptBreakdown) {
		this.strExceptBreakdown = strExceptBreakdown;
	}

	/**
	 * 支払金額を取得します。
	 * @return strPaymentAmount
	 */
	public String getStrPaymentAmount() {
		return strPaymentAmount;
	}

	/**
	 * 支払金額を設定します。
	 * @param strPaymentAmount 支払金額
	 */
	public void setStrPaymentAmount(final String strPaymentAmount) {
		this.strPaymentAmount = strPaymentAmount;
	}

	/**
	 * 返品金額を取得します。
	 * @return strReturnedAmount
	 */
	public String getStrReturnedAmount() {
		return strReturnedAmount;
	}

	/**
	 * 返品金額を設定します。
	 * @param strReturnedAmount 返品金額
	 */
	public void setStrReturnedAmount(final String strReturnedAmount) {
		this.strReturnedAmount = strReturnedAmount;
	}

	/**
	 * 仕入高を取得します。
	 * @return strStockingAmount
	 */
	public String getStrStockingAmount() {
		return strStockingAmount;
	}

	/**
	 * 仕入高を設定します。
	 * @param strStockingAmount 仕入高
	 */
	public void setStrStockingAmount(final String strStockingAmount) {
		this.strStockingAmount = strStockingAmount;
	}

	/**
	 * 消費税額を取得します。
	 * @return strTaxAmount
	 */
	public String getStrTaxAmount() {
		return strTaxAmount;
	}

	/**
	 * 消費税額を設定します。
	 * @param strTaxAmount 消費税額
	 */
	public void setStrTaxAmount(final String strTaxAmount) {
		this.strTaxAmount = strTaxAmount;
	}

	/**
	 * 支払先コードを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 支払先コードを設定します。
	 * @param venderCd 支払先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 支払先名称を取得します。
	 * @return venderName1
	 */
	public String getVenderName1() {
		return venderName1;
	}

	/**
	 * 支払先名称を設定します。
	 * @param venderName1 支払先名称
	 */
	public void setVenderName1(final String venderName1) {
		this.venderName1 = venderName1;
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
	 * 詳細データリストを取得します。
	 * @return detailList
	 */
	public List<ApLedgerDetail> getDetailList() {
		return detailList;
	}

	/**
	 * 詳細データリストを設定します。
	 * @param detailList 詳細データリスト
	 */
	public void setDetailList(final List<ApLedgerDetail> detailList) {
		this.detailList = detailList;
	}

	/**
	 * targetKbnを取得します。
	 * @return targetKbn
	 */
	public String getTargetKbn() {
		return targetKbn;
	}

	/**
	 * targetKbnを設定します。
	 * @param targetKbn targetKbn
	 */
	public void setTargetKbn(final String targetKbn) {
		this.targetKbn = targetKbn;
	}
}
