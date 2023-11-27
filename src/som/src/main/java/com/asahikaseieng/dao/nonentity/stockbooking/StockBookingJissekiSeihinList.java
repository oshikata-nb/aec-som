/*
 * Created on 2009/05/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.stockbooking;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 製品入出庫実績取得用データ格納クラス.
 *
 * @author
 */
public class StockBookingJissekiSeihinList extends StockBookingJissekiSeihinListBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public StockBookingJissekiSeihinList() {
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
