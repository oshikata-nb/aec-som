/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentplan;

import java.util.Date;

import com.asahikaseieng.utils.AecTextUtils;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * 
 * PaymentPlanPagerConditionクラス.入金予定一覧
 * @author tosco
 */
public class PaymentPlanPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.PaymentPlanPagerCondition
	 */
	public PaymentPlanPagerCondition() {
	}

	//
	// 検索入力用.section
	//

	/** 検索入力：部署コード */
	private String srhOrganizationCd;

	/** 検索入力：担当者コード */
	private String srhTantoCd;

	/** 検索入力：担当者名称 */
	private String srhTantoNm;

	/** 検索入力：出力区分 */
	private String srhOutputDivision;

	/** 検索入力：支払先コード */
	private String srhVenderCd;

	/** 検索入力：支払先名称 */
	private String srhVenderName;

	/** 検索入力：支払日付FROM */
	private Date srhPaymentDateFrom;

	/** 検索入力：支払日付TO */
	private Date srhPaymentDateTo;

	/** 検索入力：銀行+支店コード */
	private String srhBankCd;

	/** 検索入力：銀行名+支店名 */
	private String srhBankName;

	/** 検索入力：支払分類 */
	private String srhPaymentDivision;

	/**
	 * 部署コードを取得します。
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return srhOrganizationCd;
	}

	/**
	 * 部署コードを設定します。
	 * @param srhOrganizationCd 部署コード
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = AecTextUtils.likeFilter(srhOrganizationCd);
	}

	/**
	 * 担当者コードを取得します。
	 * @return srhTantoCd
	 */
	public String getSrhTantoCd() {
		return srhTantoCd;
	}

	/**
	 * 担当者コードを設定します。
	 * @param srhTantoCd 担当者コード
	 */
	public void setSrhTantoCd(final String srhTantoCd) {
		this.srhTantoCd = AecTextUtils.likeFilter(srhTantoCd);
	}

	/**
	 * 担当者名称を取得します。
	 * @return srhTantoNm
	 */
	public String getSrhTantoNm() {
		return srhTantoNm;
	}

	/**
	 * 担当者名称を設定します。
	 * @param srhTantoNm 担当者名称
	 */
	public void setSrhTantoNm(final String srhTantoNm) {
		this.srhTantoNm = AecTextUtils.likeFilter(srhTantoNm);
	}

	/**
	 * 支払先コードを取得します。
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * 支払先コードを設定します。
	 * @param srhVenderCd 請求先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = AecTextUtils.likeFilter(srhVenderCd);
	}

	/**
	 * 支払先名称を取得します。
	 * @return srhVenderName
	 */
	public String getSrhVenderName() {
		return srhVenderName;
	}

	/**
	 * 支払先名称を設定します。
	 * @param srhVenderName 請求先名称
	 */
	public void setSrhVenderName(final String srhVenderName) {
		this.srhVenderName = AecTextUtils.likeFilter(srhVenderName);
	}

	/**
	 * srhBankCdを取得します。
	 * @return srhBankCd
	 */
	public String getSrhBankCd() {
		return srhBankCd;
	}

	/**
	 * srhBankCdを設定します。
	 * @param srhBankCd srhBankCd
	 */
	public void setSrhBankCd(final String srhBankCd) {
		this.srhBankCd = AecTextUtils.likeFilter(srhBankCd);
	}

	/**
	 * srhBankNameを取得します。
	 * @return srhBankName
	 */
	public String getSrhBankName() {
		return srhBankName;
	}

	/**
	 * srhBankNameを設定します。
	 * @param srhBankName srhBankName
	 */
	public void setSrhBankName(final String srhBankName) {
		this.srhBankName = AecTextUtils.likeFilter(srhBankName);
	}

	/**
	 * srhPaymentDateFromを取得します。
	 * @return srhPaymentDateFrom
	 */
	public Date getSrhPaymentDateFrom() {
		return srhPaymentDateFrom;
	}

	/**
	 * srhPaymentDateFromを設定します。
	 * @param srhPaymentDateFrom srhPaymentDateFrom
	 */
	public void setSrhPaymentDateFrom(final Date srhPaymentDateFrom) {
		this.srhPaymentDateFrom = srhPaymentDateFrom;
	}

	/**
	 * srhPaymentDateToを取得します。
	 * @return srhPaymentDateTo
	 */
	public Date getSrhPaymentDateTo() {
		return srhPaymentDateTo;
	}

	/**
	 * srhPaymentDateToを設定します。
	 * @param srhPaymentDateTo srhPaymentDateTo
	 */
	public void setSrhPaymentDateTo(final Date srhPaymentDateTo) {
		this.srhPaymentDateTo = srhPaymentDateTo;
	}

	/**
	 * srhCreditDivisionを取得します。
	 * @return srhPaymentDivision
	 */
	public String getSrhPaymentDivision() {
		return srhPaymentDivision;
	}

	/**
	 * srhPaymentDivisionを設定します。
	 * @param srhPaymentDivision srhPaymentDivision
	 */
	public void setSrhPaymentDivision(final String srhPaymentDivision) {
		this.srhPaymentDivision = srhPaymentDivision;
	}

	/**
	 * srhOutputDivisionを取得します。
	 * @return srhOutputDivision
	 */
	public String getSrhOutputDivision() {
		return srhOutputDivision;
	}

	/**
	 * srhOutputDivisionを設定します。
	 * @param srhOutputDivision srhOutputDivision
	 */
	public void setSrhOutputDivision(final String srhOutputDivision) {
		this.srhOutputDivision = srhOutputDivision;
	}

}
