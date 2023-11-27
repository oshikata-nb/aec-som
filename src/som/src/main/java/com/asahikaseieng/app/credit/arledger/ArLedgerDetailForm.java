/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.app.credit.arledger;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import com.asahikaseieng.Constants;
import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerDetail;
import com.asahikaseieng.dao.nonentity.credit.arledger.ArLedgerDetailPagerCondition;
import com.asahikaseieng.struts.AbstractSearchForm;

/**
 * 売掛元帳 Formクラス
 * @author tosco
 */
public final class ArLedgerDetailForm extends AbstractSearchForm {

	private static final long serialVersionUID = 1L;

	private String dirtyFlg; /* 変更フラグ */

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

	/** 売掛番号 */
	private String depositNo;

	/** 部門コード */
	private String sectionCd;

	/** 部門名称 */
	private String sectionName;

	/** 請求先コード */
	private String venderCd;

	/** 請求先名称 */
	private String venderName;

	/** 請求先略称 */
	private String venderShortedName;

	/** 前月繰越 */
	private String strBalanceForward;

	/** 入金金額 */
	private String strDepositAmount;

	/** その他入金金額 */
	private String strOtherDepositAmount;

	/** 売上高 */
	private String strSalesAmount;

	/** 返品金額 */
	private String strReturnedAmount;

	/** 値引金額 */
	private String strDiscountAmount;

	/** その他売上金額 */
	private String strOtherSalesAmount;

	/** 相殺金額 */
	private String strOffsetAmount;

	/** 消費税額 */
	private String strTaxAmount;

	/** 売掛金(内訳) */
	private String strCreditSalesBreakdown;

	/** 未収金(内訳) */
	private String strAccruedDebitBreakdown;

	/** 売掛残高 */
	private String strCreditAmount;

	/** リスト */
	private List<ArLedgerDetail> detailList;

