/*
 * Created on 2008/06/24
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.mypage.shortcutset;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 
 * ShortCutSetのクラス
 * @author tosco
 */
public class ShortCutSet extends ShortCutSetBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ShortCutSet() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 数値にカンマを付与 */

	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */


}
