/*
 * Created on 2007/04/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.remarklistforreport;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * Conditionクラス.備考マスタ
 * @author TanakaSatoru
 */
public class RemarkListConditionForReport {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RemarkListConditionForReport() {
	}

	//
	// 検索入力用
	//

	/** 検索入力.取引先区分 */
	private String srhVenderDivision;

	/** 検索入力.取引先コード */
	private String srhVenderCd;

	/** 検索入力.納入先コード */
	private String srhDeliveryCd;

	/** 検索入力.品目コード */
	private String srhItemCd;

	/** 検索入力.他社コード1 */
	private String srhOtherCompanyCd1;

	//
	// 検索入力.取引先別単価マスタ setter,getter
	//

	/**
	 * 検索入力.取引先区分を取得します。
	 * @return srhVenderDivision
	 */
	public String getSrhVenderDivision() {
		return srhVenderDivision;
	}

	/**
	 * 検索入力.取引先区分を設定します。
	 * @param srhVenderDivision 検索入力.取引先区分
	 */
	public void setSrhVenderDivision(final String srhVenderDivision) {
		this.srhVenderDivision = srhVenderDivision;
	}

	/**
	 * 検索入力.取引先コード取得.
	 * @return srhVenderCd
	 */
	public String getSrhVenderCd() {
		return this.srhVenderCd;
	}

	/**
	 * 検索入力.取引先コード設定.
	 * @param srhVenderCd venderCd
	 */
	public void setSrhVenderCd(final String srhVenderCd) {
		this.srhVenderCd = AecTextUtils.likeFilter(srhVenderCd);
	}

	/**
	 * 検索入力.納入先コード取得.
	 * @return srhDeliveryCd
	 */
	public String getSrhDeliveryCd() {
		return this.srhDeliveryCd;
	}

	/**
	 * 検索入力.納入先コード設定.
	 * @param srhDeliveryCd srhDeliveryCd
	 */
	public void setSrhDeliveryCd(final String srhDeliveryCd) {
		this.srhDeliveryCd = AecTextUtils.likeFilter(srhDeliveryCd);
	}

	/**
	 * 検索入力.品目コード取得.
	 * @return srhItemCd
	 */
	public String getSrhItemCd() {
		return this.srhItemCd;
	}

	/**
	 * 検索入力.品目コード設定.
	 * @param srhItemCd srhItemCd
	 */
	public void setSrhItemCd(final String srhItemCd) {
		this.srhItemCd = AecTextUtils.likeFilter(srhItemCd);
	}

	/**
	 * srhOtherCompanyCd1を取得します。
	 * @return srhOtherCompanyCd1
	 */
	public String getSrhOtherCompanyCd1() {
		return srhOtherCompanyCd1;
	}

	/**
	 * srhOtherCompanyCd1を設定します。
	 * @param srhOtherCompanyCd1 srhOtherCompanyCd1
	 */
	public void setSrhOtherCompanyCd1(final String srhOtherCompanyCd1) {
		this.srhOtherCompanyCd1 = AecTextUtils.likeFilter(srhOtherCompanyCd1);
	}

}
