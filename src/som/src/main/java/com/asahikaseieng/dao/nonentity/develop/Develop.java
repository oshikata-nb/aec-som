/*
 * Created on 2007/11/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.develop;

/**
 * DevelopDtoクラス.
 * @author FPC
 */
public class Develop extends DevelopBase {

	private static final long serialVersionUID = 1L;

	/* マスターチェックの用 */
	private int ct;

	/**
	 * ctを取得します。
	 * @return ct
	 */
	public int getCt() {
		return ct;
	}

	/**
	 * ctを設定します。
	 * @param ct ct
	 */
	public void setCt(final int ct) {
		this.ct = ct;
	}

	/**
	 * コンストラクタ.
	 */
	public Develop() {
		super();
	}
}
