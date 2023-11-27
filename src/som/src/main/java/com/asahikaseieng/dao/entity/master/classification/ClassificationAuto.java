/*
 * Created on 2008/07/08
 *
 * $copyright$
 *
 */
package com.asahikaseieng.dao.entity.master.classification;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 分類マスタ　Beanクラス.
 * @author tosco
 */
public class ClassificationAuto extends ClassificationAutoBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;

	/**
	 * コンストラクタ.
	 */
	public ClassificationAuto() {
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

