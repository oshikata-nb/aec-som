/*
 * Created on 2009/01/26
 *
 * $copyright$
 *
*/
package com.asahikaseieng.dao.nonentity.mgrecipe;

import com.asahikaseieng.utils.PropertyTransferCallbacker;

/**
 * 処方その他用データ格納クラス.
 *
 * @author tosco
 */
public class RecipeRemarkList extends RecipeRemarkListBase implements
		PropertyTransferCallbacker {


	private static final long serialVersionUID = 1L;
	/** TIMESTAMPアノテーション updateDate */
	public static final String TIMESTAMP_PROPERTY = "updateDate";

	/**
	 * コンストラクタ.
	 */
	public RecipeRemarkList() {
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
