/*
 * Created on 2008/09/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.payment;

import java.math.BigDecimal;

import com.asahikaseieng.dao.entity.master.vender.VenderBase;
import com.asahikaseieng.utils.AecNumberUtils;

/**
 * PaymentVenderクラス.
 * @author TOSCO
 */
public class AltPaymentVender extends VenderBase {

	private static final long serialVersionUID = 1L;

	// 列定義----------------------------------------------------------------------------
	/** ﾃﾞｰﾀ種別　4:支払（'4'固定） */
	public static final String payableAmoun_COLUMN = "PAYABLE_AMOUNT";


	// インスタンスフィールド-----------------------------------------------------------------------------
	/** 支払残高 */
	private BigDecimal payableAmoun;


	/**
	 * コンストラクタ.
	 */
	public AltPaymentVender() {
		super();
	}

	/**
	 * 支払残高を取得します。
	 * @return 支払残高
	 */
	public BigDecimal getPayableAmoun() {
		return payableAmoun;
	}

	/**
	 * 支払残高を設定します。
	 * @param payableAmoun 支払残高
	 */
	public void setPayableAmoun(final BigDecimal payableAmoun) {
		this.payableAmoun = payableAmoun;
	}
	/**
	 * 支払残高の金額フォーマット文字列を取得する
	 * @return 支払残高の金額フォーマット文字列
	 */
	public String getPayableAmoutString() {
		String res = null;
		if (payableAmoun != null) {
			res = AecNumberUtils.decimalFormat(payableAmoun, "###,###");
		}
		return res;
	}
}
