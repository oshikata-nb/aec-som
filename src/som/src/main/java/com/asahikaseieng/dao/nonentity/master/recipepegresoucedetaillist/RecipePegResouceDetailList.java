/*
 * Created on 2009/05/18
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.recipepegresoucedetaillist;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * RecipePegResouceDetailListクラス.
 * @author t0011036
 */
public class RecipePegResouceDetailList extends RecipePegResouceDetailListBase
		implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	private Boolean checked;

	/**
	 * コンストラクタ.
	 */
	public RecipePegResouceDetailList() {
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
