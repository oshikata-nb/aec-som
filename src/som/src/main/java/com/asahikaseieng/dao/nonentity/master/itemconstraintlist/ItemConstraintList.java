/*
 * Created on 2007/10/03
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.itemconstraintlist;

import com.asahikaseieng.utils.AecDateUtils;
import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * ItemConstraintListクラス.
 * @author t1344224
 */
public class ItemConstraintList extends ItemConstraintListBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	private String strFromDate;

	private String strToDate;

	private String strPermissionDate;

	/**
	 * コンストラクタ.
	 */
	public ItemConstraintList() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		setStrFromDate(AecDateUtils.dateFormat(getFromDate(), "yyyy/MM/dd"));
		setStrToDate(AecDateUtils.dateFormat(getToDate(), "yyyy/MM/dd"));
		this.setStrPermissionDate(AecDateUtils.dateFormat(this.getPermissionDate(), "yyyy/MM/dd"));
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
		setFromDate(AecDateUtils.getTimestampYmdFormat(getStrFromDate()));
		setToDate(AecDateUtils.getTimestampYmdFormat(getStrToDate()));
		this.setPermissionDate(AecDateUtils.getTimestampYmdFormat(this.getStrPermissionDate()));
	}

	/**
	 * strFromDate取得.
	 * @return strFromDate
	 */
	public String getStrFromDate() {
		return strFromDate;
	}

	/**
	 * strFromDate設定.
	 * @param strFromDate strFromDate
	 */
	public void setStrFromDate(final String strFromDate) {
		this.strFromDate = strFromDate;
	}

	/**
	 * strToDate取得.
	 * @return strToDate
	 */
	public String getStrToDate() {
		return strToDate;
	}

	/**
	 * strToDate設定.
	 * @param strToDate strToDate
	 */
	public void setStrToDate(final String strToDate) {
		this.strToDate = strToDate;
	}

	/**
	 * strPermissionDateを取得します。
	 * @return strPermissionDate
	 */
	public String getStrPermissionDate() {
		return strPermissionDate;
	}

	/**
	 * strPermissionDateを設定します。
	 * @param strPermissionDate strPermissionDate
	 */
	public void setStrPermissionDate(final String strPermissionDate) {
		this.strPermissionDate = strPermissionDate;
	}

}
