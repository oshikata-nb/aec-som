/*
 * Created on 2008/07/09
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentplan;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 支払予定一覧クラス.
 * @author tosco
 */
public class PaymentPlanList extends PaymentPlanListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** 支払残高(支払残高-差引繰越額) */
	private String strPayableAmountBalance;

	private Boolean checked;

	/**
	 * コンストラクタ.
	 */
	public PaymentPlanList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 数値にカンマを付与 */
		// setStrPayableAmountBalance(AecNumberUtils.decimalFormat(getPayableAmountBalance(),
		// "###,###,###"));
		/* チェックをクリア */
		setChecked(Boolean.FALSE);

	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 支払額を取得します。
	 * @return strPayableAmountBalance
	 */
	public String getStrPayableAmountBalance() {
		return strPayableAmountBalance;
	}

	/**
	 * 支払額を設定します。
	 * @param strPayableAmountBalance 支払額
	 */
	public void setStrPayableAmountBalance(final String strPayableAmountBalance) {
		this.strPayableAmountBalance = strPayableAmountBalance;
	}

	/**
	 * checkedを取得します。
	 * @return checked
	 */
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * checkedを設定します。
	 * @param checked checked
	 */
	public void setChecked(final Boolean checked) {
		this.checked = checked;
	}

}
