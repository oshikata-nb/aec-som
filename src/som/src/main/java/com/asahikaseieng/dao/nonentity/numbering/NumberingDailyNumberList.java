/*
 * Created on 2009/02/20
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.numbering;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 日毎発番管理テーブル発番データ格納クラス.
 *
 * @author tosco
 */
public class NumberingDailyNumberList extends NumberingDailyNumberListBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public NumberingDailyNumberList() {
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
