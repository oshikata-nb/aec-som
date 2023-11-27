/*
 * Created on 2009/01/23
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.operationlist;

import com.asahikaseieng.utils.AecTextUtils;

/**
 * OperationListクラス.
 * @author t0011036
 */
public class OperationList extends OperationListBase {

	private static final long serialVersionUID = 1L;

	private String shortOperationName;

	/**
	 * コンストラクタ.
	 */
	public OperationList() {
		super();
	}

	/* ---------- 仮想プロパティ ---------- */

	/**
	 * 表示用operationName取得.
	 * @return operationName
	 */
	public String getDispOperationName() {
		/* 短く切ったものと異なれば文字数OVER */
		return AecTextUtils.overFilter(getOperationName(),
			getShortOperationName());
	}

	/**
	 * shortOperationNameを取得します。
	 * @return shortOperationName
	 */
	public String getShortOperationName() {
		return shortOperationName;
	}

	/**
	 * shortOperationNameを設定します。
	 * @param shortOperationName shortOperationName
	 */
	public void setShortOperationName(final String shortOperationName) {
		this.shortOperationName = shortOperationName;
	}
}
