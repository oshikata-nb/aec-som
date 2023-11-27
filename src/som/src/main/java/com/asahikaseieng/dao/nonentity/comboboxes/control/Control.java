/*
 * Created on 2008/05/07
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.nonentity.comboboxes.control;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 操作IDと操作名称を保持するBeanクラス.
 * @author tosco
 */
public class Control extends ControlBase implements
		PropertyTransferCallbacker {

	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public Control() {
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

}
