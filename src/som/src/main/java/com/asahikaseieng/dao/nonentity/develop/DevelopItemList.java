/*
 * Created on 2007/12/12
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.develop;

/**
 * DevelopItemListクラス.
 * @author FPC
 */
public class DevelopItemList extends DevelopItemListBase {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DevelopItemList() {
		super();
	}

	/* チェックバックス */
	private boolean checkline;

	/**
	 * checklineを取得します。
	 * @return checkline
	 */
	public boolean getCheckline() {
		return checkline;
	}

	/**
	 * checklineを設定します。
	 * @param checkline checkline
	 */
	public void setCheckline(final boolean checkline) {
		this.checkline = checkline;
	}

}

