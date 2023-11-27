/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.venderlistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * VenderPagerConditionクラス.
 * @author TanakaSatoru
 */
public class VenderListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public VenderListConditionForReport() {
	}

	//
	// 検索入力用
	//

	/** 検索入力：取引先区分 */
	private String srhVenderDivision;

	/** 検索入力：取引先コード */
	private String srhVenderCd;

	/** 検索入力：担当部署コード */
	private String srhOrganizationCd;

	//
	// 検索入力
	//

	/**
	 * 検索入力：取引先コード取得.
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return this.srhVenderCd;
	}

	/**
	 * 検索入力：取引先コード設定.
	 * @param srhVenderCd srhVenderCd
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = AecTextUtils.likeFilter(srhVenderCd);
	}

	/**
	 * 検索入力：取引先区分取得.
	 * @return srhVenderDivision
	 */
	public String getSrhVenderDivision() {
		return this.srhVenderDivision;
	}

	/**
	 * 検索入力：取引先区分設定.
	 * @param srhVenderDivision srhVenderDivision
	 */
	public void setSrhVenderDivision(final String srhVenderDivision) {
		this.srhVenderDivision = srhVenderDivision;
	}

	/**
	 * srhOrganizationCdを取得します。
	 * @return srhOrganizationCd
	 */
	public String getSrhOrganizationCd() {
		return srhOrganizationCd;
	}

	/**
	 * srhOrganizationCdを設定します。
	 * @param srhOrganizationCd srhOrganizationCd
	 */
	public void setSrhOrganizationCd(final String srhOrganizationCd) {
		this.srhOrganizationCd = AecTextUtils.likeFilter(srhOrganizationCd);
	}
}
