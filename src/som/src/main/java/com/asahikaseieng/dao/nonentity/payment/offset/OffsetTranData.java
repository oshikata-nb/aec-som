/*
 * Created on 2008/07/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.payment.offset;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * グループ間相殺トランザクション用Daoクラス.
 * @author tosco
 */
public class OffsetTranData extends OffsetTranDataBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public OffsetTranData() {
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
