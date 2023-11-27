/*
 * Created on 2008/07/22
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arledger;

import org.seasar.dao.pager.DefaultThresholdPagerCondition;

/**
 * 
 * ArLedgerDetailPagerConditionクラス.売掛元帳詳細
 * @author tosco
 */
public class ArLedgerDetailPagerCondition extends DefaultThresholdPagerCondition {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ArLedgerDetailPagerCondition() {
	}

	//
	// 検索条件.section
	//

	/** 売掛番号 */
	private String srhDepositNo;

	/** 請求先コード */
	private String srhVenderCd;

	/**
	 * 売掛番号を取得します。
	 * @return srhDepositNo
	 */
	public String getSrhDepositNo() {
		return srhDepositNo;
	}

	/**
	 * 売掛番号を設定します。
	 * @param srhDepositNo 売掛番号
	 */
	public void setSrhDepositNo(final String srhDepositNo) {
		this.srhDepositNo = srhDepositNo;
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
		this.srhVenderCd = srhVenderCd;
	}

}
