/*
 * Created on 2008/06/11
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.sql;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * LabelListクラス.
 * @author a1020630
 */
public class Sql extends SqlBase implements PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	private String linkString;

	/**
	 * コンストラクタ.
	 */
	public Sql() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}


	/* ---------- getter ,setter ---------- */
	/**
	 * linkString取得.
	 * @return linkString
	 */
	public String getLinkString() {
		return this.linkString;
	}

	/**
	 * linkString設定.
	 * @param linkString linkString
	 */
	public void setLinkString(final String linkString) {
		this.linkString = linkString;
	}


}

