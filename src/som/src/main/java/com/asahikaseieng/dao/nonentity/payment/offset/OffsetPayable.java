/*
 * Created on 2008/07/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offset;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 買掛残リスト表示用Daoクラス.
 * @author tosco
 */
public class OffsetPayable extends OffsetPayableBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** 買掛残 */
	private String strPayableAmount;

	/** 買掛残合計 */
	private String strTotalPayableAmount;

	/**
	 * コンストラクタ.
	 */
	public OffsetPayable() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 数値にカンマを付与 */
		// setStrPayableAmount(AecNumberUtils.decimalFormat(getPayableAmount(),
		// "###,###,###"));
		// setStrTotalPayableAmount(AecNumberUtils.decimalFormat(getTotalPayableAmount(),
		// "###,###,###"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 買掛残を取得します。
	 * @return strPayableAmount
	 */
	public String getStrPayableAmount() {
		return strPayableAmount;
	}

	/**
	 * 買掛残を設定します。
	 * @param strPayableAmount 買掛残
	 */
	public void setStrPayableAmount(final String strPayableAmount) {
		this.strPayableAmount = strPayableAmount;
	}

	/**
	 * 買掛残合計を取得します。
	 * @return strTotalPayableAmount
	 */
	public String getStrTotalPayableAmount() {
		return strTotalPayableAmount;
	}

	/**
	 * 買掛残合計を設定します。
	 * @param strTotalPayableAmount 買掛残合計
	 */
	public void setStrTotalPayableAmount(final String strTotalPayableAmount) {
		this.strTotalPayableAmount = strTotalPayableAmount;
	}

}
