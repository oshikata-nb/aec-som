/*
 * Created on 2008/07/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arrollback;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 売掛ロールバック用Entityクラス.
 * @author tosco
 */
public class ArRollback extends ArRollbackBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ArRollback() {
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

