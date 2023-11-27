/*
 * Created on 2008/10/10
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.eraser;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 消込入力詳細 消込ヘッダー内訳登録更新用Daoクラス.
 * @author tosco
 */
public class ClaimEraserHeaderDetail extends ClaimEraserHeaderDetailBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClaimEraserHeaderDetail() {
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

