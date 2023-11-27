/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentbalancelistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * 
 * PaymentBalancePagerConditionクラス.支払残高一覧
 * @author tosco
 */
public class PaymentBalanceListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.支払残高一覧
	 */
	public PaymentBalanceListConditionForReport() {
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

	/** 検索入力：対象年月 */
	private String srhTargetMonth;

	/** 検索入力：出力区分 */
	private boolean srhOutputDivision;

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
	 * 出力区分を取得します。
	 * @return srhOutputDivision
	 */
	public boolean getSrhOutputDivision() {
		return srhOutputDivision;
	}

	/**
	 * 出力区分を設定します。
	 * @param srhOutputDivision 出力区分
	 */
	public void setSrhOutputDivision(final boolean srhOutputDivision) {
		this.srhOutputDivision = srhOutputDivision;
	}
}
