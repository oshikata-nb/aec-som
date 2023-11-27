/*
 * Created on 2009/05/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.master.changehistorylist;

/**
 * ChangeHistoryListクラス.
 * @author t0011036
 */
public class ChangeHistoryList extends ChangeHistoryListBase {

	private static final long serialVersionUID = 1L;

	private String strUpdateDate;

	/**
	 * コンストラクタ.
	 */
	public ChangeHistoryList() {
		super();
	}

	/**
	 * strUpdateDateを取得します。
	 * @return strUpdateDate
	 */
	public String getStrUpdateDate() {
		return strUpdateDate;
	}

	/**
	 * strUpdateDateを設定します。
	 * @param strUpdateDate strUpdateDate
	 */
	public void setStrUpdateDate(final String strUpdateDate) {
		this.strUpdateDate = strUpdateDate;
	}
}
