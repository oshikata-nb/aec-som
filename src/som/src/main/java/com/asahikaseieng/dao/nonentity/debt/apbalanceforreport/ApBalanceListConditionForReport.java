/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apbalanceforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 
 * ArBalancePagerConditionクラス.買掛残高一覧
 * @author tosco
 */
public class ApBalanceListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ApBalanceListConditionForReport() {
	}

	//
	// 検索入力用.section
	//

	/** 部署コード */
	private String srhOrganizationCd;

	/** 担当者コード */
	private String srhTantoCd;

	/** 支払先コード */
	private String srhVenderCd;

	/** 対象年月 */
	private String srhTargetMonth;

	/** 出力区分(買掛金) */
	private boolean srhAccountPayableDivi;

	/** 出力区分(未払金) */
	private boolean srhArrearageDivi;

	/** 対象区分 */
	private String srhTargetDivision;

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
	 * 出力区分(買掛金)を取得します。
	 * @return srhAccountPayableDivi
	 */
	public boolean getSrhAccountPayableDivi() {
		return srhAccountPayableDivi;
	}

	/**
	 * 出力区分(買掛金)を設定します。
	 * @param srhAccountPayableDivi 出力区分(買掛金)
	 */
	public void setSrhAccountPayableDivi(final boolean srhAccountPayableDivi) {
		this.srhAccountPayableDivi = srhAccountPayableDivi;
	}

	/**
	 * 出力区分(未払金)を取得します。
	 * @return srhArrearageDivi
	 */
	public boolean isSrhArrearageDivi() {
		return srhArrearageDivi;
	}

	/**
	 * 出力区分(未払金)を設定します。
	 * @param srhArrearageDivi 検索入力：出力区分(未払金)
	 */
	public void setSrhArrearageDivi(final boolean srhArrearageDivi) {
		this.srhArrearageDivi = srhArrearageDivi;
	}

	/**
	 * 対象区分取得.
	 * @return String 対象区分
	 */
	public String getSrhTargetDivision() {
		return srhTargetDivision;
	}

	/**
	 * 対象区分設定.
	 * @param srhTargetDivision 対象区分
	 */
	public void setSrhTargetDivision(final String srhTargetDivision) {
		this.srhTargetDivision = srhTargetDivision;
	}
}
