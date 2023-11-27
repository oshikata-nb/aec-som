/*
 * Created on 2007/12/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.develop;

/**
 * DevelopItemクラス.
 * @author FPC
 */
public class DevelopItem extends DevelopItemBase {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public DevelopItem() {
		super();
	}

	//チェックボックス
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

