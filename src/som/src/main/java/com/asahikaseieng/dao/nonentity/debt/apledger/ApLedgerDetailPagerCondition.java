/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.apledger;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * 
 * ApLedgerDetailPagerConditionクラス.買掛元帳詳細
 * @author tosco
 */
public class ApLedgerDetailPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ApLedgerDetailPagerCondition() {
	}

	//
	// 検索条件.section
	//

	/** 買掛番号 */
	private String srhPayableNo;

	/** 支払先コード */
	private String srhVenderCd;

	/**
	 * 買掛番号を取得します。
	 * @return srhPayableNo
	 */
	public String getSrhPayableNo() {
		return srhPayableNo;
	}

	/**
	 * 買掛番号を設定します。
	 * @param srhPayableNo 買掛番号
	 */
	public void setSrhPayableNo(final String srhPayableNo) {
		this.srhPayableNo = srhPayableNo;
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
		this.srhVenderCd = srhVenderCd;
	}

}
