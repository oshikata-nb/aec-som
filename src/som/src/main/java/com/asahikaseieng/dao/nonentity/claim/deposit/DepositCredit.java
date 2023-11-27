/*
 * Created on 2008/08/06
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.claim.deposit;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 入金トランザクションテーブルデータ
 * @author tosco
 */
public class DepositCredit extends DepositCreditBase implements
		PropertyTransferCallbacker {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private Boolean checked;

	/**
	 * コンストラクタ
	 */
	public DepositCredit() {
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* チェックをクリア */
		setChecked(Boolean.FALSE);
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
