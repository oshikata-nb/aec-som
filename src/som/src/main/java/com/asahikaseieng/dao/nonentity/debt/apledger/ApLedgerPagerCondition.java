/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apledger;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 
 * ApLedgerPagerConditionクラス.買掛元帳
 * @author tosco
 */
public class ApLedgerPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ApLedgerPagerCondition() {
	}

	//
	// 検索入力用.section
	//

	/** 部署コード */
	private String srhOrganizationCd;

	/** 担当者コード */
	private String srhTantoCd;

	/** 担当者名称 */
	private String srhTantoNm;

	/** 支払先コード */
	private String srhVenderCd;

	/** 支払先名称 */
	private String srhVenderName1;

	/** 対象年月 */
	private String srhTargetMonth;

	/** 検索入力：通常処理分 */
	private boolean srhNormalFlg;

	/** 検索入力：仮締処理分 */
	private boolean srhTempClosingFlg;

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
	 * @param srhVenderCd 支払先コード
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = AecTextUtils.likeFilter(srhVenderCd);
	}

	/**
	 * 支払先名称を取得します。
	 * @return srhVenderName1
	 */
	public String getSrhVenderName1() {
		return srhVenderName1;
	}

	/**
	 * 支払先名称を設定します。
	 * @param srhVenderName1 支払先名称
	 */
	public void setSrhVenderName1(final String srhVenderName1) {
		this.srhVenderName1 = AecTextUtils.likeFilter(srhVenderName1);
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
	 * srhNormalFlgを取得します。
	 * @return srhNormalFlg
	 */
	public boolean isSrhNormalFlg() {
		return srhNormalFlg;
	}

	/**
	 * srhNormalFlgを設定します。
	 * @param srhNormalFlg srhNormalFlg
	 */
	public void setSrhNormalFlg(final boolean srhNormalFlg) {
		this.srhNormalFlg = srhNormalFlg;
	}

	/**
	 * srhTempClosingFlgを取得します。
	 * @return srhTempClosingFlg
	 */
	public boolean isSrhTempClosingFlg() {
		return srhTempClosingFlg;
	}

	/**
	 * srhTempClosingFlgを設定します。
	 * @param srhTempClosingFlg srhTempClosingFlg
	 */
	public void setSrhTempClosingFlg(final boolean srhTempClosingFlg) {
		this.srhTempClosingFlg = srhTempClosingFlg;
	}
}
