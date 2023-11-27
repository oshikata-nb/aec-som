/*
 * Created on 2008/08/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.paymentupdate;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 支払更新用Entityクラス.
 * @author tosco
 */
public class PaymentUpdate extends PaymentUpdateBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public PaymentUpdate() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */

}