	/**
	 * コンストラクタ.
	 */
	public ArLedgerDetailForm() {
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
		return ArLedgerDetailPagerCondition.class;
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
		if ("initNew".equals(getOp())) {
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
			// 下段の明細一覧をクリア
			setDetailList(null);
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		if ("update".equals(getOp())) {
			/* Validatorによる判定 */
			errors = super.validate(mapping, request);
		}
		if ("insert".equals(getOp())) {
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

		// 売掛番号
		setDepositNo(null);

		// 部門コード
		setSectionCd(null);
		// 部門名称
		setSectionName(null);
		// 取引先コード
		setVenderCd(null);
		// 取引先名称
		setVenderName(null);
		// 取引先略称
		setVenderShortedName(null);
		// 前月繰越
		setStrBalanceForward(null);
		// 入金額
		setStrDepositAmount(null);
		// その他入金金額
		setStrOtherDepositAmount(null);
		// 売上高
		setStrSalesAmount(null);
		// 返品
		setStrReturnedAmount(null);
		// 値引
		setStrDiscountAmount(null);
		// その他売上金額
		setStrOtherSalesAmount(null);
		// 相殺
		setStrOffsetAmount(null);
		// 消費税
		setStrTaxAmount(null);
		// 未収金(内訳)
		setStrAccruedDebitBreakdown(null);
		// 売掛金(内訳)
		setStrCreditSalesBreakdown(null);
		// 売掛残高
		setStrCreditAmount(null);

		// 下段の明細一覧をクリア
		setDetailList(null);
	}

	//	
	// インスタンスメソッド
	//		

	/**
	 * dirtyFlgを取得します。
	 * 
	 * @return dirtyFlg
	 */
	public String getDirtyFlg() {
		return dirtyFlg;
	}

	/**
	 * dirtyFlgを設定します。
	 * 
	 * @param dirtyFlg dirtyFlg
	 */
	public void setDirtyFlg(final String dirtyFlg) {
		this.dirtyFlg = dirtyFlg;
	}

	/**
	 * リストを取得します。
	 * 
	 * @return List<DepositDetail> リスト
	 * 
	 */
	public List<ArLedgerDetail> getDetailList() {
		return detailList;
	}

	/**
	 * リストを設定します。
	 * 
	 * @param detailList リスト
	 * 
	 */
	public void setDetailList(final List<ArLedgerDetail> detailList) {
		this.detailList = detailList;
	}

	/**
	 * 売掛番号を取得します。
	 * @return depositNo
	 */
	public String getDepositNo() {
		return depositNo;
	}

	/**
	 * 売掛番号を設定します。
	 * @param depositNo 売掛番号
	 */
	public void setDepositNo(final String depositNo) {
		this.depositNo = depositNo;
	}

	/**
	 * 部門コードを取得します。
	 * @return sectionCd
	 */
	public String getSectionCd() {
		return sectionCd;
	}

	/**
	 * 部門コードを設定します。
	 * @param sectionCd 部門コード
	 */
	public void setSectionCd(final String sectionCd) {
		this.sectionCd = sectionCd;
	}

	/**
	 * 部門名称を取得します。
	 * @return sectionName
	 */
	public String getSectionName() {
		return sectionName;
	}

	/**
	 * 部門名称を設定します。
	 * @param sectionName 部門名称
	 */
	public void setSectionName(final String sectionName) {
		this.sectionName = sectionName;
	}

	/**
	 * 請求先コードを取得します。
	 * @return venderCd
	 */
	public String getVenderCd() {
		return venderCd;
	}

	/**
	 * 請求先コードを設定します。
	 * @param venderCd 請求先コード
	 */
	public void setVenderCd(final String venderCd) {
		this.venderCd = venderCd;
	}

	/**
	 * 請求先名称を取得します。
	 * @return venderName
	 */
	public String getVenderName() {
		return venderName;
	}

	/**
	 * 請求先名称を設定します。
	 * @param venderName 請求先名称
	 */
	public void setVenderName(final String venderName) {
		this.venderName = venderName;
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
	 * 入金金額を取得します。
	 * @return strDepositAmount
	 */
	public String getStrDepositAmount() {
		return strDepositAmount;
	}

	/**
	 * 入金金額を設定します。
	 * @param strDepositAmount 入金金額
	 */
	public void setStrDepositAmount(final String strDepositAmount) {
		this.strDepositAmount = strDepositAmount;
	}

	/**
	 * その他入金金額を取得します。
	 * @return strOtherDepositAmount
	 */
	public String getStrOtherDepositAmount() {
		return strOtherDepositAmount;
	}

	/**
	 * その他入金金額を設定します。
	 * @param strOtherDepositAmount その他入金金額
	 */
	public void setStrOtherDepositAmount(final String strOtherDepositAmount) {
		this.strOtherDepositAmount = strOtherDepositAmount;
	}

	/**
	 * 売上高を取得します。
	 * @return strSalesAmount
	 */
	public String getStrSalesAmount() {
		return strSalesAmount;
	}

	/**
	 * 売上高を設定します。
	 * @param strSalesAmount 売上高
	 */
	public void setStrSalesAmount(final String strSalesAmount) {
		this.strSalesAmount = strSalesAmount;
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
	 * その他売上金額を取得します。
	 * @return strOtherSalesAmount
	 */
	public String getStrOtherSalesAmount() {
		return strOtherSalesAmount;
	}

	/**
	 * その他売上金額を設定します。
	 * @param strOtherSalesAmount その他売上金額
	 */
	public void setStrOtherSalesAmount(final String strOtherSalesAmount) {
		this.strOtherSalesAmount = strOtherSalesAmount;
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
	 * 消費税を取得します。
	 * @return strTaxAmount
	 */
	public String getStrTaxAmount() {
		return strTaxAmount;
	}

	/**
	 * 消費税を設定します。
	 * @param strTaxAmount 消費税
	 */
	public void setStrTaxAmount(final String strTaxAmount) {
		this.strTaxAmount = strTaxAmount;
	}

	/**
	 * 未収金(内訳)を取得します。
	 * @return strAccruedDebitBreakdown
	 */
	public String getStrAccruedDebitBreakdown() {
		return strAccruedDebitBreakdown;
	}

	/**
	 * 未収金(内訳)を設定します。
	 * @param strAccruedDebitBreakdown 未収金(内訳)
	 */
	public void setStrAccruedDebitBreakdown(
			final String strAccruedDebitBreakdown) {
		this.strAccruedDebitBreakdown = strAccruedDebitBreakdown;
	}

	/**
	 * 売掛金(内訳)を取得します。
	 * @return strCreditSalesBreakdown
	 */
	public String getStrCreditSalesBreakdown() {
		return strCreditSalesBreakdown;
	}

	/**
	 * 売掛金(内訳)を設定します。
	 * @param strCreditSalesBreakdown 売掛金(内訳)
	 */
	public void setStrCreditSalesBreakdown(final String strCreditSalesBreakdown) {
		this.strCreditSalesBreakdown = strCreditSalesBreakdown;
	}

	/**
	 * 売掛残高を取得します。
	 * @return strCreditAmount
	 */
	public String getStrCreditAmount() {
		return strCreditAmount;
	}

	/**
	 * 売掛残高を設定します。
	 * @param strCreditAmount 売掛残高
	 */
	public void setStrCreditAmount(final String strCreditAmount) {
		this.strCreditAmount = strCreditAmount;
	}

	/**
	 * 対象区分(0:通常 1:仮締め 2:両方)取得.
	 * @return String 対象区分(0:通常 1:仮締め 2:両方)
	 */
	public String getTargetKbn() {
		return targetKbn;
	}

	/**
	 * 対象区分(0:通常 1:仮締め 2:両方)設定
	 * @param targetKbn 対象区分(0:通常 1:仮締め 2:両方)
	 */
	public void setTargetKbn(final String targetKbn) {
		this.targetKbn = targetKbn;
	}

}
