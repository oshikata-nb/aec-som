/*
 * Created on 2009/03/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.salestermsandestimatedetail;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * SalestermsAndEstimateDetailクラス.
 * @author t0011036
 */
public class SalestermsAndEstimateDetail extends SalestermsAndEstimateDetailBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	private Boolean checked;
	
	/** 入力日 */
	private String strInputDate;

	/**
	 * コンストラクタ.
	 */
	public SalestermsAndEstimateDetail() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* チェックをクリア */
		setChecked(Boolean.FALSE);
		// 取得した日付をyyyy/MM/ddに変換し、セット
		setStrInputDate(AecDateUtils.dateFormat(getInputDate(),
			"yyyy/MM/dd"));
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
	 * strInputDateを取得します。
	 * @return strInputDate
	 */
	public String getStrInputDate() {
		return strInputDate;
	}

	/**
	 * strInputDateを設定します。
	 * @param strInputDate strInputDate
	 */
	public void setStrInputDate(final String strInputDate) {
		this.strInputDate = strInputDate;
	}
}

