/*
 * Created on 2009/03/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.sales;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 詳細画面(標準)_表示用データ格納クラス.
 *
 * @author tosco
 */
public class SalesDetailStandardEntity extends SalesDetailEntity implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public SalesDetailStandardEntity() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		super.init();
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */

}
