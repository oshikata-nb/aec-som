/*
 * Created on 2009/03/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.sales;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 納入先検索(ポップアップ)用データ格納クラス.
 * @author tosco
 */
public class SalesDeliverySearchList extends SalesDeliverySearchListBase implements
	PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/** 住所 */
	private String address;

	/**
	 * コンストラクタ.
	 */
	public SalesDeliverySearchList() {
		super();
	}
	/* ---------- callbacker ---------- */

	/**
	 * 初期処理
	 * @throws Exception 例外
	 */
	public void init() throws Exception {
		//住所＝納入先住所1＋納入先住所2＋納入先住所3
		StringBuffer buf = new StringBuffer("");
		if (getAddress1() != null) {
			buf.append(getAddress1());
		}
		if (getAddress2() != null) {
			buf.append(getAddress2());
		}
		if (getAddress3() != null) {
			buf.append(getAddress3());
		}
		setAddress(buf.toString());
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */

	/**
	 * 住所取得
	 * @return String 住所
	*/
	public String getAddress() {
		return this.address;
	}

	/**
	 * 住所設定
	 * @param address 住所
	*/
	public void setAddress(final String address) {
		this.address = address;
	}

}

