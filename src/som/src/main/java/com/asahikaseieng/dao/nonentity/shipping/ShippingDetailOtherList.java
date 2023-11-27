/*
 * Created on 2009/02/02
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.shipping;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 出荷指図詳細画面（花王・その他）_出荷指図詳細表示用データ格納クラス.
 *
 * @author tosco
 */
public class ShippingDetailOtherList extends ShippingDetailList implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ShippingDetailOtherList() {
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
