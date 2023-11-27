/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.depositplan;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 入金予定一覧用Daoクラス.
 * @author tosco
 */
public class DepositPlanList extends DepositPlanListBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** 請求額（今回請求額 - 差引繰越額） */
	private String strClaimAmountBalance;

	/**
	 * コンストラクタ.
	 */
	public DepositPlanList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 数値にカンマを付与 */
		// setStrClaimAmountBalance(AecNumberUtils.decimalFormat(getClaimAmountBalance(),
		// "###,###,###"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */

	/**
	 * 請求額を取得します。
	 * @return strClaimAmountBalance
	 */
	public String getStrClaimAmountBalance() {
		return strClaimAmountBalance;
	}

	/**
	 * 請求額を設定します。
	 * @param strClaimAmountBalance 請求額
	 */
	public void setStrClaimAmountBalance(final String strClaimAmountBalance) {
		this.strClaimAmountBalance = strClaimAmountBalance;
	}

}

