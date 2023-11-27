/*
 * Created on 2008/07/01
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.credit.arupdate;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 売掛更新用Entityクラス.
 * @author tosco
 */
public class ArUpdate extends ArUpdateBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ArUpdate() {
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

