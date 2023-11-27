/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arbalance;

import com.asahikaseieng.utils.AecTextUtils;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * 
 * ArBalancePagerConditionクラス.売掛残高一覧
 * @author tosco
 */
public class ArBalancePagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ArBalancePagerCondition() {
	}

	//
	// 検索入力用.section
	//

	/** 部門コード */
	private String srhSectionCd;

	/** 部門名称 */
	private String srhSectionName;

	/** 担当者コード */
	private String srhTantoCd;

	/** 担当者名称 */
	private String srhTantoNm;

	/** 請求先コード */
	private String srhVenderCd;

	/** 請求先名称 */
	private String srhVenderName;

	/** 検索入力：対象年月 */
	private String srhTargetMonth;

	/** 検索入力：出力区分（売掛残） */
	private String srhCreditAmountDivi;

	/** 検索入力：出力区分（未収金残）*/
	private String srhAccruedDebitDivi;

	/** 検索入力：通常処理分 */
	private boolean srhNormalFlg;

	/** 検索入力：仮締処理分 */
	private boolean srhTempClosingFlg;

	/**
	 * 部門コードを取得します。
	 * @return srhSectionCd
	 */
	public String getSrhSectionCd() {
		return srhSectionCd;
	}

	/**
	 * 部門コードを設定します。
	 * @param srhSectionCd 部門コード
	 */
	public void setSrhSectionCd(final String srhSectionCd) {
		this.srhSectionCd = AecTextUtils.likeFilter(srhSectionCd);
	}

	/**
	 * 部門名称を取得します。
	 * @return srhSectionName
	 */
	public String getSrhSectionName() {
		return srhSectionName;
	}

	/**
	 * 部門名称を設定します。
	 * @param srhSectionName 部門名称
	 */
	public void setSrhSectionName(final String srhSectionName) {
		this.srhSectionName = AecTextUtils.likeFilter(srhSectionName);
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
	 * 請求先コードを取得します。
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return srhVenderCd;
	}

	/**
	 * 請求先コードを設定します。
	 * @param srhVenderCd 請求先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = AecTextUtils.likeFilter(srhVenderCd);
	}

	/**
	 * 請求先名称を取得します。
	 * @return srhVenderName
	 */
	public String getSrhVenderName() {
		return srhVenderName;
	}

	/**
	 * 請求先名称を設定します。
	 * @param srhVenderName 請求先名称
	 */
	public void setSrhVenderName(final String srhVenderName) {
		this.srhVenderName = AecTextUtils.likeFilter(srhVenderName);
	}

	/**
	 * 対象年月を取得します。
	 * @return srhTargetMonth
	 */
	public String getSrhTargetMonth() {
		return srhTargetMonth;
	}

	/**
	 * 対象年月を設定します。
	 * @param srhTargetMonth 対象年月
	 */
	public void setSrhTargetMonth(final String srhTargetMonth) {
		this.srhTargetMonth = srhTargetMonth;
	}

	/**
	 * 出力区分（売掛残）を取得します。
	 * @return srhCreditAmountDivi
	 */
	public String getSrhCreditAmountDivi() {
		return srhCreditAmountDivi;
	}

	/**
	 * 出力区分（売掛残）を設定します。
	 * @param srhCreditAmountDivi 出力区分（売掛残）
	 */
	public void setSrhCreditAmountDivi(final String srhCreditAmountDivi) {
		this.srhCreditAmountDivi = srhCreditAmountDivi;
	}

	/**
	 * 出力区分（未収金残）を取得します。
	 * @return srhCreditAmountDivi
	 */
	public String getSrhAccruedDebitDivi() {
		return srhAccruedDebitDivi;
	}

	/**
	 * 出力区分（未収金残）を設定します。
	 * @param srhAccruedDebitDivi 出力区分（未収金残）
	 */
	public void setSrhAccruedDebitDivi(final String srhAccruedDebitDivi) {
		this.srhAccruedDebitDivi = srhAccruedDebitDivi;
	}

	/**
	 * 検索入力：通常処理分取得.
	 * @return boolean 通常処理分
	 */
	public boolean isSrhNormalFlg() {
		return srhNormalFlg;
	}

	/**
	 * 検索入力：通常処理分設定
	 * @param srhNormalFlg 通常処理分
	 */
	public void setSrhNormalFlg(final boolean srhNormalFlg) {
		this.srhNormalFlg = srhNormalFlg;
	}

	/**
	 * 検索入力：仮締処理分取得.
	 * @return boolean 仮締処理分
	 */
	public boolean isSrhTempClosingFlg() {
		return srhTempClosingFlg;
	}

	/**
	 * 検索入力：仮締処理分設定
	 * @param srhTempClosingFlg 仮締処理分
	 */
	public void setSrhTempClosingFlg(final boolean srhTempClosingFlg) {
		this.srhTempClosingFlg = srhTempClosingFlg;
	}

}
