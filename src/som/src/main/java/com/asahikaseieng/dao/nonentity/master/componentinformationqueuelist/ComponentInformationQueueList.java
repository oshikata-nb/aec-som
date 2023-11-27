/*
 * Created on 2009/01/20
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.componentinformationqueuelist;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * ComponentInformationQueueListクラス.
 * @author t0011036
 */
public class ComponentInformationQueueList extends
		ComponentInformationQueueListBase implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	private Boolean checked;

	private String strCalcValue;

	/**
	 * コンストラクタ.
	 */
	public ComponentInformationQueueList() {
		super();
	}

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

	/**
	 * strCalcValueを取得します。
	 * @return strCalcValue
	 */
	public String getStrCalcValue() {
		return strCalcValue;
	}

	/**
	 * strCalcValueを設定します。
	 * @param strCalcValue strCalcValue
	 */
	public void setStrCalcValue(final String strCalcValue) {
		this.strCalcValue = strCalcValue;
	}
}
