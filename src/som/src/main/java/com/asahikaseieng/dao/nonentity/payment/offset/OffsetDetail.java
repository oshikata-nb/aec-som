/*
 * Created on 2008/07/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offset;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * グループ間相殺詳細画面用Daoクラス.
 * @author tosco
 */
public class OffsetDetail extends OffsetDetailBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/** 相殺金額 */
	private String strOffsetAmount;

	/**
	 * コンストラクタ.
	 */
	public OffsetDetail() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 数値にカンマを付与 */
		// setStrOffsetAmount(AecNumberUtils.decimalFormat(getOffsetAmount(),
		// "###,###,###"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

	/**
	 * 相殺金額を取得します。
	 * @return strOffsetAmount
	 */
	public String getStrOffsetAmount() {
		return strOffsetAmount;
	}

	/**
	 * 相殺金額を設定します。
	 * @param strOffsetAmount 相殺金額
	 */
	public void setStrOffsetAmount(final String strOffsetAmount) {
		this.strOffsetAmount = strOffsetAmount;
	}

}
