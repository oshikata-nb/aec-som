/*
 * Created on 2008/08/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 消込入力詳細 消込トランザクション登録更新用Daoクラス.
 * @author tosco
 */
public class ClaimEraser extends ClaimEraserBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClaimEraser() {
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

}

