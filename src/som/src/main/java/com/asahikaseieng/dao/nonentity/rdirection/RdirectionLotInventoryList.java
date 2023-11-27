/*
 * Created on 2009/05/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.rdirection;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * ロット在庫取得用データ格納クラス.
 *
 * @author
 */
public class RdirectionLotInventoryList extends RdirectionLotInventoryListBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public RdirectionLotInventoryList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * 編集処理
	 * @throws Exception 例外
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
