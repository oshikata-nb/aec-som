/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsdetaillist;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * SalesTermsDetailListクラス.
 * @author t0011036
 */
public class SalesTermsDetailList extends SalesTermsDetailListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	private Boolean checked;

	/**
	 * コンストラクタ.
	 */
	public SalesTermsDetailList() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* チェックをクリア */
		setChecked(Boolean.FALSE);
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/**
	 * checkedを取得します。
	 * @return checked
	 */
	public Boolean getChecked() {
		return checked;
	}

	/**
	 * checkedを設定します。
	 * @param checked checked
	 */
	public void setChecked(final Boolean checked) {
		this.checked = checked;
	}
}
