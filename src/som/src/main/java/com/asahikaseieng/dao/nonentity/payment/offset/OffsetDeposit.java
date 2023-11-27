/*
 * Created on 2008/07/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offset;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 相殺処理 売掛取引先一覧用Daoクラス.
 * @author tosco
 */
public class OffsetDeposit extends OffsetDepositBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** 売掛残 */
	private String strCreditAmount;

	/** 売掛残合計 */
	private String strTotalCreditAmount;

	/**
	 * コンストラクタ.
	 */
	public OffsetDeposit() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 数値にカンマを付与 */
		// setStrCreditAmount(AecNumberUtils.decimalFormat(getCreditAmount(),
		// "###,###,###"));
		// setStrTotalCreditAmount(AecNumberUtils.decimalFormat(getTotalCreditAmount(),
		// "###,###,###"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 売掛残を取得します。
	 * @return strCreditAmount
	 */
	public String getStrCreditAmount() {
		return strCreditAmount;
	}

	/**
	 * 売掛残を設定します。
	 * @param strCreditAmount 売掛残
	 */
	public void setStrCreditAmount(final String strCreditAmount) {
		this.strCreditAmount = strCreditAmount;
	}

	/**
	 * 売掛残合計を取得します。
	 * @return strTotalCreditAmount
	 */
	public String getStrTotalCreditAmount() {
		return strTotalCreditAmount;
	}

	/**
	 * 売掛残合計を設定します。
	 * @param strTotalCreditAmount 売掛残合計
	 */
	public void setStrTotalCreditAmount(final String strTotalCreditAmount) {
		this.strTotalCreditAmount = strTotalCreditAmount;
	}

}
