/*
 * Created on 2007/11/29
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.develop;

/**
 * DevelopListクラス.
 * @author FPC
 */
public class DevelopList extends DevelopListBase {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DevelopList() {
		super();
	}

	/** JSP-checkbox */
	private Boolean checkline;

	/**
	 * checklineを取得します。
	 * @return checkline
	 */
	public Boolean getCheckline() {
		return checkline;
	}

	/**
	 * checklineを設定します。
	 * @param checkline checkline
	 */
	public void setCheckline(final Boolean checkline) {
		this.checkline = checkline;
	}
}

