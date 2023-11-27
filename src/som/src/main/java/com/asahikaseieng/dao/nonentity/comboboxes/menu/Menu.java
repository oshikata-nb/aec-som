/*
 * Created on 2008/04/30
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.menu;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * メニューIDとメニュー名称を保持するBeanクラス.
 * @author tosco
 */
public class Menu extends MenuBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public Menu() {
		super();
	}

	/* ---------- callbacker ---------- */

	/**
	 * {@inheritDoc}
	 */
	public void init() throws Exception {
		/* 数値にカンマを付与 */

	}

	/**
	 * {@inheritDoc}
	 */
	public void callback() throws Exception {
	}

	/* ---------- getter ,setter ---------- */

}
