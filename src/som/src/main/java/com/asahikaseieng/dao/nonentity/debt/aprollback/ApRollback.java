/*
 * Created on 2008/07/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.debt.aprollback;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 買掛ロールバック用Entityクラス.
 * @author tosco
 */
public class ApRollback extends ApRollbackBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ApRollback() {
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

