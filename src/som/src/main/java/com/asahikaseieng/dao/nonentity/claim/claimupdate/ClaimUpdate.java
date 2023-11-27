/*
 * Created on 2008/08/04
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.claimupdate;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 請求更新用Entityクラス.
 * @author tosco
 */
public class ClaimUpdate extends ClaimUpdateBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClaimUpdate() {
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

