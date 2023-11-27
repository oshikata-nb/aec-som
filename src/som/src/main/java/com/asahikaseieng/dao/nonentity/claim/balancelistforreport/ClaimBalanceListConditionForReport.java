/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.balancelistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 
 * BalancePagerConditionクラス.請求残高一覧
 * @author tosco
 */
public class ClaimBalanceListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClaimBalanceListConditionForReport() {
	}

	//
	// 検索入力用.section
	//

	/** 部門コード */
	private String srhSectionCd;

	/** 担当者コード */
	private String srhTantoCd;

	/** 請求先コード */
	private String srhVenderCd;

	/** 検索入力：対象年月 */
	private String srhTargetMonth;

	/** 検索入力：出力区分 */
	private boolean srhOutputDivision;

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
	 * 出力区分を取得します。
	 * @return srhOutputDivision
	 */
	public boolean getSrhOutputDivision() {
		return srhOutputDivision;
	}

	/**
	 * 出力区分を設定します。
	 * @param b 出力区分
	 */
	public void setSrhOutputDivision(final boolean b) {
		this.srhOutputDivision = b;
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
